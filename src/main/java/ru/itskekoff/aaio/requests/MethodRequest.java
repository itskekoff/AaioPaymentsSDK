package ru.itskekoff.aaio.requests;

import lombok.Getter;
import okhttp3.HttpUrl;
import ru.itskekoff.aaio.client.exceptions.ClientException;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@Getter
public abstract class MethodRequest {

    public HttpUrl buildUrl(HttpUrl baseUrl) {
        Map<String, String> params = buildRequest();
        HttpUrl.Builder urlBuilder = baseUrl.newBuilder();

        params.forEach((key, value) -> {
            try {
                urlBuilder.addQueryParameter(URLEncoder.encode(key, StandardCharsets.UTF_8),
                        URLEncoder.encode(value, StandardCharsets.UTF_8));
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to encode URL parameters", e);
            }
        });

        return urlBuilder.build();
    }

    protected Map<String, String> buildRequest() {
        Map<String, String> params = new HashMap<>();
        Field[] fields = getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(QueryField.class)) {
                field.setAccessible(true);
                QueryField queryField = field.getAnnotation(QueryField.class);

                String name = queryField.value();
                boolean required = queryField.required();

                try {
                    Object value = field.get(this);
                    if (value == null || Objects.toString(value).isEmpty()) {
                        if (required) {
                            throw new ClientException("Required field " + name + " is missing or empty");
                        }
                    } else {
                        String stringValue;
                        if (value instanceof Float || value instanceof Double) {
                            double doubleValue = ((Number) value).doubleValue();
                            if (doubleValue % 1 == 0) {
                                stringValue = String.format(Locale.US, "%.0f", doubleValue);
                            } else {
                                stringValue = String.format(Locale.US, "%.2f", doubleValue);
                            }
                        } else {
                            stringValue = Objects.toString(value).trim();
                        }
                        params.put(name, stringValue);
                    }
                } catch (IllegalAccessException e) {
                    throw new ClientException("Error accessing field " + name, e);
                }
            }
        }

        return params;
    }
}