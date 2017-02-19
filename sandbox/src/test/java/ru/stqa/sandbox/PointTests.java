package ru.stqa.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Elena on 19.02.2017.
 */
public class PointTests {

  Point p1 = new Point(0,0);
  Point p2 = new Point(60,80);
  Point p3 = new Point(5.5,-3.3);

  @Test
  public void testDistance1(){
    Assert.assertEquals(p1.distance(p2), 100.0);
  }

  @Test
  public void testDistance2(){
    Assert.assertEquals(p2.distance(p1), 100.0);
  }

  @Test
  public void testDistance3(){
    Assert.assertEquals(p2.distance(p3), 99.54466334264232);
  }

  @Test
  public void testDistance4(){
    Assert.assertEquals(p2.distance(p2), 0.0);
  }

  @Test
  public void testDistance5(){
    Assert.assertEquals(p3.distance(p2), 99.5446633426423);
  }

}
