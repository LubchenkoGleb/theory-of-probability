package com.kpi.diploma.perevertailo.service.util.calculator.math;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class PirsonTable {

    private final static List<Integer> rows = new ArrayList<>(Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30));
    private final static List<Double> columns = new ArrayList<>(Arrays.asList(
            0.01, 0.025, 0.05, 0.95, 0.975, 0.99));

    private final static double[][] kohrenTable = {
            {6.6, 5.0, 3.8, 0.0039, 0.00098, 0.00016},
            {9.2, 7.4, 6.0, 0.103, 0.051, 0.020},
            {11.3, 9.4, 7.8, 0.352, 0.216, 0.115},
            {13.3, 11.1, 9.5, 0.711, 0.484, 0.297},
            {15.1, 12.8, 11.1, 1.15, 0.831, 0.554},
            {16.8, 14.4, 12.6, 1.64, 1.24, 0.872},
            {18.5, 16.0, 14.1, 2.17, 1.69, 1.24},
            {20.1, 17.5, 15.5, 2.73, 2.18, 1.65},
            {21.7, 19.0, 16.9, 3.33, 2.70, 2.09},
            {23.2, 20.5, 18.3, 3.94, 3.25, 2.56},
            {24.7, 21.9, 19.7, 4.57, 3.82, 3.05},
            {26.2, 23.3, 21.0, 5.23, 4.40, 3.57},
            {27.7, 24.7, 22.4, 5.89, 5.01, 4.11},
            {29.1, 26.1, 23.7, 6.57, 5.63, 4.66},
            {30.6, 27.5, 25.0, 7.26, 6.26, 5.23},
            {32.0, 28.8, 26.3, 7.96, 6.91, 5.81},
            {33.4, 30.2, 27.6, 8.67, 7.56, 6.41},
            {34.8, 31.5, 28.9, 9.39, 8.23, 7.01},
            {36.2, 32.9, 30.1, 10.1, 8.91, 7.63},
            {37.6, 34.2, 31.4, 10.9, 9.59, 8.26},
            {38.9, 35.5, 32.7, 11.61, 0.3, 8.90},
            {40.3, 36.8, 33.9, 12.31, 1.0, 9.54},
            {41.6, 38.1, 35.2, 13.11, 1.71, 0.2},
            {43.0, 39.4, 36.4, 13.81, 2.41, 0.9},
            {44.3, 40.6, 37.7, 14.61, 3.11, 1.5},
            {45.6, 41.9, 38.9, 15.41, 3.81, 2.2},
            {47.0, 43.2, 40.1, 16.21, 4.61, 2.9},
            {48.3, 44.5, 41.3, 16.91, 5.31, 3.6},
            {49.6, 45.7, 42.6, 17.71, 6.01, 4.3},
            {50.9, 47.0, 43.8, 18.51, 6.81, 5.0}
    };

    public static double getValues(int row, double col) {

        if (row > rows.get(rows.size() - 1)) {
            return 0.0;
        }

        int rowIndex = findNearesOnArray(row, rows);
        int colIndex = findNearesOnArray(col, columns);
        log.info("'rowIndex={}, colIndex={}'", rowIndex, colIndex);

        return kohrenTable[rowIndex][colIndex];
    }

    private static int findNearesOnArray(int val, List<Integer> arr) {
        int maxIndex = 0, minIndex = 0;

        for (int i = 1; i < arr.size(); i++) {
            minIndex = i - 1;
            maxIndex = i;
            if (val < arr.get(i)) {
                break;
            }
        }
        return Math.abs(arr.get(maxIndex) - val) > Math.abs(arr.get(minIndex) - val) ? minIndex : maxIndex;
    }

    private static int findNearesOnArray(double val, List<Double> arr) {
        int maxIndex = 0, minIndex = 0;

        for (int i = 1; i < arr.size(); i++) {
            minIndex = i - 1;
            maxIndex = i;
            if (val < arr.get(i)) {
                break;
            }
        }
        return Math.abs(arr.get(maxIndex) - val) > Math.abs(arr.get(minIndex) - val) ? minIndex : maxIndex;
    }
}
