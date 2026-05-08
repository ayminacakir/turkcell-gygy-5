package com.turkcell.spring_cqrs.core.logging;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.spring_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.spring_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
@Order(20)
public class LoggingBehavior implements PipelineBehavior {
    // sadece şunları destekler:
    @Override
    public boolean supports(Object request) {
        return !(request instanceof NotLoggableRequest); // eğer ilgili request (command/query) LoggableRequest ile
                                                         // imzalanmış ise yap.
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {
        System.out.println("Loglama çalışıyor..");
        return next.invoke();
    }

}
// LoggingBehavior, PipelineBehavior arayüzünü implement eder ve loglama ile
// ilgili davranışları tanımlar. Bu sınıf, komut veya sorguların işlendiği
// sırada loglama yapmak için kullanılabilir. Örneğin, her bir komut veya
// sorgunun işlendiği zaman, hangi komutun veya sorgunun işlendiği gibi
// bilgileri loglayabilir. supports metodu ise bu davranışın sadece belirli
// türdeki istekleri desteklemesini sağlar. Örneğin, NotLoggableRequest ile
// imzalanmış istekler için loglama yapılmaz.
