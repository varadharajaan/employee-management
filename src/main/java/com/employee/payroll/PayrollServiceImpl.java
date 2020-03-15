package com.employee.payroll;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.isEmpty;


import java.util.List;
import java.util.stream.Stream;

import com.employee.model.Employee;
import com.employee.repository.EmployeeDAO;
import com.employee.employees.Salary;
import com.employee.employees.PayrollDAO;
import org.springframework.stereotype.Service;

/**
 * Business Logic Layer Component.
 */
@Service
class PayrollServiceImpl implements PayrollService {

  private final EmployeeDAO employeeDAO;
  private final PayrollDAO payrollDAO;

  PayrollServiceImpl(EmployeeDAO employeeDAO, PayrollDAO payrollDAO) {
    this.employeeDAO = employeeDAO;
    this.payrollDAO = payrollDAO;
  }

  @Override
  public List<PayrollEmployee> getEmployees(String id) {
    Stream<Employee> employees;
    try {
      employees = isEmpty(id) ? employeeDAO.getAll():employeeDAO.getById(parseInt(id)).map(Stream::of).orElseGet(Stream::empty);
    } catch (NumberFormatException e) {
      employees = Stream.empty();
    }
    // Factory method PayrollEmployee::from for concrete instances...
    return employees.map(PayrollEmployee::from).peek(
        pe -> payrollDAO.findFirstByEmpId(pe.getId()).ifPresent(pe::setPhone)
    ).collect(toList());
  }

  @Override
  public int savaSalary(int empId, String type, String rate) {
    return payrollDAO.save(new Salary(empId, rate, Salary.Type.valueOf(type))).getId();
  }

  @Override
  public int delSalary(int empId) {
    payrollDAO.findFirstByEmpId(empId).ifPresent(payrollDAO::delete);
    return payrollDAO.countByEmpId(empId);
  }
}
