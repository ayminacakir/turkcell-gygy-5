package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.swing.text.html.parser.Entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryResponse;
import com.turkcell.spring_starter.dto.UpdateCategoryRequest;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.repository.CategoryRepository;

import jakarta.persistence.EntityManager;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    public CategoryServiceImpl(CategoryRepository categoryRepository, EntityManager entityManager) {
        this.categoryRepository = categoryRepository;
        this.entityManager = entityManager;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        // Veritabanında insert-update çalıştır.
        // entity id'e sahipse update
        // entity id'si null ise insert

        // Mapleme(Dto -> Entity, Entity -> Dto) işlemi yapacağız. // mapstruct
        // kullanacağız.
        Category category = new Category();
        category.setName(createCategoryRequest.getName());

        category = this.categoryRepository.save(category); // ekledikten sonraki halini al

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }

    public List<ListCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        // liste halinde mapleme işlemi yapacağız. // mapleme işlemi
        // için mapstruct kullanacağız.
        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }

    public ListCategoryResponse getById(UUID id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı."));

        ListCategoryResponse response = new ListCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }

    public ListCategoryResponse update(UUID id, UpdateCategoryRequest updateCategoryRequest) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı."));

        category.setName(updateCategoryRequest.getName());

        Category updatedCategory = categoryRepository.save(category);

        ListCategoryResponse response = new ListCategoryResponse();
        response.setId(updatedCategory.getId());
        response.setName(updatedCategory.getName());

        return response;
    }

    public void delete(UUID id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı."));

        categoryRepository.delete(category);
    }

    public List<ListCategoryResponse> search(String query) {
        // Set<Category> categories = categoryRepository.findByNameLike("%" + query +
        // "%");

        // Set<Category> categories = categoryRepository.findByNameLike("%" + query +
        // "%");

        // String Concatination -> KESİNLİKLE YASAK
        // String jpql = "Select c from Category c Where c.name LIKE '%" + query + "%'";

        String jpql = "Select c from Category c Where c.name like :query";

        List<Category> categories = entityManager
                .createQuery(jpql, Category.class)
                .setParameter("query", "%" + query + "%")
                .getResultList();

        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }

}