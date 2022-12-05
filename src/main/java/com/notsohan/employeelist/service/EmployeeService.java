package com.notsohan.employeelist.service;

import com.notsohan.employeelist.model.Employee;
import com.notsohan.employeelist.model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee findById(int id) {
        Employee emp = null;
        Optional<Employee> res = employeeRepository.findById(id);
        if(res.isPresent()){
            emp = res.get();
        }else{
            throw new IllegalStateException("No employee found with this id");
        }
        return emp;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public void save(Employee employee) {
        Optional<Employee> empOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if(empOptional.isPresent()){
            throw new IllegalStateException("Already present in DB");
        }
        employeeRepository.save(employee);
    }

    public void update(int id){
        Optional<Employee> empOptional = employeeRepository.findEmployeeById(id);
        Employee emp;
        if(empOptional.isEmpty()){
            throw new IllegalStateException("Not found!");
        }
        emp = empOptional.get();
        employeeRepository.save(emp);
    }

    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
