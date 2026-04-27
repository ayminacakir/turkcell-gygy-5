package com.turkcell.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.turkcell.library.dto.CategoryResponse;
import com.turkcell.library.dto.CreateCategoryRequest;
import com.turkcell.library.entity.Category;
import com.turkcell.library.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryResponse create(CreateCategoryRequest request) {

        Category category = new Category();
        category.setCategoryName(request.getCategoryName());
        Category savedCategory = categoryRepository.save(category);

        CategoryResponse response = new CategoryResponse();
        response.setId(savedCategory.getId());
        response.setCategoryName(savedCategory.getCategoryName());

        return response;
    }

    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> {
            CategoryResponse response = new CategoryResponse();
            response.setId(category.getId());
            response.setCategoryName(category.getCategoryName());
            return response;
        }).toList();
    }

}
