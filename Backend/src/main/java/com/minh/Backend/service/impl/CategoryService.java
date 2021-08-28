package com.minh.Backend.service.impl;

import com.minh.Backend.entity.Category;
import com.minh.Backend.exception.ResourceNotFoundException;
import com.minh.Backend.repository.CategoryRepository;
import com.minh.Backend.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Jwt
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired private ModelMapper modelMapper;

    @Override
    public List<Category> findAll() { return categoryRepository.findAll(); }
    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find category id: " + id));}
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public void remove(Integer id) { categoryRepository.deleteById(id); }

    public Category updateCategory(int id, Category category) throws ResourceNotFoundException{
        Category curCategory = findById(id);
        category.setId(curCategory.getId());
        return save(category);
    }

}
