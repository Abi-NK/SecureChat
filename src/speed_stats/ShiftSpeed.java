package speed_stats;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Random;

import big_math.BigMath;

public class ShiftSpeed {

  public static void main(String[] args) {
    System.out.println("\n300 DIGIT NUMBERS SHIFT SPEED TEST");
    BigMath one = new BigMath(1024, new Random());
    BigMath two = new BigMath(512, new Random());
    long startTime3 = System.nanoTime();
    System.out.println(one.shiftRight(1).toRawString());
    long endTime3 = System.nanoTime();
    long duration3 = (endTime3 - startTime3);
    System.out.println("BigM: " + duration3/1000000);
    
    BigInteger one1 = new BigInteger(one.toRawString());
    BigInteger two1 = new BigInteger(512, new Random());
    long startTime4 = System.nanoTime();
    System.out.println(one1.shiftRight(1).toString());
    long endTime4 = System.nanoTime();
    long duration4 = (endTime4 - startTime4);
    System.out.println("BigI: " + duration4/1000000);
  }

}
