package com.universitystore.utilities;

import java.util.Random;

public class StringUtils {
    public static String generateRandomAlphanumericString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // Include more characters if you want
        StringBuilder sb = new StringBuilder(length);
        Random rand = new Random();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
