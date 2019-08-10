package speed_stats;

import java.math.BigInteger;
import java.util.Random;

import big_math.BigMath;
import diffie_hellman.DiffieHellman;

public class TestDiffie {

  public static void main(String[] args) {
    
    
    long duration = 0;
    
    for (int i = 0; i < 10; i++) {
      DiffieHellman dfAlice = new DiffieHellman();
      DiffieHellman dfBob = new DiffieHellman();
    
      long startTime = System.nanoTime();
      //STEP ONE: get g(n) and send them to eachother.
      BigMath aliceCombination = dfAlice.stepOne();
      BigMath bobCombination = dfBob.stepOne();
    
      //STEP TWO: using the other person's g(n), calculate the shared secret.
      String sharedSecretAlice = dfAlice.stepTwo(bobCombination);
      String sharedSecretBob = dfBob.stepTwo(aliceCombination);
    
      //measure speed
      long endTime = System.nanoTime();
      duration = duration + (endTime - startTime);
    
      System.out.println("Ali: " + sharedSecretAlice);
      System.out.println("Bob: " + sharedSecretBob);
    
    }
    
    System.out.println("SPEED: " + duration/1000000/10 + " ms");
  }

}
