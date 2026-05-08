package com.turkcell.library_cqrs.core.mediator.pipeline;

@FunctionalInterface
public interface RequestHandlerDelegate<R> {
    R invoke();// Zincirde sıradaki halkayı çalıştırır.
}
/*
 * RequestHandlerDelegate:
 * Pipeline zincirindeki sonraki adımı temsil eder.
 * next.invoke() dediğimizde:Bir sonraki behavior çalışabilir
 * veya En sondaki gerçek handler çalışabilir.
 */