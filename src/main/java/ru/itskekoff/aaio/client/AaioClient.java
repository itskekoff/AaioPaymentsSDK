package ru.itskekoff.aaio.client;

import com.google.gson.Gson;
import lombok.Getter;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import ru.itskekoff.aaio.client.checker.PaymentStatusChecker;
import ru.itskekoff.aaio.client.exceptions.AaioException;
import ru.itskekoff.aaio.modules.account.BalanceInfo;
import ru.itskekoff.aaio.modules.exchange.ExchangeRatesInfo;
import ru.itskekoff.aaio.modules.payment.PaymentInfoResponse;
import ru.itskekoff.aaio.modules.payment.PaymentMethodsInfoResponse;
import ru.itskekoff.aaio.modules.payoff.PayoffSBPBanksResponse;
import ru.itskekoff.aaio.modules.payoff.PayoffMethodsInfoResponse;
import ru.itskekoff.aaio.modules.payoff.PayoffInfoResponse;
import ru.itskekoff.aaio.modules.payoff.PayoffResponse;
import ru.itskekoff.aaio.requests.impl.pay.info.PaymentInfoRequest;
import ru.itskekoff.aaio.requests.impl.pay.info.PaymentMethodsInfoRequest;
import ru.itskekoff.aaio.requests.impl.payoff.PayoffInfoRequest;
import ru.itskekoff.aaio.requests.impl.payoff.PayoffRequest;
import ru.itskekoff.aaio.requests.impl.pay.PaymentURLRequest;

import java.io.IOException;
import java.net.Proxy;

/**
 * @author itskekoff
 * @since 23:31 of 18.06.2024
 */

@Getter
public class AaioClient {
    private final String apiKey;
    private final String secretKey;
    private final String shopId;
    private final OkHttpClient client;

    private final PaymentStatusChecker statusChecker;

    /**
     * Constructor for Aaio client
     *
     * @param apiKey    <a href="https://aaio.so/cabinet/api">Cabinet</a> api key
     * @param secretKey Secret key №1 from shop settings
     * @param shopId    Shop id from shop settings
     * @see AaioClient
     */
    public AaioClient(String apiKey, String secretKey, String shopId) {
        this(apiKey, secretKey, shopId, new OkHttpClient.Builder().build());
    }

    /**
     * Constructor for Aaio client
     *
     * @param apiKey    <a href="https://aaio.so/cabinet/api">Cabinet</a> api key
     * @param secretKey Secret key №2 from shop settings
     * @param shopId    Shop id from shop settings
     * @param proxy     {@link Proxy} for {@link OkHttpClient}
     * @see AaioClient
     */
    public AaioClient(String apiKey, String secretKey, String shopId, Proxy proxy) {
        this(apiKey, secretKey, shopId, new OkHttpClient.Builder().proxy(proxy).build());
    }

    /**
     * Constructor for Aaio client
     *
     * @param apiKey    <a href="https://aaio.so/cabinet/api">Cabinet</a> api key
     * @param secretKey Secret key №2 from shop settings
     * @param shopId    Shop id from shop settings
     * @param client    {@link OkHttpClient}
     * @see AaioClient
     */
    public AaioClient(String apiKey, String secretKey, String shopId, OkHttpClient client) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.shopId = shopId;
        this.client = client;

        this.statusChecker = new PaymentStatusChecker(this);
    }

    /**
     * Unique function of request creating for client's api methods
     *
     * @param formBody Request body
     * @param url      Request url
     * @param _class   Class of retrieving T object
     * @return {@link T} object
     * @throws IOException If request exception occurred
     */
    public <T> T request(RequestBody formBody, HttpUrl url, Class<T> _class) throws IOException {
        Request request = new Request.Builder()
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("X-Api-Key", this.apiKey)
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw handleErrorResponse(response);
        }

        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            throw new AaioException("Response body is null");
        }

        String body = responseBody.string();
        if (body.isEmpty()) {
            throw new AaioException("Response body is empty");
        }

        T info = new Gson().fromJson(body, _class);
        response.close();
        return info;
    }

    private AaioException handleErrorResponse(Response response) throws IOException {
        ResponseBody errorBody = response.body();
        if (errorBody != null) {
            String errorJson = errorBody.string();
            AaioException.ErrorInfo errorInfo = new Gson().fromJson(errorJson, AaioException.ErrorInfo.class);
            return new AaioException("Failed with error code " + errorInfo.getCode() + ": " + errorInfo.getMessage());
        } else {
            return new AaioException("Failed with HTTP error code: " + response.code());
        }
    }

    /**
     * Generates the full URL for the payment request.
     *
     * @param request The {@link PaymentURLRequest} object containing all necessary parameters for the URL.
     * @return The complete URL as a String to initiate the payment process.
     */
    public String createPaymentURL(PaymentURLRequest request) {
        return request.buildUrl(URLConstants.PaymentEndpoints.CREATE_PAYMENT).url().toString();
    }

    /**
     * Creates a payment info request based on the provided {@link PaymentInfoRequest}.
     *
     * @param request The request object containing details for the withdrawal.
     * @return A {@link PaymentInfoResponse} object representing the response from the server.
     * @throws IOException If an I/O exception occurs while making request.
     */
    public PaymentInfoResponse createPaymentInfoRequest(PaymentInfoRequest request) throws IOException {
        FormBody formBody = new FormBody.Builder().build();
        return request(formBody, request.buildUrl(URLConstants.PaymentEndpoints.INFO_PAY), PaymentInfoResponse.class);
    }

    /**
     * Creates a payment methods info request based on the provided {@link PaymentMethodsInfoRequest}.
     *
     * @param request The request object containing details for the withdrawal.
     * @return A {@link PaymentMethodsInfoResponse} object representing the response from the server.
     * @throws IOException If an I/O exception occurs while making request.
     */
    public PaymentMethodsInfoResponse createPaymentMethodsRequest(PaymentMethodsInfoRequest request) throws IOException {
        FormBody formBody = new FormBody.Builder().build();
        return request(formBody, request.buildUrl(URLConstants.PaymentEndpoints.METHODS_PAY), PaymentMethodsInfoResponse.class);
    }


    /**
     * Creates a balance info request.
     *
     * @return A {@link BalanceInfo} object representing the response from the server.
     * @throws IOException If an I/O exception occurs while making request.
     */
    public BalanceInfo createBalanceInfoRequest() throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, URLConstants.BalanceEndpoints.GET_BALANCE, BalanceInfo.class);
    }

    /**
     * Creates a withdrawal request based on the provided {@link PayoffRequest}.
     *
     * @param request The request object containing details for the withdrawal.
     * @return A {@link PayoffResponse} object representing the response from the server.
     * @throws IOException If an I/O exception occurs while making request.
     */
    public PayoffResponse createPayoffRequest(PayoffRequest request) throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, request.buildUrl(URLConstants.PayoffEndpoints.CREATE_PAYOFF), PayoffResponse.class);
    }

    /**
     * Creates a withdrawal info request based on the provided {@link PayoffInfoRequest}.
     *
     * @param request The request object containing details for the withdrawal.
     * @return A {@link PayoffInfoResponse} object representing the response from the server.
     * @throws IOException If an I/O exception occurs while making request.
     */
    public PayoffInfoResponse createPayoffInfoRequest(PayoffInfoRequest request) throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, request.buildUrl(URLConstants.PayoffEndpoints.INFO_PAYOFF), PayoffInfoResponse.class);
    }

    public PayoffMethodsInfoResponse createPayoffMethodsRequest() throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, URLConstants.PayoffEndpoints.METHODS_PAYOFF, PayoffMethodsInfoResponse.class);
    }

    /**
     * Creates an exchange rates request.
     *
     * @return A {@link ExchangeRatesInfo} object representing the response from the server.
     * @throws IOException If an I/O exception occurs while making request.
     */
    public ExchangeRatesInfo createExchangeRatesRequest() throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, URLConstants.BalanceEndpoints.EXCHANGE_RATES, ExchangeRatesInfo.class);
    }

    /**
     * Creates an sbp banks  request.
     *
     * @return A {@link PayoffSBPBanksResponse} object representing the response from the server.
     * @throws IOException If an I/O exception occurs while making request.
     */
    public PayoffSBPBanksResponse createSBPBanksRequest() throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, URLConstants.PayoffEndpoints.SPB_BANKS_PAYOFF, PayoffSBPBanksResponse.class);
    }

    /**
     * Generates an SHA-256 signature for the given parameters.
     *
     * @param currency  The currency of the transaction.
     * @param orderId   The unique identifier of the payment.
     * @param payAmount The amount to be paid.
     * @return The SHA-256 hash of the concatenated string.
     * @throws IllegalArgumentException if any of the parameters are null or empty.
     */
    public String generateSignature(String currency, String orderId, float payAmount) {
        if (currency == null || currency.isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        if (orderId == null || orderId.isEmpty()) {
            throw new IllegalArgumentException("Order ID cannot be null or empty");
        }
        if (payAmount <= 0) {
            throw new IllegalArgumentException("Pay amount must be greater than zero");
        }

        String str = String.format("%s:%f:%s:%s:%s", this.shopId, payAmount, currency, this.secretKey, orderId);
        return DigestUtils.sha256Hex(str);
    }
}
