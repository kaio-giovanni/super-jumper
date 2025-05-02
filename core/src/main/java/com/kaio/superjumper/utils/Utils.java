package com.kaio.superjumper.utils;

public class Utils {

    private Utils() {}

    public static float formatNumber(float number){
        return (float) (Math.round(number * 100.0) / 100.0);
    }
}
