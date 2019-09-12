package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		Point p1 = new Point(7, 5.5);
		Point p2 = new Point(2, 6);
		System.out.println("The distance between the points is: " + p1.distance(p2));

	}

}
