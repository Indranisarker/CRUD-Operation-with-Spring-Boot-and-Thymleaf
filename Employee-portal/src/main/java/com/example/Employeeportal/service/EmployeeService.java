package com.example.Employeeportal.service;

import com.example.Employeeportal.domain.EmployeeDTO;
import com.example.Employeeportal.entity.Employee;
import com.example.Employeeportal.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Page<Employee> getEmployees(int pageNo, int pageSize, String sortOrder, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortOrder).ascending() :
                Sort.by(sortOrder).descending();
        Pageable paging = PageRequest.of(pageNo - 1, pageSize, sort); // spring jpa work with page number to 0
        //but user give the page number 1 that's why we need to subtract -1.
        return employeeRepository.findAll(paging);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setSalary(employeeDto.getSalary());
        Employee employees = employeeRepository.save(employee);
        return EmployeeDTO.entityToDTO(employees);
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

    public void updateEmployeeById(Long id, EmployeeDTO employee) {
       Optional<Employee> findEmployee = employeeRepository.findById(id);
       Employee existingEmployee = findEmployee.get();
       existingEmployee.setId(id);
       existingEmployee.setName(employee.getName());
       existingEmployee.setDepartment(employee.getDepartment());
       existingEmployee.setSalary(employee.getSalary());
      Employee updateEmployee = employeeRepository.save(existingEmployee);
      EmployeeDTO.entityToDTO(updateEmployee);

    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
