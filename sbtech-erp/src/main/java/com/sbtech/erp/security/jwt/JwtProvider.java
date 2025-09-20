//package com.sbtech.erp.security.jwt;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.security.Key;
//import java.time.Duration;
//import java.time.Instant;
//import java.util.Base64;
//import java.util.Date;
//
//@Slf4j
//@Component
//public class JwtProvider {
//
//    @Value("${JWT_SECRET}")
//    private String secretKey;
//    private Key key;
//    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//    @PostConstruct
//    public void init(){
//        byte[] decode = Base64.getDecoder().decode(secretKey);
//        key = Keys.hmacShaKeyFor(decode);
//    }
//
//    public boolean validToken(String token){
//        try{
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        }catch (SecurityException | MalformedJwtException e){
//            log.error("Invalid Jwt Signature, 유효허지 않은 Jwt 서명입니다.");
//        }catch (ExpiredJwtException e){
//            log.error(e.getMessage());
//            log.error("유효기간이 만료된 Jwt Token 입니다.");
//        }catch (UnsupportedJwtException e){
//            log.error("지원하지 않는 Jwt Token 입니다.");
//        }catch (IllegalArgumentException e){
//            log.error("{}", e.getMessage());
//        }
//        return false;
//    }
//
//    public String generateAccessToken(String loginId){
//        long ACCESS_TOKEN_EXPIRED = 30 * 60 * 1000L;
//
//       return createToken(loginId, ACCESS_TOKEN_EXPIRED);
//    }
//    public String generateRefreshToken(String loginId){
//        long ACCESS_TOKEN_EXPIRED =  24 * 60 * 60 * 1000L;
//
//        return createToken(loginId, ACCESS_TOKEN_EXPIRED);
//    }
//
//
//    // req Header 에서 jwt Token 추출
//    public String getJwtToken(HttpServletRequest req){
//        String authorizationHeader = "Authorization";
//        String bearerToken = req.getHeader(authorizationHeader);
//        String BEARER = "Bearer ";
//
//        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)){
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    public Claims getClaims(String token){
//        try{
//            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//        }catch (ExpiredJwtException e){
//            throw e;
//        }
//    }
//
//    public long getRemainingValidity(String token){
//        Claims claims = getClaims(token);
//        Date expiration = claims.getExpiration();
//        return expiration.getTime() - System.currentTimeMillis();
//    }
//
//    public String getLoginIdFromToken(String token) {
//        Claims claims = getClaims(token);
//        return claims.getSubject();
//    }
//
//    private String createToken(String loginId, long expiredTime) {
//        Instant now = Instant.now();
//        Instant expirationTime = now.plusMillis(expiredTime);
//
//        return Jwts.builder()
//                .setSubject(loginId)
//                .setIssuedAt(Date.from(now))
//                .setExpiration(Date.from(expirationTime))
//                .signWith(key, signatureAlgorithm)
//                .compact();
//    }
//
//    public Duration getRefreshTokenTtl() {
//        return Duration.ofDays(1);
//    }
//    public Duration getAccessTokenTtl() {
//        return Duration.ofMinutes(30);
//    }
//
//
//}