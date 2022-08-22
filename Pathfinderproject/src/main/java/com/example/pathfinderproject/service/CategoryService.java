package com.example.pathfinderproject.service;

import com.example.pathfinderproject.model.entity.Category;
import com.example.pathfinderproject.model.entity.enums.CategoryNameEnum;

public interface CategoryService {
    Category findCategoryByName(CategoryNameEnum categoryNameEnum);
}
