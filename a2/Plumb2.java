package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class Plumb2 {
	// Name : Nando Beijaard
	// Assignment : Plumber 2
	// Date : 14-09-2020

	static final double CALL_OUT_COST = 16.00;
	static final String EURO_SIGN = "\u20AC";
	
	PrintStream out;

	Plumb2() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		out.printf("Enter the hourly wages: ");
		double hourlyWages = in.nextDouble();
		out.printf("Enter the number of billable hours: ");
		int roundedHours = (int) (in.nextDouble() + 0.5);

		in.close();

		double totalCost = hourlyWages * roundedHours + CALL_OUT_COST;
		out.printf("The total cost of this repair is: %s%.2f\n", EURO_SIGN, totalCost);
	}

	public static void main(String[] args) {
		new Plumb2().start();
	}

}
