package com.turkcell.library_cqrs.core.logging;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

import tools.jackson.databind.ObjectMapper;

//Ödev:Logging Behavior -> Tüm requestleri içindeki bilgi, dönen cevap nedir ayrı ayrı loglasın. (Konsol)
@Component
@Order(20)
public class LoggingBehavior implements PipelineBehavior {
    private final ObjectMapper objectMapper;// ObjectMapper nesneleri JSON string'e çevirmek için kullanılır.

    public LoggingBehavior(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(Object request) {
        return !(request instanceof NotLoggableRequest);
    }

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {

        String requestName = request.getClass().getSimpleName();
        /* Handler çalışmadan önce request'i logluyoruz. */
        System.out.println();
        System.out.println("--REQUEST LOG--");
        System.out.println("Request: " + requestName);
        System.out.println("Body   : " + toJson(request));

        try {
            R response = next.invoke();
            /* Handler başarılı çalıştıktan sonra response'u logluyoruz. */
            System.out.println();
            System.out.println("--RESPONSE LOG--");
            System.out.println("Request : " + requestName);
            System.out.println("Response: " + toJson(response));

            return response;

        } catch (Exception exception) {
            /* Hata olursa hata mesajını logluyoruz. */
            System.out.println();
            System.out.println("-- ERROR LOG---");
            System.out.println("Request: " + requestName);
            System.out.println("Error  : " + exception.getMessage());

            throw exception;
        }
    }

    private String toJson(Object value) {
        try {
            return value == null ? "null" : objectMapper.writeValueAsString(value);
        } catch (Exception exception) {
            return String.valueOf(value);
        }
    }
}