package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.service.UserService;
import com.example.DoodmonSarmayeProject.user.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/adminPage")
    public String adminPage() {
        return "/admin/adminPage";
    }

    @GetMapping("/signup")
    public String showSignupPage(FrontUser frontUser) {
        return "signup-page";
    }

    @GetMapping("/list")
    public String getAllUsers(Model model) {

        List<User> userList = userService.findAll();
        model.addAttribute("users", userList);

        return "/admin/list-users";
    }
}
