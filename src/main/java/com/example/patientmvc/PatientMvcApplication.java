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
        patientRepository.save(new Patient(null, "youness", new Date(), false, 5356));
        patientRepository.save(new Patient(null, "oumaima", new Date(), true, 3434));
        patientRepository.save(new Patient(null, "wimrg", new Date(), false, 443));
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
