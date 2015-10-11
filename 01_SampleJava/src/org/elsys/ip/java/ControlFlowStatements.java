package org.elsys.ip.java;

public class ControlFlowStatements {

	public static void main(String[] args) {
		// select statements that should be
		// extracted to method, press Ctrl+1
		// ifExample();
		// type forExample() and press Ctrl + 1
		// to automatically create method definition
		ifExample();
		forExample();
	}

	private static void forExample() {
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	}

	private static void ifExample() {
		if (true) {
			System.out.println("called");
		}
		
//		if (1>2) {
//			System.out.println("not called");
//		} else {
//			System.out.println("called");
//		}
	}

}
