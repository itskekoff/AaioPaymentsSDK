package ru.itskekoff.aaio.requests.impl.payoff;

import lombok.Builder;
import lombok.Getter;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.requests.MethodRequest;
import ru.itskekoff.aaio.requests.QueryField;

/**
 * @author itskekoff
 * @since 1:13 of 19.06.2024
 * @see <a href="https://wiki.aaio.so/api/vyvod-sredstv">Documentation</a>
 */

@Getter
public class PayoffRequest extends MethodRequest {
    @QueryField(value = "wallet", required = true)
    private String walletNumber;

    @QueryField(value = "amount", required = true)
    private double withdrawAmount;

    @QueryField(value = "method", required = true)
    private String method;

    @QueryField(value = "commission_type")
    private Integer commissionType;

    @QueryField(value = "bank")
    private Integer bank;

    @QueryField(value = "phone_operator")
    private String phoneOperator;

    @QueryField(value = "webhook_url")
    private String webhookUrl;

    @Builder
    public PayoffRequest(String walletNumber, double withdrawAmount, String method,
                         Integer commissionType, Integer bank, String phoneOperator, String webhookUrl) {
        this.walletNumber = walletNumber;
        this.withdrawAmount = withdrawAmount;
        this.method = method;
        this.commissionType = commissionType;
        this.bank = bank;
        this.phoneOperator = phoneOperator;
        this.webhookUrl = webhookUrl;

        if (walletNumber == null || walletNumber.isEmpty()) {
            throw new ClientException("walletNumber cannot be null or empty");
        }
        if (method == null || method.isEmpty()) {
            throw new ClientException("method cannot be null or empty");
        }
    }
}
