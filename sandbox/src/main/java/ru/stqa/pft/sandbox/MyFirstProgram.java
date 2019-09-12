package ru.stqa.pft.sandbox;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Aleksei");
		System.out.println();

		Square s = new Square(5);
		System.out.println("The square area with the side " + s.l + " = " + area(s));

		Rectangle r = new Rectangle(4, 6);
		System.out.println("The square area with the sides " + r.a + " and " + r.b + " = " + area(r));
	}

	public static void hello(String somebody) {
		System.out.println("Hello, " + somebody + "!");
	}

	public static double area(Square s) {
		return s.l * s.l;
	}

	public static double area(Rectangle r) {
		return r.a * r.b;
	}
	
}
