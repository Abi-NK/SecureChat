import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import big_math.BigMath;
import rsa.ProcessMessage;

class ProcessMessageTest {


  @Test //Test 3: Test processing a message which would be used for encryption.
  void testToInt2() {
    ProcessMessage pm = new ProcessMessage();
    assertEquals("test process message",
        new BigMath("87521618088882533792115812").toRawString(),
        pm.toInt("Hello World").toString());
  }
}
