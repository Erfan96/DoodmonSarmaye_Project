package com.example.DoodmonSarmayeProject.repository;

import com.example.DoodmonSarmayeProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByNationalCode(String nationalCode);
    List<User> findAllByOrderByLastNameAsc();
}
