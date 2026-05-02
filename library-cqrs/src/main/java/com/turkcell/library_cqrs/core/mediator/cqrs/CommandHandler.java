package com.turkcell.library_cqrs.core.mediator.cqrs;

// C: Hangi command'ı işleyeceğini söyler.
// R: İşlem sonucunda ne döneceğini söyler.
//
// Örnek:
// CommandHandler<CreateCategoryCommand, UUID>
// demek:
// "Ben CreateCategoryCommand işlerim ve UUID dönerim."
public interface CommandHandler<C extends Command<R>, R> {

    R handle(C command);
}