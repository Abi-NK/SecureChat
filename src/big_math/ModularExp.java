package big_math;

import java.math.BigInteger;

public class ModularExp {

  /**
   * Calculates a number to the power of something mod another number.
   * Based on Bruce Schneier’s “Right-to-left binary method” algorithm
   * from “Applied cryptography”. 
   * 
   * @param x the base number.
   * @param y the exponent number.
   * @param p the mod number.
   * @return the result after calculating the modular exponential.
   */
  public BigInteger modPower(BigInteger x, BigInteger y, BigInteger p) {
    //Initialize result 
    BigInteger res = new BigInteger("1");
   
    x = x.mod(p);  
    while (y.compareTo(new BigInteger("0")) == 1) {
      if (isEven(y) == false) {
        res = (res.multiply(x)).mod(p);
      }
      y = y.shiftRight(1);
      x = (x.multiply(x)).mod(p);  
    } 
    return res; 
  }
  
  /**
   * Checks if a number is even.
   * 
   * @param val the number to be checked.
   * @return boolean value true whether the value is even.
   */
  public static boolean isEven(BigInteger val) {
    if (val.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
      return true;
    } else {
      return false;
    }
  }

}
