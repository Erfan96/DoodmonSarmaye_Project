package com.example.DoodmonSarmayeProject.repository;

import com.example.DoodmonSarmayeProject.entities.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
}
