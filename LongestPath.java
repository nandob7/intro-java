package longestPath;

import java.util.Scanner;

import ui.LabyrinthUserInterface;
import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;

public class LongestPath {

	static final int WIDTH = 32, HEIGHT = 24, WAIT_TIME = 10, LOAD_TIME = 500;
	static final String[] DIRECTIONS = { "West", "South", "East", "North" };

	LabyrinthUserInterface ui;

	Coordinate start;
	Coordinate destination;
	CoordinateRow walls;
	CoordinateRow visited;
	CoordinateRow longest;

	LongestPath() {
		ui = UserInterfaceFactory.getLabyrinthUI(WIDTH, HEIGHT);
		visited = new CoordinateRow();
		longest = new CoordinateRow();
	}

	Coordinate readCoordinate(String coordinate) {
		Scanner coordinateScanner = new Scanner(coordinate);

		int x = coordinateScanner.nextInt();
		int y = coordinateScanner.nextInt();

		return new Coordinate(x, y);
	}

	CoordinateRow readCoordinateRow(String coordinateRow) {
		CoordinateRow result = new CoordinateRow();
		Scanner coordinateRowScanner = new Scanner(coordinateRow);

		while (coordinateRowScanner.hasNext()) {
			Coordinate coordinate = readCoordinate(coordinateRowScanner.nextLine());
			result.addBehind(coordinate);
		}

		return result;
	}

	Coordinate nextCoordinate(String direction, Coordinate current) {
		if (direction.equals("West")) {
			return new Coordinate(current.x - 1, current.y);
		} else if (direction.equals("South")) {
			return new Coordinate(current.x, current.y + 1);
		} else if (direction.equals("East")) {
			return new Coordinate(current.x + 1, current.y);
		} else {
			return new Coordinate(current.x, current.y - 1);
		}
	}

	void mark(Coordinate c, int type) {
		ui.wait(WAIT_TIME);
		if (type == ui.PATH) {
			visited.addBehind(c);
		} else {
			visited.removeLast();
		}
		ui.place(c.x, c.y, type);
		ui.showChanges();
	}

	void checkLongest() {
		if (visited.numberOfElements > longest.numberOfElements) {
			longest.numberOfElements = visited.numberOfElements;

			for (int i = 0; i < visited.numberOfElements; i++) {
				longest.elements[i] = visited.elements[i];
			}

			ui.clearStatusBar();
			ui.printf("Current longest path length: %d\n", longest.numberOfElements);
		}
	}

	boolean isAvailable(Coordinate nextCoordinate) {
		for (int i = 0; i < visited.numberOfElements; i++) {
			if (nextCoordinate.x == visited.elements[i].x && nextCoordinate.y == visited.elements[i].y) {
				return false;
			}
		}

		for (int i = 0; i < walls.numberOfElements; i++) {
			if (nextCoordinate.x == walls.elements[i].x && nextCoordinate.y == walls.elements[i].y) {
				return false;
			}
		}

		return true;
	}

	void findPath(Coordinate current) {
		if (current.equals(destination)) {
			checkLongest();
		} else {
			for (int i = 0; i < DIRECTIONS.length; i++) {
				Coordinate next = nextCoordinate(DIRECTIONS[i], current);
				if (isAvailable(next)) {
					mark(next, ui.PATH);
					findPath(next);
					mark(next, ui.EMPTY);
				}
			}
		}
	}

	void placeRow(CoordinateRow row, int type) {
		for (int i = 0; i < row.numberOfElements; i++) {
			ui.place(row.elements[i].x, row.elements[i].y, type);
		}

		ui.showChanges();
	}

	void visualizeInput(Coordinate start, Coordinate destination, CoordinateRow walls) {
		ui.encircle(start.x, start.y);
		ui.encircle(destination.x, destination.y);
		placeRow(walls, ui.WALL);
	}

	void readInput(Scanner input) {
		input.useDelimiter("=");
		start = readCoordinate(input.next());
		visited.addBehind(start);
		destination = readCoordinate(input.next());
		walls = readCoordinateRow(input.next());

		visualizeInput(start, destination, walls);
	}

	void start() {
		Scanner in = UIAuxiliaryMethods.askUserForInput().getScanner();
		readInput(in);

		ui.wait(LOAD_TIME);

		findPath(start);

		placeRow(longest, ui.PATH);

		ui.clearStatusBar();
		ui.printf("Longest path length: %d\n", longest.numberOfElements);
	}

	public static void main(String[] args) {
		new LongestPath().start();
	}
}
