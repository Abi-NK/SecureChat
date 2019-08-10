package big_math;

public class ExpBySquaring {
  /**
   * Calculates a number to the power of another BigMath number.
   * Based on pseudocode: https://en.wikipedia.org/wiki/Exponentiation_by_squaring
   * 
   * @param x the base.
   * @param n the power.
   * @return the result as a new BigMath number.
   */
  public BigMath pow(BigMath x, BigMath n) {
    if (n.compareTo(new BigMath("0")) == 0) {
      return new BigMath("1");
    }
    
    BigMath y = new BigMath("1");
    while (n.compareTo(new BigMath("1")) == 1) {
      if (n.isEven()) {
        x = x.multiply(x);
        n = n.divideBy(new BigMath("2"));
      } else {
        y = x.multiply(y);
        x = x.multiply(x);
        n = n.subtract(new BigMath("1")).divideBy(new BigMath("2"));
      }
    }
    return x.multiply(y);
  }

}
