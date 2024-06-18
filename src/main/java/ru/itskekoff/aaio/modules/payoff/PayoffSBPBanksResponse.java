package ru.itskekoff.aaio.modules.payoff;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author itskekoff
 * @since 1:29 of 19.06.2024
 */

@Getter
@AllArgsConstructor
@ToString
public class PayoffSBPBanksResponse {
    private String type;
    private List<Bank> list;

    @Getter
    @AllArgsConstructor
    @ToString
    public static class Bank {
        @SerializedName("bankId")
        private String bankId;
        @SerializedName("bankName")
        private String bankName;
    }
}
