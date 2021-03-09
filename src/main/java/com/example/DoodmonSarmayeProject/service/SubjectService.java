package com.example.DoodmonSarmayeProject.service;

import com.example.DoodmonSarmayeProject.entities.Subject;
import com.example.DoodmonSarmayeProject.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAllSubjects() {
       return this.subjectRepository.findAll();
    }

    public void saveSubject(Subject subject) {
        this.subjectRepository.save(subject);
    }

    public void deleteSubjectById(Long id) {
        this.subjectRepository.deleteById(id);
    }
}
