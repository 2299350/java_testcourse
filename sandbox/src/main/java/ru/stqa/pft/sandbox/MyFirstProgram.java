package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		Point p1 = new Point(-2.3, 4);
		Point p2 = new Point(8.5, 0.7);
		System.out.println("The distance between the points is: " + p1.distance(p2));
		
	}

}
