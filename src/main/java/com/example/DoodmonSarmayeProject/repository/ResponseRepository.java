package com.example.DoodmonSarmayeProject.repository;

import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    Optional<Response> findByRequest(Request request);
}
