package ru.stqa.pft.sandbox;

/**
 * Created by Светлана on 09.11.2016.
 */
public class Primes {

    public static boolean isPrime (int n) {
        // (i++) = (i = i + 1) = (i =+ 1)  можно обозначать действие любым из трех способов
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return  true;
    }

    public static boolean isPrimeFast (int n) {
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return  true;
    }

    public static boolean isPrimeFastSqrt (int n) {
        // (int) Math.sqrt - таким образом результат приводим к типу int
        int m = (int) Math.sqrt(n);
        for (int i = 2; i < m; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return  true;
    }


    //функция isPrimeWhile (int n) аналогична функции isPrime (int n), только истользуется другой оператор цикла
    public static boolean isPrimeWhile (int n) {
        int i = 2;
        while (i < n && n % i != 0) {
            i++;
        }
       return  i == n;
    }


    public static boolean isPrime (long n) {
        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return  true;
    }


}
