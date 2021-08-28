package com.minh.Backend.service.impl;

import com.minh.Backend.constant.Constant;
import com.minh.Backend.entity.Category;
import com.minh.Backend.entity.Product;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.repository.ProductRepository;
import com.minh.Backend.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class ProductService implements IProductService {
    @Autowired private ProductRepository productRepository;
    @Autowired private CategoryService categoryService;
    @Autowired private ProductImageService productImageService;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find product id: " + id));
    }
    @Override
    public Product save(Product product) { return productRepository.save(product); }
    @Override
    public void remove(Integer id) { productRepository.deleteById(id); }

    public Product createProduct(Product product, int catId, List<MultipartFile> images) {
        Category category = categoryService.findById(catId);
        product.setCategory(category);
        productRepository.save(product);
        productImageService.uploadImages(product, images);
        return product;
    }

    public Product updateProduct(Product product, Integer id) {
        Product updatedProduct = findById(id);
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setStock(product.getStock());
        updatedProduct.setCategory(product.getCategory());
        return save(updatedProduct);
    }

    public List<Product> findByName(String searchName, Integer page) {
        return productRepository.findBySearchName(searchName, PageRequest.of(page, Constant.PAGE_SIZE));
    }

    public List<Product> findByCategoryOrderByName(Integer catId, String ordNameType, Integer page) {
        if (ordNameType.equalsIgnoreCase("asc"))
            return productRepository.findByCategoryOrderByNameAsc(catId, PageRequest.of(page, Constant.PAGE_SIZE));
        return productRepository.findByCategoryOrderByNameDesc(catId, PageRequest.of(page, Constant.PAGE_SIZE));
    }

    public List<Product> findByCatByPrice(Integer catId, Double minPrice, Double maxPrice, Integer page) {
        return productRepository.findByCatByPrice(catId, minPrice, maxPrice, PageRequest.of(page, Constant.PAGE_SIZE));
    }

    public List<Product> findAllByCatByOrderPrice(Integer catId, String ordPriceType, Integer page) {
        if (ordPriceType.equalsIgnoreCase("asc")) {
            return productRepository.findByCatOrderByPriceAsc(catId, PageRequest.of(page, Constant.PAGE_SIZE));
        } else {
            return productRepository.findByCatOrderByPriceDesc(catId, PageRequest.of(page, Constant.PAGE_SIZE));
        }
    }

    public List<Product> findByCategory(Integer catId, Integer page) {
        Category category = categoryService.findById(catId);
        return productRepository.findByCategory(category, PageRequest.of(page, Constant.PAGE_SIZE));
    }

    public List<Product> findByPage(Integer page) {
        Page<Product> curPage = productRepository.findAll(PageRequest.of(page, Constant.PAGE_SIZE));
        log.info("Total pages "+curPage.getNumber());
        return curPage.getContent();
    }
}
