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
                          BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "signup-page";
        }

            UserDetails existingUser = registerService.loadUserByUsername(frontUser.getUserName());
            UserDetails existingNationalCode = registerService.loadUserByNationalCode(frontUser.getNationalCode());
            UserDetails existingPhoneNumber = registerService.loadUserByPhoneNumber(frontUser.getPhoneNumber());
            if (existingUser != null || existingNationalCode != null || existingPhoneNumber != null) {

                if (existingUser != null) {
                    model.addAttribute("User", new FrontUser());
                    model.addAttribute("usernameError", "این نام کاربری قبلا ثبت نام کرده");
                }

                if (existingNationalCode != null) {
                    model.addAttribute("nationalCodeError", "این کدملی در سامانه موجود است");
                }

                if (existingPhoneNumber != null) {
                    model.addAttribute("phoneNumberError", "این شماره موبایل تکراری می باشد");
                }

                return "signup-page";
            }


        registerService.saveUser(frontUser);
        return "registration-confirmation";
    }
}
