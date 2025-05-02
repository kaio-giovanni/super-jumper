package com.kaio.superjumper.utils;

import java.text.DecimalFormat;

public class Utils {

    private Utils() {}

    public static String formatNumber(float n){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(n);
    }
}
