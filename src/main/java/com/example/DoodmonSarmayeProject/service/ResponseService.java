package com.example.DoodmonSarmayeProject.service;

import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Response;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    public Response getResponseByRequest(Request request) {
        Optional<Response> response = this.responseRepository.findByRequest(request);
        if (response.isPresent()) {
            return response.get();
        }

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Response res = new Response();
        res.setRequest(request);
        res.setForUser(request.getUser());
        res.setFromUser(user);
        return res;
    }

    public void saveResponse(Response response) {
        response.setDate(LocalDateTime.now());
        this.responseRepository.save(response);
    }
}
