package com.example.DoodmonSarmayeProject.controller;

import com.example.DoodmonSarmayeProject.entities.*;
import com.example.DoodmonSarmayeProject.service.FileStorageService;
import com.example.DoodmonSarmayeProject.service.RequestService;
import com.example.DoodmonSarmayeProject.service.ResponseService;
import com.example.DoodmonSarmayeProject.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/investor")
@SessionAttributes("log-user")
public class InvestorController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ResponseService responseService;

    @Autowired
    private FileStorageService fileStorageService;

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

        Request req = this.requestService.saveRequest(request);
        model.addAttribute("req", req);
        return "/investor/uploadFile-page";
    }

    @GetMapping("/uploadFilePage")
    public String uploadFilePage(@RequestParam("Id") Long id, Model model) {
        Request request = this.requestService.getRequestById(id);
        model.addAttribute("req", request);
        return "/investor/uploadFile-page";
    }

    @PostMapping("/uploadFile/{id}")
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long id,
                             Model model, RedirectAttributes attributes) throws IOException {

        DBFile dbFile = this.fileStorageService.store(file);
        this.requestService.addFileToRequest(dbFile, id);
        Request request = this.requestService.getRequestById(id);
        model.addAttribute("req", request);

        attributes.addFlashAttribute("message","فایل شما با موفقیت به درخواست پیوست شد" + " " + file.getOriginalFilename());
        return "redirect:/investor/listRequests";
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
        Response response = this.responseService.getResponseByRequest(request);
        model.addAttribute("request", request);
        model.addAttribute("subjects", subjects);
        model.addAttribute("response", response);
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

    @PostMapping("/closeRequest")
    public String closeRequest(@RequestParam("Id")Long id) {
        this.requestService.changeStatus(id, Status.STATUS_CLOSED);
        return "redirect:/investor/listRequests";
    }
}
