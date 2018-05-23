package com.kpi.diploma.perevertailo;

import com.kpi.diploma.perevertailo.model.document.task.Answer;
import com.kpi.diploma.perevertailo.service.util.ConversionService;
import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@Slf4j
@RunWith(SpringRunner.class)
//@SpringBootTest
public class TheoryOfProbabilityApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRegex() {

		CalculatorImpl calculator = new CalculatorImpl(
				"", "", "", "", "", null);

		String param1Name = "test1";
		String param2Name = "test2";
		HashMap<String, Object> params = new HashMap<>();
		params.put(param1Name, 1);
		params.put(param2Name, 2);

		String testString1 = "test string with param {{" + param1Name + "}} and {{" + param2Name + "}}";

		boolean result = calculator.checkAllPlaceHoldersIsPresent(testString1, params);

		String matches = calculator.changePlaceHoldersToValues(testString1, params);
	}

	@Test
	public void testJsonSerialization() {

		Answer answer = new Answer();
		HashMap<String, Object> openAnswerValues = new HashMap<>();
		openAnswerValues.put("one", true);
		openAnswerValues.put("two", false);
		answer.setOpenAnswerValues(openAnswerValues);
		answer.setTaskId("test");

		log.info("test={}", ConversionService.convertToJsonNode(answer).toString());
	}
}
