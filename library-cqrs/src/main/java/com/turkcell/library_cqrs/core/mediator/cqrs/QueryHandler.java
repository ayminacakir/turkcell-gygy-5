package com.turkcell.library_cqrs.core.mediator.cqrs;

// Q: Hangi query'yi işleyeceğini söyler.
// R: İşlem sonucunda ne döneceğini söyler.
public interface QueryHandler<Q extends Query<R>, R> {

    R handle(Q query);
}