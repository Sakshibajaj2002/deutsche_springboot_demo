package com.deutsche.demo.repository;

import com.deutsche.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{


     public abstract List<Employee> findByName(String name);
     public abstract List<Employee> findBySalary(Double Salary);

}

