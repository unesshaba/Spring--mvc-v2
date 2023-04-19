package com.example.patientmvc;

import com.example.patientmvc.Repositories.PatientRepository;
import com.example.patientmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication implements CommandLineRunner {

    @Autowired
    PatientRepository patientRepository;

    public static void main(String[] args) {

        SpringApplication.run(PatientMvcApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        patientRepository.save(new Patient(null, "Othmane", new Date(), false, 230));
        patientRepository.save(new Patient(null, "Darhoni", new Date(), true, 460));
        patientRepository.save(new Patient(null, "Kapera", new Date(), false, 130));
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
