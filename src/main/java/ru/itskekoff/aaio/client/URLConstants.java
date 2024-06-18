package ru.itskekoff.aaio.client;

import okhttp3.HttpUrl;

/**
 * @author itskekoff
 * @since 23:32 of 18.06.2024
 */
public class URLConstants {
    public static class BalanceEndpoints {
        public static final HttpUrl GET_BALANCE = HttpUrl.get("https://aaio.so/api/balance");
        public static final HttpUrl EXCHANGE_RATES = HttpUrl.get("https://aaio.so/api/rates-payoff");
    }

    public static class PayoffEndpoints {
        public static final HttpUrl CREATE_PAYOFF = HttpUrl.get("https://aaio.so/api/create-payoff");
        public static final HttpUrl METHODS_PAYOFF = HttpUrl.get("https://aaio.so/api/methods-payoff");
        public static final HttpUrl SPB_BANKS_PAYOFF = HttpUrl.get("https://aaio.so/api/sbp-banks-payoff");
        public static final HttpUrl INFO_PAYOFF = HttpUrl.get("https://aaio.so/api/info-payoff");
    }

    public static class PaymentEndpoints {
        public static final HttpUrl CREATE_PAYMENT = HttpUrl.get("https://aaio.so/merchant/pay");
        public static final HttpUrl METHODS_PAY = HttpUrl.get("https://aaio.so/api/methods-pay");
        public static final HttpUrl INFO_PAY = HttpUrl.get("https://aaio.so/api/info-pay");
    }

}
