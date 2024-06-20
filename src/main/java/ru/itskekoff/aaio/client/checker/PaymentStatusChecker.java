package ru.itskekoff.aaio.client.checker;

import lombok.RequiredArgsConstructor;
import ru.itskekoff.aaio.client.AaioClient;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.modules.payment.PaymentInfoResponse;
import ru.itskekoff.aaio.requests.impl.pay.info.PaymentInfoRequest;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author itskekoff
 * @since 12:22 of 20.06.2024
 */

@RequiredArgsConstructor
public class PaymentStatusChecker {
    private final AaioClient client;

    /**
     * Waits asynchronously for the payment status to become either "hold" or "success".
     *
     * @param paymentInfoRequest The {@link PaymentInfoRequest} object used to check the payment status.
     * @return A {@link CompletableFuture} that will complete with the {@link PaymentInfoResponse} once the status is "hold" or "success".
     */
    public CompletableFuture<PaymentInfoResponse> waitForPaymentStatus(PaymentInfoRequest paymentInfoRequest) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        CompletableFuture<PaymentInfoResponse> future = new CompletableFuture<>();

        scheduler.scheduleAtFixedRate(() -> {
            try {
                PaymentInfoResponse response = client.createPaymentInfoRequest(paymentInfoRequest);
                String status = response.getStatus();
                if ("hold".equals(status) || "success".equals(status)) {
                    future.complete(response);
                    scheduler.shutdown();
                }
            } catch (IOException e) {
                future.completeExceptionally(new ClientException("Error has occurred while checking status", e));
                scheduler.shutdown();
            }
        }, 0, 5, TimeUnit.SECONDS);

        return future;
    }
}
