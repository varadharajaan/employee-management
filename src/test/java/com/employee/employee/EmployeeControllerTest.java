package com.employee.employee;

import com.employee.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Varadharajan on 17/03/20 00:46
 * @Projectname employee
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void createEmployee() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .post("/employees")
                .content(asJsonString(new Employee()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void updateEmployee() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put("/employees")
                .content(asJsonString(new Employee()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
    }

    @Test
    public void getAllEmployeesAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
    }

    @Test
    public void getEmployeesAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/employee/1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteSalaryAPI() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.delete("/salary/", 1) )
                .andExpect(status().isAccepted());
    }
}
