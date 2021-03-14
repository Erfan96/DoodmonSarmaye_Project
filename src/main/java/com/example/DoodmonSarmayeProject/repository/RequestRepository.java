package com.example.DoodmonSarmayeProject.repository;

import com.example.DoodmonSarmayeProject.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
   List<Request> findAllByUserIdOrderByDateDesc(Long id);
   List<Request> findByOrderByDateDesc();
}
