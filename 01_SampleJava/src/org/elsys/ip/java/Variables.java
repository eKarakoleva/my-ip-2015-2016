package org.elsys.ip.java;

public class Variables {

	// select constant value in the code, press Ctrl+1
	// press "Extract to constant"
	
	// define constant
	private static final String HELLO_WORLD = "Hello world";

	public static void main(String[] args) {
		
		// error:
		// a = 0;
		
		// define local variable
		int a;
		
		// assign value
		a = 0;
				
		// init variable value
		String b = HELLO_WORLD;
	}
	// error:
	// a = 0;

}
