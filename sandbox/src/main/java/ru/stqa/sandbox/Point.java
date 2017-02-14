package ru.stqa.sandbox;

/**
 * Created by Elena on 12.02.2017.
 */
public class Point {

  public double a;
  public double b;

  public Point (double a,double b) {
    this.a = a;
    this.b = b;
  }

  public double distance(Point p1, Point p2){

    double a = (p2.a - p1.a);
    double b = (p2.b - p1.b);

    return (Math.sqrt(a*a+b*b));
  }
}
