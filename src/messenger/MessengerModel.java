package messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import big_math.BigMath;
import big_math.LargePrime;
import des.DES;
import diffie_hellman.DiffieHellman;
import javafx.concurrent.Task;
import rsa.GenerateKeys;
import rsa.ProcessMessage;
import rsa.RsaEncryption;


public class MessengerModel {
  
  BufferedReader in;
  PrintWriter out;
  private String name = "";
  private BigMath[] encryptionPair = new BigMath[2];
  private BigMath[] decryptionPair = new BigMath[2];
  private String sharedKey = "";
  private String clientTwoName = "";
  private String serverKey;
  private String[] clientTwoKey = new String[2];
  private BigMath[] clientTwoPublicKeys = new BigMath[2];
  private BigMath[] ciphertextBig;
  private BigMath[] plaintextBig;
  private String serverAddress;
  
  
  Task<Void> task = new Task<Void>() {
    @Override
    public Void call() throws IOException {
      MessengerController mc = new MessengerController();
      int count = 0;
      DiffieHellman df = new DiffieHellman();
      BigMath ga = null;
      boolean nameSet = false;
      while (true) {
        String line = "";
        setSocket();
        line = in.readLine();

        if (line.startsWith("SUBMITNAME")) {
          System.out.println("SubmitName " + name);
          out.println(name);
          nameSet = true;
        } else if (line.startsWith("NAMEACCEPTED")) {
          System.out.println("NameAccepted");

        } else if (line.startsWith("SUBMITKEY")) {
          System.out.println("submitkey: " + encryptionPair[0].toRawString());
          out.println(encryptionPair[0].toRawString() + " " + encryptionPair[1].toRawString());

        } else if (line.startsWith("MESSAGE")) {
          RsaEncryption rsa = new RsaEncryption();
          System.out.println(line);
          System.out.println("des key: " + sharedKey);
          String[] splitStr = line.split("\\s+");

          String signature = checkSignature(splitStr[2]);
          mc.appendCiphertext(splitStr[2] + "\n" + splitStr[3] + "\n\n");
          
          DES des = new DES();
          String decrypted = des.decrypt(splitStr[3], sharedKey);
          System.out.println(decrypted);

          mc.appendPlaintext(signature + ": " + decrypted + "\n");

        } else if (line.startsWith("SERVERKEY")) {
          serverKey = line.substring(9);
          System.out.println("Client side server key: " + serverKey);

        } else if (line.startsWith("KEYACCEPTED")) {
          System.out.println("Key accepted.");

        } else if (line.startsWith("DIFFIE")) {
          ga = df.stepOne();
          out.println(ga.toRawString());
        } else if (line.startsWith("STEPTWODIFFIE")) {
          String allDiffie = line.substring(14);
          String[] splitStr = allDiffie.split("\\s+");

          if (splitStr[1].equals(ga.toRawString())) {
            String sharedKey = df.stepTwo(new BigMath(splitStr[2]));
            System.out.println(sharedKey.length());
            setKey(sharedKey);

          } else {
            sharedKey = df.stepTwo(new BigMath(splitStr[1]));
            System.out.println(sharedKey.length());
            setKey(sharedKey);
          }
        } else if (line.startsWith("PUBLICKEYS")) {

          String clientTwoPublic = line.substring(11);
          String[] splitStr = clientTwoPublic.split("\\s+");

          if (splitStr[1].equals(encryptionPair[0].toRawString())) {
            clientTwoKey[0] = splitStr[3];
            clientTwoKey[1] = splitStr[4];
          } else {
            clientTwoKey[0] = splitStr[1];
            clientTwoKey[1] = splitStr[2];
          }
          clientTwoPublicKeys[0] = new BigMath(clientTwoKey[0]);
          clientTwoPublicKeys [1] = new BigMath(clientTwoKey[1]);
        } else if (line.startsWith("CLIENTTWONAME")) {
          String clientTwoNames = line.substring(14);
          String[] splitStr = clientTwoNames.split("\\s+");
          System.out.println("array to string names: " + Arrays.toString(splitStr));
          if (splitStr[1].equals(name)) {
            clientTwoName  = splitStr[2];
          } else {
            clientTwoName = splitStr[1];
          }
          mc.setMessengerUI();
        }
      }
    }
  };
  
  public void setName(String n) {
    this.name = n;
  }
  
  public void setKeyPair(BigMath[] e, BigMath[] d) {
    this.encryptionPair = e;
    this.decryptionPair = d;
  }
  
  public void setKey(String key) {
    this.sharedKey = key;
  }
  
  public static void resetKeys(int rsaBitLength) {
    System.out.println("Generating Client keys...");
    //Generate the server keys.
    GenerateKeys gen = new GenerateKeys();
    LargePrime lp = new LargePrime();
      
    //Generate the large primes.
    BigMath primeP = lp.generatePrime(rsaBitLength);
    BigMath primeQ = lp.generatePrime(rsaBitLength);

    //Generate the keys.
    gen.generate(primeP, primeQ);
    BigMath[] encryptionPair = gen.getEncryptionKeys();
    BigMath[] decryptionPair = gen.getDecryptionKeys();
    MessengerController.setEncryptionPair(encryptionPair);
    MessengerController.setDecryptionPair(decryptionPair);
  }
  
  /**
   * Checks the digital signature.
   * 
   * @param sig the digital signature.
   * @return the string based on if the signature passed.
   */
  public String checkSignature(String sig) {
    RsaEncryption rsa = new RsaEncryption();
    BigMath[] ciphertext = rsa.split(sig);
    System.out.println("client two name: " + clientTwoName);
    
    try {
      plaintextBig = rsa.decryption(ciphertext, clientTwoPublicKeys);
      ProcessMessage pm = new ProcessMessage();
      String decryptedString = pm.toString(plaintextBig);
      if (decryptedString.equals(clientTwoName)) {
        return clientTwoName;
      }
    } catch (Exception e) {
      System.out.println("");
    }
    
    try {
      plaintextBig = rsa.decryption(ciphertext, encryptionPair);
      ProcessMessage pm1 = new ProcessMessage();
      String decryptedString1 = pm1.toString(plaintextBig);
      if (decryptedString1.equals(name)) {
        return name;
      }
    } catch (Exception e) {
      System.out.println("");
    }
    return "imposter";
  }
  
  /**
   * Sets up the java sockets using the address from the client.
   * 
   * @throws UnknownHostException if the host address wasn't found.
   * @throws IOException if there is reader problem.
   */
  public void setSocket() throws UnknownHostException, IOException {
    MessengerController mc = new MessengerController();
    Socket socket = new Socket(mc.getAddress(), 9001);
    this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //response
    this.out = new PrintWriter(socket.getOutputStream(), true); //send
  }
  
  public void setAddress(String a) {
    this.serverAddress = a;
  }

}
