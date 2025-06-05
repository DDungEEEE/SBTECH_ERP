package com.sbtech.erp.security.filter;

import com.sbtech.erp.common.code.ErrorCode;
import com.sbtech.erp.common.response.ErrorResponse;
import com.sbtech.erp.security.JwtProvider;
import com.sbtech.erp.security.user.EmployeeUserDetails;
import com.sbtech.erp.security.user.EmployeeUserDetailsService;
import com.sbtech.erp.util.ResponseWrapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final ResponseWrapper responseWrapper;
    private final EmployeeUserDetailsService employeeUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI(); // 요청 URI 가져오기

        // 특정 요청은 필터를 건너뛰도록 설정
        if (isExcludedRequest(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // JWT 토큰을 가져오고 유효성 검사
            String token = jwtProvider.getJwtToken(request);
            validateToken(token, response);

            // 토큰에서 클레임을 가져와 인증 설정
            Claims claims = jwtProvider.getClaims(token);
            setAuthentication(claims.getSubject());
            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            // 예외 발생 시 처리
            log.error("JwtAuthentication Exception : {}", ex.getMessage());
            handleException(response, ErrorCode.INVALID_TOKEN_ERROR);
        }
    }

    // 토큰 유효성 검사
    private void validateToken(String token, HttpServletResponse response) throws IOException {
        if (token == null || !jwtProvider.validToken(token)) {
            handleException(response, ErrorCode.INVALID_TOKEN_ERROR);
        }
    }

    // 특정 요청 URI를 필터링에서 제외
    private boolean isExcludedRequest(String requestURI) {
        return "/api/v1/auth/login".equals(requestURI)
                || isSwaggerRequest(requestURI)
                || "/api/v1/employee/register".equals(requestURI);
    }

    // 예외 처리 메서드
    private void handleException(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        responseWrapper.convertObjectToResponse(response, errorResponse);
    }

    // 인증 설정
    private void setAuthentication(String userId) {
        EmployeeUserDetails unifiedUserDetails = (EmployeeUserDetails) employeeUserDetailsService.loadUserByUsername(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(unifiedUserDetails, null, unifiedUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    // Swagger 관련 요청인지 확인
    private boolean isSwaggerRequest(String uri) {
        return uri.startsWith("/swagger-ui")
                || uri.startsWith("/v3/api-docs")
                || uri.startsWith("/swagger-resources")
                || uri.startsWith("/webjars")
                || uri.equals("/swagger-ui.html")
                || uri.equals("/favicon.ico");
    }
}
