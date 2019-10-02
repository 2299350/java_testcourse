package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void testPrime() {
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrime() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
  }

  @Test
  public void testPrimeFast() {
    Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimeFast() {
    Assert.assertFalse(Primes.isPrimeFast(Integer.MAX_VALUE - 2));
  }

  @Test
  public void testPrimeTheFastest() {
    Assert.assertTrue(Primes.isPrimeTheFastest(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimeTheFastest() {
    Assert.assertFalse(Primes.isPrimeTheFastest(Integer.MAX_VALUE - 2));
  }

  @Test
  public void testPrimeWhile() {
    Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
  }

  @Test
  public void testNonPrimeWhile() {
    Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE - 2));
  }

  @Test (enabled = false)
  public void testPrimeLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }

  @Test (enabled = false)
  public void testNonPrimeLong() {
    long n = Integer.MAX_VALUE - 2;
    Assert.assertFalse(Primes.isPrime(n));
  }
}
