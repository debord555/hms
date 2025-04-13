package com.debord555.hms_backend.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if the roles already exist
        if (roleRepository.count() == 0) {
            // Create and save the roles
            Role adminRole = new Role();
            adminRole.setRoleName("ROLE_ADMIN");
            adminRole.setRoleDescription("Administrator Role");
            roleRepository.save(adminRole);

            Role patientRole = new Role();
            patientRole.setRoleName("ROLE_PATIENT");
            patientRole.setRoleDescription("A patient");
            roleRepository.save(patientRole);

            System.out.println("Initialized roles.");
        } else {
            System.out.println("Roles already present.");
        }
    }
}