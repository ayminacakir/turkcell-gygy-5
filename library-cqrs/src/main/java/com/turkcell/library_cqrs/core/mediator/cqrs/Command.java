package com.turkcell.library_cqrs.core.mediator.cqrs;

// Command veri değiştiren işlemleri temsil eder.
// Örnek: kategori ekle, kitap ekle, öğrenci sil, kitap ödünç ver.
//
// R dönüş tipidir.
// Örneğin CreateCategoryCommand implements Command<UUID> ise
// bu command çalışınca UUID döner.
public interface Command<R> {
}
