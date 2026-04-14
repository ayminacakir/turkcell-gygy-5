package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.dto.ProductCreatedResponse;
import com.turkcell.spring_starter.dto.ProductForCreateDto;
import com.turkcell.spring_starter.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Altın kural: Veritabanı nesneleri requestte de responseda da kullanılamaz.
@RestController // Uygulamada gerektiğinde controlleri newleyebilmek için spring'e haber
                // veririz. Spring, controlleri newler ve onun üzerinden istekleri karşılar.
@RequestMapping("/api/product")
public class ProductController {

    // private final ProductServiceImpl prouctServiceImple = new ProductServiceImpl
    // YANLIŞ

    private final ProductServiceImpl productServiceImpl; // Bağımlılık Enjeksiyonu -> Dependency Injection

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("path")
    public ProductCreatedResponse create(@RequestBody ProductForCreateDto productForCreateDto) {

        return this.productServiceImpl.create(productForCreateDto);
    }

}
