package com.sbtech.erp.web;

import com.sbtech.erp.employee.adapter.out.dto.EmployeeResDto;
import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import com.sbtech.erp.employee.domain.model.Employee;
import com.sbtech.erp.employee.domain.model.EmployeeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final EmployeeUseCase employeeUseCase;

    @GetMapping("/admin")
    public String admin(){
        return "admin/admin";
    }

    @GetMapping("/admin/employees")
    public String showEmployeeList(Model model) {

        model.addAttribute("employees", EmployeeResDto.from(employeeUseCase.findAllEmployees()));
        return "admin/employees"; // resources/templates/admin/employees.html
    }

    @GetMapping("/admin/employees/active")
    public String  showActiveEmployees(Model model){
        List<Employee> allEmployees = employeeUseCase.findAllEmployees();
        List<Employee> activeEmployees = allEmployees.stream().filter(employee -> employee.getStatus().equals(EmployeeStatus.ACTIVE)).toList();
        model.addAttribute("employees", EmployeeResDto.from(activeEmployees));
        return "admin/employees";
    }

    @GetMapping("/admin/employees/pending")
    public String  showPendingEmployees(Model model){
        List<Employee> allEmployees = employeeUseCase.findAllEmployees();
        List<Employee> pendingEmployees = allEmployees.stream().filter(employee -> employee.getStatus().equals(EmployeeStatus.PENDING_APPROVAL)).toList();
        model.addAttribute("employees", EmployeeResDto.from(pendingEmployees));
        return "admin/employees";
    }
}
