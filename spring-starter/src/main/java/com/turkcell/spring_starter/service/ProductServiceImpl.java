package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.model.Product;

//Service => İş katmanını temsil eder. Veritabanı işlemleri, iş kuralları gibi işlemler burada yapılır.
//Implementation 
//ProductService => Interface
// ProductServiceImpl => Class

@Service // IOC ye bu tütü eklediniğinde bu classı newleyebilirsin demektir. Spring, bu
         // classı newler ve onun üzerinden istekleri karşılar.
public class ProductServiceImpl {
    private final List<Product> productsInMemory = new ArrayList<>();

    public ProductCreatedResponse create(ProductForCreateDto productForCreateDto) {

        // Aynı isimde 2 ürün olamaz

        // Business Rule

        checkIfProductWithSameNameExist(productForCreateDto.getName());

        Product product = new Product();
        product.setId(new Random().nextInt(100)); // Veritabanında id otomatik artar, biz burada random atıyoruz.
        product.setName(productForCreateDto.getName());
        product.setPrice(productForCreateDto.getPrice());

        productsInMemory.add(product);

        ProductCreatedResponse response = new ProductCreatedResponse(); // neden response oluşturduk? Çünkü controllerda
                                                                        // ne dönüceğini belirtmemiz gerekiyor.
                                                                        // Controllerda product dönebiliriz ama biz
                                                                        // productCreatedResponse dönmek istiyoruz. O
                                                                        // yüzden response oluşturduk.
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response;

    public void update() {
        // Aynı iş kuralı..
        checkIfProductWithSameNameExist("");
    }

    // İş kuralları -> Kendine has bir classta bulunmalıdır. ->
    // ProductBusinessRules.java
    private void checkIfProductWithSameNameExist(String name) {
        Product productWithSameName = productsInMemory
                .stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (productWithSameName != null)
            throw new RuntimeException("Aynı isimde 2 ürün eklenemez");
    }
}

// NOT: Controllerı service i çağırıcak ve service ne dönüyorsa onu return
// edicek. Service de repository i çağıracak ve repository ne dönüyorsa onu
// return edecek.

// IProductRepository -> ProductRepository
// ProductRepository <Product> -> Spring auto-generated.

// Spring IoC Nedir? Bean,Service nedir?