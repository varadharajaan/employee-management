package com.employee.repository;

import com.employee.model.Employee;

import com.employee.model.Salary;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * @Author Varadharajan on 16/03/20 19:13
 * @Projectname employee
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer>  {

    Optional<Employee> findFirstByEmpId(int empId);

    int countByEmpId(int empId);
}
