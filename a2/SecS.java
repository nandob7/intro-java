package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class SecS {
	// Name : Nando Beijaard
	// Assignment : SecondSmallest
	// Date : 21-09-2020
	PrintStream out;

	SecS() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		int smallest;
		int secondSmallest;

		smallest = secondSmallest = Integer.MAX_VALUE;

		while (in.hasNext()) {
			int next = in.nextInt();

			if (next < smallest) {
				secondSmallest = smallest;
				smallest = next;
			} else if (next < secondSmallest) {
				secondSmallest = next;
			}
		}
		in.close();

		out.printf("The second smallest number is: %d\n", secondSmallest);
	}

	public static void main(String[] args) {
		new SecS().start();

	}

}
