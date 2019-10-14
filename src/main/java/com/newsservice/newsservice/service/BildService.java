package com.newsservice.newsservice.service;

import com.newsservice.newsservice.model.Bild;
import com.newsservice.newsservice.repository.BildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Transactional
public class BildService {

    private static String UPLOAD_ROOT = "upload-dir";

    @Autowired
    private BildRepository bildRepository;

    private ResourceLoader resourceLoader;

    public Resource findOneImage(String path, String name) {
        return resourceLoader.getResource("file:" + path + "/" + name);
    }

    public void createBild(MultipartFile file) throws IOException{
        if(!file.isEmpty()){
            Files.copy(file.getInputStream(), Paths.get(UPLOAD_ROOT, file.getOriginalFilename()));
            bildRepository.save(new Bild(file.getOriginalFilename(),file.getContentType(), file.getBytes()));
        }
    }

}
