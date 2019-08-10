package speed_stats;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Random;

import big_math.BigMath;

public class AdditionSpeed {

  public static void main(String[] args) {
    
    //Test 1: 128-bit calculation
    long bmAverage = 0;
    long biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(128, new Random());
      BigMath two = new BigMath(128, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n128-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage / 100) / 1000000);
    System.out.println("BigInteger: " + (biAverage / 100) / 1000000);
    
    //Test 2: 256-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(256, new Random());
      BigMath two = new BigMath(256, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n256-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage/100)/1000000);
    System.out.println("BigInteger: " + (biAverage/100)/1000000);
    
    //Test 3: 512-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(512, new Random());
      BigMath two = new BigMath(512, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n512-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage/100)/1000000);
    System.out.println("BigInteger: " + (biAverage/100)/1000000);
    
    //Test 4: 1024-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(1024, new Random());
      BigMath two = new BigMath(1024, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n1024-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage/100)/1000000);
    System.out.println("BigInteger: " + (biAverage/100)/1000000);
    
    //Test 5: 2048-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(2048, new Random());
      BigMath two = new BigMath(2048, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n2048-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage/100)/1000000);
    System.out.println("BigInteger: " + (biAverage/100)/1000000);
    
    //Test 6: 4096-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(4096, new Random());
      BigMath two = new BigMath(4096, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n4096-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage/100)/1000000);
    System.out.println("BigInteger: " + (biAverage/100)/1000000);
    
    //Test 7: 8192-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(8192, new Random());
      BigMath two = new BigMath(8192, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n8192-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage/100)/1000000);
    System.out.println("BigInteger: " + (biAverage/100)/1000000);
    
    //Test 8: 16384-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(16384, new Random());
      BigMath two = new BigMath(16384, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n16384-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage / 100) / 1000000);
    System.out.println("BigInteger: " + (biAverage / 100) / 1000000);
    
    //Test 8: 32768-bit calculation
    bmAverage = 0;
    biAverage = 0;
    for (int i = 0; i < 100; i++) {
      
      BigMath one = new BigMath(32768, new Random());
      BigMath two = new BigMath(32768, new Random());
      long startTime3 = System.nanoTime();      
      one = one.add(two);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      bmAverage += duration3;
      
      BigInteger one1 = new BigInteger(one.toRawString());
      BigInteger two1 = new BigInteger(two.toRawString());
      long startTime4 = System.nanoTime();
      one1 = one1.add(two1);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      biAverage += duration4;
    }
    
    System.out.println("\n32768-BIT NUMBER SUBTRACT SPEED TEST");
    System.out.println("BigMath: " + (bmAverage / 100) / 1000000);
    System.out.println("BigInteger: " + (biAverage / 100) / 1000000);
  }
}
