package com.sbtech.erp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String dashboard() {
        return "dashboard/main";  // templates/dashboard/main.html
    }

    @GetMapping("/employee")
    public String employeeList() {
        return "employee/employee-dashboard";
    }

    @GetMapping("/admin")
    public String adminDashBoard() {
        return "admin/admin-dashboard";
    }

    @GetMapping("/task")
    public String taskList() {
        return "task/list";
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
