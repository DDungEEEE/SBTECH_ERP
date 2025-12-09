package com.sbtech.erp.web;

import com.sbtech.erp.employee.application.port.in.EmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/web")
public class MainController {


    @GetMapping("/employee")
    public String employeeDashboard(Model model) {
        model.addAttribute("content", "employee/employee-dashboard");
        model.addAttribute("pageName", "employee-dashboard");
        return "layout/employee-layout";
    }

    @GetMapping("/employee/test")
    public String employeeTestDashboard(Model model) {
        model.addAttribute("content", "employee/employee-test-dashboard");
        model.addAttribute("pageName", "employee-dashboard");
        return "layout/employee-layout";
    }

    @GetMapping("/admin/employee-management")
    public String employeeManagement(Model model) {
        model.addAttribute("content", "admin/employee-management");
        model.addAttribute("pageName", "employee-management");
        return "layout/admin-layout";
    }


    @GetMapping("/admin/task-management")
    public String taskManagementList(Model model) {
        model.addAttribute("content", "admin/task-management");
        model.addAttribute("pageName", "task-management");
        return "layout/admin-layout";
    }

    @GetMapping("/admin/inventory")
    public String inventoryManagement(Model model) {
        model.addAttribute("content", "admin/inventory");
        model.addAttribute("pageName", "inventory");
        return "layout/admin-layout";
    }

    @GetMapping("/admin/journal")
    public String journalManagement(Model model) {
        model.addAttribute("content", "admin/journal");
        model.addAttribute("pageName", "journal");
        return "layout/admin-layout";
    }



    @GetMapping("/admin/inventory-inout")
    public String inventoryManagementInOut(Model model) {
        model.addAttribute("content", "admin/inventory-inout");
        model.addAttribute("pageName", "inventory-inout");
        return "layout/admin-layout";
    }

    @GetMapping("/admin")
    public String adminDashBoard(Model model) {
        model.addAttribute("content", "admin/admin-dashboard");
        model.addAttribute("pageName", "dashboard");
        return "layout/admin-layout";
    }

    @GetMapping("/employee/task")
    public String taskList(Model model) {
        model.addAttribute("content", "employee/employee-task");
        model.addAttribute("pageName", "employee-task");
        return "layout/employee-layout";
    }

    @GetMapping("/employee/order")
    public String getOrderList(Model model){
        model.addAttribute("content", "employee/employee-order");
        model.addAttribute("pageName", "employee-order");
        return "layout/employee-layout";
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
