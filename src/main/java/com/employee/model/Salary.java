package com.employee.model;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * @Author Varadharajan
 * @Projectname employee-management
 */

@Entity(name = "salary")
public class Salary {

  @Id
  @Column(nullable = false)
  @GeneratedValue(strategy = IDENTITY)
  int id;

  @Column(nullable = false)
  @Positive
  int empId;

  @Column(nullable = false, length = 15, unique = true)
  @NotBlank
  String rate;

  @Column(nullable = false, length = 10)
  @Enumerated(STRING)
  Type type;

  @SuppressWarnings("unused")
  public enum Type {
    HOURLY,MONTHLY,CONTRACTOR,SHARE
  }

  public Salary() {
  }

  public Salary(@Positive int empId, @NotBlank String rate, Type type) {
    this.empId = empId;
    this.rate = rate;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public int getEmpId() {
    return empId;
  }

  public Type getType() {
    return type;
  }

  public String getRate() {
    return rate;
  }
}
