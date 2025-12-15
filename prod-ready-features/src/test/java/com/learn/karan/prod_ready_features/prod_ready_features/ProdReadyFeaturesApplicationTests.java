package com.learn.karan.prod_ready_features.prod_ready_features;

import com.learn.karan.prod_ready_features.prod_ready_features.clients.EmployeeClient;
import com.learn.karan.prod_ready_features.prod_ready_features.dto.employeedto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeaturesApplicationTests {

    @Autowired
    private EmployeeClient employeeClient;

	@Test
    @Order(3)
	void getAllEmployees(){
        List<employeedto> employeedtoList= employeeClient.getAllEmployee();
        System.out.println(employeedtoList);
    }
    @Test
    @Order(2)
    void getEmployeeByIdTest(){
        employeedto employeedto=employeeClient.getEmployeeId(100L);
        System.out.println(employeedto);
    }

    @Test
    @Order(1)
    void createnewEmployeeTest(){
        employeedto employeedto=new employeedto(null,"Anuj","karan888@gmail.com",2,"USER",5000.0, LocalDate.of(2025,5,1),true);
        employeedto savedemployeedto=employeeClient.createNewEmployee(employeedto);
        System.out.println(savedemployeedto);
    }

}
