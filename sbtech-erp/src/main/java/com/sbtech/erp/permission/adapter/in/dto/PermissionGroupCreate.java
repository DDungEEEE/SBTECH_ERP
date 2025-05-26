package com.sbtech.erp.permission.adapter.in.dto;

import java.util.List;

public record PermissionGroupCreate(
        String groupName,
        List<Long> permissionIds
) {
}
