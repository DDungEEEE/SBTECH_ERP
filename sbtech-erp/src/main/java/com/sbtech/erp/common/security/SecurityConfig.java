package com.sbtech.erp.common.security;

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
//    private final JwtUtil jwtUtil;
//    private final ResponseWrapper responseWrapper;
//    private final AuthenticationConfiguration authenticationConfiguration;
//    private final UserDetailsServiceImpl userDetailsService;
//    private final CustomUnauthorizedHandler customUnauthorizedHandler;
//
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    public JwtAuthorizationFilter jwtAuthorizationFilter(){
//        return new JwtAuthorizationFilter(jwtUtil, responseWrapper, userDetailsService);
//    }
//    @Bean
//    public LoginAuthenticationFilter loginAuthenticationFilter(AuthenticationManager authenticationManager){
//        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(jwtUtil, responseWrapper);
//        loginAuthenticationFilter.setAuthenticationManager(authenticationManager);
//        loginAuthenticationFilter.setFilterProcessesUrl("/api/v1/user/login");
//        return loginAuthenticationFilter;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
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
                                                "/swagger-resources/**",
                                                "/webjars/**"
                                        ).permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/v1/user/login").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
//                                .anyRequest().authenticated()
                                        .requestMatchers("/**").permitAll()
                );

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