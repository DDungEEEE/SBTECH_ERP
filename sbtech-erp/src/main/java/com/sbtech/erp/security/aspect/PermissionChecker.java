package com.sbtech.erp.security.aspect;

import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.permission.application.port.RolePermissionRepository;
import com.sbtech.erp.util.FindEntityHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermissionChecker {
    private final FindEntityHelper findEntityHelper;
    private final RolePermissionRepository rolePermissionRepository;

    public boolean hasPermission(Long employeeId, String permissionCode){
        Employee employee = findEntityHelper.findEmployeeElseThrow404(employeeId);

        return rolePermissionRepository.hasPermission(employee.getPosition(), employee.getRank(), permissionCode);
    }
}
