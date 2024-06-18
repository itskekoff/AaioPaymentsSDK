package ru.itskekoff.aaio.modules.payoff;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author itskekoff
 * @since 1:52 of 19.06.2024
 */

@Getter
@AllArgsConstructor
@ToString
public class PayoffMethodsInfoResponse {
    @SerializedName("type")
    private String type;
    @SerializedName("list")
    private Map<String, WithdrawMethodInfo> paymentMethods;

    @Getter
    @AllArgsConstructor
    @ToString
    public static class WithdrawMethodInfo {
        @SerializedName("name")
        private String name;
        @SerializedName("min")
        private float minAmount;
        @SerializedName("max")
        private float maxAmount;
        @SerializedName("commission_percent")
        private float commissionPercent;
        @SerializedName("commission_sum")
        private float commissionSum;
    }
}
