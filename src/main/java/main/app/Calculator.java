/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.app;

/**
 *
 * @author egorm
 */
import java.util.*;

public class Calculator {

    public double evaluate(String expression) {
        return evaluateExpression(expression);
    }

    public static Vector<Double> evaluate(Vector<String> expressions) {
        Vector<Double> results = new Vector<>();
        for (String expr : expressions) {
            results.add(evaluateExpression(expr));
        }
        return results;
    }

    private static double evaluateExpression(String expression) {
        // Убираем пробелы из выражения
        expression = expression.replaceAll(" ", "");
        return parseExpression(expression);
    }

    private static double parseExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // Если текущий символ - число, считываем его
            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    sb.append(expression.charAt(i++));
                }
                values.push(Double.parseDouble(sb.toString()));
                i--; // Уменьшаем индекс на 1, так как он будет увеличен в следующем цикле
            } 
            // Если текущий символ - '(', добавляем его в стек операторов
            else if (ch == '(') {
                operators.push(ch);
            } 
            // Если текущий символ - ')', вычисляем выражение до '('
            else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Удаляем '(' из стека
            } 
            // Если текущий символ - оператор
            else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(ch) <= precedence(operators.peek())) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(ch);
            }
        }

        // Выполняем оставшиеся операции
        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private static double applyOperation(char operator, double b, double a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            case '^':
                return Math.pow(a, b);
            default:
                throw new UnsupportedOperationException("Unsupported operation: " + operator);
        }
    }
}