package com.employee.employee;

import com.employee.controller.PayrollController;
import com.employee.exception.AppException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeDAO;
import com.employee.repository.EmployeeRepository;
import com.employee.repository.PayrollRepository;
import com.employee.services.servicesimpl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @Author Varadharajan on 16/03/20 19:29
 * @Projectname employee
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Autowired
    EmployeeServiceImpl payrollService;

    @Mock
    @Autowired
    PayrollRepository payrollRepository;

    @Mock
    @Autowired
    EmployeeRepository employeeRepository;

    @Mock
    @Autowired
    PayrollController payrollController;

    @Mock
    @Autowired
    EmployeeDAO employeeDAO;

    @Mock
    @Autowired
    RestTemplate restTemplate;

    @Mock
    @Autowired
    public SecurityContextHolder securityContextHolder;

    @Mock
    @Autowired
    public SecurityContext securityContext;

    @Mock
    @Autowired
    public Authentication authentication;

    Employee employee = new Employee();

    @Before
    public void setUp()
    {
        initMocks(this);
        employee.setEmail("varathu09@gmail.com");
        employee.setDepartment("IT");
        employee.setContractTypeName("HOUR");
        employee.setPosition("Software Engineer");
    }

    @Test
    public void CheckSaveEmployee() {

        ResponseEntity<Employee> myEntity = new ResponseEntity<>(employee,
                HttpStatus.CREATED);
        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<Employee>>any()))
                .thenReturn(myEntity);
    when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
    Employee em = payrollService.saveEmployee(employee);
    assertNotNull(employee);

    }

    @Test
    public void updateEmployee() {

        ResponseEntity<Employee> myEntity = new ResponseEntity<>(employee,
                HttpStatus.CREATED);
        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<Employee>>any()))
                .thenReturn(myEntity);
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        Employee em = payrollService.updateEmployee(employee);
        assertNotNull(employee);
        StringBuilder sb = new StringBuilder();
        sb.insert(0,"hello");


    }

    @Test
    public void getEmployee() {

        ResponseEntity<Employee> myEntity = new ResponseEntity<>(employee,
                HttpStatus.CREATED);
        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<Employee>>any()))
                .thenReturn(myEntity);
        when(employeeRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(employee));
        Employee em = payrollService.getEmployee(1);
        assertNotNull(employee);

    }

    @Test(expected = AppException.class)
    public void createInvalidPayrollValues() {


        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<Employee>>any())).thenThrow(AppException.class);
        Employee em = payrollService.saveEmployee(employee);

    }

    @Test(expected = AppException.class)
    public void createEmployeeupdateException() {


        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<Employee>>any())).thenThrow(AppException.class);
        Employee em = payrollService.updateEmployee(employee);

    }







}
