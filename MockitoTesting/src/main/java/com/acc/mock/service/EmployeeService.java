package com.acc.mock.service;

import com.acc.mock.entity.EmployeeEntity;
import com.acc.mock.repo.EmployeeRepo;
import com.acc.mock.utils.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepo repo;

    public EmployeeEntity add(EmployeeEntity employeeEntity){
        log.info("EmployeeService :: trying to add employee entity to db "+employeeEntity);
        int amount= Sample.discount(employeeEntity.getAmount());
        employeeEntity.setAmount(employeeEntity.getAmount()-amount);
        EmployeeEntity employeeEntity1=repo.save(employeeEntity);
        log.info("EmployeeService ::  successfully added the  employee entity to db "+employeeEntity);
        return employeeEntity1;
    }

    public EmployeeEntity getEmployeeById(int id){
        log.info("EmployeeService :: trying to find the employeeentity by id "+id);
        EmployeeEntity employeeEntity=repo.findById(id).orElseThrow(()->new RuntimeException("id id nt valid"));
        log.info("EmployeeService ::  Successfully fetched the employeeentity by id "+id);
        return employeeEntity;
    }

    public List<EmployeeEntity> listOfEmployees(){
        log.info("EmployeeService :: trying to fetch all the employees from db ");
       List<EmployeeEntity> list=repo.findAll();
        log.info("EmployeeService ::  fetched all the employees from db "+list);
        return list;
    }

    public List<EmployeeEntity> listOfEmployeesFindByname(String name){
        log.info("EmployeeService :: trying to fetch all employees from db by name " + name);
        List<EmployeeEntity> list= repo.findByName(name);
        log.info("EmployeeService ::  fetched, all the employees from db by name " + list);
        return list;
    }

    public void deleteById(int id){
        log.info("EmployeeService :: trying to delete the employees from db by id " + id);
        repo.deleteById(id);
        log.info("EmployeeService ::  deleted the employee from db by id " + id);
    }
}
