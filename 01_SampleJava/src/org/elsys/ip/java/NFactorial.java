package org.elsys.ip.java;

import java.util.Scanner; // This will import just the Scanner class.

public class NFactorial {

	public static void main(String[] args) {
		int n, i, fact = 1;
		 
	      System.out.println("Enter number");
	      @SuppressWarnings("resource")
	      Scanner in = new Scanner(System.in);
	      
	      while (!in.hasNextInt()) {
				in.next();
				System.out.println("That's not a number!");
	      }
	      n = in.nextInt();
	 
	      if ( n < 0 )
	         System.out.println("Number should be non-negative.");
	      else
	      {
	         for ( i = 1 ; i <= n ; i++ )
	            fact = fact*i;
	 
	         System.out.println(n+"! = "+fact);
	 
	      }

	}
}
