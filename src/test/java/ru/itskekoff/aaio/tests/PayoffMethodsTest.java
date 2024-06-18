package ru.itskekoff.aaio.tests;

import ru.itskekoff.aaio.client.AaioClient;

import java.io.IOException;

/**
 * @author itskekoff
 * @since 2:10 of 19.06.2024
 */
public class PayoffMethodsTest {
    public static void main(String[] args) throws IOException {
        AaioClient client = new AaioClient("", "", "");

        System.out.println(client.createPayoffMethodsRequest());
    }
}
