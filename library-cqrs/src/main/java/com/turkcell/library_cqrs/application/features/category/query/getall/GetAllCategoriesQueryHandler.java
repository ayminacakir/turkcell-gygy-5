package com.turkcell.library_cqrs.application.features.category.query.getall;

import java.util.List;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.persistence.repositories.CategoryRepository;

@Component
public class GetAllCategoriesQueryHandler
        implements QueryHandler<GetAllCategoriesQuery, List<ListCategoryResponse>> {

    private final CategoryRepository categoryRepository;

    public GetAllCategoriesQueryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ListCategoryResponse> handle(GetAllCategoriesQuery query) {

        return categoryRepository.findAll()
                .stream()
                .map(category -> new ListCategoryResponse(category.getId(), category.getCategoryName()))
                .toList();
    }
}