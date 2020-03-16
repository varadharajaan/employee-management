package com.employee.health;


import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;


/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        // Use the builder to build the com.employee.health status details that should be reported.
        // If you throw an com.employee.exception, the status will be DOWN with the com.employee.exception message.

        builder.up()
                .withDetail("app", "Alive and Kicking")
                .withDetail("error", "Nothing! I'm good.");
    }
}