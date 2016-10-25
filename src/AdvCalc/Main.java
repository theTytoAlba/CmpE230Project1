package AdvCalc;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO: Remove after compiler mode testing is done.
		args = new String[1];
		args[0] = "file.ac";
		
		// If there is an argument, enter the compiler mode.
		if (args.length == 1) {
			compilerMode(args[0]);
			return;
		}

		// If not, enter the interpreter mode.
		interpreterMode();
	}
	
	/**
	 * Generates A86 code for the given file.
	 * @param fileName
	 */
	private static void compilerMode(String fileName) {
		A86Writer a86 = new A86Writer(fileName);
		try {
			a86.generate();
			System.out.println(a86.getOutputFileName() + " was generated.");	
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(a86.getOutputFileName() + " was not generated.");		
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens the interpreter mode where user can interact directly.
	 */
	private static void interpreterMode() {
		Scanner in = new Scanner(System.in);
		Interpreter advcalc = new Interpreter();
	
		while (true) {
			try {
				advcalc.process(in.nextLine());
			} catch (NoSuchElementException e) {
				in.close();
				return;
			}
		}
	}
}
