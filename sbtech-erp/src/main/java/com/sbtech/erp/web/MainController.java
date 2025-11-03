package com.sbtech.erp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MainController {

    @GetMapping("/")
    public String dashboard() {
        return "dashboard/main";  // templates/dashboard/main.html
    }

    @GetMapping("/employee")
    public String employeeList() {
        return "employee/employee-dashboard";
    }

    @GetMapping("/admin/employee")
    public String employeeManagentList() {
        return "admin/employee";
    }


    @GetMapping("/admin/task")
    public String taskManagentList() {
        return "admin/task-management";
    }

    @GetMapping("/admin")
    public String adminDashBoard() {
        return "admin/admin-dashboard";
    }

    @GetMapping("/task")
    public String taskList() {
        return "task/task";
    }

    @GetMapping("/report")
    public String reportList() {
        return "report/report";
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
