package ru.stqa.pft.sandbox;

/**
 * Created by Светлана on 29.10.2016.
 */
public class Distance {

   //Функция которая вычисляет расстояние между двумя точками
  public static double distance(Point p1, Point p2) {

    //длина по оси x
    double os_x;
    //длина по оси y
    double os_y;

    //расчет
    os_x = p1.x - p2.x;
    os_y = p1.y - p2.y;
    return Math.sqrt(Math.pow(os_x, 2) + Math.pow(os_y, 2));

  }

  //запускаемый класс
  public static void main(String[] args) {

    //передача координат точки
    Point p1 = new Point(2.3, 4);
    Point p2 = new Point(8.5, 0.7);

    //пример использования функции из запускаемого класса Distance
    System.out.println("Расстояние между точками: (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " +
            distance(p1, p2));

    //пример использования метода класса Point
    System.out.println("Расстояние между точками: (" + p1.x + ", " + p1.y + ") и (" + p2.x + ", " + p2.y + ") = " +
            p1.distance(p2));

  }


}
