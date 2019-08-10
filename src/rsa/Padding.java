package rsa;

import java.util.Random;

public class Padding {
  public int cutoff;
  
  /**
   * Adds padding to a message.
   * 
   * @param message the string sent to be padded.
   * @return padded message.
   */
  public String padMessage(String message) {
    String paddedMessage = "\0" + "";
    Random rand = new Random();
    char[] c = new char[40];
    int random;
    int min = 65;
    int max = 122;
    
    for (int i = 0; i < c.length; i++) { 
      random = rand.nextInt((max - min) + 1) + min;
      c[i] = (char)random;
    }
    
    paddedMessage = paddedMessage + String.valueOf(c);
    paddedMessage = paddedMessage + "\0" + message;
    return paddedMessage;
  }
  
  /**
   * Removes padding from a message.
   * 
   * @param message the padded string sent to be processed.
   * @return un-padded message or 'denied' for a message that isn't padded.
   */
  public String removePad(String message) {
    char[] messageChar = message.toCharArray();
    
    if (message.charAt(0) == '\0' && message.charAt(1) == '') {
      for (int i = 1; i < messageChar.length; i++) {
        if (messageChar[i] == '\0') {
          this.cutoff = i;
        }
      }
      
      String resultMessage = "";
      for (int i = this.cutoff + 1; i < messageChar.length; i++) {
        resultMessage = resultMessage + messageChar[i];
      }
      
      return resultMessage;
    }
    return "Denied";
  }
}
