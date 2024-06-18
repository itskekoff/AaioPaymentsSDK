package ru.itskekoff.aaio.tests;

import ru.itskekoff.aaio.client.AaioClient;
import ru.itskekoff.aaio.requests.impl.pay.PaymentURLRequest;

import java.util.UUID;

/**
 * @author itskekoff
 * @since 0:58 of 19.06.2024
 */
public class PaymentTests {
    public static void main(String[] args) {
        AaioClient client = new AaioClient("", "", "");
        String orderId = UUID.randomUUID().toString();

        PaymentURLRequest request = PaymentURLRequest.builder()
                .merchantId(UUID.randomUUID())
                .amount(100.18f)
                .orderId(orderId)
                .sign(client.generateSignature("RUB", orderId, 100.18f))
                .build();

        System.out.println(client.createPaymentURL(request));
    }
}
