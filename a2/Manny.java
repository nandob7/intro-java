package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class Manny {
	// Name : Nando Beijaard
	// Assignment : Manny
	// Date : 16-09-2020

	static final int MIN_DONATION = 50;

	PrintStream out;

	Manny() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		double donation = 0;

		do {
			out.printf("Enter the amount you want to donate: ");
			donation = in.nextDouble();
		} while (donation < MIN_DONATION);

		in.close();

		out.printf("Thank you very much for your contribution of %.2f euro.\n", donation);
	}

	public static void main(String[] args) {
		new Manny().start();

	}

}
