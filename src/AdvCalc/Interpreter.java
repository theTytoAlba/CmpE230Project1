package AdvCalc;

import java.util.ArrayList;
import java.util.HashMap;


public class Interpreter {
	private HashMap<Character, Double> variables;
	
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
	 * Uses updateValue and calculateValue functions to
	 * update the value in x to y.
	 */
	private void processAssignment(String line) {
		// TODO: get target from the equation.
		char target = 'A';
		// TODO: get right hand side from the equation.
		String rhs = null;
		updateValue(target, calculateValue(rhs));
	}
	
	/**
	 * Updates the value of the target.
	 */
	private void updateValue(char target, double d) {
		// TODO: implement update logic
		System.out.println("Updating value");
	}
	
	/**
	 * Calculates the given line mathematically.
	 */
	private double calculateValue(String line) {
		// Get the line as an ArrayList<String> of its tokens.
		ArrayList<String> tokens = ExpressionHelper.getTokensOfExpression(line);
        // Convert tokens from infix to postfix.
        tokens = ExpressionHelper.convertInfixToPostfix(tokens);
        // Calculate the postfix value.
        return ExpressionHelper.calculatePostfixValue(tokens, variables);   
	}
}
