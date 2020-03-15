package com.employee.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmployeePayrollScedhulder {


    private static final Logger logger = LoggerFactory.getLogger(EmployeePayrollScedhulder.class);

    /**
     * generate payslip for every employees every 30 days.
     */
    @Scheduled(fixedRate = 180000, initialDelay = 30000)
    public void scheduleTaskWithFixedDelay() {

        logger.info("I am scheduling the application");

    }


}
