package big_math;
import java.math.BigInteger;
import java.util.Random;

public class LargePrime {
  
  /**
   * Picks a random number and checks if it's prime, if it isn't
   * then add one to that number and check again.
   *   
   * @return large prime number generated.
   */
  public BigMath generatePrime(int bitLength) {
    //if (a^(p-1)) = 1 (mod p) then p is a prime.
    BigMath primeBigMath = new BigMath(bitLength, new Random());
    BigInteger p = new BigInteger(primeBigMath.toRawString());
    
    while (!primalityTest(p)) {
      p = p.add(new BigInteger("2"));
    }
    return new BigMath(p.toString());
  }
  
  /**
   * Fermat test: Checks if a number is prime.
   * If (a^(p-1)) = 1 (mod p) then p is a prime.
   * Test this for multiple random values of a.
   * 
   * @param p the number to be checked.
   * @return true or false whether p is prime.
   */
  public Boolean primalityTest(BigInteger p) {
    ModularExp modExp = new ModularExp();
    BigInteger primeSubtract = (p.subtract(BigInteger.ONE));
    
    int count = 10;
    BigInteger a = new BigInteger(32, new Random());
    //perform 10 primality tests.
    for (int i = 0; i < count; i++) {
      BigInteger answer = modExp.modPower(a, primeSubtract, p);
      if (!answer.equals(BigInteger.ONE)) {
        return false;
      }
      a = a.add(BigInteger.ONE);
    }
    return true;
  }
}
