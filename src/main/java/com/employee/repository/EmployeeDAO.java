package com.employee.repository;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.employee.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Employee's Data Access Layer Repo.
 */
@Component
public class EmployeeDAO {

  private final static String EMPLOYEES = "/employees";
  private final RestTemplate rest;


  EmployeeDAO(RestTemplateBuilder builder,
      @Value("${masglobal.datasource.api-uri}") String apiUri) {
    this.rest = builder.rootUri(apiUri).build();
  }

  public Optional<Employee> getById(int id) {
    return getAll().filter(emp -> emp.getId() == id).findAny();
  }

  /**
   * Consumes API end-point returning all values (the API doesn't have a filtering option).
   */
  public Stream<Employee> getAll() {

    return Stream.of(Objects.requireNonNull(rest.getForObject(EMPLOYEES, Employee[].class))).flatMap(Stream::of);

  }
}
