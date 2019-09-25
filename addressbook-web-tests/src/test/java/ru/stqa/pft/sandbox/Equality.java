package ru.stqa.pft.sandbox;

public class Equality {

  public static void main(String args[]) {
    String a1 = "firefox";
    String a2 = a1;

    String b1 = "firefox";
    String b2 = new String(b1);

    String c1 = "firefox";
    String c2 = "fire" + "fox";

    String d1 = "firefox 2.0";
    String d2 = "firefox " + Math.sqrt(4.0);

    System.out.println(a1 == a2);
    System.out.println(a1.equals(a2));

    System.out.println();

    System.out.println(b1 == b2); // Different link to the object that's why it's false
    System.out.println(b1.equals(b2));

    System.out.println();

    System.out.println(c1 == c2);
    System.out.println(c1.equals(c2));

    System.out.println();

    System.out.println(d1 == d2); // "firefox " + Math.sqrt(4.0) != "firefox 2.0"
    System.out.println(d1.equals(d2));
  }
}
