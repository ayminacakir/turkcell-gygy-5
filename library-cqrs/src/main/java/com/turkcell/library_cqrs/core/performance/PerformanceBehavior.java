package com.turkcell.library_cqrs.core.performance;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.turkcell.library_cqrs.core.mediator.pipeline.PipelineBehavior;
import com.turkcell.library_cqrs.core.mediator.pipeline.RequestHandlerDelegate;

/*
 * Ödev: PerformanceBehavior:
 * Belirli bir süreyi aşan requestleri uyarı olarak yakala.
 * 3000ms'i geçen requestler konsola kendi ismiyle birlikte bilgi düşsün.
 */
@Component
@Order(30)
public class PerformanceBehavior implements PipelineBehavior {

    private static final long WARNING_THRESHOLD_MS = 3000;

    @Override
    public <R> R handle(Object request, RequestHandlerDelegate<R> next) {

        String requestName = request.getClass().getSimpleName();

        long startTime = System.currentTimeMillis();// Request başlamadan önceki zaman.

        try {
            return next.invoke();
        } finally {

            long endTime = System.currentTimeMillis();

            long elapsedTime = endTime - startTime;// Toplam geçen süre.

            if (elapsedTime > WARNING_THRESHOLD_MS) {
                System.out.println();
                System.out.println("[PERFORMANCE WARNING]");
                System.out.println("Request : " + requestName);
                System.out.println("Duration: " + elapsedTime + " ms");
                System.out.println("Limit   : " + WARNING_THRESHOLD_MS + " ms");
            }
        }
    }
}