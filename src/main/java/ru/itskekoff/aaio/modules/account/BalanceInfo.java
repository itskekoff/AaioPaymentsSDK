package ru.itskekoff.aaio.modules.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author itskekoff
 * @since 1:08 of 19.06.2024
 */

@AllArgsConstructor
@Getter
@ToString
public class BalanceInfo {
    private String type;
    private float balance;
    private float referral;
    private float hold;
}
