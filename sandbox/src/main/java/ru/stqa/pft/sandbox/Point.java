package ru.stqa.pft.sandbox;

public class Point {
  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point anotherPoint) {

    double result;
    result = Math.sqrt((Math.pow((anotherPoint.x - this.x), 2) + (Math.pow((anotherPoint.y - this.y), 2))));
    return result;

  }

}
