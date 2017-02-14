package ru.stqa.sandbox;

/**
 * Created by Elena on 14.02.2017.
 */
public class MyFunction {

  public static double distance(Point p1, Point p2){

    double a = (p2.a - p1.a);
    double b = (p2.b - p1.b);

    return (Math.sqrt(a*a+b*b));
  }
}
