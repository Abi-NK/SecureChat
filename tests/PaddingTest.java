import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rsa.Padding;

class PaddingTest {
  
  @Test //Test 1
  void testPadMessage() {
    Padding padding = new Padding();
    System.out.println(padding.padMessage("Hello World"));
  }
  
  @Test //Test 2
  void testRemovePadding() {
    Padding padding = new Padding();
    System.out.println(padding.removePad("Hello World"));
    
    assertEquals("Denied", padding.removePad("Hello World"));
    
    System.out.println(padding.removePad("\0MwbOXdGRysBkQ^ZmgsI^y["
        + "[Lbv^xYgkGFW[xVpHc\0Hello World"));
    assertEquals("Hello World", padding.removePad("\0MwbOXdGRysBkQ^"
        + "ZmgsI^y[[Lbv^xYgkGFW[xVpHc\0Hello World"));
  }
}
