package com.notsohan.employeelist.controller;

import com.notsohan.employeelist.service.EmployeeService;
import com.notsohan.employeelist.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String getEmployee(Model model){
        List<Employee> emp = employeeService.findAll();
        model.addAttribute("emp", emp);
        return "employee/employee";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("emp", employee);
        return "employee/employeeForm";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id){
        employeeService.update(id);
//        model.addAttribute("emp", emp);
        return "employee/employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("emp") Employee employee){
        employeeService.save(employee);
        return "redirect:list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:list";
    }
}
