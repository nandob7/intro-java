package pirate;

public class Coordinate {

	static final int SHIFT_IN_X = 1,
					 SHIFT_IN_Y = 0;
	
	int x;
	int y;
	
	Coordinate(int x, int y) {
		this.x = x + SHIFT_IN_X;
		this.y = y + SHIFT_IN_Y;
	}
}