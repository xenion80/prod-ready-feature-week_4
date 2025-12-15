package com.learn.karan.prod_ready_features.prod_ready_features.dto;


import lombok.*;

import java.time.LocalDate;

//presentation layer lec 2 week 3
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class employeedto {

    private Long id;



    private String name;


    private String email;


    private Integer age;

//    @Pattern(regexp ="^(ADMIN|USER)",message = "Role can be either be user or admin")

    private String role;//admin ,user


    private Double salary;


    private LocalDate dateofjoining;


    private Boolean active;



}
