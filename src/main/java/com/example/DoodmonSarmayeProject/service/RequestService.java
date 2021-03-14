package com.example.DoodmonSarmayeProject.service;

import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Status;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void saveRequest(Request request) {
        request.setDate(LocalDateTime.now());
        request.setStatus(Status.STATUS_CONSIDERATION);
        requestRepository.save(request);
    }

    public List<Request> getAllRequest(User user) {
       return this.requestRepository.findAllByUserIdOrderByDateDesc(user.getId());
    }

    public Request getRequestById(Long id) {
        Optional<Request> request = this.requestRepository.findById(id);
        return request.orElse(null);
    }

    public void updateRequest(Request req) {
        Request request = getRequestById(req.getId());
        request.setSubject(req.getSubject());
        request.setDescription(req.getDescription());
        request.setStatus(req.getStatus());
        request.setDate(LocalDateTime.now());
        this.requestRepository.save(request);
    }
}
