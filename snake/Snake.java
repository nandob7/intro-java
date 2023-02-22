package snake;

import java.util.Scanner;

import ui.Event;
import ui.SnakeUserInterface;
import ui.UIAuxiliaryMethods;
import ui.UserInterfaceFactory;

public class Snake {

	static final double FPS = 15;
	static final int WIDTH = 32, HEIGHT = 24;

	SnakeUserInterface ui;
	CoordinateRow snake;
	CoordinateRow walls;
	Coordinate apple;
	String direction;
	int score;
	boolean runSnake;
	boolean hasMoved;

	Snake() {
		ui = UserInterfaceFactory.getSnakeUI(WIDTH, HEIGHT);
		ui.setFramesPerSecond(FPS);
		snake = new CoordinateRow();
		snake.addCoordinateInFront(new Coordinate(1, 0));
		snake.addCoordinateBehind(new Coordinate(0, 0));
		walls = new CoordinateRow();
		direction = "R";
		score = 0;
		runSnake = true;
		hasMoved = false;
	}

	void appleEaten(Coordinate head) {
		if (head.equals(apple)) {
			makeNewApple();
			score += 1;
			ui.clearStatusBar();
			ui.printf("Score: %d\n", score);
		} else {
			snake.removeLast();
		}
	}

	Coordinate makeNewApple() {
		apple = new Coordinate().createRandom();
		;

		if (!posIsUnique(apple)) {
			makeNewApple();
		}

		return apple;
	}

	boolean posIsUnique(Coordinate coordinate) {
		return (!snake.contains(coordinate) && !walls.contains(coordinate));
	}

	void moveSnake() {
		Coordinate newHead = moveHead();

		if (!posIsUnique(newHead))
			endGame();

		snake.addCoordinateInFront(newHead);
	}

	Coordinate moveHead() {
		Coordinate head = snake.elements[0];

		if (direction.equals("U")) {
			return checkBounds(new Coordinate(head.x, head.y - 1));
		} else if (direction.equals("R")) {
			return checkBounds(new Coordinate(head.x + 1, head.y));
		} else if (direction.equals("D")) {
			return checkBounds(new Coordinate(head.x, head.y + 1));
		} else {
			return checkBounds(new Coordinate(head.x - 1, head.y));
		}
	}

	Coordinate checkBounds(Coordinate c) {
		if (c.x < 0) {
			c.x = WIDTH - 1;
		} else if (c.x >= WIDTH) {
			c.x = 0;
		}

		if (c.y < 0) {
			c.y = HEIGHT - 1;
		} else if (c.y >= HEIGHT) {
			c.y = 0;
		}

		return new Coordinate(c.x, c.y);
	}

	void processRefresh() {
		moveSnake();
		appleEaten(snake.elements[0]);

		if (runSnake) {
			ui.clear();
			placeRow(snake, ui.SNAKE);
			placeRow(walls, ui.WALL);

			while (!posIsUnique(apple)) {
				makeNewApple();
			}

			ui.place(apple.x, apple.y, ui.FOOD);
			ui.showChanges();
		}
	}

	void processArrow(String arrowDirection) {
		if (arrowDirection.equals("U") && !direction.equals("D")) {
			direction = "U";
		} else if (arrowDirection.equals("R") && !direction.equals("L")) {
			direction = "R";
		} else if (arrowDirection.equals("D") && !direction.equals("U")) {
			direction = "D";
		} else {
			if (!direction.equals("R"))
				direction = "L";
		}
	}

	void processEvent(Event event) {
		if (event.name.equals("arrow") && !hasMoved) {
			processArrow(event.data);
			hasMoved = true;
		} else if (event.data.equals("refresh")) {
			processRefresh();
			hasMoved = false;
		}
	}

	void endGame() {
		ui.clearStatusBar();
		ui.printf("Game over!\n");
		ui.printf("Final score: %d", score);
		runSnake = false;
	}

	Coordinate readCoordinate(String coordinate) {
		Scanner coordinateScanner = new Scanner(coordinate);

		int x = coordinateScanner.nextInt();
		int y = coordinateScanner.nextInt();

		return new Coordinate(x, y);
	}

	CoordinateRow readCoordinateRow(String coordinateRow) {
		Scanner coordinateRowScanner = new Scanner(coordinateRow);
		CoordinateRow result = new CoordinateRow();

		while (coordinateRowScanner.hasNext()) {
			Coordinate coordinate = readCoordinate(coordinateRowScanner.nextLine());
			result.addCoordinateBehind(coordinate);
		}

		return result;
	}

	void placeRow(CoordinateRow row, int type) {
		for (int i = 0; i < row.numberOfElements; i++) {
			ui.place(row.elements[i].x, row.elements[i].y, type);
		}
	}

	void readLevelInput(Scanner input) {
		input.useDelimiter("=");
		snake = readCoordinateRow(input.next());
		direction = input.next();
		walls = readCoordinateRow(input.next());
	}

	void startingPosition() {
		placeRow(snake, ui.SNAKE);
		apple = makeNewApple();
		ui.place(apple.x, apple.y, ui.FOOD);
		ui.showChanges();
		ui.printf("Score: %d\n", score);
	}

	void start() {
		ui.enableEventProcessing(false);
		Scanner in = UIAuxiliaryMethods.askUserForInput().getScanner();

		if (in != null) { // Input level optional
			readLevelInput(in);
		}

		startingPosition();
		ui.enableEventProcessing(true);

		while (runSnake) {
			Event event = ui.getEvent();
			processEvent(event);
		}
	}

	public static void main(String[] args) {
		new Snake().start();
	}
}
