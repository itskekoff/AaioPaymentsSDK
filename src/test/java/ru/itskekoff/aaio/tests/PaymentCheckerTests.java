package ru.itskekoff.aaio.tests;

import ru.itskekoff.aaio.client.AaioClient;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.modules.payment.PaymentInfoResponse;
import ru.itskekoff.aaio.requests.impl.pay.PaymentURLRequest;
import ru.itskekoff.aaio.requests.impl.pay.info.PaymentInfoRequest;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * @author itskekoff
 * @since 12:23 of 20.06.2024
 */
public class PaymentCheckerTests {
    public static void main(String[] args) {
        UUID shopId = UUID.randomUUID();
        String orderId = UUID.randomUUID().toString();

        AaioClient client = new AaioClient("api_key", "secret_key_no_1", shopId.toString());

        PaymentURLRequest paymentURL = PaymentURLRequest.builder()
                .merchantId(UUID.randomUUID())
                .amount(100.18f)
                .orderId(orderId)
                .sign(client.generateSignature("RUB", orderId, 100.18f))
                .build();

        String url = client.createPaymentURL(paymentURL);
        // do something with url....

        PaymentInfoRequest request = PaymentInfoRequest.builder()
                .merchantId(shopId)
                .orderId(orderId)
                .build();

        CompletableFuture<PaymentInfoResponse> future = client.getStatusChecker().waitForPaymentStatus(request);
        future.thenAccept(response -> System.out.println("Оплата успешная!"));
    }
}
