package com.sbtech.erp.common;

import com.sbtech.erp.auth.adapter.out.repository.JpaSystemRoleRepository;
import com.sbtech.erp.auth.domain.SystemRole;
import com.sbtech.erp.department.adapter.out.repository.JpaDepartmentRepository;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.position.adapter.out.repository.JpaPositionRepository;
import com.sbtech.erp.position.domain.Position;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDataLoader {
    private final JpaDepartmentRepository departmentRepository;
    private final JpaPositionRepository positionRepository;
    private final JpaSystemRoleRepository systemRoleRepository;

    @PostConstruct
    public void init() {
        initDepartments();
        initPositions();
        initSystemRoles();
    }

    private void initDepartments() {
        if (departmentRepository.count() > 0) return;

        Department root = new Department("본사", null);
        Department sales = new Department("영업부", root);
        Department hr = new Department("인사부", root);

        departmentRepository.save(root);
        departmentRepository.save(sales);
        departmentRepository.save(hr);

        System.out.println("✅ 부서 초기 데이터 등록 완료");
    }

    private void initPositions() {
        if (positionRepository.count() > 0) return;

        positionRepository.save(new Position("사원", true));
        positionRepository.save(new Position("대리", true));
        positionRepository.save(new Position("과장", true));
        positionRepository.save(new Position("부장", true));

        System.out.println("✅ 직급 초기 데이터 등록 완료");
    }

    private void initSystemRoles() {
        if (systemRoleRepository.count() > 0) return;

        systemRoleRepository.save(SystemRole.of("REQUESTER", "제품 요청 권한"));
        systemRoleRepository.save(SystemRole.of("APPROVER", "제품 승인 권한"));
        systemRoleRepository.save(SystemRole.of("ERP_ADMIN", "ERP 전체 관리자 권한"));

        System.out.println("✅ 시스템 권한 초기 데이터 등록 완료");
    }
}
