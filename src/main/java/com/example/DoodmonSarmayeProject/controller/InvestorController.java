package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Subject;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.service.RequestService;
import com.example.DoodmonSarmayeProject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/investor")
@SessionAttributes("log-user")
public class InvestorController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/showRequestForm")
    public String showRequestPage(@ModelAttribute("log-user") User user, Request request, Model model) {
        request.setUser(user);
        List<Subject> subjects = this.subjectService.findAllSubjects();
        model.addAttribute("subjects", subjects);
        return "/investor/createRequest-page";
    }

    @PostMapping("/createRequest")
    public String createRequest(@Valid @ModelAttribute("request") Request request,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/investor/createRequest-page";
        }

        requestService.saveRequest(request);
        return "home";
    }

    @GetMapping("/listRequests")
    public String showListOfRequest(@ModelAttribute("log-user") User user, Model model) {
        List<Request> requests = requestService.getAllRequestByUser(user);
        model.addAttribute("requests", requests);
        return "/investor/listRequest_page";
    }

    @GetMapping("/findRequest")
    public String detailOfRequest(@RequestParam("Id") Long id, Model model) {
        Request request = this.requestService.getRequestById(id);
        List<Subject> subjects = this.subjectService.findAllSubjects();
        model.addAttribute("subjects", subjects);
        model.addAttribute("request", request);
        return "/investor/detailRequest-page";
    }

    @PostMapping("/updateRequest")
    public String updateRequest(@Valid @ModelAttribute("Request") Request request,
                                BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/investor/detailRequest-page";
        }

        this.requestService.updateRequest(request);
        return "redirect:/investor/listRequests";
    }
}
