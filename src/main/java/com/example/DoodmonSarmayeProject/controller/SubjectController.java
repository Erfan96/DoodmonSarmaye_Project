package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.Subject;
import com.example.DoodmonSarmayeProject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller

public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public String listOfSubjects(Subject subject, Model model) {
        List<Subject> allSubjects = this.subjectService.findAllSubjects();
        model.addAttribute("allSubjects", allSubjects);
        return "/subject/subject-page";
    }

    @PostMapping("/addSubject")
    public String addSubject(@Valid @ModelAttribute("subject") Subject subject,
                             BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/subject/subject-page";
        }

        this.subjectService.saveSubject(subject);
        return "redirect:/subjects";
    }

    @PostMapping("/removeSubject")
    public String removeSubjectById(@RequestParam("Id") Long id) {
        this.subjectService.deleteSubjectById(id);
        return "redirect:/subjects";
    }
}
