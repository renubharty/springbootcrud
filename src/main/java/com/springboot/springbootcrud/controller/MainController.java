package com.springboot.springbootcrud.controller;

import java.net.URI;
import com.springboot.springbootcrud.dao.EmployeeDAO;
import com.springboot.springbootcrud.model.Employee;
import com.springboot.springbootcrud.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/employees")
public class MainController {

        @Autowired
        private EmployeeDAO employeeDao;

        @GetMapping(path="/", produces = "application/json")
        public Employees getEmployees()
        {
            return employeeDao.getAllEmployees();
        }

        @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
        public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
        {
            Integer id = employeeDao.getAllEmployees().getEmployeeList().size() + 1;
            employee.setId(id);

            employeeDao.addEmployee(employee);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(employee.getId())
                    .toUri();

            return ResponseEntity.created(location).build();
        }}