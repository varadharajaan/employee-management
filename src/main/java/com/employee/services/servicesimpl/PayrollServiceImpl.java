package com.employee.services.servicesimpl;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.isEmpty;

import com.employee.exception.AppException;
import  com.employee.model.Employee;

import java.util.*;
import java.util.stream.Stream;

import com.employee.repository.PayrollRepository;
import com.employee.repository.EmployeeDAO;
import com.employee.model.Salary;
import com.employee.repository.EmployeeRepository;
import com.employee.services.PayrollEmployee;
import com.employee.services.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Business Logic Layer Component.
 */
/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

@Service
public class PayrollServiceImpl implements PayrollService {

  private final RestTemplate rest;

  @Value("${masglobal.datasource.api-uri}")
  String uri;

  PayrollServiceImpl(RestTemplateBuilder builder,
              @Value("${masglobal.datasource.api-uri}") String apiUri) {
    this.rest = builder.rootUri(apiUri).build();
  }

  @Autowired
  private  EmployeeDAO employeeDAO;
  @Autowired
  private  PayrollRepository payrollRepository;

  @Autowired
  private  EmployeeRepository employeeRepository;


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
            pe -> payrollRepository.findFirstByEmpId(pe.getId()).ifPresent(pe::setPhone)
    ).collect(toList());
  }

  @Override
  public int savaSalary(int empId, String type, String rate) {
    return payrollRepository.save(new Salary(empId, rate, Salary.Type.valueOf(type))).getId();
  }

  @Override
  public List<Employee> saveEmployees(List<Employee> employees) {
try {
  rest.postForObject( uri, employees, Employee.class);
  return  (List<Employee>)  employeeRepository.saveAll(employees);
}

catch (Exception e) {
  throw  new AppException("value not stored");
}

  }

  @Override
  public Employee saveEmployee(Employee employee) {

    try {
      employeeRepository.save(employee);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);

      return rest.exchange( uri, HttpMethod.POST , entity, Employee.class).getBody();


    }
    catch (Exception e) {
      throw  new AppException("value not stored");
    }

  }

  @Override
  public Employee updateEmployee(Employee employee) {
    try {
      Employee em = employeeRepository.save(employee);
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<Employee> entity = new HttpEntity<>(employee, headers);
      rest.exchange( uri,HttpMethod.PUT, entity, Employee.class);
      return em;
    }
    catch (Exception e) {
      throw  new AppException("value not stored");
    }

  }

  @Override
  public Employee getEmployee(int id) {

    try {
     employeeRepository.findById(id);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

      Map<String, String> params = new HashMap<String, String>();
      params.put("id", "1");
      return rest.getForObject(uri, Employee.class, params);
    }
    catch ( Exception e) {
      throw  new AppException("value not stored");
    }

  }

  @Override
  public int delSalary(int empId) {
    payrollRepository.findFirstByEmpId(empId).ifPresent(payrollRepository::delete);
    return payrollRepository.countByEmpId(empId);
  }
}
