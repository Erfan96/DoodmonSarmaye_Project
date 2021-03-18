package com.example.DoodmonSarmayeProject.service;

import com.example.DoodmonSarmayeProject.entities.DBFile;
import com.example.DoodmonSarmayeProject.repository.DBFileRepository;
import exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile store(MultipartFile file) {

        try {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DBFile DBFile = new DBFile(fileName, file.getContentType(), file.getBytes());
        return dbFileRepository.save(DBFile);

        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("متاسفانه فایل شما بارگزاری نشد" + file.getOriginalFilename()
                    + ". لطفا مجددا تلاش کنید");
        }
    }

    public DBFile getFile(String id) {
        return dbFileRepository.findById(id).get();
    }

    public Stream<DBFile> getAllFiles() {
        return dbFileRepository.findAll().stream();
    }
}
