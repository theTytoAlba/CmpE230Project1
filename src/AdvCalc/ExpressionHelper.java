package AdvCalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ExpressionHelper {
	/**
	 * Parses the math expression by +, -, *, /, (, ) and returns an ArrayList of Strings.
	 * @param expression
	 * @return parsedExpr is an ArrayList of Strings of the parsed expression.
	 */
	public static ArrayList<String> getTokensOfExpression(String expression) {
		expression = expression.replaceAll("\\+", " + ");
		expression = expression.replaceAll("-", " - ");
		expression = expression.replaceAll("\\*", " * ");
		expression = expression.replaceAll("/", " / ");
		expression = expression.replaceAll("\\(", " ( ");
		expression = expression.replaceAll("\\)", " ) ");
		expression = expression.replaceAll("  ", " ");
		
		String[] tokens = expression.split(" ");
		ArrayList<String> parsedExpr = new ArrayList<>();
        for (String token : tokens) {
			if (token != null && token != "") {
				parsedExpr.add(token);
			}
		}
		return parsedExpr;
	}
	
	/**
	 * Converts a given infix ArrayList of tokens to a postfix one.
	 * @param infix
	 * @return postfix
	 */
	public static ArrayList<String> convertInfixToPostfix(ArrayList<String> infix) {
		ArrayList<String> postfix = new ArrayList<>();
		Stack<String> operatorStack = new Stack<>();
		
		for (String token : infix) {
			// if: operators, else: operands.
			if (isOperator(token)) {
				// if: operators to push to stack, else: move operators from stack to postfix, then push to stack.
				if (operatorStack.isEmpty() || hasPrecedenceOver(token, operatorStack.lastElement())) {
					operatorStack.push(token);
				} else {
					// if: closing a parentheses, else: other operator.
					if (token.equals(")")) {
						// move all operators to postfix until top of the stack is opening parenthesis.
						while (!operatorStack.lastElement().equals("(")) {
							postfix.add(operatorStack.pop());
						}
						operatorStack.pop();
					} else {
						// move all operators to postfix until top of the stack has precedence over the token.
						while ((!operatorStack.isEmpty() && !operatorStack.lastElement().equals("(")) && !hasPrecedenceOver(token, operatorStack.lastElement())) {
							postfix.add(operatorStack.pop());
						}
						operatorStack.push(token);		
					}
				}
			} else {
				postfix.add(token);
			}
		}
		
		//add remaining operators.
		while (!operatorStack.isEmpty()) {
			postfix.add(operatorStack.pop());
		}
		
		return postfix;
	}
	
	/**
	 * Returns true if op1 has precedence over op2.
	 * * and / have precedence over + and -.
	 * ( has precedence over everything.
	 * @param op1
	 * @param op2
	 * @return
	 */
	private static boolean hasPrecedenceOver(String op1, String op2) {
		// "(" has highest priority.
		if (op1.equals("(") && !op2.equals("(")) {
			return true;
		}
		// "*" and "/" has middle priority.
		if ((op1.equals("*") || op1.equals("/")) && (op2.equals("+") || op2.equals("-"))) {
			return true;
		}
		
		// "+" and "-" has lowest priority.
		return false;

	}

	/**
	 * Calculates given postfix value using the values of variables .
	 * @param tokens
	 * @param variables
	 * @return value
	 */
	public static double calculatePostfixValue(ArrayList<String> tokens, HashMap<Character, Double> variables) {
		Stack<String> stack = new Stack<String>();
		for (String token : tokens) {
			// if: token is operand so push to stack, else: token is operator so calculate and update stack.
			if (!isOperator(token)) {
				stack.push(token);
			} else {
				char operator = token.charAt(0);
				String op2 = stack.pop();
				if (isVariable(op2)) {
					if (variables.containsKey(op2.charAt(0))) {
						op2 = "" + variables.get(op2.charAt(0));
					} else {
						op2 = "" + 0.0;
					}
				}
				String op1 = stack.pop();
				if (isVariable(op1)) {
					if (variables.containsKey(op1.charAt(0))) {
						op1 = "" + variables.get(op1.charAt(0));
					} else {
						op1 = "" + 0.0;
					}
				}
				stack.push("" + calculateOperation(Double.parseDouble(op1), Double.parseDouble(op2), operator));
			}
		}
		String result = stack.pop();
		if (isVariable(result)) {
			if (variables.containsKey(result.charAt(0))) {
				return variables.get(result.charAt(0));
			} else {
				return 0.0;
			}
		} else {
			return Double.parseDouble(result);		
		}
	}
	
	/**
	 * Returns true if given String is "+", "-", "*", "/", "(" or ")".
	 * @param token
	 * @return
	 */
	private static boolean isOperator(String token) {
		if (token.equals("+") || token.equals("-")
					|| token.equals("*") || token.equals("/")
					|| token.equals("(") || token.equals(")")) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Calculates the operation "op1 operator op2".
	 * @param op1
	 * @param op2
	 * @param operator
	 * @return
	 */
	private static double calculateOperation(double op1, double op2, char operator) {
		switch (operator) {
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
			case '*':
				return op1 * op2;
			case '/':
				return op1 / op2;
		}
		return 0.0;
	}
	
	/**
	 * Checks if given string is a variable. Returns true if it is a single letter.
	 * @param s
	 * @return
	 */
	private static boolean isVariable(String s) {
		if (s.length() != 1) {
			return false;
		} 
		
		char c = s.charAt(0);
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
			return true;
		}
		return false;
	}
}
