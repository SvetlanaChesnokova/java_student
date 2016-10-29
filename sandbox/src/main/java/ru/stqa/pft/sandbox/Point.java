package ru.stqa.pft.sandbox;

/**
 * Created by Светлана on 28.10.2016.
 */
//класс Point для представления точек на двумерной плоскости
public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p1) {

    double os_x;
    double os_y;

    //расчет
    os_x = this.x - p1.x;
    os_y = this.y - p1.y;
    return Math.sqrt(Math.pow(os_x, 2) + Math.pow(os_y, 2));

  }

}
