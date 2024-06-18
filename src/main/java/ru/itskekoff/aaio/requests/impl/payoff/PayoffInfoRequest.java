package ru.itskekoff.aaio.requests.impl.payoff;

import lombok.Builder;
import lombok.Getter;
import ru.itskekoff.aaio.client.exceptions.ClientException;
import ru.itskekoff.aaio.requests.MethodRequest;
import ru.itskekoff.aaio.requests.QueryField;

/**
 * @author itskekoff
 * @since 1:31 of 19.06.2024
 */

@Getter
public class PayoffInfoRequest extends MethodRequest {
    @QueryField(value = "my_id", required = true)
    private String withdrawId;

    @QueryField(value = "id", required = true)
    private String withdrawServerId;

    @Builder
    public PayoffInfoRequest(String withdrawId, String withdrawServerId) {
        this.withdrawId = withdrawId;
        this.withdrawServerId = withdrawServerId;

        if (withdrawId == null || withdrawId.isEmpty()) {
            throw new ClientException("withdrawId cannot be null or empty");
        }
        if (withdrawServerId == null || withdrawServerId.isEmpty()) {
            throw new ClientException("withdrawServerId cannot be null or empty");
        }
    }
}