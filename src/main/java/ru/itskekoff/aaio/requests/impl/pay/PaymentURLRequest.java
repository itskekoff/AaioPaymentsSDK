package ru.itskekoff.aaio.requests.impl.pay;

import lombok.Builder;
import lombok.Getter;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.requests.QueryField;
import ru.itskekoff.aaio.requests.MethodRequest;

import java.util.UUID;

/**
 * @author itskekoff
 * @since 23:29 of 18.06.2024
 * @see <a href="https://wiki.aaio.so/priem-platezhei/sozdanie-zakaza">Documentation</a>
 */

@Getter
public class PaymentURLRequest extends MethodRequest {
    @QueryField(value = "merchant_id", required = true)
    private UUID merchantId;
    @QueryField(value = "amount", required = true)
    private float amount;
    @QueryField(value = "order_id", required = true)
    private String orderId;
    @QueryField(value = "sign", required = true)
    private String sign;
    @QueryField(value = "currency")
    private String currency;
    @QueryField(value = "method")
    private String method;
    @QueryField(value = "desc")
    private String desc;
    @QueryField(value = "email")
    private String email;
    @QueryField(value = "lang")
    private String lang;
    @QueryField(value = "referral")
    private String referral;
    @QueryField(value = "us_key")
    private String usKey;

    @Builder
    public PaymentURLRequest(UUID merchantId, float amount, String orderId, String sign,
                             String currency, String method, String desc, String email,
                             String lang, String referral, String usKey) {
        this.merchantId = merchantId;
        this.amount = amount;
        this.orderId = orderId;
        this.sign = sign;
        this.currency = currency;
        this.method = method;
        this.desc = desc;
        this.email = email;
        this.lang = lang;
        this.referral = referral;
        this.usKey = usKey;

        if (merchantId == null) {
            throw new ClientException("merchantId cannot be null");
        }
        if (amount <= 0) {
            throw new ClientException("amount must be greater than 0");
        }
        if (orderId == null || orderId.isEmpty()) {
            throw new ClientException("orderId cannot be null or empty");
        }
        if (sign == null || sign.isEmpty()) {
            throw new ClientException("sign cannot be null or empty");
        }
    }
}