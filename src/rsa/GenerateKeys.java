package rsa;
/**
 * @author Abhishek Nand Kumar.
 * @version 1.0
 *
 */

import big_math.BigMath;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;



public class GenerateKeys {

  BigMath[] encryptionLock = new BigMath[2];
  BigMath[] decryptionLock = new BigMath[2];

  /**
   * Generates the public and private keys and stores it.
   * 
   * @param primeP the first prime used to calculate the keys.
   * @param primeQ the second prime used to calculate the keys.
   */
  public void generate(BigMath primeP, BigMath primeQ) {
    BigMath product = primeP.multiply(primeQ);
    BigMath one = new BigMath("1");
    BigMath phi = (primeP.subtract(one)).multiply((primeQ.subtract(one)));

    // generate the encryption lock
    encryptionLock[0] = generateEncryptionKey(primeP, primeQ, product, phi);
    encryptionLock[1] = product;


    // generate the decryption lock
    decryptionLock[0] = genDecryptionKey(primeP, primeQ, product, phi, encryptionLock[0]);
    decryptionLock[1] = product;
  }

  /**
   * Generate the public key.
   * 
   * @param primeP the value used to generate the keys.
   * @param primeQ the value used to generate the keys.
   * @param product the value when multiplying primeQ with primeP.
   * @param phi the value of number of co-primes that primeQ and primeP has.
   * @return private key 'encryption'.
   */
  public BigMath generateEncryptionKey(BigMath primeP, BigMath primeQ,
      BigMath product, BigMath phi) {
    Random rand = new Random();

    BigMath minRange = new BigMath(370, new Random());
    BigMath encryption = new BigMath("0");
    BigMath a = new BigMath("100");
    BigMath searchLength = minRange.add(a);

    // List for all the possible encryption numbers
    ArrayList<BigMath> encryptionList = new ArrayList<BigMath>();

    // Choose e where 1 < e < phi
    // and is coprime with product and phi
    BigMath i;
    BigMath one = new BigMath("1");
    for (i = minRange; i.compareTo(searchLength) < 0 && i.compareTo(phi) < 0; i = i.add(one)) {
      // check if coprime with product and phi, then add to the list
      if (coprime(i, phi)) {
        if (coprime(i, product)) {
          encryptionList.add(i);
          //System.out.println(i);
        }
      }
    }

    // check if there are valid encryption numbers available
    if (encryptionList.size() == 0) {
      System.out.println("No valid encrytption numbers");
    } else {
      // Select a random number from the encryptionList and set is as the encryption
      // number
      while (encryption.intValue() == 0 || encryption.intValue() == 1) {
        encryption = encryptionList.get(rand.nextInt(encryptionList.size()));
      }
    }
    return encryption;
  }

  /**
   * Generates the private key.
   * 
   * @param primeP the first prime number used to generate the keys.
   * @param primeQ the second prime used to generate the keys.
   * @param product the value when multiplying primeQ with primeP.
   * @param phi the number of co-primes that primeQ and primeP has.
   * @param encryption the private key used to calculate the public key
   * @return public key 'decryption'.
   */
  public BigMath genDecryptionKey(BigMath primeP, BigMath primeQ,
      BigMath product, BigMath phi, BigMath encryption) {
    BigMath decryption = new BigMath("0");
    decryption = new BigMath(new BigInteger(encryption.toRawString())
            .modInverse(new BigInteger(phi.toRawString())).toString());
    return decryption;
  }
  
  //Code edited from geeksforgeeks.org by Ankur
  static BigMath modInverse(BigMath a, BigMath m) {
    System.out.println("modinverse");
    BigMath m0 = m;
    BigMath y = new BigMath("0");
    BigMath x = new BigMath("1");

    if (m.compareTo(new BigMath("1")) == 0) {
      return new BigMath("0");
    }

    while (a.compareTo(new BigMath("1")) == 1) {
      //q is quotient
      BigMath q = a.divideBy(m);
      BigMath t = m;

      //m is remainder now, process
      //same as Euclid's algo
      m = a.modBigInt(m);
      a = t;
      t = y;

      // Update x and y
      y = x.subtract(q.multiply(y));
      x = t;
    }

    // Make x positive
    if (x.compareTo(new BigMath("0")) == -1) {
      x = x.add(m0);
    }
 
    return x;
  }

  /**
   * Finds the greatest common divisor between two numbers.
   * 
   * @param i the first integer used to calculate the greatest common divisor.
   * @param product the second integer used to calculate the greatest common divisor.
   * @return the greatest common divisor.
   */
  public BigMath gcd(BigMath i, BigMath product) {
    if (i.intValue() == 0) {
      return product;
    }
    return gcd(product.mod(i), i);
  }

  /**
   * Checks if two numbers are coprime.
   * 
   * @param i the integer that is checked if coprime with 'q'.
   * @param product the integer that is check if coprime with 'q'.
   * @return boolean value whether or not p is coprime with q.
   */
  public boolean coprime(BigMath i, BigMath product) {
    return (gcd(i, product).intValue() == 1);
  }

  /**
   * Returns the private key.
   * 
   * @return encryption lock array.
   */
  public BigMath[] getEncryptionKeys() {
    return encryptionLock;
  }

  /**
   * Returns the public key.
   * 
   * @return decryption lock array.
   */
  public BigMath[] getDecryptionKeys() {
    return decryptionLock;
  }
}
