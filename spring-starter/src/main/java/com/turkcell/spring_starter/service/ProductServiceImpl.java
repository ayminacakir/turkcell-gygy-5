package com.turkcell.spring_starter.service;

//Service => İş katmanını temsil eder. Veritabanı işlemleri, iş kuralları gibi işlemler burada yapılır.
//Implementation 
//ProductService => Interface
// ProductServiceImpl => Class
public class ProductServiceImpl {
    // Controllerın size akrıcağı işlemleri burada yaparsınız.
    // iş kodu
}

// NOT: Controllerı service i çağırıcak ve service ne dönüyorsa onu return
// edicek. Service de repository i çağıracak ve repository ne dönüyorsa onu
// return edecek.

// IProductRepository -> ProductRepository
// ProductRepository <Product> -> Spring auto-generated.

// Spring IoC Nedir? Bean,Service nedir?