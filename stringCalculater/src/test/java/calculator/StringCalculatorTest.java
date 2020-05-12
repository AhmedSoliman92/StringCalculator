package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import calculator.StringCalculator.InnerStringCalculator;

public class StringCalculatorTest {
    @Test
    public void calculateEmptyResult() {
        Assert.assertEquals(0, StringCalculator.add(""));
       
    }

    @Test
    public void calculateSingleNumber() {
        Assert.assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void calculateTwoNumbers() {
        Assert.assertEquals(3, StringCalculator.add("2,1"));
    }

    @Test
    public void calculateTwoNumbersWithNewline() {
        Assert.assertEquals(3, StringCalculator.add("2\n1"));
    }

    @Test
    public void calculateTwoNumbersWithCustomDelimiter() {
        Assert.assertEquals(3, StringCalculator.add("//[x]\n2x1"));
    }

    @Test
    public void calculateTwoNumbersWithCustomDelimiterWithLengthTwo() {
        Assert.assertEquals(3, StringCalculator.add("//[xx]\n2xx1"));
    }

    @Test
    public void calculateThreeNumbersWithTwoCustomDelimiter() {
        Assert.assertEquals(6, StringCalculator.add("//[x][y]\n2x1y3"));
    }

    @Test
    public void calculateSingleNegativeNumber() {
        try {
            StringCalculator.add("-1");
            
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed (-1)", e.getMessage());
        }
    }

    @Test
    public void calculateTwoNegativeNumbersException() {
        try {
            StringCalculator.add("-1,-2");
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed (-1,-2)", e.getMessage());
        }
    }

    @Test
    public void calculateOneThousandOnePlusTwo() {
        Assert.assertEquals(2, StringCalculator.add("2,1001"));
    }
    @Test
    public void isItInValidRangeTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertTrue(innerStringCalculator.isItInValidRange(999));
    }
    @Test
    public void isItInValidRangeWithOneThousandToOneTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertFalse(innerStringCalculator.isItInValidRange(1001));
    }
    @Test
    public void isMinusOneNegativeTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertTrue(innerStringCalculator.isItNegative(-1));
    }
    @Test
    public void isOneNegativeTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertFalse(innerStringCalculator.isItNegative(1));
    }
    @Test
    public void addSingleNumberTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals(1,innerStringCalculator.addSingleNumber("1"));
    }
    @Test
    public void addSingleNegativeNumbersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals(0,innerStringCalculator.addSingleNumber("-1"));
    }
    @Test
    public void addSingleInvalidNumberTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals(0,innerStringCalculator.addSingleNumber("1001"));
    }
    
    @Test
    public void hasNotNegativeNumbersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	List<String> negativeNumbers=new ArrayList<String>();
    	assertFalse(innerStringCalculator.hasNegativeNumbers(negativeNumbers));
    }
    
    @Test
    public void hasNegativeNumbersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	List<String> negativeNumbers=new ArrayList<String>();
    	negativeNumbers.add("-1");
    	assertTrue(innerStringCalculator.hasNegativeNumbers(negativeNumbers));
    }
    @Test
    public void throwExceptionIfNegativeNumbersExistTestException() {
    	try {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	List<String> negativeNumbers=new ArrayList<String>();
    	negativeNumbers.add("-1");
    	innerStringCalculator.throwExceptionIfNegativeNumbersExist(negativeNumbers);
        } catch (IllegalArgumentException e) {
            assertEquals("negatives not allowed (-1)", e.getMessage());
        }
    }
    @Test
    public void throwExceptionIfNegativeNumbersExistTest() {
    	try {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	List<String> negativeNumbers=new ArrayList<String>();
    	innerStringCalculator.throwExceptionIfNegativeNumbersExist(negativeNumbers);
        } catch (IllegalArgumentException e) {
            assertEquals("", e.getMessage());
        }
    }
    @Test
    public void calculateSumTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	String [] calculateSumVariable= {"2","1"};
    	 assertEquals(3,innerStringCalculator.calculateSum(calculateSumVariable) );
    }
    @Test
    public void calculateNegativeSumTestException() {
    	try {
    		InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    		String[] negativeNumbers= {"-1","-2"};
        	
        	innerStringCalculator.calculateSum(negativeNumbers);
    	}catch(IllegalArgumentException e) {
    		assertEquals("negatives not allowed (-1)", e.getMessage());
    	}
    }
    @Test
    public void splitNumberByDelimiter() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	  assertEquals("[2, 1]",Arrays.toString(innerStringCalculator.splitNumberByDelimiter("2\n1")));
    }
    @Test
    public void addDelimitersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
     	assertEquals("[,\n]x|",innerStringCalculator.addDelimiters("//[x]\n2x1"));
    }
    
    @Test
    public void addDelimitersTwoCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
     	assertEquals("[,\n]x|y|",innerStringCalculator.addDelimiters("//[x][y]\n2x1y3"));
    }
    
    @Test
    public void addDelimitersWithoutCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
     	assertEquals("[,\n]",innerStringCalculator.addDelimiters("2,1"));
    }
    
    @Test
    public void determineDelimiterWithCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
        assertEquals("x",innerStringCalculator.determineDelimiter("//[x]\n2x1"));	
    }
    
    @Test
    public void determineDelimiterWithRepeatedCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("xx",innerStringCalculator.determineDelimiter("//[xx]\n2xx1"));
    }
    @Test
    public void determineDelimiterWithTwoCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("x|y",innerStringCalculator.determineDelimiter("//[x][y]\n2x1y3"));
    }
    
    @Test
    public void findLineWithNumbersAndDelimitersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("1;2",innerStringCalculator.findLineWithNumbersAndDelimiters("//;\n1;2"));
    }
    
    @Test
    public void findLineWithNumbersAndCustomDelimitersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("2x1",innerStringCalculator.findLineWithNumbersAndDelimiters("//[x]\n2x1"));
    }
    
    @Test
    public void findLineWithNumbersAndRepeatedDelimitersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("2xx1",innerStringCalculator.findLineWithNumbersAndDelimiters("//[xx]\n2xx1"));
    }
    
    @Test
    public void findLineWithNumbersAndTwoDelimitersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("2x1y3",innerStringCalculator.findLineWithNumbersAndDelimiters("//[x][y]\n2x1y3"));
    }
    @Test
    public void hasDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertTrue(innerStringCalculator.hasDelimiter("//[x][y]\n2x1y3"));
    }
    @Test
    public void hasNotDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertFalse(innerStringCalculator.hasDelimiter("2,1"));
    }
    @Test
    public void determineDelimiterAndFindLineWithNumbersTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("1;2",innerStringCalculator.findLineWithNumbersAndDelimiters("//;\n1;2"));
    }
    @Test
    public void determineDelimiterAndFindLineWithNumbersWithCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("2x1",innerStringCalculator.findLineWithNumbersAndDelimiters("//[x]\n2x1"));
    }
    @Test
    public void determineDelimiterAndFindLineWithNumbersWithRepeatedCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("2xx1",innerStringCalculator.findLineWithNumbersAndDelimiters("//[xx]\n2xx1"));
    }
    @Test
    public void determineDelimiterAndFindLineWithNumbersWithTwoCustomDelimiterTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertEquals("2x1y3",innerStringCalculator.findLineWithNumbersAndDelimiters("//[x][y]\n2x1y3"));
    }
    @Test
    public void isNumberEmptyTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertFalse(innerStringCalculator.isNumberEmpty("2,1"));
    }
    @Test
    public void isNumberNotEmptyTest() {
    	InnerStringCalculator innerStringCalculator=new InnerStringCalculator();
    	assertTrue(innerStringCalculator.isNumberEmpty(""));
    }
    @Test
    public void calculatePlainText() {
    	try { StringCalculator.add("text");
    	} catch(NumberFormatException nfe) {
    		assertEquals("For input string: \"text\"",nfe.getMessage());
    	}
    }
    @Test
    public void addFortyIntegerNumbersTest() {
    	assertEquals(40,StringCalculator.add("1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1"));
    	    }
   }
