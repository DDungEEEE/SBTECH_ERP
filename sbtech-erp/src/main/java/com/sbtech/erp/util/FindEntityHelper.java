package com.sbtech.erp.util;

import com.sbtech.erp.auth.domain.SystemRole;
import com.sbtech.erp.common.BaseTimeEntity;
import com.sbtech.erp.department.domain.Department;
import com.sbtech.erp.employee.domain.Employee;
import com.sbtech.erp.position.domain.Position;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public record FindEntityHelper(EntityManager em, ServiceErrorHelper errorHelper) {

    private  <T extends BaseTimeEntity> T findElseThrow404(Class<T> clazz, Long primaryKey){
        T entity = em.find(clazz, primaryKey);
        if(entity == null){
            throw errorHelper.badRequest(
                    String.format("%s id : %s 를 찾을 수 없습니다.", clazz.getSimpleName(), primaryKey));
        }
        return entity;
    }

    public Employee findEmployeeElseThrow404(Long id) throws ResponseStatusException{
        return findElseThrow404(Employee.class, id);
    }

    public Position findPositionElseThrow404(Long id) throws ResponseStatusException{
        return findElseThrow404(Position.class, id);
    }
    public SystemRole findRoleElseThrow404(Long id) throws ResponseStatusException{
        return findElseThrow404(SystemRole.class, id);
    }

    public Department findDepartmentElseThrow404(Long id) throws ResponseStatusException{
        return findElseThrow404(Department.class, id);
    }
}
