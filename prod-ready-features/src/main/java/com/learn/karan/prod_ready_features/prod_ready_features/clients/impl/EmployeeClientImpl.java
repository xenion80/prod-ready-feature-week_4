package com.learn.karan.prod_ready_features.prod_ready_features.clients.impl;

import com.learn.karan.prod_ready_features.prod_ready_features.advice.ApiResponse;
import com.learn.karan.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.learn.karan.prod_ready_features.prod_ready_features.dto.employeedto;
import com.learn.karan.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log= LoggerFactory.getLogger(EmployeeClientImpl.class);
    @Override
    public List<employeedto> getAllEmployee() {
        log.trace("Trying to retrieve all employees in getAllEmployees");
        try {
            log.info("Attempting to call the restClient Method in getAllEmployees");
            ApiResponse<List<employeedto>> employeedtoList=restClient.get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,((request, response) -> {
                        log.error(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("couldn't create the employee");
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrived the employees n getAllEmployees");
            log.trace("Retrived employees list in getAllEmployess: {},{}",employeedtoList.getData(),"hello",5);
            return employeedtoList.getData();

        }catch (Exception e){
            log.error("Exception occured in getAllEmployees",e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public employeedto getEmployeeId(Long employeeId) {
        log.trace("Trying to get  employees by id in getEmployeeId:{}",employeeId);

        try{

            ApiResponse<employeedto> employeedResponse=restClient.get()
                    .uri("employees/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,((request, response) -> {
                        log.error(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("couldn't create the employee");
                    }))
                    .body(new ParameterizedTypeReference<ApiResponse<employeedto>>() {
                    });

            return employeedResponse.getData();
        } catch (Exception e) {
            log.error("Exception occured in getEmployeeId",e);

            throw new RuntimeException(e);
        }
    }

    @Override
    public employeedto createNewEmployee(employeedto employeedto) {
        log.trace("Trying to retrieve all employees with information {}",employeedto);

        try{
            ApiResponse<employeedto> employeedtoApiResponse=restClient.post()
                    .uri("employees")
                    .body(employeedto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,((request, response) -> {
                        log.debug("4xxClient error occured during createNewEmployee");
                        log.error(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("couldn't create the employee");
                    }))
                    .body(new ParameterizedTypeReference<ApiResponse<employeedto>>() {
                    });
            log.trace("Successfully created a new employee: {}",employeedtoApiResponse.getError());
            return employeedtoApiResponse.getData();

        } catch (Exception e) {
            log.error("Exception occured in createNewEmployee",e);

            throw new RuntimeException(e);
        }
    }
}
