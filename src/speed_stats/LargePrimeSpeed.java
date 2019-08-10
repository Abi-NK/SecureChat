package speed_stats;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Random;

import big_math.BigMath;
import big_math.LargePrime;
import big_math.LargePrimeBigMath;

public class LargePrimeSpeed {

  public static void main(String[] args) {
    LargePrimeBigMath lp = new LargePrimeBigMath();
    LargePrime lpi = new LargePrime();
    Random rand = new Random();
    double[] data1 = new double[10];
    double[] data2 = new double[10];
    
    //collect speedtest variables
    double bmAverage = 0;
    double biAverage = 0;
    for (int i = 0; i < 10; i++) {
      
      long startTime3 = System.nanoTime();      
      BigMath one = lp.generatePrime(1024);
      long endTime3 = System.nanoTime();
      long duration3 = (endTime3 - startTime3);
      data1[i] = duration3/1000000.0/1000.0;
      bmAverage += (duration3/1000000.0/1000.0);

      long startTime4 = System.nanoTime();
      BigMath one1 = lpi.generatePrime(1024);
      long endTime4 = System.nanoTime();
      long duration4 = (endTime4 - startTime4);
      data2[i] = duration4/1000000.0/1000.0;
      biAverage += (duration4/1000000.0/1000.0);
    }
    
    //calculate mean
    bmAverage = (bmAverage/10.0);
    biAverage = (biAverage/10.0);
    
    //calculate variance for both tests
    double temp1 = 0;
    for (double a: data1) {
      temp1 += (a - bmAverage)*(a - bmAverage);
    }
    temp1 = Math.sqrt(temp1/9);
    
    double temp2 = 0;
    for (double a: data2) {
      temp2 += (a - biAverage)*(a - biAverage);
    }
    temp2 = Math.sqrt(temp2/9);
    
    //output results
    System.out.println("\n1024-BIT PRIME GENERATION SPEED TEST");
    System.out.println("BigMath mean: " + (bmAverage));
    System.out.println("BigMath variance: " + (temp1));
    System.out.println("BigInteger mean: " + (biAverage));
    System.out.println("BigInteger variance: " + (temp2));
    
    for (int i = 0; i < 100; i++) {
        
        long startTime3 = System.nanoTime();      
        BigMath one = lp.generatePrime(398);
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3);
        bmAverage += (duration3/1000000);

        long startTime4 = System.nanoTime();
        BigMath one1 = lpi.generatePrime(398);
        long endTime4 = System.nanoTime();
        long duration4 = (endTime4 - startTime4);
        biAverage += (duration4/1000000);
      }
    
    System.out.println("\n\nBigMath mean: " + (bmAverage/100));
    System.out.println("BigInteger mean: " + (biAverage/100));
      
      
    for (int i = 0; i < 100; i++) {
        
        long startTime3 = System.nanoTime();      
        BigMath one = lp.generatePrime(512);
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3);
        bmAverage += (duration3/1000000);

        long startTime4 = System.nanoTime();
        BigMath one1 = lpi.generatePrime(512);
        long endTime4 = System.nanoTime();
        long duration4 = (endTime4 - startTime4);
        biAverage += (duration4/1000000);
      }
      
    System.out.println("\nBigMath mean: " + (bmAverage/100));
    System.out.println("BigInteger mean: " + (biAverage/100));
      
    for (int i = 0; i < 100; i++) {
        
        long startTime3 = System.nanoTime();      
        BigMath one = lp.generatePrime(1024);
        long endTime3 = System.nanoTime();
        long duration3 = (endTime3 - startTime3);
        bmAverage += (duration3/1000000);

        long startTime4 = System.nanoTime();
        BigMath one1 = lpi.generatePrime(1024);
        long endTime4 = System.nanoTime();
        long duration4 = (endTime4 - startTime4);
        biAverage += (duration4/1000000);
      }
    System.out.println("\nBigMath mean: " + (bmAverage/100));
    System.out.println("BigInteger mean: " + (biAverage/100));
  }
}
