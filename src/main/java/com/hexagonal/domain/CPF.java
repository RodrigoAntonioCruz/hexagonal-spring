package com.hexagonal.domain;

public class CPF {
    private static final int[] WEIGHT_SSN = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calculate(final String str) {
        int sum = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * WEIGHT_SSN[WEIGHT_SSN.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public static boolean isValid(final String ssn) {
        if ((ssn == null) || (ssn.length() != 11) || ssn.matches(ssn.charAt(0) + "{11}")) return false;

        var digit1 = calculate(ssn.substring(0, 9));
        var digit2 = calculate(ssn.substring(0, 9) + digit1);
        return ssn.equals(ssn.substring(0, 9) + digit1 + digit2);
    }
}