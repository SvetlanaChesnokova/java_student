package ru.stqa.pft.sandbox;

/**
 * Created by Светлана on 04.11.2016.
 */
public class Equality {

  public static void main (String [] args) {

    String s1 = "firefix 2.0";
   // String s2 = s1;
   // String s2 = new String(s1);
    String s2 = "fire" + "fix " + Math.sqrt(4.0);

    System.out.println(s1 == s2);
    System.out.println(s1.equals(s2));
  }

}
