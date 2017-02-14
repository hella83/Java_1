package ru.stqa.sandbox;

/**
 * Created by Elena on 14.02.2017.
 */
public class MyFunction {

  public static void main(String[] args) {

    Point p1 = new Point(2,3);

    Point p2 = new Point(60,80);

    //System.out.println("Расстояние между точками ("  + p1.a + ","+ p1.b+") и ("+ p2.a + ","+ p2.b+") равно: "+distance(p1, p2));

    System.out.println("Расстояние между точками ("  + p1.a + ","+ p1.b+") и ("+ p2.a + ","+ p2.b+") равно: "+p1.distance(p2));
    System.out.println("Расстояние между точками ("  + p1.a + ","+ p1.b+") и ("+ p2.a + ","+ p2.b+") равно: "+p2.distance(p1));

  }

  //public static double distance(Point p1, Point p2){

    //double a = (p2.a - p1.a);
    //double b = (p2.b - p1.b);

    //return (Math.sqrt(a*a+b*b));
  //}
}
