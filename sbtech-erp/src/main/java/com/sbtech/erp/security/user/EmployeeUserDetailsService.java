package com.sbtech.erp.security.user;

import com.sbtech.erp.employee.adapter.out.persistence.repository.EmployeeJpaRepository;
import com.sbtech.erp.employee.adapter.out.persistence.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {
    private final EmployeeJpaRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<EmployeeEntity> findEmployee = employeeRepository.findByLoginId(username);

        if(findEmployee.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }else{
            return new EmployeeUserDetails(findEmployee.get());
        }
    }
}
