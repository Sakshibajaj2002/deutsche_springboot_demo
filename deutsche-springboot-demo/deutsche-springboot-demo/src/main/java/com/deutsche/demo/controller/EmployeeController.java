package com.deutsche.demo.controller;

import com.deutsche.demo.model.Employee;
import com.deutsche.demo.services.EmployeeService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService empservice ;

    //    http://localhost:8090/api/v1/employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return empservice.getAllEmployees();
    }

    //    http://localhost:8080/api/emp/101
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable( name = "id") Integer id) {
        System.out.println("works");
        return ResponseEntity
                .ok()
                .header("message","Employee with the id " + id + " fetched successfully.")
                .body(empservice.getEmployeeById(id));
    }


    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        //return empservice.addEmployee(employee);
        return ResponseEntity
                .ok()
                .header("message","Employee added successfully.")
                .body(empservice.addEmployee(employee));
    }

    //    http://localhost:8090/api/v1/employees
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {

        //return empservice.updateEmployee(employee);
        return ResponseEntity
                .ok()
                .header("message","Employee updated successfully.")
                .body(empservice.updateEmployee(employee));
    }

    //    http://localhost:8090/api/v1/employees/101
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable(name = "id") Integer id) {
        return ResponseEntity
                .ok()
                .header("message",empservice.getEmployeeById(id) + "Employee Deleted successfully")
                .body(empservice.deleteEmployee(id));
        //return empservice.deleteEmployee(id);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<List<Employee>> getEmployeeByName(@PathVariable( name = "name") String name) {
        System.out.println("works");
        return ResponseEntity
                .ok()
                .header("message","Employee with the name " + name + " fetched successfully.")
                .body(empservice.getEmployeeByName(name));
    }

    @GetMapping("Salary/{Salary}")
    public ResponseEntity<List<Employee>> getEmployeeBySalary(@PathVariable( name = "Salary") Double salary) {
        System.out.println("works");
        return ResponseEntity
                .ok()
                .header("message","Employee with the name " + salary + " fetched successfully.")
                .body(empservice.getEmployeeBySalary(salary));
    }


//    public Object addEmployee() {
//        return null;
//    }
//
//    public Object updateEmployee() {
//        return null;
//    }
//
//    public Object deleteEmployee() {
//        return null;
//    }

}
