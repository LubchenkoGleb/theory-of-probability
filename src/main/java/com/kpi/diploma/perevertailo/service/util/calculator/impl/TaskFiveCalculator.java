package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;

import java.util.HashMap;
import java.util.Map;

public class TaskFiveCalculator extends CalculatorImpl {
    public static final String NAME = "Задача про стрілків";
    private static final String FULL_NAME = "Задача про стрілків";
    private static final ThemeValues THEME_VALUES = ThemeValues.ACTIONS_ON_EVENTS;
    private static final String PARAM_PFIRST = "pFirst"; // вероятность попадания первого
    private static final String PARAM_PSECOND = "pSecond"; //вероятность попадания второго
    private static final String PARAM_PANY = "pa"; //ймовірність що попаде 0
    private static final String PARAM_PONE = "po"; // ймовірність що попаде 1
    private static final String PARAM_PTWO = "pt"; // ймовірність що попадуть обидва

    private static final String QUESTION_TEMPLATE = "Два стрілка стріляють по мішені, ймовірність, що влучить перший P<sub>1</sub> = {{" + PARAM_PFIRST + "}}, другий P<sub>2</sub>{{" + PARAM_PSECOND + "}}.";
    private static final String ANSWER_TEMPLATE = "Ймовірність, що не влучить жоден: <br> P<sub>0</sub> ={{" + PARAM_PANY + "}}<br>" +
            "Ймовірність, що влучить один з них: <br> P<sub>1</sub> = {{" + PARAM_PONE + "}}<br> " +
            "Ймовірність, що влучать обидва: <br> P<sub>2</sub> ={{" + PARAM_PTWO +"}}.";
    private static final String QUESTION_TO_STUDENT = "";


    public TaskFiveCalculator(){
        super(NAME, FULL_NAME, QUESTION_TEMPLATE, QUESTION_TO_STUDENT, ANSWER_TEMPLATE, THEME_VALUES);
    }
    @Override
    public CalculationData calculate(Map<String, Object> inputData) {
    //    log.info("'calculate' invoked with params'{}'", inputData);
        Integer pFirst = (Integer) inputData.get(PARAM_PFIRST);
        Integer pSecond = (Integer) inputData.get(PARAM_PSECOND);
        StringBuilder calculation = new StringBuilder();
        int pa = 0;
        int po = 0;
        int pt = 0;
//        for (pFirst >= 0 && pFirst <= 1){
 //           for (pSecond >= 0 && pSecond <= 1){
               pa = (1 - pFirst)*(1 - pSecond);
               po = pFirst * (1 - pSecond) + pSecond * (1 - pFirst);
               pt = pFirst * pSecond;
                String calculations = "P<sub>0</sub> = q<sub>1</sub> * q<sub>2</sub> = " + pa + " <br>" +
                        "P<sub>1</sub> = p<sub>1</sub> * q<sub>2</sub> + p<sub>2</sub> * q<sub>1</sub>  = " + po + " <br>" +
                        "P<sub>3</sub> = p<sub>1</sub> * p<sub>2</sub> = " + pt + " ";
//               calculation.append("P<sup>n</n").append(n).append(" = ").append(pN).append("<br>");
//            }
//        }


      //    log.info("calculation={}'", calculation.toString());
          HashMap<String, Object> calculatedData = new HashMap<>();
          calculatedData.put(PARAM_PANY, pa);
          calculatedData.put(PARAM_PONE, po);
          calculatedData.put(PARAM_PTWO, pt);
        CalculationData calculationData = new CalculationData(calculatedData, calculation.toString());
        //log.info("'calculationData={}'", calculatedData);

        return calculationData;
    }
}
