package AdvCalc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class A86Writer {
	private final String variables = "vars";
	private final String outputFile;
	private final String inputFile;
	private PrintWriter writer;
	private Scanner reader;
	
	/**
	 * Initializes input and output file names from given file name.
	 * Expected filename is the input file in the format "xxxx.ac".
	 * @param filename the input file
	 */
	public A86Writer(String filename) {
		inputFile = filename;
		outputFile = filename.substring(0, filename.length()-2) + "asm";
	}

	public void generate() throws FileNotFoundException, UnsupportedEncodingException {
		writer = new PrintWriter(outputFile, "UTF-8");
		File file = new File(inputFile);
		reader = new Scanner(file);
		initializeFile();
		
		String line = readLine();
		while (line != null) {
			process(line);
			line = readLine();
		}
		
		finishFile();
		writer.close();
	}
	
	/**
	 * Initializes the file with necessary declarations.
	 */
	private void initializeFile() {
		writer.println("name \"advcalc\"");
		writer.println("org 100h");
		writer.println("jmp start");
		declareArray();
		writer.println("start:");
		writer.println("lea si, " + variables);
	}

	/**
	 * Declares the variable array for the A86 file.
	 */
	private void declareArray() {
		writer.println();
		writer.println("; array of 52 for variables:");
		writer.print(variables + " dw");
		for (int i = 0; i < 51; i++) {
			writer.print(" 0,");
		}
		writer.println(" 0");
	}
	
	/**
	 * Ends the file with a "wait for any key press".
	 */
	private void finishFile() {
		writer.println();
		writer.println("; wait for any key press:");
		writer.println("mov ah, 0");
		writer.println("int 16h");
		writer.println();
		writer.println("ret");
	}
	
	private void process (String line) {
		System.out.println("Processing: " + line);
		// TODO: Parse and process lines.	
	}
	
	/**
	 * Reads one line from the input file.
	 * @return
	 */
	private String readLine() {
		try {
			return reader.nextLine();	
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * Returns the name of the asm file.
	 * @return
	 */
	public String getOutputFileName() {
		return outputFile;
	}
}
