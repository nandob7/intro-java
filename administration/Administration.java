package administration;

import java.io.PrintStream;
import java.util.Scanner;

import ui.UIAuxiliaryMethods;

public class Administration {

	static final int MIN_HIGH_MATCHES = 20;

	PrintStream out;

	Administration() {
		out = new PrintStream(System.out);
	}

	void printSimilarityStudents(String similarityStudents) {
		Scanner similarityStudentsScanner = new Scanner(similarityStudents);
		similarityStudentsScanner.useDelimiter(",");

		while (similarityStudentsScanner.hasNext()) {
			String similarityStudentName = similarityStudentsScanner.next();
			out.printf("\t%s\n", similarityStudentName);
		}
	}

	void similarityGraphSymbol(int similarityMatches) {
		if (similarityMatches == 0) {
			out.printf("_");
		} else if (similarityMatches > 0 && similarityMatches < MIN_HIGH_MATCHES) {
			out.printf("-");
		} else if (similarityMatches >= MIN_HIGH_MATCHES) {
			out.printf("^");
		}
	}

	void graphSimilarity(String similarityScores) {
		Scanner similarityScoresScanner = new Scanner(similarityScores);
		similarityScoresScanner.useDelimiter("=");

		out.printf("\t");

		while (similarityScoresScanner.hasNext()) {
			int percentageScore = similarityScoresScanner.nextInt();
			similarityGraphSymbol(percentageScore);
		}

		out.printf("\n");
	}

	void readSimilarity(Scanner similarityScanner) {
		similarityScanner.useDelimiter(";");
		String similarityScores = similarityScanner.next();

		graphSimilarity(similarityScores);

		if (similarityScanner.hasNext()) {
			String similarityStudents = similarityScanner.next();
			printSimilarityStudents(similarityStudents);
		} else {
			out.printf("\tNo matches found\n");
		}
	}

	double roundToHalf(double average) {
		return Math.round(average / 0.5) * 0.5;
	}

	void printGrade(String studentName, double finalGrade) {
		if (finalGrade < 6 && finalGrade >= 5.5) {
			out.printf("%s has an average of 6-\n", studentName);
		} else {
			out.printf("%s has an average of %.1f\n", studentName, roundToHalf(finalGrade));
		}
	}

	double calculateFinalGrade(String grades) {
		Scanner gradesScanner = new Scanner(grades);

		int sum = 0, countGrades = 0;
		while (gradesScanner.hasNext()) {
			sum += gradesScanner.nextInt();
			countGrades += 1;
		}
		return (double) sum / countGrades;
	}

	void readStudent(Scanner studentScanner) {
		studentScanner.useDelimiter("_");

		String studentName = studentScanner.next();
		String grades = studentScanner.next();

		printGrade(studentName, calculateFinalGrade(grades));
	}

	void start() {
		Scanner in = UIAuxiliaryMethods.askUserForInput().getScanner();

		while (in.hasNext()) {
			String student = in.nextLine();
			String similarity = in.nextLine();
			Scanner studentScanner = new Scanner(student);
			Scanner similarityScanner = new Scanner(similarity);

			readStudent(studentScanner);
			readSimilarity(similarityScanner);
		}
	}

	public static void main(String[] args) {
		new Administration().start();

	}

}
