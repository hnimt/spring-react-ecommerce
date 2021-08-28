package com.minh.Backend.controller;

import com.minh.Backend.dto.product.ProductCreateDTO;
import com.minh.Backend.dto.product.ProductDTO;
import com.minh.Backend.entity.Product;
import com.minh.Backend.service.impl.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAll () {
        List<ProductDTO> result = productService.findAll()
                .stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        return new ResponseEntity(result, HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity addProduct(@RequestBody ProductCreateDTO productCreateDTO) {
//        Product product = modelMapper.map(productCreateDTO, Product.class);
//        int catId = productCreateDTO.getCategoryId();
//        ProductDTO result = modelMapper.map(productService.createProduct(product, catId), ProductDTO.class);
//        return new ResponseEntity(result, HttpStatus.CREATED);
//    }

    @PostMapping
    public ResponseEntity addProduct (@ModelAttribute ProductCreateDTO productCreateDTO) {
        Product product = new Product(productCreateDTO.getName(), productCreateDTO.getDescription(),
                productCreateDTO.getPrice(), productCreateDTO.getStock());
        int catId = productCreateDTO.getCategoryId();
        List<MultipartFile> images = productCreateDTO.getImages();
        ProductDTO result = modelMapper.map(productService.createProduct(product, catId, images), ProductDTO.class);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Integer id) {
        ProductDTO result = modelMapper.map(productService.findById(id), ProductDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Integer id) {
        Product product = modelMapper.map(productDTO, Product.class);
        ProductDTO result = modelMapper.map(productService.updateProduct(product, id), ProductDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct (@PathVariable Integer id) {
        productService.remove(id);
        return new ResponseEntity(Map.of("message", "Delete successfully"), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity filterProducts(@RequestParam(name = "name", required = false) String searchName,
                                         @RequestParam(name = "catId", required = false) Integer catId,
                                         @RequestParam(name = "sort_name", required = false) String ordNameType,
                                         @RequestParam(name = "min", required = false) Double minPrice,
                                         @RequestParam(name = "max", required = false) Double maxPrice,
                                         @RequestParam(name = "sort_price", required = false) String ordPriceType,
                                         @RequestParam(name = "page", required = false) Integer page) {
        List<Product> products = null;
        if (searchName != null && page != null)
            products = productService.findByName(searchName, page);
        else if (ordNameType != null && catId != null && page != null)
            products = productService.findByCategoryOrderByName(catId, ordNameType, page);
        else if (minPrice != null && maxPrice != null && catId != null && page != null)
            products = productService.findByCatByPrice(catId, minPrice, maxPrice, page);
        else if (ordPriceType != null && catId != null && page != null)
            products = productService.findAllByCatByOrderPrice(catId, ordPriceType, page);
        else if (catId != null && page != null)
            products = productService.findByCategory(catId, page);
        List<ProductDTO> results = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        return new ResponseEntity(results, HttpStatus.OK);
    }
}
