package des;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class DES {
  
  /**
   * Processes the plaintext message by splitting it into 64bit
   * blocks and encrypts it.
   * 
   * @param m the message as an ASCII string.
   * @param key the key used to encrypt the message "m".
   * @return the encrypted message as a HEX string.
   */
  public String encrypt(String m, String key) {
    String messageHex = toHex(m);
    String ciphertext = "";
    
    String[] messageHexSplit = (messageHex.split("(?<=\\G................)"));
    String[] messageBinarySplit = new String[messageHexSplit.length];
    
    for (int i = 0; i < messageHexSplit.length; i++) {
      while (messageHexSplit[i].length() < 16) {
        messageHexSplit[i] = messageHexSplit[i] + "0";
      }
    }
    
    for (int i = 0; i < messageBinarySplit.length; i++) {
      messageBinarySplit[i] = hexToBinary(messageHexSplit[i]);
      while (messageBinarySplit[i].length() < 64) {
        messageBinarySplit[i] = "0" + messageBinarySplit[i];
      }
    }
    
    for (int i = 0; i < messageHexSplit.length; i++) {
      ciphertext = ciphertext + binaryToHex(encryptBin(messageBinarySplit[i], key));
    }

    return ciphertext;
  }
  
  /**
   * Converts a binary String into a Hex String.
   * 
   * @param encryptBin the binary String to be converted.
   * @return the converted hex String.
   */
  public String binaryToHex(String encryptBin) {
    String hex = "";
    String[] binarySplit = (encryptBin.split("(?<=\\G....)"));
    for (int i = 0; i < binarySplit.length; i++) {
      int decimal = Integer.parseInt(binarySplit[i], 2);
      String hexStr = Integer.toString(decimal, 16);
      hex = hex + hexStr;
    }
    return hex;
  }

  /**
   * Encrypts a 64bit message using a 64bit DES key.
   * 
   * @param m the message that will be encrypted.
   * @param key the 64bit DES key used to encrypt the message.
   * @return the ciphertext once encrypted.
   */
  public String encryptBin(String m, String key) {
    
    String[] messageSplit = m.split("(?!^)");
    KeyGenDES kgd = new KeyGenDES(key);
    String[] subKeys = kgd.generateSubKeys();
    String ip = "";
    int[] ipTable = {58, 50, 42, 34, 26, 18, 10, 2,
                     60, 52, 44, 36, 28, 20, 12, 4,
                     62, 54, 46, 38, 30, 22, 14, 6,
                     64, 56, 48, 40, 32, 24, 16, 8,
                     57, 49, 41, 33, 25, 17, 9, 1,
                     59, 51, 43, 35, 27, 19, 11, 3,
                     61, 53, 45, 37, 29, 21, 13, 5,
                     63, 55, 47, 39, 31, 23, 15, 7};
    
    for (int i = 0; i < ipTable.length; i++) {
      ip = ip + messageSplit[ipTable[i] - 1];
    }
    
    String[] l = new String[16];
    String[] r = new String[16];
    String l0 = ip.substring(0, ip.length() / 2);
    String r0 = ip.substring(ip.length() / 2);
    
    for (int i = 0; i < 16; i++) {
      if (i == 0) {
        l[i] = r0;
        r[i] = xor(l0, (function(r0, subKeys[0])));
      } else {
        l[i] = r[i - 1];
        r[i] = xor(l[i - 1], function(r[i - 1], subKeys[i]));
      }
    }
    
    String rlInverse = r[15] + l[15];
    String ipFinal = ipPermutation(rlInverse);
    
    return ipFinal;
  }
  
  /**
   * Converts an ASCII string to Hexidecimal.
   * 
   * @param m the String that will be converted to hex.
   * @return the converted hex String.
   */
  public String toHex(String m) {
    return String.format("%040x", new BigInteger(1, m.getBytes(/*YOUR_CHARSET?*/)));
  }
  
  /**
   * Converts a hexidecimal string to binary.
   * 
   * @param hex the hexidecimal string.
   * @return the converted binary string.
   */
  public String hexToBinary(String hex) {
    return new BigInteger(hex, 16).toString(2);
  }
  
  /**
   * Processes the ciphertext by converting the ciphertext from
   * hexidecimal to binary and then decrypts the binary ciphertext.
   *  
   * @param ciphertext the hexidecimal ciphertext string.
   * @param key the key used to decrypt the ciphertext.
   * @return the decrypted plaintext as an ASCII string.
   */
  public String decrypt(String ciphertext, String key) {
    
    String[] cipherSplit = (ciphertext.split("(?<=\\G................)"));
    
    for (int i = 0; i < cipherSplit.length; i++) {
      cipherSplit[i] = new BigInteger(cipherSplit[i], 16).toString(2);
      
      
      while (cipherSplit[i].length() < 64) {
        cipherSplit[i] = "0" + cipherSplit[i];
      }
      
    }
    System.out.println("ciphersplit: " + Arrays.toString(cipherSplit));
    String plaintext = "";
    
    for (int i = 0; i < cipherSplit.length; i++) {
      plaintext = plaintext + hexToAscii(binaryToHex(decryptBin(cipherSplit[i], key)));
    }
    
    return plaintext;
  }
  
  /**
   * Converts a hexidecimal string to ASCII.
   * 
   * @param hex the hexidecimal string to be converted.
   * @return the ASCII string once converted.
   */
  public String hexToAscii(String hex) {
    if (hex.equals("0000000000000000")) {
      return "";
    }
    
    System.out.println("hex: " + hex);
    //first remove any null bytes at the end of hex String.
    while ((hex.substring(hex.length() - 2)).equals("00")) {
      hex = hex.substring(0, hex.length() - 2);
    }
    
    //remove any null bytes at the beggining of the hex srting.
    while (hex.substring(0, 2).equals("00")) {
      hex = hex.substring(2, hex.length());
    }
    
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < hex.length(); i += 2) {
      String str = hex.substring(i, i + 2);
      output.append((char)Integer.parseInt(str, 16));
    }
    
    return output.toString();
  }

  /**
   * Decrypts a 64bit ciphertext using a 64bit DES key.
   * 
   * @param ciphertext the 64 bit ciphertext to be decrypted.
   * @param key the 64bit DES key used to decrypt the message.
   * @return the plaintext once decrypted.
   */
  public String decryptBin(String ciphertext, String key) {
    String[] ciphertextSplit = ciphertext.split("(?!^)");
    KeyGenDES kgd = new KeyGenDES(key);
    String[] subKeys = kgd.generateSubKeys();
    String ip = "";
    int[] ipTable = {58, 50, 42, 34, 26, 18, 10, 2,
                     60, 52, 44, 36, 28, 20, 12, 4,
                     62, 54, 46, 38, 30, 22, 14, 6,
                     64, 56, 48, 40, 32, 24, 16, 8,
                     57, 49, 41, 33, 25, 17, 9, 1,
                     59, 51, 43, 35, 27, 19, 11, 3,
                     61, 53, 45, 37, 29, 21, 13, 5,
                     63, 55, 47, 39, 31, 23, 15, 7};
    
    for (int i = 0; i < ipTable.length; i++) {
      ip = ip + ciphertextSplit[ipTable[i] - 1];
    }
    
    String[] l = new String[16];
    String[] r = new String[16];
    String l0 = ip.substring(0, ip.length() / 2);
    String r0 = ip.substring(ip.length() / 2);
    
    for (int i = 0; i < 16; i++) {
      if (i == 0) {
        l[i] = r0;
        r[i] = xor(l0, (function(r0, subKeys[subKeys.length - 1])));
      } else {
        l[i] = r[i - 1];
        r[i] = xor(l[i - 1], function(r[i - 1], subKeys[subKeys.length - 1 - i]));
      }
    }
    
    String rlInverse = r[15] + l[15];
    String ipFinal = ipPermutation(rlInverse);
    
    return ipFinal;
  }
  
  /**
   * Applies the final permutation as defined by the table "ipInverseTable".
   * 
   * @param rlInverse the result of R16 + L16.
   * @return the binary string after permutation.
   */
  public String ipPermutation(String rlInverse) {
    
    String[] rlSplit = rlInverse.split("(?!^)");
    
    int[] ipInverseTable = 
        {40,     8,   48,    16,    56,   24,    64,   32,
         39,     7,   47,    15,    55,   23,    63,   31,
         38,     6,   46,    14,    54,   22,    62,   30,
         37,     5,   45,    13,    53,   21,    61,   29,
         36,     4,   44,    12,    52,   20,    60,   28,
         35,     3,   43,    11,    51,   19,    59,   27,
         34,     2,   42,    10,    50,   18,    58,   26,
         33,     1,   41,     9,    49,   17,    57,   25};
    
    String ipInverse = "";
    for (int i = 0; i < ipInverseTable.length; i++) {
      ipInverse = ipInverse + rlSplit[ipInverseTable[i] - 1];
    }
    
    return ipInverse;
  }
  
  /**
   * Calculates the function f which is used 16 times to
   * calculate L16 and R16.
   * 
   * @param r the Ri value used to calculate f.
   * @param subKey the subkey used to calculate f.
   * @return the result of the permutation defined by the
   *         table "selectionTable".
   */
  public String function(String r, String subKey) { 
    String[] splitR = r.split("(?!^)");
    
    int[] selectionTable = {32,  1,  2,  3,  4,  5,
                             4,  5,  6,  7,  8,  9,
                             8,  9, 10, 11, 12, 13,
                            12, 13, 14, 15, 16, 17,
                            16, 17, 18, 19, 20, 21,
                            20, 21, 22, 23, 24, 25,
                            24, 25, 26, 27, 28, 29,
                            28, 29, 30, 31, 32, 1};
    
    String er = "";
    
    for (int i = 0; i < selectionTable.length; i++) {
      er = er + splitR[selectionTable[i] - 1];
    }
    
    String ker = xor(er, subKey);
    
    //splits into array of eight 6bit binary numbers.
    String[] kerArray = (ker.split("(?<=\\G......)"));
    String sb = sboxes(kerArray);
    
    String f = permutationP(sb);
    
    return f;
  }
  
  /**
   * Yields a 32bit output from a 32bit input by permuating
   * the bits of the input block using the permutation table.
   * 
   * @param sb the 32bits used in the permutation.
   * @return the the 32bit output after permutation.
   */
  public String permutationP(String sb) {
    
    int[] p = {16,   7,  20, 21,
               29,  12,  28, 17,
               1,  15,  23,  26,
               5,  18,  31,  10,
               2,   8,  24,  14,
               32, 27,   3,   9,
               19,  13,  30,  6,
               22,  11,   4, 25};

    String[] sbSplit = sb.split("(?!^)");
    String f = "";
    
    for (int i = 0; i < p.length; i++) {
      f = f + sbSplit[p[i] - 1];
    }
    
    return f;
  }
  
  /**
   * Returns a permutation of a 48bit number represented as
   * an array of eight 6bit binary strings. The  output is
   * used to calculate f.
   * 
   * @param kerArray the array of eight 6bit binary strings.
   * @return the 32bit output after permutation.
   */
  public String sboxes(String[] kerArray) {
    //3d array for the sboxes
    int[][][] sbox = { { {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                         {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                         {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                         {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
                      },
                      
                      {  {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                         {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                         {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                         {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
    		          },
    		          
    		          {  {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                         {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                         {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                         {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
    		          },
    		          
    		          {  {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
    		             {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
    		             {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
    		             {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
    		          },
    		          
                      {  {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
    		             {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
    		             {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
    		             {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
                      },
                      
                      {  {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                         {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    	 {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                         {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
    		          },
    		          
                      {  {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                         {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                         {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                         {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
                      },
                      
                      {  {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                         {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                         {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                         {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
                      }
                   };
    
    //Where S is the sbox and B is the 6bit number sent in.
    //sbit is Si(Bi) where i is (1 <= i <= 8).
    String sbit = "";
    String sbResult = ""; 
    for (int i = 0; i < kerArray.length; i++) {

      //convert first and last binary digit to int to get row of s1.
      int row1 = Integer.parseInt((kerArray[i].substring(0, 1)
          + kerArray[i].substring(kerArray[i].length() - 1)), 2);
      
      //convert 4 middle bits to int to get the column of sboxes.
      int column1 = Integer.parseInt((kerArray[i].substring(1, 5)), 2);
      sbit = Integer.toBinaryString(sbox[i][row1][column1]);
      
      //make sure the result is 4 bits long, if not add "0" until 4 bits.
      while (sbit.length() < 4) {
        sbit = "0" + sbit;
      }
      
      sbResult = sbResult + sbit;
    }
    
    return sbResult;
  }
  
  /**
   * Calculates logical XOR of two binary strings.
   * 
   * @param a the first binary value.
   * @param b the second binary value.
   * @return the result as a binary String.
   */
  public String xor(String a, String b) {
    //make a the same length as b if a is shorter.
    if (a.length() < b.length()) {
      while (a.length() != b.length()) {
        a = "0" + a;
      }
    }
    
    //make b the same length as a if b is shorter.
    if (b.length() < a.length()) {
      while (b.length() != a.length()) {
        b = "0" + b;
      }
    }
  
    String[] splitA = a.split("(?!^)");
    String[] splitB = b.split("(?!^)");
    String result = "";
    
    for (int i = 0; i < splitA.length; i++) {
      if (Integer.parseInt(splitA[i]) == Integer.parseInt(splitB[i])) {
        result = result + "0";
      } else {
        result = result + "1";
      }
    }
    return result;
  }
  
  /**
   * Generates a random DES key in HEX.
   * 
   * @return the generated DES key.
   */
  public String genKey() {
    int length = 16;
    Random r = new Random();
    StringBuffer sb = new StringBuffer();
    while (sb.length() < length) {
      sb.append(Integer.toHexString(r.nextInt()));
    }
    
    String key = sb.toString().substring(0, length);
    
    //Protection against DES weak keys.
    //Re-generate key if key is weak.
    if (key == "0101010101010101") {
      return genKey();
    } else if (key == "1f1f1f1f0e0e0e0e") {
      return genKey();
    } else if (key == "e0e0e0e0f1f1f1f1") {
      return genKey();
    } else if (key == "fefefefefefefefe") {
      return genKey();
    }
    return key;
  }

}
