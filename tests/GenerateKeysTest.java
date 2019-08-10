import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import big_math.BigMath;
import rsa.GenerateKeys;

public class GenerateKeysTest {
  GenerateKeys gen = new GenerateKeys();

  /*@Test //Test 1
  public void testGenerateEncryptionKey() {
    BigInteger primeP = new BigInteger("2");
    BigInteger primeQ = new BigInteger("7");
    BigInteger product = new BigInteger("14");
    BigInteger phi = new BigInteger("6");
    BigInteger answer = new BigInteger("5");
    assertEquals("Test getting the encryption key",
        //answer, gen.generateEncryptionKey(primeP, primeQ, product, phi));
  }
  
 /* @Test //Test 2
  public void testGenerateDecryptionKey() {
    assertEquals("Test getting the decryption key", 11, gen.genDecryptionKey(2, 7, 14, 6, 5));
  }*/
  
  @Test //Test 3
  public void testGreatestCommonDivisor() {
    BigMath primeP = new BigMath("68");
    BigMath primeQ = new BigMath("100");
    BigMath answer = new BigMath("4");
    assertEquals("Test getting the greatest common divisor",
        answer.toRawString(), gen.gcd(primeP, primeQ).toRawString());
    //assertEquals("Test getting the greatest common divisor", 5, gen.gcd(500, 45));
    //assertEquals("Test getting the greatest common divisor", 8, gen.gcd(168, 856));
  }
  
  @Test //Test 4
  public void testCoprime() {
    BigMath p = new BigMath("12");
    BigMath q = new BigMath("11");
    BigMath answer = new BigMath("4");
    assertEquals("Test if two numbers are coprime", true, gen.coprime(p, q));
    //assertEquals("Test if two numbers are coprime", true, gen.coprime(12, 23));
  }
  
  /*@Test //Test 5
  public void testGetEncryptionKeys() {
    BigInteger five = new BigInteger("5");
    BigInteger fourteen = new BigInteger("14");
    BigInteger a = new BigInteger("2");
    BigInteger b = new BigInteger("7");
    gen.generate(a, b);
    BigInteger[] result = {five, fourteen};
    assertArrayEquals("Test getting the private key", result, gen.getEncryptionKeys());
  }*/
}