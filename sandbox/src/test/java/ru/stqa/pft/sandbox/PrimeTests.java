package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Светлана on 09.11.2016.
 */
public class PrimeTests {
    @Test
    public void testPrime() {
        //проверка с простым числом
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeFast() {
        //проверка с простым числом
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeFastSqrt() {
        //проверка с простым числом
        Assert.assertTrue(Primes.isPrimeFastSqrt(Integer.MAX_VALUE));
    }

    @Test (enabled = false) //чтоб тест не запускался
    public void testPrimeLong() {
        //проверка с длинным целым числом
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void testNonPrime() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }

}
