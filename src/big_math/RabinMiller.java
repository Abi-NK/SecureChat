package big_math;

import java.math.BigInteger;

public class RabinMiller {
  
  /**
   * Used to calcuate t for Rabin-Miller.
   * 
   * @param n the number sent in.
   * @return the value of t.
   */
  public BigMath calculateT(BigMath n) {
    n = n.subtract(new BigMath("1"));
    BigMath t = new BigMath("0");
    
    while (n.modBigInt(new BigMath("2")).compareTo(new BigMath("0")) == 0) {
      n = n.divideBy(new BigMath("2"));
      t = t.add(new BigMath("1"));
    }
    return t;
  }
  
  /**
   * Not finished.
   * 
   * @param n the number being tested.
   * @return the boolean value whether n is prime or not.
   */
  public boolean primeCheck(BigMath n) {
    BigMath t = calculateT(n);
    /*BigInteger calc = new BigInteger("2").p;
    BigMath s = n.subtract(new BigMath("1")).divideBy(new BigMath("2").)*/
    
    return false;
  }
  
}
