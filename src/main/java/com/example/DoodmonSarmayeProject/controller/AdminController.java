package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Response;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.service.RequestService;
import com.example.DoodmonSarmayeProject.service.ResponseService;
import com.example.DoodmonSarmayeProject.service.UserService;
import com.example.DoodmonSarmayeProject.user.FrontUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ResponseService responseService;

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

    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("Id") Long id
            , FrontUser frontUser) {

        User user = userService.loadUserByID(id);
        frontUser.setId(user.getId());
        frontUser.setUserName(user.getUsername());
        frontUser.setPassword(user.getPassword());
        frontUser.setMatchingPassword(user.getPassword());
        frontUser.setFirstName(user.getFirstName());
        frontUser.setLastName(user.getLastName());
        frontUser.setNationalCode(user.getNationalCode());
        frontUser.setPhoneNumber(user.getPhoneNumber());
        frontUser.setEmail(user.getEmail());
        frontUser.setRole(user.getRoles());
        frontUser.setEnabled(user.isEnabled());
        return "/admin/update-page";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute FrontUser frontUser,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/admin/update-page";
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
                return "/admin/update-page";
            }
        }


        this.userService.saveUserFromAdminWithRole(frontUser);
        return "redirect:/admin/list";
    }

    @GetMapping("/listRequestsInvestor")
    public String listOfRequestForInvestor(@RequestParam("Id") Long id, Model model) {
        List<Request> requests = this.requestService.getAllRequestByUser(userService.loadUserByID(id));
        model.addAttribute("requests", requests);
        return "/admin/listRequestForAnInvestor-page";
    }

    @GetMapping("/findRequest")
    public String detailOfRequest(@RequestParam("Id") Long id, Model model) {
        Request request = this.requestService.getRequestById(id);
        Response response = this.responseService.getResponseByRequest(request);
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        return "/admin/detailRequest-page";
    }
}
