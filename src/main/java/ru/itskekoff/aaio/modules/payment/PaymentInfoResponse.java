package ru.itskekoff.aaio.modules.payment;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author itskekoff
 * @since 1:33 of 19.06.2024
 */

@Getter
@AllArgsConstructor
@ToString
public class PaymentInfoResponse {
    private String type;
    private String id;
    @SerializedName("order_id")
    private String orderId;
    private String desc;
    @SerializedName("merchant_id")
    private String merchantId;
    @SerializedName("merchant_domain")
    private String merchantDomain;
    private String method;
    private double amount;
    private String currency;
    private double profit;
    private double commission;
    @SerializedName("commission_client")
    private double commissionClient;
    @SerializedName("commission_type")
    private String commissionType;
    private String email;
    private String status;
    private String date;
    @SerializedName("expired_date")
    private String expiredDate;
    @SerializedName("complete_date")
    private String completeDate;
    @SerializedName("us_vars")
    private List<Object> usVars;
}
