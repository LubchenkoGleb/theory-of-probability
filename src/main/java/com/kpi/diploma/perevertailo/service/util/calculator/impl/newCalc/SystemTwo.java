package com.kpi.diploma.perevertailo.service.util.calculator.impl.newCalc;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import com.kpi.diploma.perevertailo.service.util.calculator.math.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SystemTwo extends CalculatorImpl {
    public static final String NAME = "SystemOfRandomVariables";
    private static final String FULL_NAME = "Системи випадкових величин";
    private static final ThemeValues THEME_VALUES = ThemeValues.SYSTEMS_OF_TWO_RANDOM_VALUES;

    private static final String PARAM_X1 = "x1", PARAM_X2 = "x2", PARAM_X3 = "x3";
    private static final String PARAM_Y1 = "y1", PARAM_Y2 = "y2";
    private static final String PARAM_P11 = "p11", PARAM_P12 = "p12", PARAM_P13 = "p13";
    private static final String PARAM_P21 = "p21", PARAM_P22 = "p22", PARAM_P23 = "p23";

    private static final String PARAM_MX = "mx";
    private static final String PARAM_DX = "dx";
    private static final String PARAM_SIGMAX = "sigmax";
    private static final String PARAM_MY = "my";
    private static final String PARAM_DY = "dy";
    private static final String PARAM_SIGMAY = "sigmay";

    private static final String PARAM_PXY11 = "pxy11";
    private static final String PARAM_PXY12 = "pxy12";
    private static final String PARAM_PXY13 = "pxy13";
    private static final String PARAM_PXY21 = "pxy21";
    private static final String PARAM_PXY22 = "pxy22";
    private static final String PARAM_PXY23 = "pxy23";
    private static final String PARAM_MPXY1 = "mpxy1";
    private static final String PARAM_MPXY2 = "mpxy2";
    private static final String PARAM_DPXY1 = "dpxy1";
    private static final String PARAM_DPXY2 = "dpxy2";

    private static final String PARAM_PYX11 = "pyx11";
    private static final String PARAM_PYX12 = "pyx12";
    private static final String PARAM_PYX21 = "pyx21";
    private static final String PARAM_PYX22 = "pyx22";
    private static final String PARAM_PYX31 = "pyx31";
    private static final String PARAM_PYX32 = "pyx32";

    private static final String PARAM_MPYX1 = "mpyx1";
    private static final String PARAM_MPYX2 = "mpyx2";
    private static final String PARAM_MPYX3 = "mpyx3";
    private static final String PARAM_DPYX1 = "dpyx1";
    private static final String PARAM_DPYX2 = "dpyx2";
    private static final String PARAM_DPYX3 = "dpyx3";

    private static final String PARAM_COV = "cov";
    private static final String PARAM_R = "r";

    private static final String QUESTION_TEMPLATE = "Задана дискретна двомірна випадкова величина (X,Y) <br>:" +
            "<table><tr><th>Y/X</th><td>{{" + PARAM_X1 + "}}</td><td>{{" + PARAM_X2 + "}}</td><td>{{" + PARAM_X3 + "}}</td></tr><tr><th>{{" + PARAM_Y1 + "}}</th><td>{{" + PARAM_P11 + "}}</td><td>{{" + PARAM_P12 + "}}</td><td>{{" + PARAM_P13 + "}}</td></tr><tr><th>{{" + PARAM_Y2 + "}}</th><td>{{" + PARAM_P21 + "}}</td><td>{{" + PARAM_P22 + "}}</td><td>{{" + PARAM_P23 + "}}</td></tr></table> <br>" +
            "Знайти математичне сподівання, дисперсію та середньоквадратичне відхилення X і Y. Знайти умовний закон розподілу X і умовний закон розподілу Y. Знайти коваріацію, коефіцієнт корреляції.";
    private static final String ANSWER_TEMPLATE = "<h2>1. Математичне сподівання M(X) :</h2>" +
            "M(X) = {{" + PARAM_MX + "}}; <br>" +
            "Дисперсія D(X): <br>" +
            "D(X) = {{" + PARAM_DX + "}}; <br>" +
            "Середньоквадратичне відхилення: <br>" +
            "σ(x)  = {{" + PARAM_SIGMAX + "}} <br>" +
            "Математичне сподівання M(Y) :<br>" +
            "M(Y) = {{" + PARAM_MY + "}}; <br>" +
            "Дисперсія D(Y): <br>" +
            "D(Y) = {{" + PARAM_DY + "}}; <br>" +
            "Середньоквадратичне відхилення: <br>" +
            "σ(y)  = {{" + PARAM_SIGMAY + "}} <br>" +

            "<h2>2. Умовний закон розподілу X: </h2>" +
            "<b>Умовний закон розподілу X(Y = {{" + PARAM_Y1 + "}}). </b><br>" +
            "P(X = {{" + PARAM_X1 + "}}/Y = {{" + PARAM_Y1 + "}}) = {{" + PARAM_PXY11 + "}} <br>" +
            "P(X = {{" + PARAM_X2 + "}}/Y = {{" + PARAM_Y1 + "}}) = {{" + PARAM_PXY12 + "}} <br>" +
            "P(X = {{" + PARAM_X3 + "}}/Y = {{" + PARAM_Y1 + "}}) = {{" + PARAM_PXY13 + "}} <br>" +
            "Умовне математичне сподівання M(X/Y = {{" + PARAM_Y1 + "}}) : <br>" +
            "M(X/Y = {{" + PARAM_Y1 + "}}) = {{" + PARAM_MPXY1 + "}} <br>" +
            "Умовна дисперсія D(X/Y = {{" + PARAM_Y1 + "}}) <br>" +
            "D(X/Y = {{" + PARAM_Y1 + "}}) = {{" + PARAM_DPXY1 + "}} <br>" +

            "<b>Умовний закон розподілу X(Y = {{" + PARAM_Y2 + "}}). </b><br>" +
            "P(X = {{" + PARAM_X1 + "}}/Y = {{" + PARAM_Y2 + "}}) = {{" + PARAM_PXY21 + "}} <br>" +
            "P(X = {{" + PARAM_X2 + "}}/Y = {{" + PARAM_Y2 + "}}) = {{" + PARAM_PXY22 + "}} <br>" +
            "P(X = {{" + PARAM_X3 + "}}/Y = {{" + PARAM_Y2 + "}}) = {{" + PARAM_PXY23 + "}} <br>" +
            "Умовне математичне сподівання M(X/Y = {{" + PARAM_Y2 + "}}) : <br>" +
            "M(X/Y = {{" + PARAM_Y2 + "}}) = {{" + PARAM_MPXY2 + "}} <br>" +
            "Умовна дисперсія D(X/Y = {{" + PARAM_Y2 + "}}) <br>" +
            "D(X/Y = {{" + PARAM_Y2 + "}}) = {{" + PARAM_DPXY2 + "}} <br>" +


            "<h2>3. Умовний закон розподілу Y: </h2>" +
            "<b> Умовний закон розподілу Y(X = {{" + PARAM_X1 + "}}). </b><br>" +
            " P(Y = {{" + PARAM_Y1 + "}}/X = {{" + PARAM_X1 + "}}) = {{" + PARAM_PYX11 + "}} <br>" +
            " P(Y = {{" + PARAM_Y2 + "}}/X = {{" + PARAM_X1 + "}}) = {{" + PARAM_PYX12 + "}} <br>" +
            "Умовне математичне сподівання M(Y/X = {{" + PARAM_X1 + "}}) : <br>" +
            "M(Y/X = {{" + PARAM_X1 + "}}) = {{" + PARAM_MPYX1 + "}} <br>" +
            "Умовна дисперсія D(Y/X = {{" + PARAM_X1 + "}}) <br>" +
            "D(Y/X = {{" + PARAM_X1 + "}}) = {{" + PARAM_DPYX1 + "}} <br>" +

            "<b> Умовний закон розподілу Y(X = {{" + PARAM_X2 + "}}). </b><br>" +
            " P(Y = {{" + PARAM_Y1 + "}}/X = {{" + PARAM_X2 + "}}) = {{" + PARAM_PYX21 + "}} <br>" +
            " P(Y = {{" + PARAM_Y2 + "}}/X = {{" + PARAM_X2 + "}}) = {{" + PARAM_PYX22 + "}} <br>" +
            "Умовне математичне сподівання M(Y/X = {{" + PARAM_X2 + "}}) : <br>" +
            "M(Y/X = {{" + PARAM_X2 + "}}) = {{" + PARAM_MPYX2 + "}} <br>" +
            "Умовна дисперсія D(Y/X = {{" + PARAM_X2 + "}}) <br>" +
            "D(Y/X = {{" + PARAM_X2 + "}}) = {{" + PARAM_DPYX2 + "}} <br>" +

            "<b> Умовний закон розподілу Y(X = {{" + PARAM_X3 + "}}). </b><br>" +
            " P(Y = {{" + PARAM_Y1 + "}}/X = {{" + PARAM_X3 + "}}) = {{" + PARAM_PYX31 + "}} <br>" +
            " P(Y = {{" + PARAM_Y2 + "}}/X = {{" + PARAM_X3 + "}}) = {{" + PARAM_PYX32 + "}} <br>" +
            "Умовне математичне сподівання M(Y/X = {{" + PARAM_X3 + "}}) : <br>" +
            "M(Y/X = {{" + PARAM_X3 + "}}) = {{" + PARAM_MPYX3 + "}} <br>" +
            "Умовна дисперсія D(Y/X = {{" + PARAM_X3 + "}}) <br>" +
            "D(Y/X = {{" + PARAM_X3 + "}}) = {{" + PARAM_DPYX3 + "}} <br>" +

            "<h2>Коваріація: </h2><br>" +
            "cov(X,Y) = M[X*Y] - M[X]*M[Y] = {{" + PARAM_COV + "}} <br>" +
            "Якщо випадкові величини незалежні, то їх коваріація рівна 0. <br>" +
            "Коефіцієнт корреляції: <br>" +
            "r<sub>x,y</sub> = {{" + PARAM_R + "}}";


    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE + "(округлити максимум до другого знаку)";


    public SystemTwo() {
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }

    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
        log.info("'calculate' invoked with params'{}'", inputData);

        Integer x1 = Integer.valueOf(inputData.get(PARAM_X1).toString());
        Integer x2 = Integer.valueOf(inputData.get(PARAM_X2).toString());
        Integer x3 = Integer.valueOf(inputData.get(PARAM_X3).toString());
        Double y1 = Double.valueOf(inputData.get(PARAM_Y1).toString());
        Double y2 = Double.valueOf(inputData.get(PARAM_Y2).toString());
        Double p11 = Double.valueOf(inputData.get(PARAM_P11).toString());
        Double p12 = Double.valueOf(inputData.get(PARAM_P12).toString());
        Double p13 = Double.valueOf(inputData.get(PARAM_P13).toString());
        Double p21 = Double.valueOf(inputData.get(PARAM_P21).toString());
        Double p22 = Double.valueOf(inputData.get(PARAM_P22).toString());
        Double p23 = Double.valueOf(inputData.get(PARAM_P23).toString());

        double px1 = p11 + p21;
        double px2 = p12 + p22;
        double px3 = p13 + p23;
        double mxRes = (px1) * x1 + (px2) * x2 + (px3) * x3;
        double dxRes = Math.pow(x1, 2) * px1 + Math.pow(x2, 2) * px2 + Math.pow(x3, 2) * px3 - mxRes * mxRes;
        double sigxRes = Math.sqrt(dxRes);

        double py1 = p11 + p12 + p13;
        double py2 = p21 + p22 + p23;
        double myRes = (py1) * y1 + (py2) * y2;
        double dyRes = Math.pow(y1, 2) * py1 + Math.pow(y2, 2) * py2 - myRes * myRes;
        double sigyRes = Math.sqrt(Math.abs(dyRes));


        double resPhy11 = p11 / py1;
        double resPhy12 = p12 / py1;
        double resPhy13 = p13 / py1;
        double resPhMy1 = x1 * resPhy11 + x2 * resPhy12 + x3 * resPhy13;
        double resPhDy1 = x1 * x1 * resPhy11 + x2 * x2 * resPhy12 + x3 * x3 * resPhy13 - resPhMy1 * resPhMy1;

        double resPhy21 = p21 / py2;
        double resPhy22 = p22 / py2;
        double resPhy23 = p23 / py2;
        double resPhMy2 = x1 * resPhy21 + x2 * resPhy22 + x3 * resPhy23;
        double resPhDy2 = x1 * x1 * resPhy21 + x2 * x2 * resPhy22 + x3 * x3 * resPhy23 - resPhMy2 * resPhMy2;


        double resPhx11 = p11 / px1;
        double resPhx21 = p21 / px1;
        double resPhMx1 = y1 * resPhx11 + y2 * resPhx21;
        double resPhDx1 = y1 * y1 * resPhx11 + y2 * y2 * resPhx21 - resPhMx1 * resPhMx1;

        double resPhx12 = p12 / px2;
        double resPhx22 = p22 / px2;
        double resPhMx2 = y1 * resPhx12 + y2 * resPhx22;
        double resPhDx2 = y1 * y1 * resPhx12 + y2 * y2 * resPhx22 - resPhMx2 * resPhMx2;

        double resPhx13 = p13 / px3;
        double resPhx23 = p23 / px3;
        double resPhMx3 = y1 * resPhx13 + y1 * resPhx23;
        double resPhDx3 = y1 * y1 * resPhx13 + y2 * y2 * resPhx23 - resPhMx3 * resPhMx3;

        double cov =
                x1 * y1 * p11 +
                        x2 * y1 * p12 +
                        x3 * y1 * p13 +
                        x1 * y2 * p21 +
                        x2 * y2 * p22 +
                        x3 * y2 * p23 - mxRes * myRes;

        double covCoef = cov / (sigxRes * sigyRes);

        HashMap<String, Object> calculatedData = new HashMap<>();
        calculatedData.put(PARAM_MX, MathUtil.roundDouble(mxRes, 3));
        calculatedData.put(PARAM_DX, MathUtil.roundDouble(dxRes, 3));
        calculatedData.put(PARAM_SIGMAX, MathUtil.roundDouble(sigxRes, 3));

        calculatedData.put(PARAM_MY, MathUtil.roundDouble(myRes, 3));
        calculatedData.put(PARAM_DY, MathUtil.roundDouble(dyRes, 3));
        calculatedData.put(PARAM_SIGMAY, MathUtil.roundDouble(sigyRes, 3));

        calculatedData.put(PARAM_PXY11, MathUtil.roundDouble(resPhy11, 3));
        calculatedData.put(PARAM_PXY12, MathUtil.roundDouble(resPhy12, 3));
        calculatedData.put(PARAM_PXY13, MathUtil.roundDouble(resPhy13, 3));
        calculatedData.put(PARAM_MPXY1, MathUtil.roundDouble(resPhMy1, 3));
        calculatedData.put(PARAM_DPXY1, MathUtil.roundDouble(resPhDy1, 3));

        calculatedData.put(PARAM_PXY21, MathUtil.roundDouble(resPhy21, 3));
        calculatedData.put(PARAM_PXY22, MathUtil.roundDouble(resPhy22, 3));
        calculatedData.put(PARAM_PXY23, MathUtil.roundDouble(resPhy23, 3));
        calculatedData.put(PARAM_MPXY2, MathUtil.roundDouble(resPhMy2, 3));
        calculatedData.put(PARAM_DPXY2, MathUtil.roundDouble(resPhDy2, 3));


        calculatedData.put(PARAM_PYX11, MathUtil.roundDouble(resPhx11, 3));
        calculatedData.put(PARAM_PYX12, MathUtil.roundDouble(resPhx21, 3));
        calculatedData.put(PARAM_MPYX1, MathUtil.roundDouble(resPhMx1, 3));
        calculatedData.put(PARAM_DPYX1, MathUtil.roundDouble(resPhDx1, 3));

        calculatedData.put(PARAM_PYX21, MathUtil.roundDouble(resPhx12, 3));
        calculatedData.put(PARAM_PYX22, MathUtil.roundDouble(resPhx22, 3));
        calculatedData.put(PARAM_MPYX2, MathUtil.roundDouble(resPhMx2, 3));
        calculatedData.put(PARAM_DPYX2, MathUtil.roundDouble(resPhDx2, 3));

        calculatedData.put(PARAM_PYX31, MathUtil.roundDouble(resPhx13, 3));
        calculatedData.put(PARAM_PYX32, MathUtil.roundDouble(resPhx23, 3));
        calculatedData.put(PARAM_MPYX3, MathUtil.roundDouble(resPhMx3, 3));
        calculatedData.put(PARAM_DPYX3, MathUtil.roundDouble(resPhDx3, 3));

        calculatedData.put(PARAM_COV, MathUtil.roundDouble(cov, 3));
        calculatedData.put(PARAM_R, MathUtil.roundDouble(covCoef, 3));
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}

