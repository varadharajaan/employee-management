package com.employee.services;

import java.util.List;

import com.employee.model.Employee;


/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

public interface PayrollService {

  List<PayrollEmployee> getEmployees(String id);

  int savaSalary(int empId, String type, String rate);

  List<Employee> saveEmployees(List<Employee> employees);

  Employee saveEmployee (Employee employee);

  Employee updateEmployee (Employee employee);

  Employee getEmployee(int id);

  int delSalary(int empId);
}
