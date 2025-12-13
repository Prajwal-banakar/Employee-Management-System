package com.ems.app.controller;

import com.ems.app.pojo.ConfirmationForm;
import com.ems.app.pojo.Employee;
import com.ems.app.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create";
    }

    @PostMapping("/create")
    public String newEmployee(@ModelAttribute Employee employee, Model model) {
        String empId = "EMP" + (1000 + new Random().nextInt(9000));
        employee.setId(empId);

        employeeRepo.save(employee);
        model.addAttribute("successMessage", "Employee created successfully with ID: " + empId);

        return "create";
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "update";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute Employee employee, Model model) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());

        if (existingEmployee.isPresent()) {
            employeeRepo.save(employee);
            model.addAttribute("successMessage", "Employee updated successfully.");
        } else {
            model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found.");
        }

        return "update";
    }

    @GetMapping("/remove")
    public String showRemoveForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "remove";
    }

    @PostMapping("/remove")
    public String removeEmployee(@ModelAttribute Employee employee, Model model) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());

        if (existingEmployee.isPresent()) {
            employeeRepo.deleteById(employee.getId());
            model.addAttribute("successMessage", "Employee removed successfully.");
        } else {
            model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found.");
        }

        return "remove";
    }

    @GetMapping("/removeall")
    public String showRemoveAllForm(Model model) {
        model.addAttribute("confirmationForm", new ConfirmationForm());
        return "removeall";
    }

    @PostMapping("/removeall")
    public String removeAll(@ModelAttribute ConfirmationForm confirmationForm, Model model) {
        if ("Yes".equalsIgnoreCase(confirmationForm.getConfirmation())) {
            employeeRepo.deleteAll();
            model.addAttribute("successMessage", "All employees deleted successfully.");
        } else {
            model.addAttribute("infoMessage", "Deletion canceled.");
        }

        return "removeall";
    }

    @GetMapping("/viewall")
    public String viewAllEmployees(Model model) {
        List<Employee> employees = employeeRepo.findAll();
        model.addAttribute("employees", employees);
        return "viewall";
    }
}
