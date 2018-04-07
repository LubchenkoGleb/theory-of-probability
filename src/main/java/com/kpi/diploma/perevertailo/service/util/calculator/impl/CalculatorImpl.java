package com.kpi.diploma.perevertailo.service.util.calculator.impl;

import com.kpi.diploma.perevertailo.model.pojo.CalculationData;
import com.kpi.diploma.perevertailo.model.util.value.ThemeValues;
import com.kpi.diploma.perevertailo.service.util.calculator.Calculator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Slf4j
public class CalculatorImpl implements Calculator {

    private final String name;

    private final String fullName;

    private final String questionTemplate;

    private final String answerTemplate;

    private final ThemeValues themeValues;

    public CalculatorImpl(String name, String fullName, String questionTemplate, String answerTemplate, ThemeValues themeValue) {
        this.name = name;
        this.fullName = fullName;
        this.questionTemplate = questionTemplate;
        this.answerTemplate = answerTemplate;
        this.themeValues = themeValue;
    }

    @Override
    public CalculationData calculate(Map<String, Object> input) {
        return null;
    }

    public String changePlaceHoldersToValues(String input, Map<String, Object> params) {

        List<String> matches  = getRegexMatches("(\\{\\{\\w+}})", input);

        for (String match : matches) {
            String matchWithoutBrackets = match.substring(2, match.length() - 2);
            log.info("'matchWithoutBrackets={}'", matchWithoutBrackets);
            input = input.replace(match, params.get(matchWithoutBrackets).toString());
        }

        return input;
    }

    public boolean checkAllPlaceHoldersIsPresent(String input, Map<String, Object> params) {

        List<String> regexMatches = getRegexMatches("\\{\\{(.+?)}}", input);
        log.info("matches={}'", regexMatches);

        Optional<String> any = regexMatches.stream().filter(match -> !params.containsKey(match)).findAny();

        if(any.isPresent()) {
            return false;
        } else {
            return true;
        }
    }

    private List<String> getRegexMatches(String regex, String input) {

        Pattern compile = Pattern.compile(regex);
        Matcher matcher = compile.matcher(input);
        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group(1));
        }

        return matches;
    }
}
