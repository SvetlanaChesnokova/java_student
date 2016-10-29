package ru.stqa.pft.sandbox;

//для использование коротких названий в тексте кода
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Светлана on 30.10.2016.
 */
public class PointTests {
  //Указываем, что это тестовый метод, анатация
  @Test

  public void distance(){
    //передача координат точки
    Point p1 = new Point(2.3, 4);
    Point p2 = new Point(8.5, 0.7);
    //проверяем действительно ли равенство
    //проверяем, что расстояние между точками вычисляется правильно
    assert p1.distance(p2) == 7.023531875061151;

  }

  //Указываем, что это тестовый метод, анатация
  @Test

  public void distance2(){
    //передача координат точки
    Point p1 = new Point(2.3, 4);
    Point p2 = new Point(8.5, 0.7);

    //проверка на равенство, с возвращением реального результата / с проверкой типа
    //проверяем, что расстояние между точками вычисляется правильно
    Assert.assertEquals(p1.distance(p2), 7.023531875061151);
  }


  @Test

  public void tochka(){
    //передача координат точки
    Point p1 = new Point(2.3, 4);
    Point p2 = new Point(8.5, 0.7);

    //проверка на равенство, с возвращением реального результата / с проверкой типа
    //проверяем равно ли 4 значение координаты y в первой точки
    Assert.assertEquals(p1.y, 4.0);
  }
}
