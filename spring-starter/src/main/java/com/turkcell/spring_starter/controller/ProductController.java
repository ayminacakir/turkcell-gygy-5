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

import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.model.Product;
import org.springframework.web.bind.annotation.PutMapping;

// Altın kural: Veritabanı nesneleri requestte de responseda da kullanılamaz.
@RestController
@RequestMapping("/api/product")
public class ProductController {
    // InMemory çalış(değişkenlerle çünkü değişkenler ramde tutulur.)
    private List<Product> productList = new ArrayList<>();

    @GetMapping()
    public List<Product> getAllProducts() {
        return productList; // Veritabanındaki Product nesnelerini listele.
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) { // detay sayfası için id'ye göre ürün getirmirirz daha
                                                          // detaylıdır burası
        return productList.stream().filter(i -> i.getId() == id).findFirst().orElse(null);
    }

    // Request-Response Pattern =>
    // Her istek cevap kendine ait bir modele sahip olmak zorunda.Birebir başka bir
    // istek-cevap çiftiyle aynı içeriğe sahip olabilirler ama birbirlerinin aynısı
    // olamazlar.
    @PostMapping
    public ProductCreatedResponse createProduct(@RequestBody ProductForCreateDto productDto) {
        // Sen dışarodan ProductForCreateDto alıyorsun ama senin veritabanında Product
        // nesnesi var. O yüzden ProductForCreateDto'yu Product'a çevirmen gerekiyor.
        if (productDto.getPrice() < 0)
            throw new RuntimeException("Para 0'dan küçük olamaz.");

        // Transfer -> Manuel Mappıng
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setId(productList.size() + 1); // Id'yi kendimiz atıyoruz çünkü veritabanı yok.

        productList.add(product);
        // Veritabanına product nesnesini ekle..

        // ProductCreatedResponse => Dışarıya dönmek istediğimiz cevaba ait model.
        // Entity Nesnesi -> Dto
        ProductCreatedResponse response = new ProductCreatedResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        return response; // Dışarıya ProductCreatedResponse dönüyoruz çünkü biz dışarıya Product
                         // nesnesini
        // dönmek istemiyoruz.Çünkü Product nesnesi veritabanı nesnesidir ve biz
        // veritabanı nesnelerini dışarıya dönmek istemiyoruz.

        // Ben controller olarak iş kodu çalıştıramam, ama bunu yapmam gerekli..
        // İş kodunu çalıştıracak olan yapıya BAĞIMLIYIM.
        // Bağımlılık Enjeksiyonu -> Dependency Injection

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
        productList.removeIf(i -> i.getId() == id); // Ürünü veritabanından siler
    }
}
