package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class VAT {
	// Name : Nando Beijaard
	// Assignment : VAT
	// Date : 14-09-2020

	static final double VAT = 21;

	PrintStream out;

	VAT() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		out.printf("Enter the price of an article including VAT: ");
		int priceIncludingVAT = in.nextInt();
		in.close();

		double priceExcludingVAT = priceIncludingVAT / (1 + (VAT / 100));
		out.printf("This article will cost %.2f euro without %.2f%% VAT.\n", priceExcludingVAT, VAT);
	}

	public static void main(String[] args) {
		new VAT().start();

	}

}
