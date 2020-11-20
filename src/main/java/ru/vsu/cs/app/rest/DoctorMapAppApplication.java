package ru.vsu.cs.app.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.vsu.cs.app.services.ServiceConfig;

@SpringBootApplication
@Import(ServiceConfig.class)
public class DoctorMapAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorMapAppApplication.class, args);
    }

}
