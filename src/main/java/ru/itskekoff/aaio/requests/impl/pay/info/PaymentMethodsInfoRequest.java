package ru.itskekoff.aaio.requests.impl.pay.info;

import lombok.Builder;
import lombok.Getter;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.requests.MethodRequest;
import ru.itskekoff.aaio.requests.QueryField;

import java.util.UUID;

/**
 * @author itskekoff
 * @since 1:54 of 19.06.2024
 */

@Getter
public class PaymentMethodsInfoRequest extends MethodRequest {
    @QueryField(value = "merchant_id", required = true)
    private UUID merchantId;

    @Builder
    public PaymentMethodsInfoRequest(UUID merchantId) {
        this.merchantId = merchantId;

        if (merchantId == null) {
            throw new ClientException("merchantId cannot be null");
        }
    }
}