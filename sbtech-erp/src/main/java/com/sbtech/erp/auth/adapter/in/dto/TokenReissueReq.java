package com.sbtech.erp.auth.adapter.in.dto;

public record TokenReissueReq(
        String refreshToken,
        String loginId
) {
}
