package pirate;

public class CoordinateRow {
	
	static final int MAX_NUMBER_OF_ELEMENTS = 5000;
	
	Coordinate[] elements;
	int numberOfElements;
	
	CoordinateRow() {
		elements = new Coordinate[MAX_NUMBER_OF_ELEMENTS];
		numberOfElements = 0;
	}
	
	void add(Coordinate coordinate) {
		elements[numberOfElements] = coordinate;
		numberOfElements += 1;
	}
	
	CoordinateRow addRowBehind(CoordinateRow original, CoordinateRow addBehind) {
		CoordinateRow result = new CoordinateRow();
		result.numberOfElements = original.numberOfElements + addBehind.numberOfElements;
		
		if (addBehind.numberOfElements != 1) {
			for (int i = 1; i < addBehind.numberOfElements; i++) {
				original =  original.addCoordinateBehind(addBehind.elements[i - 1]);
				result = original.addCoordinateBehind(addBehind.elements[i]);
			}
		} else {
			result = addCoordinateBehind(addBehind.elements[0]);
		}
		
		return result;
	}
	
	CoordinateRow addCoordinateBehind(Coordinate coordinate) {
		CoordinateRow result = new CoordinateRow();
		result.numberOfElements = numberOfElements + 1;
		
		for (int i = 0; i < numberOfElements; i++) {
			result.elements[i] = elements[i];
		}
		
		result.elements[numberOfElements] = coordinate;
		return result;
	}
	
	CoordinateRow addRowInFront(CoordinateRow original, CoordinateRow addInFront) {
		CoordinateRow result = new CoordinateRow();
		result.numberOfElements = original.numberOfElements + addInFront.numberOfElements;
		
		if (addInFront.numberOfElements != 1) {
			for (int j = addInFront.numberOfElements - 2; j >= 0 ; j--) {
				original =  original.addCoordinateInFront(addInFront.elements[j + 1]);
				result = original.addCoordinateInFront(addInFront.elements[j]);
			}
		} else {
			result = addCoordinateInFront(addInFront.elements[0]);
		}
		
		return result;
	}

	CoordinateRow addCoordinateInFront(Coordinate coordinate) {
		CoordinateRow result = new CoordinateRow();
		result.numberOfElements = numberOfElements + 1;
		
		result.elements[0] = coordinate;
		
		for (int i = 0; i < result.numberOfElements; i++) {
			result.elements[i + 1] = elements[i];
		}
		
		return result;
	}
}