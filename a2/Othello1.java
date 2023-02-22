package Assignments;

import java.io.PrintStream;
import java.util.Scanner;

public class Othello1 {
	// Name : Nando Beijaard
	// Assignment : Othello 1
	// Date : 14-09-2020

	static final double NUMBER_OF_TILES = 64;

	PrintStream out;

	Othello1() {
		out = new PrintStream(System.out);
	}

	void start() {
		Scanner in = new Scanner(System.in);

		out.printf("Enter the number of white pieces on the board: ");
		int whitePieces = in.nextInt();
		out.printf("Enter the number of black pieces on the board: ");
		int blackPieces = in.nextInt();

		int totalPieces = whitePieces + blackPieces;
		double percentageBoardBlack = blackPieces / NUMBER_OF_TILES * 100;
		double percentagePiecesBlack = (double) blackPieces / totalPieces * 100;

		in.close();

		out.printf("The percentage of black pieces on the board is: %.2f%%\n", percentageBoardBlack);
		out.printf("The percentage of black pieces of all the pieces on the board is: %.2f%%\n", percentagePiecesBlack);
	}

	public static void main(String[] args) {
		new Othello1().start();

	}

}
