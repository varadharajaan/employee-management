package com.employee.employees;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Phone's Data Access Layer Repo.
 */
@Repository
public interface PayrollDAO extends CrudRepository<Salary, Integer> {

  Optional<Salary> findFirstByEmpId(int empId);

  int countByEmpId(int empId);
}
