package com.sbtech.erp.util;

import com.sbtech.erp.employee.domain.Rank;
import com.sbtech.erp.organization.application.port.PositionUseCase;
import com.sbtech.erp.permission.application.port.PermissionUseCase;
import com.sbtech.erp.permission.application.port.PositionRankPermissionUseCase;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final PermissionUseCase permissionUseCase;
    private final PositionUseCase positionUseCase;
    private final PositionRankPermissionUseCase positionRankPermissionUseCase;

    @PostConstruct
    public void initAll() {
        // 1. Permission 생성
        permissionUseCase.createPermission("employee", "VIEW", "직원 목록 조회");
        permissionUseCase.createPermission("employee", "EDIT", "직원 정보 수정");
        permissionUseCase.createPermission("department", "CREATE", "부서 생성");
        permissionUseCase.createPermission("department", "DELETE", "부서 삭제");
        permissionUseCase.createPermission("product", "VIEW", "상품 조회");
        permissionUseCase.createPermission("product", "EDIT", "상품 수정");
        permissionUseCase.createPermission("product", "CREATE", "상품 등록");
        permissionUseCase.createPermission("product", "DELETE", "상품 삭제");

        // 2. Position 생성
        positionUseCase.createPosition("백엔드 개발자", true);    // ID = 1
        positionUseCase.createPosition("프론트엔드 개발자", true); // ID = 2
        positionUseCase.createPosition("iOS 개발자", true);
        positionUseCase.createPosition("Android 개발자", true);
        positionUseCase.createPosition("인사담당자", true);
        positionUseCase.createPosition("회계담당자", true);

        // 3. 권한 부여
        Long backendDevId = 1L;
        Long frontendDevId = 2L;

        positionRankPermissionUseCase.grantPermission(backendDevId, Rank.STAFF, 1L);
        positionRankPermissionUseCase.grantPermission(backendDevId, Rank.STAFF, 2L);
        positionRankPermissionUseCase.grantPermission(backendDevId, Rank.STAFF, 3L);
        positionRankPermissionUseCase.grantPermission(backendDevId, Rank.MANAGER, 1L);
        positionRankPermissionUseCase.grantPermission(backendDevId, Rank.MANAGER, 2L);
        positionRankPermissionUseCase.grantPermission(frontendDevId, Rank.DIRECTOR, 3L);
    }
}
