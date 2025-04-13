package com.debord555.hms_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByPhoneNo(String phoneNo);

}
