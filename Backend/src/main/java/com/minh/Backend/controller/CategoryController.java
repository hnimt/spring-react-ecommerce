package com.minh.Backend.controller;

import com.minh.Backend.dto.category.CategoryDTO;
import com.minh.Backend.entity.Category;
import com.minh.Backend.service.impl.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired private CategoryService categoryService;
    @Autowired private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAll() {
        List<CategoryDTO> result = categoryService.findAll().stream().map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        CategoryDTO result = modelMapper.map(categoryService.save(category), CategoryDTO.class);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO){
        Category category = modelMapper.map(categoryDTO, Category.class);
        CategoryDTO result = modelMapper.map(categoryService.updateCategory(id, category), CategoryDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id) {
        categoryService.remove(id);
        return new ResponseEntity(Map.of("message", "Deleted successfully") ,HttpStatus.OK);
    }
}
