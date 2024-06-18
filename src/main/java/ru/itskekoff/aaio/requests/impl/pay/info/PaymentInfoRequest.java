package ru.itskekoff.aaio.requests.impl.pay.info;

import lombok.Builder;
import lombok.Getter;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.requests.MethodRequest;
import ru.itskekoff.aaio.requests.QueryField;

import java.util.UUID;

/**
 * @author itskekoff
 * @since 1:46 of 19.06.2024
 */

@Getter
public class PaymentInfoRequest extends MethodRequest {
    @QueryField(value = "order_id", required = true)
    private String orderId;

    @QueryField(value = "merchant_id", required = true)
    private UUID merchantId;

    @Builder
    public PaymentInfoRequest(String orderId, UUID merchantId) {
        this.orderId = orderId;
        this.merchantId = merchantId;

        if (orderId == null || orderId.isEmpty()) {
            throw new ClientException("orderId cannot be null or empty");
        }
        if (merchantId == null) {
            throw new ClientException("merchantId cannot be null");
        }
    }
}