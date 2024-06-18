package ru.itskekoff.aaio.modules.payoff;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author itskekoff
 * @since 1:30 of 19.06.2024
 */

@Getter
@AllArgsConstructor
@ToString
public class PayoffInfoResponse {
    private String type;
    private String id;
    private String myId;
    private String method;
    private String bank;
    private String phoneOperator;
    private String wallet;
    private double amount;
    @SerializedName("amount_down")
    private double amountDown;
    private double commission;
    @SerializedName("commission_type")
    private int commissionType;
    private String status;
    @SerializedName("cancel_message")
    private String cancelMessage;
    private String date;
    @SerializedName("complete_date")
    private String completeDate;
}
