package com.turkcell.spring_starter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.spring_starter.model.Product;

@RestController
@RequestMapping("/api/product") // localhost:8080/api/product -> ProductController
// Bu class bir rest controllerdır, içini uygulama başladığında tara,
// http->function
// tanımlarını al
public class ProductController {
    // Kullanıcı ne zaman /api/product alanına istek atarsa -> cevap bu fonksiyondan
    // dönen cevap olsun
    // /api/product -> sayHi(); matchle
    // HTTP Method -> GET,POST,PUT,DELETE,PATCH...
    // slash demek bu yolun üzerine hiç bişey yazmıyorum demek. ne zaman
    // api/producta bir get isteği atılırsa sen onu bu methoda yönlendir diyo
    @GetMapping("") // controllerın uzantısı + getin uzantısı -> /api/product
    public String sayHi(String name, int age) { // QueryString “Bu parametreler URL’den gelecek”
        return "Hi " + name + " yaşınız: " + age;
    }

    // Controllerın uzantısı + get'in uzantısı -> /api/product/hello/{name}
    @GetMapping("hello/{name}/{age}")
    public String sayHello(@PathVariable String name, @PathVariable int age) {
        return "Hello " + name + " yaşınız: " + age;
    }

    @PostMapping("")
    public String add(@RequestBody Product product) { // bodyden gelen jsonı producta aktardık. Json-> java objesine
        return product.getId() + " id'li " + product.getName() + " ürününü eklediniz. Fiyatı: " + product.getPrice();
    }
}
