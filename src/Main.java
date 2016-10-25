import java.util.NoSuchElementException;
import java.util.Scanner;

import AdvCalc.Interpreter;

public class Main {
	public static void main(String[] args) {
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
		//TODO: do the assembly stuff.
		System.out.println("Creating assebmly for file " + fileName);
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
