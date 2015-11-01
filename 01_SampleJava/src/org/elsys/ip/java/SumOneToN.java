package org.elsys.ip.java;

import java.util.Scanner; // This will import just the Scanner class.

public class SumOneToN {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int number, sum = 0;
		// Initiate a new Scanner
        Scanner userInputScanner = new Scanner(System.in);
 
        // Testing nextLine();
        System.out.print("\nType number: ");
       
        while (!userInputScanner.hasNextInt()) {
        	userInputScanner.next();
			System.out.println("That's not a number!");
        }
        
        number = userInputScanner.nextInt();
        for (int i = 0; i <= number; i++) {
        		sum += i;
        }
System.out.printf("Sum of the numers from 1 to %d is %d", number, sum);

	}

}
