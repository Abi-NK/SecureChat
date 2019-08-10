package big_math;

public class ModPow {
  
  /**
   * The operation of modular exponentiation calculates the remainder when
   * an integer b (the base) raised to the eth power (the exponent), b^e,
   * is divided by a positive integer m (the modulus).
   * 
   * @param base the base.
   * @param exponent the exponent.
   * @param modulus the modulus.
   * @return the remainder once calculated.
   */
  public BigMath modPow(BigMath base, BigMath exponent, BigMath modulus) {
    
    if (modulus.compareTo(new BigMath("1")) == 0) {
      return new BigMath("0");
    }
    
    BigMath result = new BigMath("1");
    base = base.modBigInt(modulus);
    
    while (exponent.compareTo(new BigMath("0")) == 1) {
      System.out.println(result.toRawString());
      if (exponent.modBigInt(new BigMath("2")).compareTo(new BigMath("1")) == 0) {
        result = (result.multiply(base)).modBigInt(modulus);
      }
      exponent = exponent.shiftRight(2);
      base = (base.multiply(base)).modBigInt(modulus);
    }
    
    return result;
  }

}
