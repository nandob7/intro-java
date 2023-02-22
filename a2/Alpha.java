package Assignments;

import java.io.PrintStream;

public class Alpha {
	// Name : Nando Beijaard
	// Assignment : Alphabet
	// Date : 16-09-2020

	PrintStream out;

	Alpha() {
		out = new PrintStream(System.out);
	}

	void start() {

		for (char letter = 'a'; letter <= 'z'; letter++) {
			out.printf("%c", letter);
		}
	}

	public static void main(String[] args) {
		new Alpha().start();

	}

}
