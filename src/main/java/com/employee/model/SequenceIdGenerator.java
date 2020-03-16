package com.employee.model;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

public class SequenceIdGenerator implements IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o)  {

        String prefix="NETANT-EMP-";

        return prefix.concat(String.valueOf(Math.random()));
    }
}
