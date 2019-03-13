package br.com.team.appx.convinience.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

public class NumberUtils {
    public static String currencyFormat(double amount) {
        return currencyFormat(amount, "R$");
    }

    public static String currencyFormat(double amount, String symbol) {
        String pattern = symbol + " ###,###,##0.00";
        DecimalFormat formatter = new DecimalFormat(pattern);

        return formatter.format(amount);
    }

    public static double round(double value) {
        return round(value, 2);
    }

    public static double round(double value, int places) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public static boolean isNumeric(String str) {
        if (str == null)
            return false;

        try {
            Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public static Long toLong(Object value) {
        return Long.valueOf(String.valueOf(value));
    }

    public static int getRandom(int bound) {
        Random random = new Random();

        return random.nextInt(bound);
    }
}
