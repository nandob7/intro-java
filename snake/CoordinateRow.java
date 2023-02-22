package snake;

public class CoordinateRow {

static final int MAX_NUMBER_OF_ELEMENTS = 5000;
	
	Coordinate[] elements;
	int numberOfElements;
	
	CoordinateRow() {
		elements = new Coordinate[MAX_NUMBER_OF_ELEMENTS];
		numberOfElements = 0;
	}
	
	void addCoordinateBehind(Coordinate coordinate) {
		numberOfElements += 1;
		elements[numberOfElements - 1] = coordinate;
	}
	
	void addCoordinateInFront(Coordinate coordinate){
		for (int i = numberOfElements; i >= 1; i--) {
			elements[i] = elements[i - 1];
		}

		elements[0] = coordinate;
		numberOfElements += 1;
	}
	
	boolean contains(Coordinate coordinate) {
		for(int i = 0; i < numberOfElements; i++) {
			if(coordinate.equals(elements[i])) {
				return true;
			}
		}
		
		return false;
	}

	 void removeLast() {
		numberOfElements--;
	}
}
