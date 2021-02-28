package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.service.RegisterService;
import com.example.DoodmonSarmayeProject.service.UserService;
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
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/signup")
    public String signupPage(FrontUser frontUser) {
        return "signup-page";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute("frontUser") FrontUser frontUser,
                          BindingResult result, Model model){

        if (result.hasErrors()) {
            return "signup-page";
        }

        UserDetails existing = registerService.loadUserByUsername(frontUser.getUserName());
        if (existing != null) {
            model.addAttribute("User", new FrontUser());
            model.addAttribute("registrationError", "این نام کاربری قبلا ثبت نام کرده.");
            return "signup-page";
        }

        registerService.saveUser(frontUser);
        return "registration-confirmation";
    }
}
