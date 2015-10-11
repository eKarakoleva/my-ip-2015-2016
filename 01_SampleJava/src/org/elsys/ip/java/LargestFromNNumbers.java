package org.elsys.ip.java;

import java.util.Scanner;

public class LargestFromNNumbers {

	public static void main(String[] args) {
		int n = 0, max ,a;
		int i = 1;
		
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		
		System.out.println("How many numbers you want to compare? ");
		while (!in.hasNextInt()) {
			in.next();
			System.out.println("That's not a number!");
		}
		
		n = in.nextInt();
		max = n;
		
		while (i<=n) {
			System.out.printf("Enter number %d ", i);
			a = in.nextInt();
			
			if (a>max) {
				max =a;
				i +=1;
				
			}else {
				i+=1;
			}
			
		}
		System.out.printf("Largest number is %d", max);

	}

}
