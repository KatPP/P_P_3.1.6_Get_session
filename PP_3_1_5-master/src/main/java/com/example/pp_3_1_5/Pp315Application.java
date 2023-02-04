package com.example.pp_3_1_5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
public class Pp315Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        UserChange.exchangeMethodsOfRestTemplate();
//        SpringApplication.run(Pp315Application.class, args);
    }

}
