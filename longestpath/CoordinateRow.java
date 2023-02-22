package longestPath;

public class CoordinateRow {

static final int MAX_NUMBER_OF_ELEMENTS = 1000;
	
	Coordinate[] elements;
	int numberOfElements;
	
	CoordinateRow() {
		elements = new Coordinate[MAX_NUMBER_OF_ELEMENTS];
		numberOfElements = 0;
	}
	
	void addBehind(Coordinate coordinate) {
		elements[numberOfElements] = coordinate;
		numberOfElements += 1;
	}
	
	void removeLast() {
		numberOfElements -= 1;
	}
}
