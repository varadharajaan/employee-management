package com.employee.payroll;

import java.util.List;

/**
 * Business Logic Layer Interface.
 */
interface PayrollService {

  List<PayrollEmployee> getEmployees(String id);

  int savaSalary(int empId, String type, String rate);

  int delSalary(int empId);
}
