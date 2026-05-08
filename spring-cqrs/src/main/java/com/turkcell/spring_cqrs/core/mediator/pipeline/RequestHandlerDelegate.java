package com.turkcell.spring_cqrs.core.mediator.pipeline;

@FunctionalInterface
public interface RequestHandlerDelegate<R> {// Zincirdeki sonraki işi çalıştıracak fonksiyonel arayüz.
    R invoke();
}
// bu ne demek? Zincirdeki sonraki işi çalıştıracak fonksiyonel arayüzün tek
// metodu. Yani, bir pipeline davranışı zincirinde, her bir davranışın sonunda
// bu metodu çağırarak zincirin devam etmesini sağlar. Bu, zincirdeki sonraki
// davranışın veya nihai olarak komut/query handler'ın çalıştırılmasını sağlar.
