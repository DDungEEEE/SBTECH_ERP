package com.sbtech.erp.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbtech.erp.security.JwtProvider;
import com.sbtech.erp.security.JwtToken;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import com.sbtech.erp.security.user.UserLoginDto;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.util.ResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtProvider jwtProvider;
    private final ResponseWrapper responseWrapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            UserLoginDto userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);

            log.error(userLoginDto.toString());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.loginId(),
                            userLoginDto.password(), null)
            );
        } catch (IOException e) {
            log.error("오류남 : {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        EmployeeUserDetails userDetails = (EmployeeUserDetails) authentication.getPrincipal();

        Employee employee = userDetails.getEmployee();
        String accessToken = jwtProvider.generateAccessToken(employee.getId());

        JwtToken jwtToken = JwtToken.builder()
                .accessToken(accessToken)
                .employee(employee)
                .build();

        responseWrapper.convertObjectToResponse(response, jwtToken);
    }
}