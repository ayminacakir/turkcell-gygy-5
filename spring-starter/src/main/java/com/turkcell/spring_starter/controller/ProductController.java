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
    //InMemory çalış(değişkenlerle çünkü değişkenler ramde tutulur.)
    private List<Product> products = new ArrayList<>();


    @GetMapping()
    public List<Product> getAllProducts() {
        return null; // Ürünleri veritabanından çekip döndürecek
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) { // detay sayfası için id'ye göre ürün getirmirirz daha
                                                          // detaylıdır burası
        return null;
    }

    @PostMapping()
    public void createProduct(@RequestBody Product product) {
    }

    @PutMapping("path/{id}")
    public void updateProduct(@PathVariable Product product) {

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
    }
}
