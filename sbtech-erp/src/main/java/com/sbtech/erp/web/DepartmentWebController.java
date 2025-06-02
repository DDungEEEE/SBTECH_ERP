package com.sbtech.erp.web;

import com.sbtech.erp.department.application.port.in.DepartmentUseCase;
import com.sbtech.erp.department.domain.model.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/departments")
@RequiredArgsConstructor
public class DepartmentWebController {
    private final DepartmentUseCase departmentUseCase;
    @GetMapping
    public String showDepartmentPage(Model model) {
        List<Department> departments = departmentUseCase.getTopLevelDepartments();
        model.addAttribute("departments", departments);
        return "admin/department-management"; // templates/admin/department-management.html
    }

    @PostMapping
    public String createDepartment(@RequestParam String name,
                                   @RequestParam(required = false) Long parentId) {
        departmentUseCase.create(name, parentId);
        return "redirect:/admin/departments";
    }


}
