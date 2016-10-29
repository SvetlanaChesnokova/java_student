package ru.stqa.pft.sandbox;

/**
 * Created by Светлана on 27.10.2016.
 */
public class Rectangle {

  public double a;
  public double b;

  public Rectangle (double a, double b) {
    this.a = a;
    this.b = b;
  }

  //функция расчета площади прямоугольника
  public  double area () {
    return this.a * this.b;
  }
}
