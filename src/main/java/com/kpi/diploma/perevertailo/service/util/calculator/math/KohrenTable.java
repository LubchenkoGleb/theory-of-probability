package com.kpi.diploma.perevertailo.service.util.calculator.math;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class KohrenTable {

    private final static List<Integer> rows = new ArrayList<>(Arrays.asList(
            2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 20, 24, 30, 40, 60, 120));
    private final static List<Integer> columns = new ArrayList<>(Arrays.asList(
            1, 2, 3, 4, 5, 6, 8, 10, 16, 36, 37));

    private final static double[][] kohrenTable = {
            {0.9985, 0.9750, 0.9392, 0.9057, 0.8772, 0.8534, 0.8159, 0.7880, 0.7341, 0.6602, 0.5000},
            {0.9669, 0.8709, 0.0797, 0.7454, 0.7071, 0.6771, 0.6333, 0.6025, 0.5466, 0.4748, 0.3333},
            {0.9065, 0.7679, 0.6841, 0.6287, 0.5895, 0.5598, 0.5175, 0.4884, 0.4366, 0.3720, 0.2500},
            {0.8412, 0.6838, 0.5981, 0.5441, 0.5065, 0.4783, 0.4387, 0.4118, 0.3645, 0.3066, 0.2000},
            {0.7808, 0.6161, 0.5321, 0.4803, 0.4447, 0.4184, 0.3817, 0.3568, 0.3135, 0.2612, 0.1667},
            {0.7271, 0.5612, 0.4800, 0.4307, 0.3974, 0.3726, 0.3384, 0.3154, 0.2756, 0.2278, 0.1429},
            {0.6798, 0.5157, 0.4377, 0.3910, 0.3595, 0.3362, 0.3043, 0.2829, 0.2462, 0.2022, 0.1250},
            {0.6385, 0.4775, 0.4027, 0.3584, 0.7276, 0.3067, 0.2768, 0.2568, 0.2226, 0.1820, 0.1111},
            {0.6020, 0.4450, 0.3733, 0.3311, 0.3029, 0.2823, 0.2541, 0.2353, 0.2032, 0.1655, 0.1000},
            {0.5410, 0.3924, 0.3264, 0.2880, 0.2624, 0.2439, 0.2187, 0.2020, 0.1737, 0.1403, 0.0833},
            {0.4709, 0.3346, 0.2758, 0.2419, 0.2195, 0.2034, 0.1815, 0.1671, 0.1429, 0.1144, 0.0667},
            {0.3894, 0.2705, 0.2205, 0.1921, 0.1735, 0.1602, 0.1422, 0.1303, 0.1108, 0.0879, 0.0500},
            {0.3434, 0.2354, 0.1907, 0.1656, 0.1493, 0.1374, 0.1216, 0.1113, 0.0942, 0.0743, 0.0417},
            {0.2929, 0.1980, 0.1593, 0.1377, 0.1237, 0.1137, 0.1001, 0.0921, 0.0771, 0.0604, 0.0333},
            {0.2370, 0.1576, 0.1259, 0.1082, 0.0968, 0.0887, 0.5950, 0.0713, 0.0595, 0.0462, 0.0250},
            {0.1737, 0.1131, 0.0895, 0.0765, 0.0682, 0.0623, 0.0552, 0.0497, 0.0411, 0.0316, 0.0167},
            {0.0998, 0.0632, 0.0495, 0.0419, 0.0371, 0.0337, 0.0292, 0.0266, 0.0218, 0.0165, 0.0083},
    };

    public static double getValues(int row, int col) {

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
}
