package com.acc.mock.controller;

import com.acc.mock.entity.EmployeeEntity;
import com.acc.mock.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @PostMapping("/add")
    public EmployeeEntity add(@RequestBody EmployeeEntity employeeEntity) {

        return service.add(employeeEntity);
    }

    @GetMapping("/get/{id}")
    public EmployeeEntity getById(@PathVariable int id) {

        return service.getEmployeeById(id);
    }

    @GetMapping("/getAll")
    public List<EmployeeEntity> getAll() {
        log.info("EmployeeController :: fetching all employees");
        return service.listOfEmployees();
    }

    @GetMapping("/getByname")
    public List<EmployeeEntity> getListByName(@RequestParam String name){

        return service.listOfEmployeesFindByname(name);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById(id);
    }
}
