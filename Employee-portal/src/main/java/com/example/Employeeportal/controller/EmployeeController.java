package com.example.Employeeportal.controller;

import com.example.Employeeportal.entity.Employee;
import com.example.Employeeportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class EmployeeController {
    @Autowired
     private EmployeeService employeeService;

    @GetMapping("/employees")
    public String getAllEmployee(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "listOfEmployees.html";
    }
    @GetMapping("/employee/new")
    public String createEmployeeForm(Model model){
        //create empty employee object to hold form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add-form.html";
    }
    @PostMapping("/employee/add-employee")
    public String addEmployee(@ModelAttribute ("employee") Employee employee, RedirectAttributes redirectAttributes){
        employeeService.createEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage", "New Employee Added Successfully!");

        return "redirect:/employees";
    }

    @GetMapping("/employee/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", employeeService.updateEmployee(id));
        return "update-employee.html";
    }

    @PostMapping("/employee/updateEmployee/{id}")
    public String updateEmployeeById(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee,
                                     RedirectAttributes ra){
        employeeService.updateEmployeeById(id, employee);
        ra.addFlashAttribute("successMessage", "Update Successfully!");
        return "redirect:/employees";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, RedirectAttributes ra){
        employeeService.deleteEmployeeById(id);
        ra.addFlashAttribute("successMessage", "Delete Successfully!");
        return "redirect:/employees";
    }
}
