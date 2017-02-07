package com.samsclub.qa.utils;

import java.util.Random;

public class StringHelper {

    /**
     * Set default value if the current value is empty
     * @param value
     * @param defaultValue
     * @return Default/Current Value
     */
    public static String getValueOrDefault(String value, String defaultValue) {
        return value != null && !value.isEmpty() ? value : defaultValue;
    }

    /**
     * Generate Random String of the specified length
     * @param length
     * @return Generated String
     */
    protected static String generateString(int length) {
        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random(System.nanoTime());
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);
    }
}
