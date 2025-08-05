package com.deutsche.demo.services;
import com.deutsche.demo.exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deutsche.demo.model.Employee;
import com.deutsche.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EmployeeRepository empRepository;

    public List<Employee> getAllEmployees() {
        LOG.info("get all employees");
        return empRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        LOG.info("Employee with the id " + id);
        Optional<Employee> employeeOptional = empRepository.findById(id);
        if (employeeOptional.isPresent())
            return employeeOptional.get();
        else
            throw new EmployeeNotFoundException(id);
    }

    public Employee addEmployee(Employee employee) {
        LOG.info("Adding employee: " + employee);

        return empRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        LOG.info("Updating employee: " + employee);

        // Check if employee with given ID exists
        Optional<Employee> existingEmployee = empRepository.findById(employee.getId());

        if (existingEmployee.isPresent()) {
            // Update existing employee fields
            Employee updated = existingEmployee.get();
            updated.setName(employee.getName());
            updated.setSalary(employee.getSalary());

            // Save and return the updated employee
            return empRepository.save(updated);
        } else {
            // If employee doesn't exist, throw exception or handle accordingly
            throw new EmployeeNotFoundException(employee.getId());
        }
    }

    public Employee deleteEmployee(Integer id) {
        LOG.info("Deleting employee: " + id);
        empRepository.deleteById(id);
        // return the deleted employee object;
        return null;
    }

    public List<Employee> getEmployeeByName(String name) {
        LOG.info("Employee with the name " + name);

        List<Employee> employeeOptional = empRepository.findByName(name);

        if (employeeOptional.isEmpty())
            throw new EmployeeNotFoundException(name);
        else
           return employeeOptional;
    }

    public List<Employee> getEmployeeBySalary(Double Salary) {
        LOG.info("Employee with the Salary " + Salary);

        List<Employee> employeeOptional = empRepository.findBySalary(Salary);

        if (employeeOptional.isEmpty())
            throw new EmployeeNotFoundException(Salary);
        else
            return employeeOptional;
    }



}








//package com.deutsche.demo.services;
//
//import com.deutsche.demo.model.Employee;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    private List<Employee> tempEmpList = new ArrayList<>(
//            Arrays.asList(
//                    new Employee(101, "Sonu", 90000.00),
//                    new Employee(101, "Sonu", 90000.00),
//                    new Employee(101, "Sonu", 90000.00)));
//
//    public List<Employee> getAllEmployees() {
//        System.out.println(tempEmpList.size());
//        return tempEmpList;
//    }
//
//
//    public Employee getEmployeeById(Integer id) {
//        System.out.println(id);
//        // write your logic
//        return tempEmpList.get(0);
//    }
//
//    public Employee addEmployee(Employee employee) {
//        System.out.println(employee.toString());
//        // write your logic
//        return employee;
//    }
//}
