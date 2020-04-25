package ru.stqa.pft.point;

public class PointDistance {
  public static void main(String[] args) {

    Point p1 = new Point(2, 8);
    Point p2 = new Point(8, 16);

    System.out.println("Кооридинаты первой точки = " + p1.x + "; " + p1.y);
    System.out.println("Кооридинаты второй точки = " + p2.x + "; " + p2.y);
    System.out.println("Расстояние между точками = " + p1.distance(p1, p2));

  }
}
