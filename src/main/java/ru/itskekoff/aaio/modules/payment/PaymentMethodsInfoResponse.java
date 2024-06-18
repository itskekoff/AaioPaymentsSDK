package ru.itskekoff.aaio.modules.payment;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author itskekoff
 * @since 1:59 of 19.06.2024
 */

@Getter
@AllArgsConstructor
@ToString
public class PaymentMethodsInfoResponse {
    @SerializedName("type")
    private String type;

    @SerializedName("list")
    private Map<String, PaymentMethodDetails> paymentMethods;

    @Getter
    @AllArgsConstructor
    @ToString
    public static class PaymentMethodDetails {
        @SerializedName("name")
        private String name;

        @SerializedName("min")
        private Map<String, Integer> minAmounts;

        @SerializedName("max")
        private Map<String, Integer> maxAmounts;

        @SerializedName("commission_percent")
        private int commissionPercent;

        @SerializedName("commission_user_percent")
        private int commissionUserPercent;

        @SerializedName("commission_merchant_percent")
        private int commissionMerchantPercent;

        @SerializedName("commission_type")
        private String commissionType;
    }
}
