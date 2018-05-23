package com.kpi.diploma.perevertailo.service.util.calculator.math;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MathUtil {
    public static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }

    public static long permutations(int a) {
        return factorial(a);
    }

    public static long placements(int k, int n) {
        return factorial(n) / factorial(n - k);

    }

    public static long combinations(int k, int n) {
        return placements(k, n) / factorial(k);
    }

    public static double roundDouble(double number, int precision) {
        DecimalFormat df = new DecimalFormat("#." + new String(new char[precision]).replace("\0", "#"));
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.valueOf(df.format(number));
    }
}
