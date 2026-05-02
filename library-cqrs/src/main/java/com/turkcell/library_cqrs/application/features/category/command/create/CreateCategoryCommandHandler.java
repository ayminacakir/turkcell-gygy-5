package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.domain.entities.Category;
import com.turkcell.library_cqrs.persistence.repositories.CategoryRepository;

@Component
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, UUID> {
    private final CategoryRepository categoryRepository;

    public CreateCategoryCommandHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public UUID handle(CreateCategoryCommand command) {// handle etmek demek, komutu işlemek demektir. Yani, bu komut
                                                       // geldiğinde ne yapacağımızı burada tanımlıyoruz.
        if (categoryRepository.existsByCategoryName(command.categoryName())) {
            throw new RuntimeException("Category already exists");
        }

        Category category = new Category();
        category.setCategoryName(command.categoryName());

        Category savedCategory = categoryRepository.save(category);
        // Kategori oluşturma işlemi burada yapılır.
        // Örneğin, veritabanına kaydedilir ve yeni kategorinin ID'si döndürülür.
        return savedCategory.getId(); //
    }

}

/*
 * MANTIK AKIŞI
 * Postman
 * |
 * v
 * CategoriesController.create()
 * |
 * v
 * mediator.send(CreateCategoryCommand)
 * |
 * v
 * SpringMediator doğru handler'ı bulur
 * |
 * v
 * CreateCategoryCommandHandler.handle()
 * |
 * v
 * CategoryRepository.save()
 * |
 * v
 * Databas
 */
