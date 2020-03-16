package com.employee.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

@Entity
@Table(name = "employee")
@Data
public class Employee  extends AuditEntity {

    /**
     * Data Access Layer Entity.
     */
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer id;
    @Size(min = 3)
    @Column(name = "name")
    private String name;
    @NotNull(message = "position cant be null")
    private String position;
    @NotNull(message = "department cant be null")
    private String department;
    @Min(0)
    private Float salary;

    private String email;
    @JsonIgnore // don't serialize in output
    private String password;
    @Min(0) @Max(1)
    private Integer enabled = 0;

    private String contractTypeName;

    private int roleId;

   private  String roleName;

   private String roleDescription;

   private  float hourlySalary;

    private float monthlySalary;

    @Override
    public String toString() {
        return String.format("%s %s", name, position);
    }

}
