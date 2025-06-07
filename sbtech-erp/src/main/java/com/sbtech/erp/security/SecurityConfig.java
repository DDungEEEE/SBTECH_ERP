package com.sbtech.erp.security;

import com.sbtech.erp.security.filter.JwtAuthenticationFilter;
import com.sbtech.erp.security.filter.LoginAuthenticationFilter;
import com.sbtech.erp.security.user.EmployeeUserDetailsService;
import com.sbtech.erp.util.ResponseWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtProvider jwtProvider;
    private final ResponseWrapper responseWrapper;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final EmployeeUserDetailsService employeeUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter(jwtProvider, responseWrapper, employeeUserDetailsService);
    }

    @Bean
    public LoginAuthenticationFilter loginAuthenticationFilter(AuthenticationManager authenticationManager){
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(jwtProvider, responseWrapper);

        loginAuthenticationFilter.setAuthenticationManager(authenticationManager);
        loginAuthenticationFilter.setFilterProcessesUrl("/api/v1/auth/login");

        return loginAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, LoginAuthenticationFilter loginAuthenticationFilter) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequest ->
                                authorizeRequest
                                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                        .requestMatchers(
                                                "/swagger-ui.html",
                                                "/swagger-ui/**",
                                                "/v3/api-docs/**",
                                                "/v3/api-docs.yaml",
                                                "/swagger-resources/**",
                                                "/webjars/**"
                                        ).permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/v1/employee/register").permitAll()
                                        .requestMatchers("/api/v1/**").authenticated()
                                        .requestMatchers("/actuator/**").permitAll()
                                    .anyRequest().authenticated()

                );

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), LoginAuthenticationFilter.class);
        httpSecurity.addFilterBefore(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // '*' 쓰면 allowCredentials 안 됨
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}