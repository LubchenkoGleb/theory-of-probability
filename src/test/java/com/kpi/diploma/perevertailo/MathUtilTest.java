package com.kpi.diploma.perevertailo;

import com.kpi.diploma.perevertailo.service.util.calculator.math.LaplaceTable;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import org.junit.Test;

public class MathUtilTest {

    @Test
    public void testMathUtil() {
        double notLessRss = MathUtil.puassonSum(8, 100, 100, 0.01);
        System.out.println("notLessRss=" + notLessRss);
        double moreRes = MathUtil.puassonSum(9 + 1, 100, 100, 0.01);
        System.out.println("moreRes=" + moreRes);
    }

    @Test
    public void test2() {
        double x = 2.37;

        double res = LaplaceTable.getNeares(x);
        System.out.println(res);

        res = LaplaceTable.getNeares(-x);
        System.out.println(res);
    }

    @Test
    public void test3() {
        double res = MathUtil.calcLaplase(0, 5, 10, 0.8);
        System.out.println("res=" + res);
    }
}
