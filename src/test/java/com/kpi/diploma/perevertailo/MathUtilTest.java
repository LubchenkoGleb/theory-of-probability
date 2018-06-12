package com.kpi.diploma.perevertailo;

import com.kpi.diploma.perevertailo.service.util.calculator.math.KohrenTable;
import com.kpi.diploma.perevertailo.service.util.calculator.math.LaplaceTable;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import org.junit.Assert;
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
        double res = Math.log10(4.688);
        System.out.println("res=" + res);

        double temp1 = 8 * Math.log10(3.2);
        double temp2 = 12 * Math.log10(3.8);
        double temp3 = 14 * Math.log10(6.3);
        System.out.println(temp1 + " " + temp2 + " " + temp3);

    }

    @Test
    public void testKohernTable() {

        Assert.assertEquals(0.9985, KohrenTable.getValues(1, 1), 0.0001);
        Assert.assertEquals(0.9750, KohrenTable.getValues(2, 2), 0.0001);
        Assert.assertEquals(0.9669, KohrenTable.getValues(3, 1), 0.0001);
        Assert.assertEquals(0.2187, KohrenTable.getValues(11, 7), 0.0001);
        Assert.assertEquals(0.0, KohrenTable.getValues(121, 7), 0.0001);
        Assert.assertEquals(0.6602, KohrenTable.getValues(1, 36), 0.0001);
        Assert.assertEquals(0.5, KohrenTable.getValues(1, 37), 0.0001);
        Assert.assertEquals(0.5, KohrenTable.getValues(1, 1000), 0.0001);
        Assert.assertEquals(0.6602, KohrenTable.getValues(1, 30), 0.0001);
    }

    @Test
    public void test4() {
        double res = MathUtil.roundDouble(-1.6, 3);
        System.out.println("res=" + res);
    }
}
