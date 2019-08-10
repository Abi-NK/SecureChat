import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import big_math.BigMath;
import big_math.ExpBySquaring;

class ExpBySquaringTest {
  
  @Test //Test 1
  void testPow() {
    BigMath base = new BigMath("2");
    BigMath power = new BigMath("23");
    BigMath result = new BigMath("8388608");
    ExpBySquaring exp = new ExpBySquaring();
    assertEquals(result.toRawString(), exp.pow(base, power).toRawString());
    
    BigMath base1 = new BigMath("2");
    BigMath power1 = new BigMath("231");
    BigMath result1 = new BigMath("3450873173395281893717377931138512726225554486085193277581262111899648");
    //assertEquals(result1.toRawString(), exp.pow(base1, power1).toRawString());
  }

}
