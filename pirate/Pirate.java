package pirate;

import java.io.PrintStream;
import java.util.Scanner;

import ui.UIAuxiliaryMethods;

public class Pirate {
	
	PrintStream out;
	
	Pirate() {
		out = new PrintStream(System.out);
	}
	
	Coordinate readCoordinate(String coordinate) {
		Scanner coordinateScanner = new Scanner(coordinate);
		coordinateScanner.useDelimiter(",");
		
		int x = coordinateScanner.nextInt();
		int y = coordinateScanner.nextInt();
		
		return new Coordinate(x, y);
	}
	
	CoordinateRow readCoordinateRow(String coordinateRow) {
		Scanner coordinateRowScanner = new Scanner(coordinateRow);
		CoordinateRow result = new CoordinateRow();
		
		while (coordinateRowScanner.hasNext()) {
			Coordinate coordinate = readCoordinate(coordinateRowScanner.next());
			result.add(coordinate);
		}
		
		return result;
	}
	
	CoordinateRow generateRoute(CoordinateRow start, Scanner input) { 
		input.useDelimiter("=");
		CoordinateRow finalRoute = start;
		
		while (input.hasNext()) {
		CoordinateRow addBehind = readCoordinateRow(input.next());
		finalRoute = finalRoute.addRowBehind(finalRoute, addBehind);
		
			if(input.hasNext()) {
				CoordinateRow addInFront = readCoordinateRow(input.next());
				finalRoute = finalRoute.addRowInFront(finalRoute, addInFront);
			}
		}
		
		return finalRoute;
	}
	
	void printRoute(CoordinateRow route) {
		for (int i = 0; i < route.numberOfElements; i++) {
			out.printf("%d,%d\n", route.elements[i].x, route.elements[i].y);
		}
	}
	
	void start() {
		Scanner in = UIAuxiliaryMethods.askUserForInput().getScanner();
		in.useDelimiter("=");
		
		CoordinateRow start = readCoordinateRow(in.next());
		
		printRoute(generateRoute(start, in));
	}
	
	public static void main(String[] args) {
		new Pirate().start();
	}

}
