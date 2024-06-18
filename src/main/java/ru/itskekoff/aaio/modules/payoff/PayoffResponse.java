package ru.itskekoff.aaio.modules.payoff;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author itskekoff
 * @since 1:18 of 19.06.2024
 */
@Getter
@AllArgsConstructor
@ToString
public class PayoffResponse {
    private String type;
    private String id;
    @SerializedName("my_id")
    private String myId;
    private String method;
    private String wallet;
    private float amount;
    @SerializedName("amount_in_currency")
    private float amountInCurrency;
    @SerializedName("amount_currency")
    private String amountCurrency;
    @SerializedName("amount_rate")
    private float amountRate;
    @SerializedName("amount_down")
    private float amountDown;
    private float commission;
    @SerializedName("commission_type")
    private int commissionType;
    private String status;
}
