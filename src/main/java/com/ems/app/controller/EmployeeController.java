package com.ems.app.controller;

//import com.ems.app.pojo.ConfirmationForm;
//import com.ems.app.pojo.Employee;
//import com.ems.app.repo.EmployeeRepo;
//import com.ems.app.repo.EmployeeRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.Optional;
//import java.util.Random;
//
//@Controller
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeRepo employeeRepo;
//
//    @GetMapping("/")
//    public String index(Model model) {
//        model.addAttribute("employee", new Employee());
//        return "index"; // matches index.html
//    }
//
//
//    // CREATE Employee
//    @PostMapping("/create")
//    public String newEmployee(@ModelAttribute Employee employee, Model model) {
//        // Creating dynamic Employee ID
//        String empId = "EMP" + (1000 + new Random().nextInt(9000));
//        employee.setId(empId);
//
//        // Save employee
//        employeeRepo.save(employee);
//
//        // Add to model (optional)
//        model.addAttribute("employee", new Employee());
//        model.addAttribute("successMessage", "Employee created successfully with ID: " + empId);
//
//        return "create";
//    }
//
//    // UPDATE Employee
//    @PostMapping("/update")
//    public String updateEmployee(@ModelAttribute Employee employee, Model model) {
//        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
//
//        if (existingEmployee.isPresent()) {
//            employeeRepo.save(employee);
//            model.addAttribute("successMessage", "Employee updated successfully.");
//        } else {
//            model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found.");
//        }
//
//        return "update";
//    }
//
//    // REMOVE Employee by ID
//    @PostMapping("/remove")
//    public String removeEmployee(@ModelAttribute Employee employee, Model model) {
//        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
//
//        if (existingEmployee.isPresent()) {
//            employeeRepo.deleteById(employee.getId());
//            model.addAttribute("successMessage", "Employee removed successfully.");
//        } else {
//            model.addAttribute("errorMessage", "Employee with ID " + employee.getId() + " not found.");
//        }
//
//        return "remove";
//    }
//
//    // REMOVE All Employees (confirmation required)
//    @PostMapping("/removeall")
//    public String removeAll(@ModelAttribute ConfirmationForm confirmationForm, Model model) {
//        if ("Yes".equalsIgnoreCase(confirmationForm.getConfirmation())) {
//            employeeRepo.deleteAll();
//            model.addAttribute("successMessage", "All employees deleted successfully.");
//        } else {
//            model.addAttribute("infoMessage", "Deletion canceled.");
//        }
//        return "removeall";
//    }
//}


import com.ems.app.pojo.ConfirmationForm;
import com.ems.app.pojo.Employee;
import com.ems.app.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.Random;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    // ===============================
    // 🏠 HOME PAGE
    // ===============================
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // ===============================
    // 👤 CREATE EMPLOYEE
    // ===============================
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "create";
    }

    @PostMapping("/create")
    public String newEmployee(@ModelAttribute Employee employee, Model model) {
        // Generate unique Employee ID
        String empId = "EMP" + (1000 + new Random().nextInt(9000));
        employee.setId(empId);

        employeeRepo.save(employee);
        model.addAttribute("successMessage", "Employee created successfully with ID: " + empId);

        return "create";
    }

    // ===============================
    // ✏️ UPDATE EMPLOYEE
    // ===============================
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

    // ===============================
    // ❌ REMOVE EMPLOYEE BY ID
    // ===============================
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

    // ===============================
    // ⚠️ REMOVE ALL EMPLOYEES
    // ===============================
    @GetMapping("/remove/all")
    public String showRemoveAllForm(Model model) {
        model.addAttribute("confirmationForm", new ConfirmationForm());
        return "confirm-delete";
    }

    @PostMapping("/remove/all")
    public String removeAll(@ModelAttribute ConfirmationForm confirmationForm, Model model) {
        if ("Yes".equalsIgnoreCase(confirmationForm.getConfirmation())) {
            employeeRepo.deleteAll();
            model.addAttribute("successMessage", "All employees deleted successfully.");
        } else {
            model.addAttribute("infoMessage", "Deletion canceled.");
        }
        return "confirm-delete";
    }
}

