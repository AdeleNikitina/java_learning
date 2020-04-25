package ru.stqa.pft.point;

public class Point {
  double x;
  double y;

  public  Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double distance(Point p1, Point p2) {
    double xdistance = p1.x - p2.x;
    double ydistance = p1.y - p2.y;
    double distance = Math.sqrt( (xdistance * xdistance) + (ydistance * ydistance) );
    return distance;
  }
}
