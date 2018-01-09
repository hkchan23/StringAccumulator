/**
 * Unit test for StringCalculator class
 */
package com.code.assessment.stringaccumlator;

import junit.framework.TestCase;

/**
 * @author Keith Chan
 *
 */
public class StringCalculatorTest extends TestCase {
	private StringCalculator stringCalculator;
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		stringCalculator = new StringCalculator();
	}
	
	public void testAddEmptyString() throws NegativeNumberException {
		assertEquals(0, stringCalculator.add(""));
	}

	public void testAddNull() throws NegativeNumberException {
		assertEquals(0, stringCalculator.add(null));
	}
	
	public void testAddOneTwoThreeWithCommaAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("1,2,3"));
	}
	
	public void testAdd1to20WithCommaAsDelimiter() throws NegativeNumberException {
		assertEquals((20/2*(1+20)), stringCalculator.add("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20"));
	}
	
	public void testAddOneTwoThreeWithNewLineAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("1\n2\n3"));
	}
	
	public void testAddOneTwoThreeWithNewLineAndCommaAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("1\n2,3"));
	}
	
	public void testAddOneTwoThreeOneThousandWithCommaAsDelimiter() throws NegativeNumberException {
		assertEquals(1006, stringCalculator.add("1,2,3,1000"));
	}
	
	public void testAddOneTwoThreeOneThousandAndOneWithCommaAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("1,2,3,1001"));
	}
	
	public void testNegativeException() {
		try {
		stringCalculator.add("-1,2,-3");
		fail();
		} catch (NegativeNumberException ex) {
			assertEquals("Negatives not allowed: -1,-3",ex.getMessage());
		}
	}

	public void testNoDifferentDelimiter() {
		assertFalse(stringCalculator.haveDifferentDelimiter("1,2,3"));
	}
	
	public void testHaveOneDifferentDelimiter() {
		assertTrue(stringCalculator.haveDifferentDelimiter("//,\n1,2,3"));
	}
	
	public void testHaveTwoDifferentDelimiters() {
		assertTrue(stringCalculator.haveDifferentDelimiter("//*|%\\n1*2%3"));
	}
	
	public void testEncodeSpecialCharacter() {
		assertEquals("\\*\\.;,\\$", stringCalculator.encodeSpecialCharaters("*.;,$"));
	}
	
	public void testFindCommaDelimiter() {
		assertEquals(",", stringCalculator.findDelimiters("//,\n1,2,3"));
	}
	
	public void testFindAsteriskAndPercentDelimiter() {
		assertEquals("\\*|%", stringCalculator.findDelimiters("//*|%\n1*2%3"));
	}
	
	public void testFindMultipleAsteriskDelimiter() {
		assertEquals("\\*\\*\\*", stringCalculator.findDelimiters("//***\n1***2***3"));
	}
	
	public void testAddOneTwoThreeWithSemicolonAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("//;\n1;2;3"));
	}
	
	public void testAddOneTwoThreeWithAsteriskAndPercentAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("//*|%\n1*2%3"));
	}
	
	public void testAddOneTwoThreeWithMultipleAsteriskAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("//***\n1***2***3"));
	}
	
	public void testAddOneTwoThreeWithMultipleAsteriskAndPercentAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("//****|%\n1****2%3"));
	}
	
	public void testAddOneTwoThreeWithAsteriskDotSemicolonAndPercentDotAsDelimiter() throws NegativeNumberException {
		assertEquals(6, stringCalculator.add("//*.;|%.\n1*.;2%.3"));
	}
	
}
