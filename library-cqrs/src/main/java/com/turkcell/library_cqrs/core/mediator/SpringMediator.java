package com.turkcell.library_cqrs.core.mediator;

import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.cqrs.Command;
import com.turkcell.library_cqrs.core.mediator.cqrs.CommandHandler;
import com.turkcell.library_cqrs.core.mediator.cqrs.Query;
import com.turkcell.library_cqrs.core.mediator.cqrs.QueryHandler;

@Component
public class SpringMediator implements Mediator {

    private final ApplicationContext context;

    public SpringMediator(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public <R> R send(Command<R> command) {
        var handler = (CommandHandler<Command<R>, R>) resolveHandler(command.getClass(), CommandHandler.class);

        return handler.handle(command);
    }

    @Override
    public <R> R send(Query<R> query) { // QueryHandler'ları gez, query ile uyuşanı bul, handle et.
        var handler = (QueryHandler<Query<R>, R>) resolveHandler(query.getClass(), QueryHandler.class);

        return handler.handle(query);
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
}