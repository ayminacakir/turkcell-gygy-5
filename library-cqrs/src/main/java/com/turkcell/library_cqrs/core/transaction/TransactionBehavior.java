package com.turkcell.library_cqrs.core.transaction;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

/*
 * TransactionBehavior:
 * Veritabanı işlemlerinin bütünlüğünü sağlar.Eğer bir hata olursa tüm işlemler geri alınır (rollback).
 * @Transactional ile işaretlenen metodlar otomatik olarak
 * transaction içinde çalışır.
 */
@Component
@Order(5)
public class TransactionBehavior implements PipelineBehavior {

    @Override
    public boolean supports(Object request) {
        return true;
    }

    @Override
    @Transactional
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {

        String requestName = request.getClass().getSimpleName();

        System.out.println();
        System.out.println(" TRANSACTION START ");
        System.out.println("Request Name: " + requestName);
        System.out.println("Transaction: BEGIN");

        try {
            R response = next.invoke();

            System.out.println("Transaction: COMMIT");

            return response;

        } catch (Exception exception) {
            /*
             * Hata durumunda transaction otomatik rollback olur.
             * Exception'ı tekrar fırlatıyoruz ki diğer behavior'lar
             * (LoggingBehavior) bunu yakalayıp loglayabilsin.
             */
            System.out.println("Transaction: ROLLBACK " + exception.getMessage());
            throw exception;
        }
    }
}