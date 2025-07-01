package com.safeflow.service.impl;

import com.safeflow.domain.model.Employee;
import com.safeflow.repository.EmployeeRepository;
import com.safeflow.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private  EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return List.of();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return  employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getUserById(Long id) {
        return employeeRepository.findAllByUserId(id);
    }
}
