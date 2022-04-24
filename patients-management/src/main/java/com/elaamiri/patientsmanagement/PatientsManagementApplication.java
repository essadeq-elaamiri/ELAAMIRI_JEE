package com.elaamiri.patientsmanagement;

import com.elaamiri.patientsmanagement.security.entities.AppUser;
import com.elaamiri.patientsmanagement.security.services.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PatientsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsManagementApplication.class, args);
    }

    //@Bean
    CommandLineRunner run(SecurityService securityService){
        // adding some users and roles
        return args-> {

            securityService.saveNewUser("user", "user", "user");
            //securityService.saveNewUser("user", "user", "user"); // exception
            //securityService.saveNewUser("admin", "admin", "user"); // exception
            securityService.saveNewUser("admin", "admin", "admin");

            securityService.saveNewRole("USER", "user disc");
            //securityService.saveNewRole("USER", "user disc"); //exception
            securityService.saveNewRole("ADMIN", "admin disc");

            securityService.addRoleToUser("user", "USER");
            securityService.addRoleToUser("admin", "admin");
            securityService.addRoleToUser("admin", "USER");

        };

    }

}
