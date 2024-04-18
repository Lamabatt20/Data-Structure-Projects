
import java.util.Stack;

import javafx.application.Application;

public class Equations {
//------------------------------------------Evaluate Postfix-----------------------------------------------------	
	private static boolean isOperator(char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
	}

	private static boolean isOperator(String s) {
		return s.length() == 1 && isOperator(s.charAt(0));
	}

	private static int getPrecedence(char c) {
		switch (c) {
		case '^':
			return 4;
		case '*':
		case '/':
			return 3;
		case '+':
		case '-':
			return 2;
		default:
			return 1;
		}
	}

	private static double evaluateOperator(double x, double y, char operator) {
		switch (operator) {
		case '+':
			return x + y;
		case '-':
			return x - y;
		case '*':
			return x * y;
		case '/':
			if (y == 0) {
				throw new ArithmeticException("Division by zero");
			} else {
				return x / y;
			}
		case '^':
			return (int) Math.pow(x, y);
		}
		return 0;
	}

	public static Double evaluatePostfix(String s) {
		Stack<Double> stack = new Stack<>();
		String[] tokens = s.split(" ");

		for (String token : tokens) {
			if (!token.isEmpty()) {
				if (isOperator(token)) {
					double y = stack.pop();
					double x = stack.pop();
					Double result = evaluateOperator(x, y, token.charAt(0));
					stack.push(result);
				} else {
					stack.push(Double.parseDouble(token));
				}
			}
		}

		return stack.pop();
	}
//------------------------------------------Convert Infix to Postfix ---------------------------------------------		

	public static String infixToPostfix(String x) {
		Stack<String> a = new Stack<>();
		String y[] = x.split(" ");
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < y.length; i++) {

			if (y[i].equals("(")) {
				a.push(y[i]);
			} else if (y[i].equals(")")) {
				while (!a.peek().equals("(")) {
					result.append(a.pop()).append(" ");
				}
				a.pop();
			} else if (isOperator(y[i])) {
				while (!a.isEmpty() && !a.peek().equals("(")
						&& getPrecedence(y[i].charAt(0)) <= getPrecedence(a.peek().charAt(0))) {
					result.append(a.pop()).append(" ");
				}
				a.push(y[i]);
			} else {
				result.append(y[i]).append(" ");
			}
		}

		while (!a.isEmpty()) {
			result.append(a.pop()).append(" ");
		}

		return result.toString().trim();

	}

//------------------------------------------Convert Postfix to Prefix-----------------------------------------------	
	public static String postfixToPrefix(String postfix) {
		Stack<String> stack = new Stack<>();
		String[] tokens = postfix.split("\\s+");

		for (String token : tokens) {
			if (!token.isEmpty()) {
				if (isOperator(token)) {
					String operand2 = stack.pop();
					String operand1 = stack.pop();
					String result = token + " " + operand1 + " " + operand2;
					stack.push(result);
				} else {
					stack.push(token);
				}
			}
		}

		return stack.pop();
	}

//------------------------------------------Evaluate Prefix-------------------------------------------------------	
	public static double evaluatePrefix(String s) {
		Stack<Double> stack = new Stack<>();
		String[] tokens = s.split(" ");

		for (int i = tokens.length - 1; i >= 0; i--) {
			String token = tokens[i].trim();

			if (!token.isEmpty()) {
				if (isOperator(token)) {
					Double x = stack.pop();
					Double y = stack.pop();
					double result = evaluateOperator(x, y, token.charAt(0));
					stack.push(result);
				} else {
					stack.push(Double.parseDouble(token));
				}
			}
		}

		return stack.pop();
	}

// check the equations is infix or postfix   
	public static boolean isInfix(String ex) {
		if (ex.isEmpty()) {
			return false;
		}

		Stack<Character> stack = new Stack<>();

		char lastOp = ' ';

		for (char c : ex.toCharArray()) {
			if (Character.isDigit(c)) {
				// operand
			} else if (isOperator(c)) {
				// operator
				if (stack.isEmpty() || getPrecedence(c) > getPrecedence(lastOp)) {
					stack.push(c);
					lastOp = c;
				} else {
					return false;
				}
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				stack.pop();
			} else {
				// invalid character
				return false;
			}
		}

		return stack.isEmpty();
	}

}
