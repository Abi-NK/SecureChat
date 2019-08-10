import static org.junit.jupiter.api.Assertions.*;

import des.KeyGenDES;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class KeyGenDESTest {
  
  KeyGenDES kgd = new KeyGenDES("133457799BBCDFF1");


  @Test
  void testGenerateKey() {
    KeyGenDES kgd = new KeyGenDES("133457799BBCDFF1");
    //System.out.println(kgd.generateKey());
  }
  
  @Test
  void testGenerateKeyPlus() {
    //using K = 133457799BBCDFF1
    assertEquals("11110000110011001010101011110101010101100110011110001111", kgd.generateKeyPlus());
    
  }
  
  @Test
  void testGenerateSubKeys() {
    kgd.generateSubKeys();
  }

}
