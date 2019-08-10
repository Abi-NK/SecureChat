package big_math;

import java.math.BigInteger;
import java.util.Random;

public class LargePrimeBigMath {
  
  /**
   * Picks a random number and checks if it's prime, if it isn't
   * then add one to that number and check again.
   *   
   * @return large prime number generated.
   */
  public BigMath generatePrime(int bitLength) {
    //if (a^(p-1)) = 1 (mod p) then p is a prime.
    BigMath p = new BigMath(bitLength, new Random());

    while (!primalityTest(p)) {
      p = p.add(new BigMath("2"));
    }
    return p;
  }
  
  /**
   * Fermat test: Checks if a number is prime.
   * If (a^(p-1)) = 1 (mod p) then p is a prime.
   * Test this for multiple random values of a.
   * 
   * @param p the number to be checked.
   * @return true or false whether p is prime.
   */
  public Boolean primalityTest(BigMath p) {
    ModularExp modExp = new ModularExp();
    BigMath primeMinus = p.subtract(new BigMath("1"));
    
    if (p.digits[p.digits.length - 1] == 5) {
      return false;
    }
    
    int count = 10;
    BigMath a = new BigMath("2");
    //perform 10 primality tests on the number.
    for (int i = 0; i < count; i++) {
      BigMath answer = new BigMath(modExp.modPower(new BigInteger(a.toRawString()),
          new BigInteger(primeMinus.toRawString()), new BigInteger(p.toRawString())).toString());
      if (answer.compareTo(new BigMath("1")) != 0) {
        return false;
      }
      a = a.add(new BigMath("1"));
    }
    return true;
  }
}
