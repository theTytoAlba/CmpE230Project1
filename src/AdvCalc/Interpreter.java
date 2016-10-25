package AdvCalc;

import java.util.ArrayList;
import java.util.HashMap;


public class Interpreter {
	private HashMap<Character, Integer> variables;
	
	public Interpreter() {
		variables = new HashMap<>();
	}
	
	/**
	 * Chooses what to do with the given line.
	 * @param line
	 */
	public void process(String line) {
		// Remove white spaces.
        line = line.replaceAll(" ", "");
        
		if (line.contains("=")) {
			processAssignment(line);
		} else {
			System.out.println(calculateValue(line));
		}
	}

	/**
	 * Parses equations in form "x = y" to x and y.
	 * Updates x with the value of y.
	 */
	private void processAssignment(String line) {
		String[] assignment = line.split("=");
		char target = assignment[0].charAt(0);
		String rhs = assignment[1];
		variables.put(target, calculateValue(rhs));
	}
		
	/**
	 * Calculates the given line mathematically.
	 */
	private int calculateValue(String line) {
		// Get the line as an ArrayList<String> of its tokens.
		ArrayList<String> tokens = ExpressionHelper.getTokensOfExpression(line);
        // Convert tokens from infix to postfix.
        tokens = ExpressionHelper.convertInfixToPostfix(tokens);
        // Calculate the postfix value.
        return ExpressionHelper.calculatePostfixValue(tokens, variables);   
	}
}
