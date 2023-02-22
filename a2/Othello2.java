package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class Othello2 {
	// Name : Nando Beijaard
	// Assignment : Othello 2
	// Date : 14-09-2020

	static final int MILLISECONDS_TO_HOURS = 3600000,
			MILLISECONDS_TO_MINUTES = 60000,
			MILLISECONDS_TO_SECONDS = 1000,
			MAX_COMPUTER_THINKING_TIME = 1000;

	PrintStream out;

	Othello2() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		out.printf("Enter the time the black player thought: ");
		int timeBlack = in.nextInt();
		out.printf("Enter the time the white player thought: ");
		int timeWhite = in.nextInt();

		in.close();

		int timeHuman = 0;

		if (timeBlack > timeWhite && timeWhite <= MAX_COMPUTER_THINKING_TIME) {
			timeHuman = timeBlack;

		} else if (timeBlack < timeWhite && timeBlack <= MAX_COMPUTER_THINKING_TIME) {
			timeHuman = timeWhite;
		}

		int timeHours = timeHuman / MILLISECONDS_TO_HOURS;
		int timeMinutes = (timeHuman % MILLISECONDS_TO_HOURS) / MILLISECONDS_TO_MINUTES;
		int timeSeconds = ((timeHuman % MILLISECONDS_TO_HOURS) % MILLISECONDS_TO_MINUTES) / MILLISECONDS_TO_SECONDS;

		out.printf("The time the human player has spent thinking is: %02d:%02d:%02d\n", timeHours, timeMinutes,
				timeSeconds);
	}

	public static void main(String[] args) {
		new Othello2().start();

	}

}
