package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Response;
import com.example.DoodmonSarmayeProject.entities.Status;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.service.RequestService;
import com.example.DoodmonSarmayeProject.service.ResponseService;
import com.example.DoodmonSarmayeProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/responsible")
public class ResponsibleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ResponseService responseService;

    @GetMapping("/listRequests")
    public String showListOfRequest(Model model) {
        List<Request> requests = requestService.getAllRequest();
        model.addAttribute("requests", requests);
        return "/responsible/listRequest-page";
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

    @GetMapping("/findRequest")
    public String detailOfRequest(@RequestParam("Id") Long id, Model model) {
        Request request = this.requestService.getRequestById(id);
        Response response = this.responseService.getResponseByRequest(request);
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        return "/responsible/detailRequest-page";
    }

    @PostMapping("/sendResponse")
    public String sendResponse(@Valid @ModelAttribute("response")Response response,
                               BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "/responsible/detailRequest-page";
        }

        this.responseService.saveResponse(response);
        this.requestService.changeStatus(response.getRequest().getId(), Status.STATUS_ANSWERED);
        return "redirect:/responsible/listRequests";
    }
}
