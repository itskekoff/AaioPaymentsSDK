package ru.itskekoff.aaio.client.exceptions;

/**
 * @author itskekoff
 * @since 23:44 of 18.06.2024
 */
public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
