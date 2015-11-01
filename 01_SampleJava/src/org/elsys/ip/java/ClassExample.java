package org.elsys.ip.java;

public class ClassExample {

	public static void main(String[] args) {
		
		//navigate cursor to Room() ctr+2 -> l
		final Room room = new Room();
		//room = new Room();
		//create object of type room
		
		// set values	
		room.setHeight(12);
		room.setWidth(10);
				
		// get value of object field
		final int height = room.getHeight();
		System.out.println(height);
				
		// directly print value (without local variable)
		System.out.println(room.getWidth());
		System.out.println(room.calculateArea());
			
		}
		

	}