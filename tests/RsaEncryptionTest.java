import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import big_math.BigMath;
import rsa.RsaEncryption;

class RsaEncryptionTest {
  RsaEncryption rsa = new RsaEncryption();

  @SuppressWarnings("static-access")
  @Test //Test 1
  public void testEncryption() throws InterruptedException {
    BigMath e = new BigMath("853");
    BigMath phi = new BigMath("1067");
    BigMath[] encryptionPair = {e, phi};
    BigMath[] ciphertextResult = new BigMath[1];
    ciphertextResult[0] = new BigMath("388");
    assertEquals("Test encryption.", ciphertextResult[0], rsa.encryption("a", encryptionPair)[0]);
    
    encryptionPair[0] = new BigMath("859");
    BigMath[] ciphertextResult2 = new BigMath[1];
    ciphertextResult2[0] = new BigMath("385");
    //assertEquals("Test encryption.", ciphertextResult2[0],
    //rsa.encryption("c", encryptionPair)[0]);

    encryptionPair[0] = new BigMath("379");
    BigMath[] ciphertextResult3 = new BigMath[1];
    ciphertextResult3[0] = new BigMath("735");
    //assertEquals("Test encryption.", ciphertextResult3[0],
    //rsa.encryption("h", encryptionPair)[0]);
  }
  
  @SuppressWarnings("static-access")
  @Test //Test 2
  public void testDecryption() {
    BigInteger d = new BigInteger("137");
    BigInteger phi = new BigInteger("1067");
    BigInteger[] decryptionPair = {d, phi};
    BigInteger ciphertext = new BigInteger("388");
    char plaintextResult = 'a';
    //assertEquals("Test decryption.", plaintextResult,
    //(char)rsa.decryption(ciphertext, decryptionPair).intValue());

    decryptionPair[0] = new BigInteger("887");
    BigInteger ciphertext1 = new BigInteger("366");
    char plaintextResult1 = 'w';
    //assertEquals("Test decryption.", plaintextResult1,
    //(char)rsa.decryption(ciphertext1, decryptionPair).intValue());
    
    decryptionPair[0] = new BigInteger("1027");
    BigInteger ciphertext2 = new BigInteger("217");
    char plaintextResult2 = 'p';
    //assertEquals("Test decryption.", plaintextResult2,
    //(char)rsa.decryption(ciphertext2, decryptionPair).intValue());
  }
}
