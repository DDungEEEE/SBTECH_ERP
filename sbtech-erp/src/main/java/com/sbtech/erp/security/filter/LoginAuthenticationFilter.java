package com.sbtech.erp.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbtech.erp.auth.adapter.in.dto.JwtToken;
import com.sbtech.erp.auth.application.port.out.RefreshTokenPort;
import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.response.ErrorResponse;
import com.sbtech.erp.employee.adapter.out.dto.EmployeeResDto;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.mapper.EmployeeMapper;
import com.sbtech.erp.security.jwt.JwtProvider;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import com.sbtech.erp.auth.adapter.in.dto.UserLoginDto;
import com.sbtech.erp.util.ResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtProvider jwtProvider;
    private final ResponseWrapper responseWrapper;
    private final RefreshTokenPort refreshTokenPort;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserLoginDto userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);

            log.error(userLoginDto.toString());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.loginId(),
                            userLoginDto.password())
            );
        } catch (IOException e) {
            log.error("오류남 : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // 로그인 성공 시 수행할 Logic
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        EmployeeUserDetails userDetails = (EmployeeUserDetails) authentication.getPrincipal();

        Employee employee = EmployeeMapper.toDomain(userDetails.getEmployeeEntity());

        JwtToken jwtToken = createAndStoreTokens(employee);

        responseWrapper.convertObjectToResponse(response, jwtToken);
    }

    private JwtToken createAndStoreTokens(Employee employee) {
        String accessToken = jwtProvider.generateAccessToken(employee.getLoginId());
        String refreshToken = jwtProvider.generateRefreshToken(employee.getLoginId());

        refreshTokenPort.save(employee.getLoginId(), refreshToken, jwtProvider.getRefreshTokenTtl());

        return JwtToken.builder()
                .accessToken(accessToken)
                .employee(EmployeeResDto.from(employee))
                .build();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error("로그인 실패: {}", failed.getMessage());

        // 관리자에게 승인 되지 않았을 시 -> 승인 대기 중 or 사용자 불일치 예외 발생
        ErrorCode errorCode = (failed instanceof DisabledException)
                ? ErrorCode.USER_NOT_APPROVAL_ERROR
                : ErrorCode.USER_NOT_FOUND_ERROR;

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        responseWrapper.convertObjectToResponse(response, new ErrorResponse(errorCode));
    }


}