package com.employee.services.servicesimpl;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.StringUtils.isEmpty;

import com.employee.exception.AppException;
import  com.employee.model.Employee;

import java.util.*;
import java.util.stream.Stream;

import com.employee.repository.EmployeeDAO;
import com.employee.repository.EmployeeRepository;
import com.employee.services.PayrollEmployee;
import com.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Business Logic Layer Component.
 */
/**
 * @Author Varadharajan
 * @Projectname employee-management
 */
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final RestTemplate rest;

  @Value("${masglobal.datasource.api-uri}")
  String uri;

  EmployeeServiceImpl(RestTemplateBuilder builder,
                      @Value("${masglobal.datasource.api-uri}") String apiUri) {
    this.rest = builder.rootUri(apiUri).build();
  }

  @Autowired
  private  EmployeeDAO employeeDAO;

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
            pe -> employeeRepository.findFirstByEmpId(pe.getId()).ifPresent(pe::setSalary)
    ).collect(toList());
  }

  @Transactional(rollbackFor = Exception.class)
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

  @Transactional(rollbackFor = Exception.class)
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

  @Transactional(rollbackFor = Exception.class)
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
     Employee em = employeeRepository.findById(id).get();

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

      Map<String, String> params = new HashMap<String, String>();
      params.put("id", "1");
      Employee emForPayroll= rest.getForObject(uri, Employee.class, params);

      if(null!= em && null!= emForPayroll && em.equals(emForPayroll))
        return em;
      else
        throw  new AppException("employee object does not match");
    }
    catch ( Exception e) {
      throw  new AppException("value not stored");
    }

  }
}
