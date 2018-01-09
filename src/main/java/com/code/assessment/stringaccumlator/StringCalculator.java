/**
 * Simple String Calculator with a method int add(String numbers)
 */
package com.code.assessment.stringaccumlator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Keith Chan
 *
 */
public class StringCalculator {
	private String defaultDelimters = ",|\n";
	private String changeDelimtersPattern = "//";
	private String specialCharaters = ".*+?^$";
	private int maxNumber = 1000;

	public StringCalculator() {

	}

	/**
	 * Calculate the sum of the numbers with delimiter
	 * 
	 * @param numbers
	 * @return sum of the numbers
	 * @throws NegativeNumberException 
	 */
	public int add(String numbers) throws NegativeNumberException {
		int results = 0;
		if (StringUtils.isEmpty(numbers))
			return results;
		String delimiters = defaultDelimters;
		String stringForCalculation = numbers;
		if (haveDifferentDelimiter(numbers)) {
			delimiters = findDelimiters(numbers);
			stringForCalculation = StringUtils.substringAfter(numbers, "\n");
		}
		// String[] numbersArray = StringUtils.split(stringForCalculation, delimiters);
		String[] numbersArray = stringForCalculation.split(delimiters);
		List negativeNumberList = new ArrayList<Integer>();
		for (String temp : numbersArray) {
			int number = Integer.parseInt(temp);
			if (number < 0) {
				negativeNumberList.add(number);
			} else {
				if (number <= maxNumber)
					results += number;
			}
		}
		if (!negativeNumberList.isEmpty()) {
			throw new NegativeNumberException("Negatives not allowed: "+StringUtils.join(negativeNumberList, ","));
		}
		return results;
	}

	/**
	 * Check the first line have different delimiter pattern
	 * 
	 * @param numbers
	 * @return True if have optional different delimiter pattern
	 */
	public boolean haveDifferentDelimiter(String numbers) {
		String str = StringUtils.left(numbers, 2);
		return StringUtils.equalsIgnoreCase(str, changeDelimtersPattern);
	}

	/**
	 * Find the delimiter
	 * 
	 * @param numbers
	 * @return String of multiple delimiters
	 */
	public String findDelimiters(String numbers) {
		String[] strArray = StringUtils.split(numbers, "\n");
		String delimiterDefineStr = StringUtils.substring(strArray[0], 2);
		String[] delimiterArray = StringUtils.split(delimiterDefineStr, "|");
		String results = "";
		for (String temp : delimiterArray) {
			results = results + encodeSpecialCharaters(temp) + "|";
		}
		results = StringUtils.chop(results);
		return results;
	}

	/**
	 * Add escape character to special character
	 * 
	 * @param input
	 * @return encoded string
	 */
	public String encodeSpecialCharaters(String input) {
		String results = "";
		String[] inputArray = input.split("(?!^)");
		for (String temp : inputArray) {
			if (specialCharaters.contains(temp)) {
				results = results + "\\" + temp;
			} else {
				results = results + temp;
			}
		}
		return results;
	}
}
