package com.turkcell.spring_starter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.model.Product;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    // InMemory çalış(değişkenlerle çünkü değişkenler ramde tutulur.)
    private List<Product> productList = new ArrayList<>();

    @GetMapping()
    public List<Product> getAllProducts() {
        return productList; // Ürünleri veritabanından çekip döndürecek
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) { // detay sayfası için id'ye göre ürün getirmirirz daha
                                                          // detaylıdır burası
        return productList.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productList.add(product); // Ürünü veritabanına kaydedecek
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        Product productToUpdate = productList.stream().filter(i -> i.getId() == product.getId()).findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {

    }
}
