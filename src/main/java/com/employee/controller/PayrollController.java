package com.employee.controller;

import static java.util.Collections.singletonMap;

import java.util.List;
import java.util.Map;
import com.employee.model.Employee;
import com.employee.services.EmployeeService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Web Service / API to be consumed by the UI.
 */
/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

@RestController
public class PayrollController {

  private final EmployeeService service;

  PayrollController(EmployeeService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String findAll(Model model, @RequestParam(required = false) String id) {
    model.addAttribute("employees", service.getEmployees(id));
    return "payroll-employees";
  }


  @PostMapping("/employee")
  public Employee saveEmployee(@RequestBody Employee employee) {

    return service.saveEmployee(employee);

  }

  @PostMapping("/employees")
  public List<Employee> saveEmployees (@RequestBody List<Employee> employeeList) {

    return  service.saveEmployees(employeeList);
  }

  @GetMapping("/employee/{empId}")
  public Employee getEmployee (@PathVariable("empId") int empId) {

    return service.getEmployee(empId);
  }

}
