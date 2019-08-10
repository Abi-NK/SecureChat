package prototype;
/**
 * @author Abhishek Nand Kumar.
 * @version 1.0
 */
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

import big_math.LargePrime;
import big_math.ModularExp;

public class PrototypeEncryption {
  
  /**
  * Main method to run the program.
  * 
  * @param args the initial setup.
  * @throws InterruptedException if the timer doesn't work.
  */
  public static void main(String[] args) throws InterruptedException {
    long startTime = System.nanoTime();
    PrototypeKeys gen = new PrototypeKeys();
    LargePrime lp = new LargePrime();
    
    //Generate the large primes.
    BigInteger primeP1 = new BigInteger(lp.generatePrime(1024).toRawString());
    BigInteger primeQ2 = new BigInteger(lp.generatePrime(1024).toRawString());
    
    //Generate the keys.
    gen.generate(primeP1, primeQ2);
    
    long endTime = System.nanoTime();
    System.out.println("\nKey Generation Time: "
                       + ((endTime - startTime) / 1000000) + " milliseconds"); 
    BigInteger[] encryptionPair = new BigInteger[2];
    BigInteger[] decryptionPair = new BigInteger[2];
    encryptionPair = gen.getEncryptionKeys();
    decryptionPair = gen.getDecryptionKeys();
    System.out.println("\n");

    Scanner reader = new Scanner(System.in);
    System.out.print("Enter a number that you would like to encrypt: ");
    String plaintext = reader.nextLine();
    reader.close();
    
    long startTime2 = System.nanoTime();
    BigInteger[] ciphertext = encryption(plaintext, encryptionPair);
    BigInteger[] plaintextBig = decryption(ciphertext, decryptionPair);
    
    long endTime2 = System.nanoTime();
    System.out.println("\nKey Generation Time: "
                       + ((endTime2 - startTime2) / 1000000) + " milliseconds");
    
  }
  
  /**
   * Decryption of the ciphertext.
   *  
   * @param ciphertext the big-integer value given to be decrypted.
   * @param decryptionPair the decryption lock including the decryption key
   *        used to decrypt the ciphertext.
   * @return plaintext once decrypted.
   */
  public static BigInteger[] decryption(BigInteger[] ciphertext, BigInteger[] decryptionPair) {
    
    BigInteger[] decrypted = new BigInteger[ciphertext.length];
    System.out.println("\nDecrypting ciphertext using key...");
    System.out.print("Decryption: ");
    ModularExp modEx = new ModularExp();

    for (int i = 0; i < ciphertext.length; i++) {
      decrypted[i] = modEx.modPower(ciphertext[i], decryptionPair[0], decryptionPair[1]);
      System.out.print(decrypted[i].toString());
    }
    
    return decrypted;
  }
  
  /**
   * Encryption of the plaintext.
   * Transforms a character into it's ASCII number equivalent.
   * 
   * @param plaintext the string to be encrypted.
   * @param encryptionPair the encryption lock used to encrypt the plaintext.
   * @return ciphertext once encrypted.
   */
  public static BigInteger[] encryption(String plaintext, BigInteger[] encryptionPair) {

    //calculate m^e(mod n)
    BigInteger[] plaintextBigInt = new BigInteger[1];
    plaintextBigInt[0] = new BigInteger(plaintext);
    BigInteger[] ciphertext = new BigInteger[plaintextBigInt.length];
    ModularExp modEx = new ModularExp();
    System.out.print("\nCiphertext: ");
    
    for (int i = 0; i < plaintextBigInt.length; i++) {
      ciphertext[i] = modEx.modPower(plaintextBigInt[i], encryptionPair[0], encryptionPair[1]);
      System.out.print(ciphertext[i].toString());
    }
    
    System.out.println("\n\n");
    return ciphertext;
  }
}
