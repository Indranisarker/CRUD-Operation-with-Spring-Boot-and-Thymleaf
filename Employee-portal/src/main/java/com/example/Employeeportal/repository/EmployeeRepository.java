package com.example.Employeeportal.repository;

import com.example.Employeeportal.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> // JpaRepository take type of Jpa Entity, type of primary key
{

}
