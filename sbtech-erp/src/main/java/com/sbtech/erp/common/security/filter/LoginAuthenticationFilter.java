package com.sbtech.erp.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbtech.erp.common.security.JwtProvider;
import com.sbtech.erp.common.security.user.EmployeeUserDetails;
import com.sbtech.erp.common.security.user.UserLoginDto;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtProvider jwtUtil;
    private final ResponseWrapper responseWrapper;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {

            System.out.println();
            UserLoginDto userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginDto.class);

            log.error(userLoginDto.toString());

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLoginDto.loginId(),
                            userLoginDto.password())
            );
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        EmployeeUserDetails userDetails = (EmployeeUserDetails) authentication.getPrincipal();
        String userId = userDetails.getUsername();

        String accessToken = jwtUtil.generateAccessToken(userId);
        Employee employee = userDetails.getEmployee();
    }
}