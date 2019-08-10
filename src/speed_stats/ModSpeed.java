package speed_stats;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Random;

import big_math.BigMath;

public class ModSpeed {

  public static void main(String[] args) {
    System.out.println("\n300 DIGIT NUMBERS MOD SPEED TEST");
    BigMath one = new BigMath("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    BigMath two = new BigMath("234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
    long startTime = System.nanoTime();
    one.mod(two);
    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    System.out.println("BigM: " + duration + " ns");
    
    BigInteger one1 = new BigInteger("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");
    BigInteger two1 = new BigInteger("234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901");
    long startTime1 = System.nanoTime();
    one1.divide(two1);
    long endTime1 = System.nanoTime();
    long duration1 = (endTime1 - startTime1);
    System.out.println("BigI: " + duration1 + " ns");
    
    System.out.println("\n300 DIGIT NUMBERS MOD SPEED TEST");
    BigMath one2 = new BigMath(1024, new Random());
    BigMath two2 = new BigMath(512, new Random());
    long startTime2 = System.nanoTime();
    one2.modFast(two2);
    long endTime2 = System.nanoTime();
    long duration2 = (endTime2 - startTime2);
    System.out.println("BigM: " + duration2/1000000 + " ms");
    
    BigInteger one3 = new BigInteger(one2.toRawString());
    BigInteger two3 = new BigInteger(one2.toRawString());
    long startTime3 = System.nanoTime();
    one3.mod(two3);
    long endTime3 = System.nanoTime();
    long duration3 = (endTime3 - startTime3);
    System.out.println("BigI: " + duration3/1000000 + " ms");
  }

}
