package com.aluracursos.LiterAlura_ChallengeONE_SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.principal.Principal;

@SpringBootApplication
@EntityScan("com.aluracursos.LiterAlura_ChallengeONE_SpringBoot.models")
public class LiterAluraChallengeOneSpringBootApplication implements CommandLineRunner {

    private final Principal principal;

    @Autowired
    public LiterAluraChallengeOneSpringBootApplication(Principal principal) {
        this.principal = principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraChallengeOneSpringBootApplication.class, args);
    }

    @Override
    public void run(String... args) {
        principal.muestraElMenu();
    }
}
