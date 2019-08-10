import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import big_math.BigMath;
import big_math.LargePrime;
import big_math.LargePrimeBigMath;

class PrimeTest {

  @Test
  void test() {
    LargePrimeBigMath lp = new LargePrimeBigMath();
    BigMath testP = lp.generatePrime(1024);
    System.out.println(testP.toString());
  }

}
