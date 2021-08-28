package com.minh.Backend.controller;

import com.minh.Backend.service.impl.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/upload")
public class UploadController {

//    @Autowired private ProductImageService productImageService;
//
//    @PostMapping
//    public ResponseEntity uploadImage(@RequestPart("subject") String subject,
//                                      @RequestPart("filename") String filename,
//                                      @RequestPart("image") MultipartFile image) {
//        productImageService.uploadImages(subject, filename, image);
//        return new ResponseEntity(HttpStatus.CREATED);
//    }
}
