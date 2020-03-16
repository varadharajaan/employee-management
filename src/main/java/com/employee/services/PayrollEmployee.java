package com.employee.services;

import com.employee.model.Salary;

import static java.text.NumberFormat.getCurrencyInstance;


/**
 * DTO Abstraction.
 */
@SuppressWarnings("unused")
public interface PayrollEmployee {

  int getId();

  String getName();

  String getContractTypeName();

  void setPhone(Salary salary);

  String getPhoneType();

  String getPhoneNumber();

  int getRoleId();

  String getRoleName();

  String getRoleDescription();

  double getBaseSalary();

  double getAnnualSalary();

  default String getFormattedBaseSalary() {
    return getCurrencyInstance().format(getBaseSalary());
  }

  default String getFormattedAnnualSalary() {
    return getCurrencyInstance().format(getAnnualSalary());
  }

  static PayrollEmployee from(com.employee.model.Employee emp) {
    return emp.getContractTypeName().contains("Hourly") ?
        new HourlyPayrollEmployee(emp.getId(), emp.getName(), emp.getContractTypeName(), emp.getRoleId(),
            emp.getRoleName(), emp.getRoleDescription(), emp.getHourlySalary()) :
        new MonthlyPayrollEmployee(emp.getId(), emp.getName(), emp.getContractTypeName(), emp.getRoleId(),
            emp.getRoleName(), emp.getRoleDescription(), emp.getMonthlySalary());
  }
}
