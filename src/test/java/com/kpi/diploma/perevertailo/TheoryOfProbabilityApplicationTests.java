package com.kpi.diploma.perevertailo;

import com.kpi.diploma.perevertailo.service.util.calculator.impl.CalculatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
//@SpringBootTest
public class TheoryOfProbabilityApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRegex() {

		CalculatorImpl calculator = new CalculatorImpl(
				"", "", "", "", null);

		String param1Name = "test1";
		String param2Name = "test2";
		HashMap<String, Object> params = new HashMap<>();
		params.put(param1Name, 1);
		params.put(param2Name, 2);

		String testString1 = "test string with param {{" + param1Name + "}} and {{" + param2Name + "}}";

		boolean result = calculator.checkAllPlaceHoldersIsPresent(testString1, params);

		String matches = calculator.changePlaceHoldersToValues(testString1, params);
	}

}
