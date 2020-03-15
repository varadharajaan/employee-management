package com.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableHystrixDashboard
@EnableSwagger2
public class EmployeeApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    DataInitializerImpl dataInitializer;

    public static void main(String[] args) {
        SpringApplication.run(com.employee.EmployeeApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(com.employee.EmployeeApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {

        //dataInitializer.initData();
    }


}
