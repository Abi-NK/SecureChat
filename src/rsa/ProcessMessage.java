package rsa;

import big_math.BigMath;
import java.math.BigInteger;
import javax.xml.bind.DatatypeConverter;

public class ProcessMessage {
  
  /**
   * Converts plaintext from ASCII characters to decimal value.
   * 
   * @param plaintextString the String to be converted to decimal.
   * @param n the second number in the encryption lock.
   * @return BigInteger decimal once converted.
   */
  public BigMath[] process(String plaintextString, BigMath n) {
    
    BigInteger bigN = new BigInteger(n.toRawString());
    Padding pad = new Padding();
    String paddedPlaintext = pad.padMessage(plaintextString);
    BigInteger plaintext = toInt(paddedPlaintext);
    
    if (messageGreaterCheck(plaintext, bigN)) {
    
      BigInteger[] plaintextList = splitMessage(plaintextString, bigN);
      BigMath[] bigPlain = new BigMath[plaintextList.length];
      
      for (int i = 0; i < plaintextList.length; i++) {
        bigPlain[i] = new BigMath(plaintextList[i].toString());
      }
      return bigPlain;
      
    } else {
      BigInteger[] plaintextList = new BigInteger[1];
      plaintextList[0] = plaintext;
      
      BigMath[] bigPlain = new BigMath[plaintextList.length];
      
      for (int i = 0; i < plaintextList.length; i++) {
        bigPlain[i] = new BigMath(plaintextList[i].toString());
      }
      return bigPlain;
    }
  }
  
  /**
   * Converts BigInteger decimals to ASCII characters.
   * 
   * @param plaintext the BigInteger value needed to be converted to ASCII string.
   * @return String after converting from decimal to ASCII.
   */
  public String toString(BigMath[] plaintext) {
    Padding pad = new Padding();
    String result = "";
    BigInteger[] plaintextBig = new BigInteger[plaintext.length];
    
    for (int i = 0; i < plaintext.length; i++) {
      plaintextBig[i] = new BigInteger(plaintext[i].toRawString());
    }
    
    for (int i = 0; i < plaintext.length; i++) {
      String plaintextHex = "000" + plaintextBig[i].toString(16);
      byte[] bytes = DatatypeConverter.parseHexBinary(plaintextHex);
      String plaintextString = new String(bytes);
      plaintextString = pad.removePad(plaintextString);
      result = result + plaintextString;
    }
    return result;
  }
  
  /**
   * Splits the message if the message as a decimal is greater than N (p*q).
   * 
   * @param plaintextString the message to be split.
   * @param n the number when you multiply the initial prime numbers.
   * @return BigInteger array once the message is split and converted to decimal.
   */
  public BigInteger[] splitMessage(String plaintextString, BigInteger n) {
    Padding pad = new Padding();
    
    int length = 100;
    String[] plaintextList = plaintextString.split("(?<=\\G.{" + length + "})");
    BigInteger[] plaintextInt = new BigInteger[plaintextList.length];
    
    
    for (int i = 0; i < plaintextList.length; i++) {
      plaintextList[i] = pad.padMessage(plaintextList[i]);
      plaintextInt[i] = toInt(plaintextList[i]);
    }
    
    return plaintextInt;
  }
  
  /**
   * Converts a string to decimal value.
   * 
   * @param plaintextString the string to be converted to a decimal value.
   * @return BigInteger value after converting to decimal.
   */
  public BigInteger toInt(String plaintextString) {
    char[] hex = "0123456789abcdef".toCharArray();
    byte[] b = plaintextString.getBytes();
    char[] c = new char[2 * b.length];
    
    for (int i = 0; i < b.length; i++) {
      c[2 * i] = hex[(b[i] & 0xF0) >>> 4];
      c[2 * i + 1] = hex[b[i] & 0x0F];
    }
    
    String plaintextHex = new String(c);
    
    BigInteger plaintext;
    
    //turn to hex
    System.out.println("plaintext Hex: " + plaintextHex);
    
    //turn to decimal
    plaintext = new BigInteger(plaintextHex, 16);
    
    return plaintext;
  }
  
  /**
   * Checks if the plaintext as decimal value is greater than N (p*q).
   * 
   * @param message the plaintext as decimal value.
   * @param n the number when you multiply the initial prime numbers. Used to
   *        check if the message is smaller than n.
   * @return true if message is greater than n.
   */
  public boolean messageGreaterCheck(BigInteger message, BigInteger n) {
    if (message.compareTo(n) == 1) {
      return true;  
    }
    
    return false;
  }
}
