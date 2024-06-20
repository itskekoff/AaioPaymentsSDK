package ru.itskekoff.aaio.client.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author itskekoff
 * @since 23:36 of 18.06.2024
 */
public class AaioException extends RuntimeException {
    public AaioException(String message) {
        super(message);
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorInfo {
        private String code;
        private String message;
    }
}
