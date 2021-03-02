package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.service.RegisterService;
import com.example.DoodmonSarmayeProject.user.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/adminPage")
    public String adminPage() {
        return "/admin/adminPage.html";
    }

    @GetMapping("/signup")
    public String showSignupPage(FrontUser frontUser) {
        return "signup-page";
    }

//    @PostMapping("/add")
//    public String addNewUser(@Valid @ModelAttribute("frontUser") FrontUser frontUser
//                            , BindingResult result, Model model) {
//
//        if (result.hasErrors()) {
//            return "signup-page";
//        }
//
//        UserDetails existing = registerService.loadUserByUsername(frontUser.getUserName());
//        if (existing != null) {
//            return "signup-page";
//        }
//
//        registerService.saveUser(frontUser);
//        return "/admin/adminPage.html";
//    }
}
