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
        String token = jwtProvider.getJwtToken(request);

        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        // 로그인 요청은 LoginAuthenticationFilter에게 위임
        if ("/api/v1/auth/login".equals(requestURI) ||isSwaggerRequest(requestURI) || "/api/v1/employee/register".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }
        if(requestURI.startsWith("/api/v1/")){
            try{
                if(token == null || !jwtProvider.validToken(token)){
                    ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INVALID_TOKEN_ERROR);
                    responseWrapper.convertObjectToResponse(response, errorResponse);
                    return;
                }

                Claims claims = jwtProvider.getClaims(token);

                setAuthentication(claims.getSubject());
                filterChain.doFilter(request, response);
            }catch (Exception ex){
                log.error("JwtAuthentication Exception : {}", ex.getMessage());

                ErrorResponse errorResponse = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
                responseWrapper.convertObjectToResponse(response, errorResponse);
            }
        }else{
            filterChain.doFilter(request, response);
            return;
        }

    }

    private void setAuthentication(String userId){
        EmployeeUserDetails unifiedUserDetails = (EmployeeUserDetails) employeeUserDetailsService.loadUserByUsername(userId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(unifiedUserDetails, null, unifiedUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private boolean isSwaggerRequest(String uri) {
        return uri.startsWith("/swagger-ui")
                || uri.startsWith("/v3/api-docs")
                || uri.startsWith("/swagger-resources")
                || uri.startsWith("/webjars")
                || uri.equals("/swagger-ui.html")
                || uri.equals("/favicon.ico");
    }
}
