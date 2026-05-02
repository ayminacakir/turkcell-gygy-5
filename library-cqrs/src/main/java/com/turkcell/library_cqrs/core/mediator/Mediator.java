package com.turkcell.library_cqrs.core.mediator;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs.core.mediator.cqrs.Query;

// Controller sadece bu interface'i bilir.
// Controller handler'ı direkt çağırmaz.
// Controller mediator.send(...) der.
public interface Mediator {

    <R> R send(Command<R> command);

    <R> R send(Query<R> query);
}