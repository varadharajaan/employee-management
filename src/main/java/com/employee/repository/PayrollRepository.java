package com.employee.repository;

import java.util.Optional;

import com.employee.model.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Phone's Data Access Layer Repo.
 */

/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

@Repository
public interface PayrollRepository extends CrudRepository<Salary, Integer> {

  Optional<Salary> findFirstByEmpId(int empId);

  int countByEmpId(int empId);
}
