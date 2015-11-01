package org.elsys.ip.java;

public class Room {
	
	// define fields
	private int width;
	private int height;

	// Alt+Shift+S R to generate getters/setters
	// or right click > Source > Generate getters and setters
	
	// getters and setters:
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	// type calculateArea, then press Ctrl+Space
	
	// define method:
	public int calculateArea() {
		// return value
		return height * width;
	}
	
	// When overriding equals() consider overriding hashCode()
	@Override
	public boolean equals(Object obj) {
		// Optimization:
		//if (obj == this) {
		//	return true;
		//}
		if (obj instanceof Room) {
			final Room room2 = (Room) obj;
			return room2.height == height && room2.width == width; 
		}
		return false;
	}

}