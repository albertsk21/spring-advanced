package com.example.pathfinderproject.service.impl;

import com.example.pathfinderproject.model.entity.Category;
import com.example.pathfinderproject.model.entity.enums.CategoryNameEnum;
import com.example.pathfinderproject.repository.CategoryRepository;
import com.example.pathfinderproject.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findCategoryByName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository.findByName(categoryNameEnum).orElse(null);
    }
}
