package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		Point p1 = new Point(-2.3, 4);
		Point p2 = new Point(8.5, 0.7);
		System.out.println("The distance between the points is: " + distance(p1, p2));
	}

	public static double distance(Point p1, Point p2) {
		double result;
		result = Math.sqrt((Math.pow((p2.x - p1.x), 2) + (Math.pow((p2.y - p1.y), 2))));
		return result;
	}
	
}
