package longestPath;

public class Coordinate {
	
	int x;
	int y;
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	boolean equals(Coordinate c) {
		return (c.x == x && c.y == y);
	}
}
