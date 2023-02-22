package Replay1;

import java.util.Scanner;

import ui.OthelloReplayUserInterface;
import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;

public class Replay1 {

	OthelloReplayUserInterface ui;

	Replay1() {
		ui = UserInterfaceFactory.getOthelloReplayUI();
	}

	boolean choiceIsMove(String choice) {
		if (choice.charAt(0) == 'm') {
			return true;
		} else {
			return false;
		}
	}

	void playMove(int x, int y, int type) {
		ui.place(x, y, type);
		ui.showChanges();
	}

	void move(Scanner input, String colour) {
		int x = input.next().charAt(0) - 'a';
		int y = input.nextInt() - 1;
		int type;
		if (colour.charAt(0) == 'b') {
			type = ui.BLACK;
		} else {
			type = ui.WHITE;
		}
		playMove(x, y, type);
	}

	void readLine(Scanner input) {
		String colour = input.next();
		int waitingTime = input.nextInt();
		String choice = input.next();
		ui.wait(waitingTime);

		if (choiceIsMove(choice)) {
			move(input, colour);
		}
	}

	void startingPosition() {
		ui.place(3, 3, ui.WHITE);
		ui.place(4, 4, ui.WHITE);
		ui.place(3, 4, ui.BLACK);
		ui.place(4, 3, ui.BLACK);
		ui.showChanges();
	}

	void start() {
		Scanner in = UIAuxiliaryMethods.askUserForInput().getScanner();

		startingPosition();

		while (in.hasNext()) {
			String line = in.nextLine();
			Scanner lineScanner = new Scanner(line);

			readLine(lineScanner);
		}
	}

	public static void main(String[] args) {
		new Replay1().start();
	}
}
