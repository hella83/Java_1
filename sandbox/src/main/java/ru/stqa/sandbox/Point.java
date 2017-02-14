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

  public double distance(Point p){

    double a = (p.a - this.a);
    double b = (p.b - this.b);

    return (Math.sqrt(a*a+b*b));
  }
}
