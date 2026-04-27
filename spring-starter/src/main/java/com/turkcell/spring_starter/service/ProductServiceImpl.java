package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateProductRequest;
import com.turkcell.spring_starter.dto.CreatedProductResponse;
import com.turkcell.spring_starter.dto.ListProductResponse;
import com.turkcell.spring_starter.dto.UpdateProductRequest;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;
import com.turkcell.spring_starter.repository.TagRepository;

@Service
public class ProductServiceImpl {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public ProductServiceImpl(ProductRepository productRepository,
            CategoryRepository categoryRepository,
            TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public CreatedProductResponse create(CreateProductRequest request) {

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı."));

        product.setCategory(category);

        Set<Tag> tags = new HashSet<>();

        if (request.getTagIds() != null) {
            tags = new HashSet<>(tagRepository.findAllById(request.getTagIds()));
        }

        product.setTags(tags);

        product = productRepository.save(product);

        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryName(product.getCategory().getName());

        response.setTagNames(
                product.getTags()
                        .stream()
                        .map(tag -> tag.getName())
                        .collect(Collectors.toList()));

        return response;
    }

    public List<ListProductResponse> getAll() {

        List<Product> products = productRepository.findAll();

        List<ListProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            ListProductResponse response = mapToListProductResponse(product);
            responseList.add(response);
        }

        return responseList;
    }

    public ListProductResponse getById(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı."));

        return mapToListProductResponse(product);
    }

    public ListProductResponse update(UUID id, UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı."));

        product.setName(request.getName());
        product.setDescription(request.getDescription());

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı."));

        product.setCategory(category);

        Set<Tag> tags = new HashSet<>();

        if (request.getTagIds() != null) {
            tags = new HashSet<>(tagRepository.findAllById(request.getTagIds()));
        }

        product.setTags(tags);

        product = productRepository.save(product);

        return mapToListProductResponse(product);
    }

    public void delete(UUID id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı."));

        productRepository.delete(product);
    }

    // Ortak mapping metodu
    private ListProductResponse mapToListProductResponse(Product product) {

        ListProductResponse response = new ListProductResponse();

        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryName(product.getCategory().getName());

        response.setTagNames(
                product.getTags()
                        .stream()
                        .map(tag -> tag.getName())
                        .collect(Collectors.toList()));

        return response;
    }
}