package com.turkcell.spring_cqrs.core.mediator.pipeline;

public interface PipelineBehavior {
    <R> R handle(Object request, RequestHandlerDelegate<R> next);

    default boolean supports(Object request) {
        return true;
    }
}
// PipelineBehavior, komut veya sorgu işlenmeden önce veya sonra çalışacak
// davranışları tanımlamak için kullanılan bir arayüzdür. Bu, örneğin, loglama,
// doğrulama, hata yönetimi gibi çapraz kesen endişeleri (cross-cutting
// concerns) uygulamak için kullanılabilir. Her bir PipelineBehavior, handle
// metodunu implement eder ve bu metodun içinde zincirdeki sonraki davranışı
// çağırmak için RequestHandlerDelegate'yi kullanır.
// supports metodu ise bu
// davranışın hangi tür istekleri desteklediğini belirlemek için
// kullanılabilir.Örneğin, sadece belirli türdeki komutlar veya sorgular
// için geçerli olabilir.