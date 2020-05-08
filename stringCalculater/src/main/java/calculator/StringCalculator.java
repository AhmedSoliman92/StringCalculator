package calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class StringCalculator {

    public static int add(String number) {
        InnerStringCalculator stringCalculater = new InnerStringCalculator(number);
        return stringCalculater.add();
    }

    static class InnerStringCalculator {
        private  String number="";

        private String delimiter = "[,\n]";
        private String delimitersWithNumbers;
        private List<String> negativeTokens = new ArrayList<String>();
        public InnerStringCalculator(String number) {
            this.number = "";
			this.number = number;
        }

        public InnerStringCalculator() {
			this.number = "";
			
		}

		public int add() {
            if (isNumberEmpty(number)) {
                return 0;
            }
            determineDelimiterAndFindLineWithNumbers( number);
           
            String[] numberSplittedByDelimiter = splitNumberByDelimiter(delimitersWithNumbers);
            return calculateSum(numberSplittedByDelimiter);
        }

        public boolean isNumberEmpty(String number) {
            return number.length() == 0;
        }

        public String determineDelimiterAndFindLineWithNumbers(String number ) {
            if (hasDelimiter(number)) {
                determineDelimiter(number);
                findLineWithNumbersAndDelimiters(number);
            } else {
                delimitersWithNumbers = number;
            }
            return delimitersWithNumbers;
        }

        public boolean hasDelimiter(String number) {
            return number.startsWith("//");
        }


        public String findLineWithNumbersAndDelimiters(String number) {
            int firstNewLine = number.indexOf("\n");
            delimitersWithNumbers = number.substring(firstNewLine + 1);
            return delimitersWithNumbers;
        }

        public String determineDelimiter(String number) {
            delimiter = "";

            addDelimiters(number); 
            delimiter = StringUtils.chop(delimiter);
            return delimiter;
        }

        public String addDelimiters(String number) {
            int startIndexDelimiter = 0;
            while (true) {
                startIndexDelimiter = number.indexOf("[", startIndexDelimiter) + 1;
                if (startIndexDelimiter == 0) {
                    break;
                }
                int endIndexDelimiter = number.indexOf("]", startIndexDelimiter);
                delimiter += number.substring(startIndexDelimiter, endIndexDelimiter) + "|";

            }
            return delimiter;
        }
        public String[] splitNumberByDelimiter(String delimitersWithNumbers ) {
            return delimitersWithNumbers.split(delimiter);
        }

        public int calculateSum(String[] numberSplittedByDelimiter) {
            int output = 0;
            for (String token : numberSplittedByDelimiter) {
                output += addSingleToken(token);
            }
            throwExceptionIfNegativeTokensExist(negativeTokens);
            return output;
        }

        public void throwExceptionIfNegativeTokensExist(List<String> negativeTokens ) {
            if (hasNegativeTokens( negativeTokens)) {
                throw new IllegalArgumentException(String.format("negatives not allowed (%s)", StringUtils.join(negativeTokens, ",")));
            }
        }

        public boolean hasNegativeTokens(List<String> negativeTokens) {
            return negativeTokens.size() > 0;
        }

        public int addSingleToken(String token) {
            Integer valueAsInteger = Integer.parseInt(token);
            if (isItNegative(valueAsInteger)) {
                negativeTokens.add(token);
            } else if (isItInValidRange(valueAsInteger)) {
                return valueAsInteger;
            }
            return 0;
        }

        public boolean isItNegative(Integer valueAsInteger) {
            return valueAsInteger < 0;
        }

        public boolean isItInValidRange(Integer valueAsInteger) {
        	return valueAsInteger < 1000;
           
        }
        	
        

    }
}