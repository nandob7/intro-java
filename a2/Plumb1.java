package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class Plumb1 {
	// Name : Nando Beijaard
	// Assignment : Plumber 1
	// Date : 14-09-2020

	static final double CALL_OUT_COST = 16.00;
	public static final String EURO = "\u20AC";
	PrintStream out;

	Plumb1() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		out.printf("Enter the hourly wages: ");
		double hourlyWages = in.nextDouble();
		out.printf("Enter the number of billable hours: ");
		int hours = in.nextInt();

		in.close();

		double totalCost = hourlyWages * hours + CALL_OUT_COST;
		out.printf("The total cost of this repair is: %s%.2f\n", EURO, totalCost);
	}

	public static void main(String[] args) {
		new Plumb1().start();
	}

}
