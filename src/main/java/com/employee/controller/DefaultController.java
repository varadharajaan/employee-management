package com.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

@RestController
public class DefaultController {

    @GetMapping("/health")
    public String firstPage() {
        return "Hello Invia Flight booking application successfully deployed and started...";
    }


    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }
}
