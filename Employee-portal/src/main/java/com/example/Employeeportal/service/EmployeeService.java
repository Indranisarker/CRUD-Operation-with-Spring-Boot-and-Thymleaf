package com.example.Employeeportal.service;

import com.example.Employeeportal.entity.Employee;
import com.example.Employeeportal.repository.EmployeeRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
//    public String helloService(){
//        return "hello.html";
//    }
//
    public List<Employee> getAllEmployees(){
    return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
         return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee new_employee = null;
        if(employee.isPresent()){
            new_employee = employee.get();
            employeeRepository.save(new_employee);
        }
        return new_employee;
    }

    public void updateEmployeeById(Long id, Employee employee) {
       Optional<Employee> findEmployee = employeeRepository.findById(id);
       Employee existingEmployee = findEmployee.get();
       existingEmployee.setId(id);
       existingEmployee.setName(employee.getName());
       existingEmployee.setDepartment(employee.getDepartment());
       existingEmployee.setSalary(employee.getSalary());
       employeeRepository.save(existingEmployee);

    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
