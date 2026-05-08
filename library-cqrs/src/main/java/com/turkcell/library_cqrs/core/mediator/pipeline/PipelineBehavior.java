package com.turkcell.library_cqrs.core.mediator.pipeline;

public interface PipelineBehavior {// Handler'dan önce veya sonra çalışabilecek ortak ara katmandır.

    <R> R handle(Object request, RequestHandlerDelegate<R> next);

    default boolean supports(Object request) {// örneğin LoginCommand loglanmasın dersek burada özel kontrol
                                              // yapabiliriz.
        return true;
    }
}

/*
 * request:Gelen command veya query nesnesidir.
 * next:Zincirdeki sonraki adımı temsil eder.Bu başka bir behavior olabilir veya
 * en sondaki gerçek handler olabilir.
 * R: Handler'ın döndürdüğü response tipidir.
 */