package com.example.DoodmonSarmayeProject.service;

import com.example.DoodmonSarmayeProject.entities.DBFile;
import com.example.DoodmonSarmayeProject.entities.Request;
import com.example.DoodmonSarmayeProject.entities.Status;
import com.example.DoodmonSarmayeProject.entities.User;
import com.example.DoodmonSarmayeProject.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public Request saveRequest(Request request) {
        request.setDate(LocalDateTime.now());
        request.setStatus(Status.STATUS_CONSIDERATION);
        return requestRepository.save(request);
    }

    public List<Request> getAllRequestByUser(User user) {
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

    public List<Request> getAllRequest() {
        return this.requestRepository.findByOrderByDateDesc();
    }

    public Request findFirstByUserIDOrderByDate(User user) {
        return this.requestRepository.findFirstByUserIdOrderByDateDesc(user.getId());
    }

    public void addFileToRequest(DBFile dbFile, Long id) throws IOException {

        Request request = getRequestById(id);
        Set<DBFile> dbFiles = request.getFile();
        dbFiles.add(dbFile);
        request.setFile(dbFiles);
        this.requestRepository.save(request);
    }

    public void changeStatus(Long id, Status status) {
        Request request = getRequestById(id);
        request.setStatus(status);
        this.requestRepository.save(request);
    }
}
