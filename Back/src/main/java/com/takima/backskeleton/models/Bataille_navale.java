package com.takima.backskeleton.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import com.takima.backskeleton.services.GameService;

import java.util.Scanner;


@SpringBootApplication
public class Bataille_navale {

    @Autowired
    private GameService gameService;

    public static void main(String[] args) {
        SpringApplication.run(Bataille_navale.class, args);
    }


}