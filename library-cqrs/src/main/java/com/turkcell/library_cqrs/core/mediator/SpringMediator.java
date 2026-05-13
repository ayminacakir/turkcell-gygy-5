package com.turkcell.library_cqrs.core.mediator;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;
import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

@Component
public class SpringMediator implements Mediator {

    private final ApplicationContext context;
    private final List<PipelineBehavior> behaviors;

    public SpringMediator(ApplicationContext context, List<PipelineBehavior> behaviors) {
        this.context = context;
        this.behaviors = behaviors.stream().sorted(AnnotationAwareOrderComparator.INSTANCE).toList();
    }

    @Override
    public <R> R send(Command<R> command) {
        var handler = (CommandHandler<Command<R>, R>) resolveHandler(command.getClass(), CommandHandler.class);

        return invokePipeline(command, () -> handler.handle(command));
    }

    @Override
    public <R> R send(Query<R> query) { // QueryHandler'ları gez, query ile uyuşanı bul, handle et.
        var handler = (QueryHandler<Query<R>, R>) resolveHandler(query.getClass(), QueryHandler.class);

        return invokePipeline(query, () -> handler.handle(query));
    }

    // Hangi command/query -> hangi handler?
    private Object resolveHandler(Class<?> requestType, Class<?> handlerInterface) {

        String[] beanNames = context.getBeanNamesForType(handlerInterface);
        for (String beanName : beanNames) {
            Class<?> beanClass = context.getType(beanName);
            if (beanClass == null) {
                continue;
            }
            ResolvableType[] interfaces = ResolvableType.forClass(beanClass).getInterfaces();
            for (ResolvableType iface : interfaces) {
                if (iface.getRawClass() != null &&
                        handlerInterface.isAssignableFrom(iface.getRawClass())) {
                    Class<?> firstGeneric = iface.getGeneric(0).resolve();
                    if (firstGeneric != null && firstGeneric.equals(requestType)) {
                        return context.getBean(beanName);
                    }
                }
            }
        }
        throw new IllegalStateException("Handler bulunamadı: " + requestType.getSimpleName());
    }

    private <R> R invokePipeline(Object request, RequestHandlerDelegate<R> handlerInvocation) { // Elimizde
                                                                                                // bir request var ve bu
                                                                                                // request'i işleyecek
                                                                                                // bir handler var.
                                                                                                // Ancak, handler'ı
                                                                                                // doğrudan çağırmak
                                                                                                // yerine, önce
                                                                                                // pipeline'daki tüm
                                                                                                // davranışları
                                                                                                // (behaviors) uygulamak
                                                                                                // istiyoruz. Bu
                                                                                                // davranışlar, örneğin
                                                                                                // yetkilendirme,
                                                                                                // loglama veya
                                                                                                // performans ölçümü
                                                                                                // gibi ortak işlemleri
                                                                                                // içerebilir.
                                                                                                // invokePipeline
                                                                                                // metodu, bu
                                                                                                // davranışları sırayla
                                                                                                // uygular ve sonunda
                                                                                                // gerçek handler'ı
                                                                                                // çağırır. Böylece, her
                                                                                                // request için belirli
                                                                                                // işlemleri otomatik
                                                                                                // olarak
                                                                                                // gerçekleştirebiliriz.
        RequestHandlerDelegate<R> next = handlerInvocation;

        for (int i = behaviors.size() - 1; i >= 0; i--) { // neden tersten gidiyoruz: Çünkü davranışları sırayla
                                                          // uygulamak istiyoruz. İlk olarak, handler'ı çağıracağız,
                                                          // sonra en son davranışı, sonra sondan bir önceki davranışı
                                                          // ve böyle devam edeceğiz. Bu şekilde, davranışlar doğru
                                                          // sırayla uygulanır.

            PipelineBehavior behavior = behaviors.get(i);
            if (!behavior.supports(request)) {
                continue;
            }
            RequestHandlerDelegate<R> current = next;
            next = () -> behavior.handle(request, current);
        }
        return next.invoke();
    }
}
// Hangi command/query -> hangi handler? Handler'ları gez, komutla/query ile
// uyuşanı dön. bu fonksiyon şunu anlatıyor: Elimizde bir command veya query
// var, şimdi bu command veya query'yi hangi handler'ın işleyeceğini bulmamız
// gerekiyor. Spring'in ApplicationContext'ini kullanarak, belirli bir handler
// arayacağız. Öncelikle, tüm handler'ları alacağız ve her birinin hangi tür
// komutları veya sorguları işlediğine bakacağız. Eğer handler'ın işlediği komut
// veya sorgu türü, bizim gönderdiğimiz komut veya sorgu türüyle eşleşiyorsa, o
// handler'ı döndüreceğiz. Eğer hiçbir handler bulunamazsa, bir istisna
// fırlatacağız.