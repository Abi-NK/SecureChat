package big_math;

public class ModularExpBigMath {


  /**
   * Calculates a number to the power of something mod another number.
   * Based on Bruce Schneier’s “Right-to-left binary method” algorithm
   * from “Applied cryptography”.
   * 
   * @param a the base number.
   * @param b the exponent number.
   * @param p the mod number.
   * @return the result after calculating the modular exponential.
   */
  public BigMath modPower(BigMath a, BigMath b, BigMath p) {
    // Initialize result 
    BigMath res = new BigMath("1");
    int count = 0;

    a = a.modBigInt(p);  
    while (b.compareTo(new BigMath("0")) == 1) {
      if (isEven(b) == false) {
        res = (res.multiply(a)).modBigInt(p);
      }
      b = b.shiftRight(1);  
      count++;
      a = (a.multiply(a)).modBigInt(p);
    } 
    
    return res; 
  }
  
  /**
   * Checks if a number is even.
   * 
   * @param val the number to be checked.
   * @return boolean value true whether the value is even.
   */
  public boolean isEven(BigMath val) {
    if (val.digits[val.digits.length - 1] == 0
        || val.digits[val.digits.length - 1] == 2
        || val.digits[val.digits.length - 1] == 4
        || val.digits[val.digits.length - 1] == 6
        || val.digits[val.digits.length - 1] == 8) {
      return true;
    } else {
      return false;
    }
  }

}
