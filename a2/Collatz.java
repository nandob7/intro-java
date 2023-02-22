package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class Collatz {
	// Name : Nando Beijaard
	// Assignment : Collatz
	// Date : 17-09-2020
	PrintStream out;

	Collatz() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		in.close();

		do {
			if (n % 2 == 0) {
				out.printf("%d\n", n);
				n = n / 2;
			} else if (n != 1) {
				out.printf("%d\n", n);
				n = (3 * n) + 1;
			} else {
				out.printf("%d\n", n);
				return;
			}
		} while (n >= 1);
	}

	public static void main(String[] args) {
		new Collatz().start();

	}

}
