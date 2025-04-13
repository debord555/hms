package com.debord555.hms_backend.authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.debord555.hms_backend.repository.User;
import com.debord555.hms_backend.repository.UserRepository;

@Service
public class HMSUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByPhoneNo(username).orElse(null);
        if (user == null) {
            System.err.println("User not found with phone number: " + username);
            throw new UsernameNotFoundException("User not found with phone number: " + username);
        }
        return user;
    }
}
