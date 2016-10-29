package ru.stqa.pft.sandbox;

//для использование коротких названий в тексте кода
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Светлана on 30.10.2016.
 */
public class SquareTests {

  //Указываем, что это тестовый метод, анатация
  @Test

  public void testArea(){
    Square s = new Square(5);
    //проверяем действительно ли равенство
    //assert s.area() == 25;
    //проверка на равенство, с возвращением реального результата / с проверкой типа
    Assert.assertEquals(s.area(), 25.0);
  }
}
