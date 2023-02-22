package snake;

import ui.UIAuxiliaryMethods;

public class Coordinate {
	
	int x;
	int y;
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Coordinate() {
		x = 0;
		y = 0;
	}
	
	Coordinate createRandom() {
		x =  UIAuxiliaryMethods.getRandom(0, Snake.WIDTH);
		y =  UIAuxiliaryMethods.getRandom(0, Snake.HEIGHT);
		
		return this;
	}
	
	boolean equals(Coordinate c) {
		if (x == c.x && y == c.y) {
			return true;
		}
		
		return false;
	}
}
