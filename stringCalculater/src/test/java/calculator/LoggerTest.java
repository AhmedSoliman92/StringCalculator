package calculator;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;


import org.junit.Test;

public class LoggerTest {
	
	StringCalculator stringCalculator = new StringCalculator();
	 
	@Test
	public void addEmptyStringWithLoggerTest() {
		StringCalculator.add("");
		
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		assertThat(loggingListener.getData() , containsString("Total SUM equals: 0"));	
	}
	@Test
	public void addOneNumbersWithLoggerTest() {
		StringCalculator.add("1");
		
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		assertThat(loggingListener.getData() , containsString("Total SUM equals: 1"));
		
	}
	@Test
	public void addTwoNumbersWithLoggerTest() {
		StringCalculator.add("1,2");
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		assertThat(loggingListener.getData() , containsString("Total SUM equals: 3"));
		
	}
	
	@Test
	public void calculateTwoNumbersWithNewlineWithLogger() {
		StringCalculator.add("2\n1");
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		assertThat(loggingListener.getData() , containsString("Total SUM equals: 3"));	
	}
	@Test
	public void calculateTwoNumbersWithCustomDelimiterWithLogger() {
		StringCalculator.add("//[x][y]\n2x1y3");
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		assertThat(loggingListener.getData() , containsString("Total SUM equals: 6"));
		
	}
	@Test
	public void calculateOneThousandOnePlusTwoWithLogger() {
		StringCalculator.add("2,1001");
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		assertThat(loggingListener.getData() , containsString("Total SUM equals: 2"));
		
	}
	@Test
	public void calculateThreeNumbersWithTwoCustomDelimiterWithLogger() {
		StringCalculator.add("//[xx]\n2xx1");
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		assertThat(loggingListener.getData() , containsString("Total SUM equals: 3"));
		
	}
	@Test
	public void calculateTwoNegativeNumbersWithLoggerException() {
		final LoggingListener loggingListener = stringCalculator.getLoggingListener();
		Logger.getLogger("calculator").addHandler(loggingListener);
		try {
			StringCalculator.add("-1,-2");
			
		}catch(IllegalArgumentException e) {
			assertThat(loggingListener.getData() , containsString("negatives not allowed (-1,-2)"));
	}
}
}