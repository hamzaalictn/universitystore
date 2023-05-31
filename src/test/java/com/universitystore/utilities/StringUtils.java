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

    public static long generateRandomNumber(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be a positive integer");
        }

        Random rand = new Random();
        long minimum = (long) Math.pow(10, length - 1);
        long maximum = (long) Math.pow(10, length) - 1;

        return minimum + (long) (rand.nextDouble() * (maximum - minimum));
    }
}
