package ru.stqa.pft.point;

public class Point {
  double x;
  double y;

  public  Point(double x, double y){
    this.x = x;
    this.y = y;
  }

  public double distance(Point that) {
    double xdistance = this.x - that.x;
    double ydistance = this.y - that.y;
    double distance = Math.sqrt( (xdistance * xdistance) + (ydistance * ydistance) );
    return distance;
  }
}
