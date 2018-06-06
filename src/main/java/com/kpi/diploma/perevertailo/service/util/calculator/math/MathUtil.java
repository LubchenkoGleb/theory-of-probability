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

    public static double puassonSum(int from, int to, int n, double p) {
        double res = 0;

        for (int i = from; i <= to; i++) {
            double curRes = puasson(i, n, p);

            if (Double.isInfinite(curRes))
                break;

            res += curRes;
        }

        return res;
    }

    public static double puasson(int k, int n, double p) {
        double l = n * p;
        return Math.pow(l, k) * Math.pow(Math.E, -l) / factorial(k);
    }

    public static double calcXFroLaplase(int k, int n, double p) {
        return (k - n * p) / Math.sqrt(n * p * (1 - p));
    }

    public static double calcLaplase(int k1, int k2, int n, double p) {
        return LaplaceTable.getNeares(calcXFroLaplase(k2, n, p)) - LaplaceTable.getNeares(calcXFroLaplase(k1, n, p));
    }

    public static double calcLaplaseForOneValue(int n, double p, int k, double x) {
        double fe = (Math.pow(Math.E, -(x * x / 2)) / Math.sqrt(2 * Math.PI));
        return fe / Math.sqrt(n * p * (1 - p));
    }

    public static double centMoments(int[] x, double[] p, int momentNumber) {

        double res = 0;

        for (int i = 0; i < x.length; i++) {
            res += Math.pow(x[i], momentNumber) * p[i];
        }

        return res;
    }
}
