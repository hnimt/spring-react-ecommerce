package com.minh.Backend.service.impl;

import com.minh.Backend.entity.Product;
import com.minh.Backend.entity.image.ProductImage;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.exception.UploadException;
import com.minh.Backend.repository.ProductImageRepository;
import com.minh.Backend.service.IProductImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Slf4j
public class ProductImageService implements IProductImageService {

    @Value("${base.path}")
    private String basePath;
    @Value("${upload.image.path}")
    private String path;

    @Autowired private ProductImageRepository productImageRepository;

    @Override
    public List<ProductImage> findAll() {
        return productImageRepository.findAll();
    }

    @Override
    public ProductImage findById(Integer id) {
        return productImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot found image: " + id));
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        return productImageRepository.save(productImage);
    }

    @Override
    public void remove(Integer id) {
        productImageRepository.deleteById(id);
    }

    @Transactional(rollbackOn = {UploadException.class})
    public void uploadImages(Product product, List<MultipartFile> images) {
        Path currentFolder = Paths.get(basePath+path);
        String subject = "products/product" + product.getId() + "/";
        if(!Files.exists(currentFolder.resolve(subject))) {
            try {
                Files.createDirectories(currentFolder.resolve(subject));
            } catch (IOException e) {
                throw new UploadException(e.getMessage());
            }
        }

        for (MultipartFile image : images) {
            ProductImage productImage = new ProductImage(image.getOriginalFilename(), image.getSize(), product);
            save(productImage);
            int id = productImage.getId();
            String extension = findExtension(image.getOriginalFilename());
            String fileName = path + subject + product.getId() + "_" + id + extension;
            String filNameWithPath = basePath + fileName;
            try {
                InputStream is = image.getInputStream();
                Files.copy(is, Paths.get(filNameWithPath), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new UploadException(e.getMessage());
            }
            productImage.setFileName(fileName);
            save(productImage);
        }
    }

    public String findExtension(String imageName) {
        int index = imageName.lastIndexOf('.');
        return imageName.substring(index);
    }
}
