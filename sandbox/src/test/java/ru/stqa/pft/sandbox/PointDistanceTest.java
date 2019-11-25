package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointDistanceTest {

    @Test
    public void testDistance() {

      Point p1 = new Point(2.0, 3.0);
      Point p2 = new Point(3.0, 4.0);
      double result = p1.distance(p2);

      Assert.assertEquals(result, 1.4142135623730951);

    }

   @Test
   public void testDistance2() {

     Point p1 = new Point(7, 5.5);
     Point p2 = new Point(2, 6);
     double result = p1.distance(p2);

     Assert.assertEquals(result, 5.024937810560445);

   }

  @Test
  public void testDistance3() {

    Point p1 = new Point(8, 5.5);
    Point p2 = new Point(4, 6);
    double result = p1.distance(p2);

    Assert.assertEquals(result, 4.031128874149275);

  }

}






