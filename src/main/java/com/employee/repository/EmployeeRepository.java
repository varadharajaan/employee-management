package com.employee.repository;

import com.employee.model.Employee;

import org.springframework.data.repository.CrudRepository;



/**
 * @Author Varadharajan on 16/03/20 19:13
 * @Projectname employee
 */
public interface EmployeeRepository extends CrudRepository<Employee, Integer>  {
}
