package com.sbtech.erp.permission.adapter.in.dto;

import com.sbtech.erp.employee.domain.Rank;

public record RolePermissionGroupReq(Long positionId, Rank rank,Long permissionGroupId) {
}
