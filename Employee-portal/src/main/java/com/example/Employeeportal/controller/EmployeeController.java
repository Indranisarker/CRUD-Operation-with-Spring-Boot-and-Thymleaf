package com.example.Employeeportal.controller;

import com.example.Employeeportal.domain.EmployeeDTO;
import com.example.Employeeportal.entity.Employee;
import com.example.Employeeportal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class EmployeeController {
    @Autowired
     private EmployeeService employeeService;

    private int calculateStartSerialForPage(int pageNumber) {
        int pageSize = 5; // Assuming 10 records per page
        return (pageNumber - 1) * pageSize;
    }
    @GetMapping("/employees")
    public String getAllEmployee(Model model){
        return employeePaginated(1, "name", "asc", model);
//        model.addAttribute("employees", employeeService.getAllEmployees());
//        return "listOfEmployees.html";
    }
    @GetMapping("/employees/page/{pageNo}")
    public String employeePaginated(@PathVariable("pageNo") int pageNo,
                                    @RequestParam(value = "sortOrder", defaultValue = "name") String sortOrder,
                                    @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection, Model model){
        int pageSize = 5;
        int startSerial = calculateStartSerialForPage(pageNo);
        model.addAttribute("startSerial", startSerial);
        Page<Employee> employeePage = employeeService.getEmployees(pageNo,pageSize,sortOrder, sortDirection);
       //convert page to list
        List<Employee> employeeList = employeePage.getContent();
        //for pagination
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPage", employeePage.getTotalPages());
        //for sorting
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("sortDirection", sortDirection);
        //model.addAttribute("reverseSorting", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("employees",employeeList);
        return "listOfEmployees.html";

    }
    @GetMapping("/employee/new")
    public String createEmployeeForm(Model model){
        //create empty employee object to hold form data
        model.addAttribute("employee", new EmployeeDTO());
        return "add-form.html";
    }
    @PostMapping("/employee/add-employee")
    public String addEmployee(@ModelAttribute ("employee") EmployeeDTO employee, RedirectAttributes redirectAttributes){
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
    public String updateEmployeeById(@PathVariable("id") Long id, @ModelAttribute("employee") EmployeeDTO employee,
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
