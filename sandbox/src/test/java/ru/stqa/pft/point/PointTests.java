package ru.stqa.pft.point;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Square;

public class PointTests {
  @Test
  public void testPoint(){
    Point a = new Point(2, 8);
    Point b = new Point(8, 16);
    Assert.assertEquals(a.distance(b), 10.0);
  }
}
