package com.kpi.diploma.perevertailo.service.util.calculator.impl.needImplement;
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
    private static final ThemeValues THEME_VALUES = ThemeValues.FORMULA_BERNULI;

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
            "<table><tr><th>Y/X</th><td>{{"+ PARAM_X1 +"}}</td><td>{{"+ PARAM_X2 +"}}</td><td>{{"+ PARAM_X3 +"}}</td></tr><tr><th>{{"+ PARAM_Y1 +"}}</th><td>{{"+ PARAM_P11 +"}}</td><td>{{"+ PARAM_P12 +"}}</td><td>{{"+ PARAM_P13 +"}}</td></tr><tr><th>{{"+ PARAM_Y2 +"}}</th><td>{{"+ PARAM_P21 +"}}</td><td>{{"+ PARAM_P22 +"}}</td><td>{{"+ PARAM_P23 +"}}</td></tr></table> <br>"+
            "Знайти математичне сподівання, дисперсію та середньоквадратичне відхилення X і Y. Знайти умовний закон розподілу X і умовний закон розподілу Y. Знайти коваріацію, коефіцієнт корреляції.";
    private static final String ANSWER_TEMPLATE = "1. Математичне сподівання M(X) :<br>" +
            "M(X) = {{"+ PARAM_MX +"}}; <br>" +
            "Дисперсія D(X): <br>" +
            "D(X) = {{"+ PARAM_DX +"}}; <br>" +
            "Середньоквадратичне відхилення: <br>" +
            "σ(x)  = {{" + PARAM_SIGMAX + "}} <br>" +
            "Математичне сподівання M(Y) :<br>" +
            "M(Y) = {{"+ PARAM_MY +"}}; <br>" +
            "Дисперсія D(Y): <br>" +
            "D(Y) = {{"+ PARAM_DY +"}}; <br>" +
            "Середньоквадратичне відхилення: <br>" +
            "σ(y)  = {{" + PARAM_SIGMAY + "}} <br>" +

            "2. Умовний закон розподілу X: <br>" +
            "Умовний закон розподілу X(Y = {{"+ PARAM_Y1 +"}}). <br>" +
            "P(X = {{"+ PARAM_X1 +"}}/Y = {{"+ PARAM_Y1 +"}}) = {{"+ PARAM_PXY11 +"}} <br>" +
            "P(X = {{"+ PARAM_X2 +"}}/Y = {{"+ PARAM_Y1 +"}}) = {{"+ PARAM_PXY12 +"}} <br>" +
            "P(X = {{"+ PARAM_X3 +"}}/Y = {{"+ PARAM_Y1 +"}}) = {{"+ PARAM_PXY13 +"}} <br>" +
            "Умовне математичне сподівання M(X/Y = {{"+ PARAM_Y1 +"}}) : <br>" +
            "M(X/Y = {{"+ PARAM_Y1 +"}}) = {{"+ PARAM_MPXY1 +"}} <br>" +
            "Умовна дисперсія D(X/Y = {{"+ PARAM_Y1 +"}}) <br>" +
            "D(X/Y = {{"+ PARAM_Y1 +"}}) = {{"+ PARAM_DPXY1 +"}} <br>" +

            "Умовний закон розподілу X(Y = {{"+ PARAM_Y2 +"}}). <br>" +
            "P(X = {{"+ PARAM_X1 +"}}/Y = {{"+ PARAM_Y2 +"}}) = {{"+ PARAM_PXY21 +"}} <br>" +
            "P(X = {{"+ PARAM_X2 +"}}/Y = {{"+ PARAM_Y2 +"}}) = {{"+ PARAM_PXY22 +"}} <br>" +
            "P(X = {{"+ PARAM_X3 +"}}/Y = {{"+ PARAM_Y2 +"}}) = {{"+ PARAM_PXY23 +"}} <br>" +
            "Умовне математичне сподівання M(X/Y = {{"+ PARAM_Y2 +"}}) : <br>" +
            "M(X/Y = {{"+ PARAM_Y2 +"}}) = {{"+ PARAM_MPXY2 +"}} <br>" +
            "Умовна дисперсія D(X/Y = {{"+ PARAM_Y2 +"}}) <br>" +
            "D(X/Y = {{"+ PARAM_Y2 +"}}) = {{"+ PARAM_DPXY2 +"}} <br>" +

            "3. Умовний закон розподілу Y: <br>" +
            " Умовний закон розподілу Y(X = {{"+ PARAM_X1 +"}}). <br>" +
            " P(Y = {{"+ PARAM_Y1 +"}}/X = {{"+ PARAM_X1 +"}}) = {{"+ PARAM_PYX11 +"}} <br>" +
            " P(Y = {{"+ PARAM_Y2 +"}}/X = {{"+ PARAM_X1 +"}}) = {{"+ PARAM_PYX12 +"}} <br>" +
            "Умовне математичне сподівання M(Y/X = {{"+ PARAM_X1 +"}}) : <br>" +
            "M(Y/X = {{"+ PARAM_X1 +"}}) = {{"+ PARAM_MPYX1 +"}} <br>" +
            "Умовна дисперсія D(Y/X = {{"+ PARAM_X1 +"}}) <br>" +
            "D(Y/X = {{"+ PARAM_X1 +"}}) = {{"+ PARAM_DPYX1 +"}} <br>" +

            " Умовний закон розподілу Y(X = {{"+ PARAM_X2 +"}}). <br>" +
            " P(Y = {{"+ PARAM_Y1 +"}}/X = {{"+ PARAM_X2 +"}}) = {{"+ PARAM_PYX21 +"}} <br>" +
            " P(Y = {{"+ PARAM_Y2 +"}}/X = {{"+ PARAM_X2 +"}}) = {{"+ PARAM_PYX22 +"}} <br>" +
            "Умовне математичне сподівання M(Y/X = {{"+ PARAM_X2 +"}}) : <br>" +
            "M(Y/X = {{"+ PARAM_X2 +"}}) = {{"+ PARAM_MPYX2 +"}} <br>" +
            "Умовна дисперсія D(Y/X = {{"+ PARAM_X2 +"}}) <br>" +
            "D(Y/X = {{"+ PARAM_X2 +"}}) = {{"+ PARAM_DPYX2 +"}} <br>" +

            " Умовний закон розподілу Y(X = {{"+ PARAM_X3 +"}}). <br>" +
            " P(Y = {{"+ PARAM_Y1 +"}}/X = {{"+ PARAM_X3 +"}}) = {{"+ PARAM_PYX31 +"}} <br>" +
            " P(Y = {{"+ PARAM_Y2 +"}}/X = {{"+ PARAM_X3 +"}}) = {{"+ PARAM_PYX32 +"}} <br>" +
            "Умовне математичне сподівання M(Y/X = {{"+ PARAM_X3 +"}}) : <br>" +
            "M(Y/X = {{"+ PARAM_X3 +"}}) = {{"+ PARAM_MPYX3 +"}} <br>" +
            "Умовна дисперсія D(Y/X = {{"+ PARAM_X3 +"}}) <br>" +
            "D(Y/X = {{"+ PARAM_X3 +"}}) = {{"+ PARAM_DPYX3 +"}} <br>" +

            "Коваріація: <br>" +
            "cov(X,Y) = M[X*Y] - M[X]*M[Y] = {{"+ PARAM_COV +"}} <br>" +
            "Якщо випадкові величини незалежні, то їх коваріація рівна 0. <br>" +
            "Коефіцієнт корреляції: <br>" +
            "r<sub>x,y</sub> = {{"+ PARAM_R +"}}";



    private static final String QUESTION_TO_STUDENT = QUESTION_TEMPLATE +"(округлити максимум до другого знаку)";


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
        double dxRes = (px1)* x1 + (px2) * x2 + (px3) * x3;
        double mxRes = Math.pow(px1, 2)* x1 + Math.pow(px2, 2) * x2 + Math.pow(px3, 2) * x3;
        double sigxRes = Math.sqrt(mxRes);

        double py1 = p11 + p12 + p13;
        double py2 = p21 + p22 + p23;
        double dyRes = (py1)* y1 + (py2) * y2;
        double myRes = Math.pow(py1, 2)* y1 + Math.pow(py2, 2) * y2;
        double sigyRes = Math.sqrt(myRes);


//        int res;
//
//        double low = n * p - (1 - p);
//        if (low != Math.floor(low)) {
//            res = (int) Math.floor(low) + 1;
//        } else if (low == Math.floor(low)) {
//            res = (int) low;
//        } else {
//            res = (int) (n * p);
//        }
//

        HashMap<String, Object> calculatedData = new HashMap<>();
//        calculatedData.put(PARAM_K0, res);
        log.info("'calculatedData={}'", calculatedData);

        CalculationData calculationData = new CalculationData(calculatedData, "");

        return calculationData;
    }
}

