package ru.itskekoff.aaio.modules.exchange;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author itskekoff
 * @since 1:25 of 19.06.2024
 */

@AllArgsConstructor
@Getter
@ToString
public class ExchangeRatesInfo {
    private String type;
    @SerializedName("USD")
    private float usd;
    @SerializedName("UAH")
    private float uah;
    @SerializedName("USDT")
    private float usdt;
    @SerializedName("BTC")
    private float btc;
}
