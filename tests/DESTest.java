import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import des.DES;
import des.KeyGenDES;

class DESTest {
  
  //Hand calulcated the whole DES process with the key below.
  KeyGenDES kgd = new KeyGenDES("133457799bbcdff1");
  DES des = new DES();
  
  @Test
  void testGenerateKey() {
    //genKey uses RNG to generate the DES key.
    //assertEquals("133457799BBCDFF1", des.genKey());
  }
  
  @Test
  void testEncrypt() {
    String message = "0000000100100011010001010110011110001001101010111100110111101111";
    String ciphertext = "1000010111101000000100110101010000001111000010101011010000000101";
    String key = "133457799BBCDFF1";
    assertEquals(ciphertext, des.encryptBin(message, key));
    
  }
  
  @Test
  void testXor() {
    
    //Test xor with same legnth binary numbers.
    assertEquals("1101", des.xor("1011", "0110"));
    assertEquals("1111", des.xor("1111", "0000"));
    assertEquals("0000", des.xor("1111", "1111"));
    
    //Test xor with different length binary numbers.
    assertEquals("1110101", des.xor("1011", "1111110"));
    assertEquals("00100", des.xor("1011", "01111"));
  }
  
  @Test
  void testF() {
    
    String[] subKeys = kgd.generateSubKeys();
    String f = "00100011010010101010100110111011";
    String r0 = "11110000101010101111000010101010";
    assertEquals(f, des.function(r0, subKeys[0]));
    
  }
  
  @Test
  void testSBoxes() {
    String[] kerArray = {"011000", "010001", "011110", "111010",
                         "100001", "100110", "010100", "100111"}; 
    assertEquals("01011100100000101011010110010111", des.sboxes(kerArray));
  }
  
  @Test
  void testPermutation() {
    //Test permuatation of SB using the permutation table.
    String sb = "01011100100000101011010110010111";
    assertEquals("00100011010010101010100110111011", des.permutationP(sb));
  }
  
  @Test
  void testIpPermutation() {
    //Test final ip permutation of RL.
    String rlInverse = "0000101001001100110110011001010101000011010000100011001000110100";
    assertEquals("1000010111101000000100110101010000001111000010101011010000000101",
            des.ipPermutation(rlInverse));
  }
  
  @Test
  void testDecrypt() {
    //Test decrypting ciphertext
    String ciphertext = "1000010111101000000100110101010000001111000010101011010000000101";
    String plaintext = "0000000100100011010001010110011110001001101010111100110111101111";
    String key = "133457799BBCDFF1";
    assertEquals(plaintext, des.decryptBin(ciphertext, key));
  }
  
  @Test
  void testMessage() {
    //acceptance test.
    String message = "hi";
    String ciphertext = "948a43f98a834f7e948a43f98a834f7e5b8b5af1ef4808d2";
    String key = "133457799BBCDFF1";
    assertEquals(ciphertext, des.encrypt(message, key));
    
    assertEquals(message, des.decrypt(ciphertext, key));
  }

}
