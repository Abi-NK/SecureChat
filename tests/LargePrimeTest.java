import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import big_math.BigMath;
import big_math.LargePrime;

class LargePrimeTest {

  @Test //Test 1
  void testPrimalityTestSmall() {
    //Test for a small prime number.
    LargePrime lp = new LargePrime();
    BigMath testP = lp.generatePrime(5);
    System.out.println(testP.toString());
    System.out.println(testP.toString());
    assertEquals("is a prime.", true, new BigInteger(testP.toRawString()).isProbablePrime(100));
    
  }
  
  @Test
  void testPrimalityTestMultipleForRsa() {
    //Acceptance Test: Test bit-lengths needed for RSA to be secure
    LargePrime lp = new LargePrime();
    BigMath testP = lp.generatePrime(398);
    assertEquals("is a prime.", true, new BigInteger(testP.toRawString()).isProbablePrime(100));
    
    BigMath testP1 = lp.generatePrime(512);
    assertEquals("is a prime.", true, new BigInteger(testP1.toRawString()).isProbablePrime(100));
    
    BigMath testP2 = lp.generatePrime(1024);
    assertEquals("is a prime.", true, new BigInteger(testP2.toRawString()).isProbablePrime(100));
  }
}
