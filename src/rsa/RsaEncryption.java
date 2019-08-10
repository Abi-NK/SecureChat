package rsa;
/**
 * @author Abhishek Nand Kumar.
 * @version 1.0
 */

import big_math.BigMath;
import big_math.ModularExpBigMath;

public class RsaEncryption {
  
  /**
   * Decryption of the ciphertext.
   *  
   * @param ciphertext the big-integer value given to be decrypted.
   * @param decryptionPair the decryption lock including the decryption key
   *        used to decrypt the ciphertext.
   * @return plaintext once decrypted.
   */
  public BigMath[] decryption(BigMath[] ciphertext, BigMath[] decryptionPair) {
    ModularExpBigMath modExp = new ModularExpBigMath();
    BigMath[] decrypted = new BigMath[ciphertext.length];

    for (int i = 0; i < ciphertext.length; i++) {
      System.out.println("Ciphertext " + i + ": " + ciphertext[i].toString());
      decrypted[i] = modExp.modPower(ciphertext[i], decryptionPair[0], decryptionPair[1]);
    }

    System.out.println("\nDecrypting ciphertext using key...");
    System.out.println("Checking for padding...");
    ProcessMessage pm = new ProcessMessage();
    //System.out.println("\nDecryption: '" + pm.toString(decrypted) + "'");
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
  public BigMath[] encryption(String plaintext, BigMath[] encryptionPair) {
    ModularExpBigMath modExp = new ModularExpBigMath();

    ProcessMessage pm = new ProcessMessage();

    //calculate m^e(mod n)
    BigMath[] plaintextBigInt = pm.process(plaintext, encryptionPair[1]);
    BigMath[] ciphertext = new BigMath[plaintextBigInt.length];
    
    for (int i = 0; i < plaintextBigInt.length; i++) {
      ciphertext[i] = modExp.modPower(plaintextBigInt[i], encryptionPair[0], encryptionPair[1]);
    }
    
    System.out.println("\n\n");
    return ciphertext;
  }
  
  /**
   * Converts a BigMath array to string.
   * 
   * @param a the BigMath array.
   * @return the string once converted.
   */
  public String toString(BigMath[] a) {
    String str = "";
    for (int i = 0; i < a.length; i++) {
      str = str + "," + a[i].toRawString();
    }
    return str;
  }
  
  /**
   * Splits a large string into a BigMath array.
   * 
   * @param a the large string.
   * @return the BigMath array once converted.
   */
  public BigMath[] split(String a) {
    String[] splitStr = a.split(",");
    BigMath[] bigSplit = new BigMath[splitStr.length];
    
    for (int i = 0; i < splitStr.length; i++) {
      bigSplit[i] = new BigMath(splitStr[i]);
    }
    return bigSplit;
  }
}

