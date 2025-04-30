package com.sbtech.erp.common.security.user;

import com.sbtech.erp.employee.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeUserDetails implements UserDetails {
    private final Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = employee.getRoleName();

        //Spring Security에서 사용하기 위한
        String authority = "ROLE_" + role;

        return List.of(new SimpleGrantedAuthority(authority));
    }

    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getLoginId();
    }

    public Employee getEmployee(){
        return employee;
    }
}
