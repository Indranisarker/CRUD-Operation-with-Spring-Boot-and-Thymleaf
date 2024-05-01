package com.example.Employeeportal.domain;

import com.example.Employeeportal.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {
    private String name;
    private String department;
    private Integer salary;

    public static EmployeeDTO entityToDTO(Employee em){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName(em.getName());
        employeeDTO.setDepartment(em.getDepartment());
        employeeDTO.setSalary(em.getSalary());
        return employeeDTO;

    }

}
