package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class Elect {
	// Name : Nando Beijaard
	// Assignment : Electronics
	// Date : 14-09-2020

	static final double DISCOUNT = 0.15;

	PrintStream out;

	Elect() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		out.printf("Enter the price of the first article: ");
		int firstPrice = in.nextInt();
		out.printf("Enter the price of the second article: ");
		int secondPrice = in.nextInt();
		out.printf("Enter the price of the third article: ");
		int thirdPrice = in.nextInt();

		in.close();

		double discount;

		if (firstPrice > secondPrice && firstPrice > thirdPrice) {
			discount = firstPrice * DISCOUNT;
		} else if (secondPrice > thirdPrice) {
			discount = secondPrice * DISCOUNT;
		} else {
			discount = thirdPrice * DISCOUNT;
		}

		out.printf("Discount: %.2f\n", discount);
		out.printf("Total: %.2f\n", firstPrice + secondPrice + thirdPrice - discount);
	}

	public static void main(String[] args) {
		new Elect().start();

	}

}
