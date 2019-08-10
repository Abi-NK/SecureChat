import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import big_math.BigMath;
import big_math.ModularExp;
import big_math.ModularExpBigMath;

class ModExpTest {

  @Test
  void testBigInteger() {
    ModularExp mdx = new ModularExp();
    BigInteger a = new BigInteger("2");
    BigInteger b = new BigInteger("5");
    BigInteger c = new BigInteger("14");
    BigInteger result = new BigInteger("4");
    
    assertEquals("modexp", result.toString(), mdx.modPower(a, b, c).toString());
    assertEquals("iseven", true, mdx.isEven(a));
  }
  
  @Test
  void testBigMath() {
    ModularExpBigMath mdx = new ModularExpBigMath();
    BigMath a = new BigMath("2");
    BigMath b = new BigMath("5");
    BigMath c = new BigMath("14");
    BigMath result = new BigMath("4");
    
    assertEquals("modexp", result.toString(), mdx.modPower(a, b, c).toString());
    assertEquals("iseven", true, mdx.isEven(a));
  }

}
