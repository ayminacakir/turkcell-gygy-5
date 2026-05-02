package com.turkcell.library_cqrs.core.mediator.cqrs;

// Query veri okuma işlemlerini temsil eder.
// Örnek: kategorileri getir, id'ye göre kitap getir, öğrencileri listele.
//
// R dönüş tipidir.
// Örneğin GetAllCategoriesQuery implements Query<List<ListCategoryResponse>>
// ise bu query çalışınca liste döner.
public interface Query<R> {
}