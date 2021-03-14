package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Subject;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.service.RequestService;
import com.example.DoodmonSarmayeProject.service.SubjectService;
import com.example.DoodmonSarmayeProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/responsible")
public class ResponsibleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/listRequests")
    public String showListOfRequest(Model model) {
        List<Request> requests = requestService.getAllRequest();
        model.addAttribute("requests", requests);
        return "/responsible/listRequest-page";
    }

    @GetMapping("/findRequest")
    public String detailOfRequest(@RequestParam("Id") Long id, Model model) {
        Request request = this.requestService.getRequestById(id);
        List<Subject> subjects = this.subjectService.findAllSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("request", request);
        return "/responsible/detailRequest-page";
    }

    @GetMapping("/information")
    public String informationOfInvestor(@RequestParam("Id") Long id, Model model) {
        User user = this.userService.loadUserByID(id);
        model.addAttribute("user", user);
        return "/responsible/informationOfInvestor-page";
    }

    @GetMapping("/listRequestsInvestor")
    public String listOfRequestForInvestor(@RequestParam("Id") Long id, Model model) {
        List<Request> requests = this.requestService.getAllRequestByUser(userService.loadUserByID(id));
        model.addAttribute("requests", requests);
        return "/responsible/listRequestForAnInvestor-page";
    }
}
