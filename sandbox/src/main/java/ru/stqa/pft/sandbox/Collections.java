package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Светлана on 10.11.2016.
 */
public class Collections {
    public static void main (String[] args) {
        /*//Аналогично записи ниже //массив
        String[] langs = new String[4];
        langs[0] = "Java";
        langs[1] = "C#";
        langs[2] = "Python";
        langs[3] = "PHP";*/

        String[] langs = {"Java","C#","Python","PHP"};

        /*//Аналогично записи ниже // лист, список
        List<String> languages = new ArrayList<String>();
        languages.add("Java");
        languages.add("C#");
        languages.add("Python");
        */
        // в <тип элементов> указывается, если убрать <>, тогда в цикле надо будет указывать тип String и указать Object
        List<String> languages = Arrays.asList("Java","C#","Python","PHP");

       /* //Для массива можно использовать другой вид
        for (int i = 0; i < langs.length; i++) {
            System.out.println("Я хочу выучить " + langs[i]);
        }*/

        for (String l : langs) {
            System.out.println("Я хочу выучить " + l);
        }

        for (String l : languages) {
            System.out.println("Я хочу выучить " + l);
        }
    }
}
