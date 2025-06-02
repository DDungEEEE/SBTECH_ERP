package com.sbtech.erp.department.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Department {
    private final Long id;
    private final String name;
    private final Department parentDepartment;
    private final List<Department> subDepartments;

    private Department(Long id, String name, Department parentDepartment, List<Department> subDepartments) {
        this.id = id;
        this.name = name;
        this.parentDepartment = parentDepartment;
        this.subDepartments = subDepartments != null ? subDepartments : new ArrayList<>();
    }

    public static Department create(Long id, String name, Department parentDepartment) {
        return new Department(id, name, parentDepartment, new ArrayList<>());
    }


    public List<Department> getSubDepartments() {
        return Collections.unmodifiableList(subDepartments);
    }

    public void addSubDepartment(Department subDepartment) {
        this.subDepartments.add(subDepartment);
    }

    public Department updateDepartment()
}
