package com.employee.payroll;

import static java.util.Collections.singletonMap;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Web Service / API to be consumed by the UI.
 */
@Controller
public class PayrollController {

  private final PayrollService service;

  PayrollController(PayrollService service) {
    this.service = service;
  }

  @GetMapping("/")
  String findAll(Model model, @RequestParam(required = false) String id) {
    model.addAttribute("employees", service.getEmployees(id));
    return "payroll-employees";
  }

  @ResponseBody
  @PostMapping("/salary")
  Map<String, Integer> calculateSalary(@RequestParam int empId, @RequestParam String type,
                                       @RequestParam String salary) {
    return singletonMap("salary", service.savaSalary(empId, type, salary));
  }

  @ResponseBody
  @DeleteMapping("/salary")
  Map<String, Integer> deleteSalary(@RequestParam int empId) {
    return singletonMap("salary", service.delSalary(empId));
  }
}
