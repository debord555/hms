package com.debord555.hms_backend.authenticator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.debord555.hms_backend.misc.PhoneNumberService;
import com.debord555.hms_backend.repository.Role;
import com.debord555.hms_backend.repository.RoleRepository;
import com.debord555.hms_backend.repository.User;
import com.debord555.hms_backend.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Authenticator {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PhoneNumberService pns;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/api/auth/register-patient")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {

        if (request.getPhoneNo() == null || request.getPhoneNo().isEmpty()) {
            return ResponseEntity.badRequest().body("Phone number is required");
        }

        if (!pns.isValidPhoneNumber(request.getPhoneNo())) {
            return ResponseEntity.badRequest().body("Invalid phone number");
        }

        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Password is required");
        }

        // TODO: Add code for password policy

        User user = userRepository.findByPhoneNo(request.getPhoneNo()).orElse(null);
        if (user != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            return ResponseEntity.badRequest().body("First name is required");
        }

        user = new User();
        user.setPhoneNo(request.getPhoneNo());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setMiddleName(request.getMiddleName());
        user.setLastName(request.getLastName());

        Role role = roleRepository.findByRoleName("ROLE_PATIENT");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

}
