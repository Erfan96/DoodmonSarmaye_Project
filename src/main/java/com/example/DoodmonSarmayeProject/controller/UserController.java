package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.service.UserService;
import com.example.DoodmonSarmayeProject.user.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@SessionAttributes("log-user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@ModelAttribute("log-user") User user
                                , FrontUser frontUser) {

        frontUser.setId(user.getId());
        frontUser.setUserName(user.getUsername());
        frontUser.setFirstName(user.getFirstName());
        frontUser.setLastName(user.getLastName());
        frontUser.setNationalCode(user.getNationalCode());
        frontUser.setPhoneNumber(user.getPhoneNumber());
        frontUser.setEmail(user.getEmail());
        return "update-page";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("frontUser") FrontUser frontUser,
                         BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "update-page";
        }

        User user = userService.loadUserByID(frontUser.getId());

            if (!frontUser.getUserName().equals(user.getUsername()) ||
                !frontUser.getNationalCode().equals(user.getNationalCode()) ||
                !frontUser.getPhoneNumber().equals(user.getPhoneNumber())) {

                boolean refresh = false;

                if (!frontUser.getUserName().equals(user.getUsername())) {
                    UserDetails existingUsername = userService.loadUserByUsername(frontUser.getUserName());
                    if (existingUsername != null) {

                        model.addAttribute("User", new FrontUser());
                        model.addAttribute("usernameError", "این نام کاربری وجود دارد");
                        refresh = true;
                    }
                }


                if (!frontUser.getNationalCode().equals(user.getNationalCode())) {
                    UserDetails existingNationalCode = userService.loadUserByNationalCode(frontUser.getNationalCode());
                    if (existingNationalCode != null) {
                        model.addAttribute("nationalCodeError", "این کدملی وجود دارد");
                        refresh = true;
                    }
                }

                if (!frontUser.getPhoneNumber().equals(user.getPhoneNumber())) {
                    UserDetails existingPhoneNumber = userService.loadUserByPhoneNumber(frontUser.getPhoneNumber());
                    if (existingPhoneNumber != null) {
                        model.addAttribute("phoneNumberError", "این شماره موبایل قبلا ذخیره شده است");
                        refresh = true;
                    }
                }

                if (refresh) {
                    return "update-page";
                }
            }


        this.userService.saveUser(frontUser);
        return "update-confirmation";
    }

    @PostMapping("/disable")
    public String unActiveAccount(@RequestParam("Id") Long id) {
        userService.disableAccountById(id);
        return "redirect:/logout";
    }
}
