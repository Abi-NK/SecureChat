import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

import big_math.BigMath;
import diffie_hellman.DiffieHellman;

class DiffieHellmanTest {

  @Test
  void testStepOne() {
    BigMath g = new BigMath("3");
    BigMath p = new BigMath("17");
    DiffieHellman df = new DiffieHellman(g, p);
    
    assertEquals("Test first step.", new BigMath("6").toString(), df.stepOne().toString());
  }
  
  @Test
  void testStepTwo() {
    BigMath g = new BigMath("12");
    BigMath p = new BigMath("17");
    BigMath gp = new BigMath("12");
    DiffieHellman df = new DiffieHellman(g, p);
    
    assertEquals("Test first step.", new BigMath("10").toString(), df.stepTwoTest(gp).toString());
  }

}
