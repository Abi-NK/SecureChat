package speed_stats;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Random;

import big_math.BigMath;
import big_math.LargePrime;
import big_math.LargePrimeBigMath;
import des.DES;
import rsa.GenerateKeys;
import rsa.RsaEncryption;

public class DESSpeed {

  public static void main(String[] args) {
    
    GenerateKeys gen = new GenerateKeys();
    LargePrime lp = new LargePrime();
    RsaEncryption rsa = new RsaEncryption();
    DES des = new DES();
    String symKey = "133457799bbcdff1";
    
    String plaintext = new BigMath(1024, new Random()).toRawString();
    
    //Generate the large primes.
    BigMath primeP = lp.generatePrime(1024);
    BigMath primeQ = lp.generatePrime(1024);
    
    
    //Generate the keys.
    gen.generate(primeP, primeQ);
    BigMath[] encryptionPair = gen.getEncryptionKeys();
    BigMath[] decryptionPair = gen.getDecryptionKeys();
    
    long startTime3 = System.nanoTime();      
    rsa.encryption(plaintext, encryptionPair);
    long endTime3 = System.nanoTime();
    long duration3 = (endTime3 - startTime3);

    long startTime4 = System.nanoTime();
    des.encrypt(plaintext, symKey);
    long endTime4 = System.nanoTime();
    long duration4 = (endTime4 - startTime4);

    System.out.println("\nRSA VS DES ENCRYPTION SPEED TEST");
    System.out.println("RSA: " + (duration3)/1000000);
    System.out.println("DES: " + (duration4)/1000000);

  }
}
