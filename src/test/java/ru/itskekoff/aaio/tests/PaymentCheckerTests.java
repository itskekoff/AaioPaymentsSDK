package ru.itskekoff.aaio.tests;

import ru.itskekoff.aaio.client.AaioClient;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.modules.payment.PaymentInfoResponse;
import ru.itskekoff.aaio.requests.impl.pay.info.PaymentInfoRequest;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author itskekoff
 * @since 12:23 of 20.06.2024
 */
public class PaymentCheckerTests {
    public static void main(String[] args) {
        AaioClient client = new AaioClient("", "", "");
        PaymentInfoRequest request = PaymentInfoRequest.builder()
                .merchantId(UUID.randomUUID())
                .orderId(UUID.randomUUID().toString()).build();

        CompletableFuture<PaymentInfoResponse> future = client.getStatusChecker().waitForPaymentStatus(request);
        future.thenAccept(response -> System.out.println("Оплата успешная!"));
    }
}
