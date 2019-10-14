package com.newsservice.newsservice.controller;

import com.newsservice.newsservice.model.Bild;
import com.newsservice.newsservice.model.News;
import com.newsservice.newsservice.service.BildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


import static com.newsservice.newsservice.Constants.API_PREFIX;

@RestController
@RequestMapping(API_PREFIX + "/news/{newsId}/bild")
public class BildController {

    @Autowired
    private BildService bildService;

    @RequestMapping(method = RequestMethod.GET, value="{filename:.+}/raw")
    @ResponseBody
//    public ResponseEntity<?> oneRawBild(@PathVariable String filename) {
        public ResponseEntity<?> oneRawBild(@PathVariable News news, @PathVariable Bild bild) {
            String path = news.getPictureLink();
            String name = bild.getPictureName();
        try {
            Resource file = bildService.findOneImage(path, name);
            return ResponseEntity.ok()
                    .contentLength(file.contentLength())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(file.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity.badRequest()
                    .body("Couldn't find " + name + " => " + e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value="{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> uploadBild(@RequestParam("file")MultipartFile file, HttpRequest request){

        try {
            bildService.createBild(file);
            return ResponseEntity.created(request.getURI().resolve(file.getOriginalFilename() + "/raw"))
                    .body("Successfully upload " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload " + file.getOriginalFilename() + " => " + e.getMessage());
        }
    }
}
