package prototype;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class PrototypeKeys {

  BigInteger[] encryptionLock = new BigInteger[2];
  BigInteger[] decryptionLock = new BigInteger[2];

  /**
   * Generates the public and private keys and stores it.
   * 
   * @param primeP the first prime used to calculate the keys.
   * @param primeQ the second prime used to calculate the keys.
   */
  public void generate(BigInteger primeP, BigInteger primeQ) {
    
    BigInteger product = primeP.multiply(primeQ);
    BigInteger one = new BigInteger("1");
    BigInteger phi = (primeP.subtract(one)).multiply((primeQ.subtract(one)));
    System.out.println("info: " + primeP.toString() + "\n"
                       + primeQ.toString() + "\n" + product.toString() + "\n" + phi.toString());

    // generate the encryption lock
    System.out.println("Generating Keys... ");
    encryptionLock[0] = generateEncryptionKey(primeP, primeQ, product, phi);
    encryptionLock[1] = product;
    System.out.println("\n\nEncryption lock: (" + encryptionLock[0] + ", "
                       + encryptionLock[1] + ")");

    // generate the decryption lock
    decryptionLock[0] = genDecryptionKey(primeP, primeQ, product, phi, encryptionLock[0]);
    decryptionLock[1] = product;
    System.out.println("Decryption lock: (" + decryptionLock[0] + ", " + decryptionLock[1] + ")");
  }
  
  /**
   * This is a second contructor for testing, it's used for setting a specific encryption key, 
   * some times it's better to have a fixed number as the encryption key.
   * 
   * @param primeP the first prime number.
   * @param primeQ the second prime number.
   * @param e the encryption key specified.
   */
  public void generate(BigInteger primeP, BigInteger primeQ, BigInteger e) {

    BigInteger product = primeP.multiply(primeQ);
    BigInteger one = new BigInteger("1");
    BigInteger phi = (primeP.subtract(one)).multiply((primeQ.subtract(one)));
    System.out.println(primeP.toString() + "\n" + primeQ.toString() + "\n" + phi.toString()
                       + "\n" + product.toString());

    //generate the encryption lock
    System.out.println("Generating Keys... ");
    encryptionLock[0] = e;
    encryptionLock[1] = product;
    System.out.println("\n\nEncryption lock: (" + encryptionLock[0] + ", "
                       + encryptionLock[1] + ")");

    // generate the decryption lock
    decryptionLock[0] = genDecryptionKey(primeP, primeQ, product, phi, encryptionLock[0]);
    decryptionLock[1] = product;
    System.out.println("Decryption lock: (" + decryptionLock[0] + ", " + decryptionLock[1] + ")");
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
  public BigInteger generateEncryptionKey(BigInteger primeP, BigInteger primeQ,
          BigInteger product, BigInteger phi) {
    Random rand = new Random();

    BigInteger minRange = new BigInteger(1016, new Random());
    BigInteger encryption = new BigInteger("0");
    BigInteger a = new BigInteger("100");
    BigInteger searchLength = minRange.add(a);

    // List for all the possible encryption numbers
    ArrayList<BigInteger> encryptionList = new ArrayList<BigInteger>();

    // Choose e where 1 < e < phi
    // and is coprime with product and phi
    BigInteger i;
    BigInteger one = new BigInteger("1");
    for (i = minRange; i.compareTo(searchLength) < 0 && i.compareTo(phi) < 0; i = i.add(one)) {
      // check if coprime with product and phi, then add to the list
      if (coprime(i, phi)) {
        if (coprime(i, product)) {
          encryptionList.add(i);
          System.out.println(i);
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
  public BigInteger genDecryptionKey(BigInteger primeP, BigInteger primeQ,
            BigInteger product, BigInteger phi, BigInteger encryption) {
    BigInteger decryption = new BigInteger("0");
    decryption = modInverse1(encryption, phi);
    return decryption;
  }

  /**
   * Finds the greatest common divisor between two numbers.
   * 
   * @param phi the first integer used to calculate the greatest common divisor.
   * @param i the second integer used to calculate the greatest common divisor.
   * @return the greatest common divisor.
   */
  public BigInteger gcd(BigInteger phi, BigInteger i) {
    if (phi.intValue() == 0) {
      return i;
    }
    return gcd(i.mod(phi), phi);
  }

  /**
   * Checks if two numbers are coprime.
   * 
   * @param i the integer that is checked if coprime with 'q'.
   * @param phi the integer that is check if coprime with 'q'.
   * @return boolean value whether or not p is coprime with q.
   */
  public boolean coprime(BigInteger i, BigInteger phi) {
    return (gcd(i, phi).intValue() == 1);
  }

  /**
   * Returns the private key.
   * 
   * @return encryption lock array.
   */
  public BigInteger[] getEncryptionKeys() {
    return encryptionLock;
  }

  /**
   * Returns the public key.
   * 
   * @return decryption lock array.
   */
  public BigInteger[] getDecryptionKeys() {
    return decryptionLock;
  }
  
  //Code edited from geeksforgeeks.org by Ankur
  static BigInteger modInverse1(BigInteger a, BigInteger m) {
    BigInteger m0 = m;
    BigInteger y = new BigInteger("0");
    BigInteger x = new BigInteger("1");

    if (m.compareTo(BigInteger.ONE) == 0) {
      return new BigInteger("0");
    }

    while (a.compareTo(BigInteger.ONE) == 1) {
      System.out.println("a: " + a.toString());
      //q is quotient
      BigInteger q = a.divide(m);
      BigInteger t = m;
      
      //m is remainder now, process
      //same as Euclid's algo
      m = a.mod(m);
      a = t;
      t = y;

      // Update x and y
      y = x.subtract(q.multiply(y));
      System.out.println("y subtract: " + y.toString() + "\n");
      x = t;
    }

    // Make x positive
    if (x.compareTo(new BigInteger("0")) == -1) {
      x = x.add(m0);
    }
    
    return x;
  }

}
