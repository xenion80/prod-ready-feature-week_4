package com.learn.karan.prod_ready_features.prod_ready_features.clients;

import com.learn.karan.prod_ready_features.prod_ready_features.dto.employeedto;

import java.util.List;

public interface EmployeeClient {

    List<employeedto> getAllEmployee();
    employeedto getEmployeeId(Long employeeId);
    employeedto createNewEmployee(employeedto employeedto);
}
