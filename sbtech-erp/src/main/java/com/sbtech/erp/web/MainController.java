package com.sbtech.erp.web;

import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/web")
public class MainController {


    @GetMapping("/employee")
    public String employeeList() {
        return "employee/employee-dashboard";
    }

    @GetMapping("/admin/employee-management")
    public String employeeManagement(Model model) {
        model.addAttribute("content", "admin/employee-management");
        model.addAttribute("pageName", "employee-management");
        return "layout/admin-layout";
    }


    @GetMapping("/admin/task")
    public String taskManagementList() {
        return "admin/task-management";
    }

    @GetMapping("/admin")
    public String adminDashBoard(Model model) {
        model.addAttribute("content", "admin/admin-dashboard");
        return "layout/admin-layout";
    }

    @GetMapping("/task")
    public String taskList() {
        return "task/task";
    }

    @GetMapping("/report")
    public String reportList() {
        return "report/report";
    }

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }

    @GetMapping("/employee-orders")
    public String orderList() {
        return "order/employee-orders";
    }

    @GetMapping("/accounting")
    public String accountingMain() {
        return "accounting/journal-list";
    }

    @GetMapping("/inventory")
    public String inventoryMain() {
        return "inventory/product-list";
    }
}
