/*
 * This class is only for testing authentication.
 * Must be removed in production.
 */

package com.debord555.hms_backend.misc;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.debord555.hms_backend.repository.User;
import com.debord555.hms_backend.repository.UserRepository;

import java.security.Principal;

// TODO: Remove this class in production

@RestController
public class Greeter {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/patient/greet")
    public String greet(Principal principal) {
        User user = userRepository.findByPhoneNo(principal.getName()).orElse(null);
        return String.format("Hello, %s, this is the HMS backend!", user.getFirstName());
    }

}
