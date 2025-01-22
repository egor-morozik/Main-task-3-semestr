/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author egorm
 */
public class CalculatorTest {
    
    private final Calculator CALCULATOR = new Calculator();

    @Test
    void testSimpleAddition() {
        String expression = "2 + 3";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(5.0, result, 0.0001, "Simple addition failed");
    }

    @Test
    void testSubtraction() {
        String expression = "10 - 4";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(6.0, result, 0.0001, "Subtraction failed");
    }

    @Test
    void testMultiplication() {
        String expression = "3 * 4";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(12.0, result, 0.0001, "Multiplication failed");
    }

    @Test
    void testDivision() {
        String expression = "20 / 4";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(5.0, result, 0.0001, "Division failed");
    }

    @Test
    void testExponentiation() {
        String expression = "2 ^ 3";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(8.0, result, 0.0001, "Exponentiation failed");
    }

    @Test
    void testComplexExpression() {
        String expression = "3 + 4 * 2 / (1 - 5) ^ 2";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(3.5, result, 0.0001, "Complex expression failed");
    }

    @Test
    void testParentheses() {
        String expression = "(2 + 3) * 4";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(20.0, result, 0.0001, "Parentheses failed");
    }

    @Test
    void testNestedParentheses() {
        String expression = "((2 + 3) * 2) / (1 + 1)";
        double result = CALCULATOR.evaluate(expression);
        assertEquals(5.0, result, 0.0001, "Nested parentheses failed");
    }

    @Test
    void testDivisionByZero() {
        String expression = "10 / 0";
        assertThrows(ArithmeticException.class, () -> CALCULATOR.evaluate(expression), "Division by zero");
    }

//    @Test
//    void testInvalidExpression() {
//        String expression = "2 + * 3";
//        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression), "Unsupported operation");
//    }
}
