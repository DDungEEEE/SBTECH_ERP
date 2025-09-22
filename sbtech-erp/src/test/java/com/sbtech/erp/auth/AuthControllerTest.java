//
//package com.sbtech.erp.auth;
//
//import com.sbtech.erp.auth.adapter.in.controller.AuthController;
//import com.sbtech.erp.auth.application.port.in.AccessTokenUseCase;
//import com.sbtech.erp.auth.application.port.in.RefreshTokenUseCase;
//import com.sbtech.erp.common.code.ErrorCode;
//import com.sbtech.erp.security.jwt.JwtProvider;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(AuthController.class)
//public class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private JwtProvider jwtProvider;
//
//    @MockBean
//    private RefreshTokenUseCase refreshTokenUseCase;
//
//    @MockBean
//    private AccessTokenUseCase accessTokenUseCase;
//
//    @Test
//    void testLogout() throws Exception {
//        // Given
//        String accessToken = "validAccessToken";
//        Mockito.when(jwtProvider.getJwtToken(any())).thenReturn(accessToken);
//
//        // When
//        mockMvc.perform(post("/api/v1/auth/logout")
//                        .header("Authorization", "Bearer " + accessToken))
//                // Then
//                .andExpect(status().isNoContent());
//
//        Mockito.verify(accessTokenUseCase).addBlacklist(eq(accessToken));
//    }
//
//    @Test
//    void testReissueToken_Success() throws Exception {
//        // Given
//        String refreshToken = "validRefreshToken";
//        String expiredAccessToken = "expiredAccessToken";
//        String newAccessToken = "newAccessToken";
//        String newRefreshToken = "newRefreshToken";
//        String loginId = "user123";
//
//        Mockito.when(jwtProvider.getJwtToken(any())).thenReturn(expiredAccessToken);
//        Mockito.when(jwtProvider.validToken(refreshToken)).thenReturn(true);
//        Mockito.when(jwtProvider.getLoginIdFromToken(refreshToken)).thenReturn(loginId);
//        Mockito.when(refreshTokenUseCase.reissue(eq(loginId), eq(refreshToken))).thenReturn(newRefreshToken);
//        Mockito.when(jwtProvider.generateAccessToken(eq(loginId))).thenReturn(newAccessToken);
//
//        // When
//        mockMvc.perform(post("/api/v1/auth/reissue")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"refreshToken\": \"" + refreshToken + "\"}")
//                        .header("Authorization", "Bearer " + expiredAccessToken))
//                // Then
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.accessToken").value(newAccessToken))
//                .andExpect(jsonPath("$.refreshToken").value(newRefreshToken));
//
//        Mockito.verify(refreshTokenUseCase).delete(eq(loginId));
//        Mockito.verify(refreshTokenUseCase).save(eq(loginId), eq(newRefreshToken));
//        Mockito.verify(accessTokenUseCase).addBlacklist(eq(expiredAccessToken));
//    }
//
//    @Test
//    void testReissueToken_InvalidRefreshToken() throws Exception {
//        // Given
//        String refreshToken = "invalidRefreshToken";
//        Mockito.when(jwtProvider.validToken(refreshToken)).thenReturn(false);
//
//        // When
//        mockMvc.perform(post("/api/v1/auth/reissue")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"refreshToken\": \"" + refreshToken + "\"}"))
//                // Then
//                .andExpect(status().isForbidden())
//                .andExpect(jsonPath("$.errorCode").value(ErrorCode.INVALID_TOKEN_ERROR.name()));
//    }
//}
//
//
