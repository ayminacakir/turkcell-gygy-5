package com.turkcell.library_cqrs.application.features.category.command.create;

import java.util.UUID;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;

// Bu command yazar oluşturma isteğini temsil eder.
// Kullanıcıdan firstname, lastname ve biography gelir.
// İşlem başarılı olursa geriye UUID döner.
public record CreateAuthorCommand(String firstname, String lastname, String biography) implements Command<UUID> {
}
