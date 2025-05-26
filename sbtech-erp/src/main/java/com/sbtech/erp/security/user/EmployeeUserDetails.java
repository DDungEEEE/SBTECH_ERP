package com.sbtech.erp.security.user;

import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sbtech.erp.employee.domain.model.EmployeeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeUserDetails implements UserDetails {
    private final EmployeeEntity employeeEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = String.valueOf(employeeEntity.getSystemRole());
        String systemRole = "ROLE_" + role;
        return List.of(new SimpleGrantedAuthority(systemRole));
    }

    @Override
    public String getPassword() {
        return employeeEntity.getPassword().getValue();
    }

    @Override
    public String getUsername() {
        return employeeEntity.getLoginId();
    }

    public EmployeeEntity getEmployeeEntity(){
        return employeeEntity;
    }

    @Override
    public boolean isEnabled() {
        return employeeEntity.getEmployeeStatus() == EmployeeStatus.ACTIVE;
    }
}
