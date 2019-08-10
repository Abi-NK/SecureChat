package messenger;

import big_math.BigMath;
import big_math.LargePrime;
import des.DES;
import diffie_hellman.DiffieHellman;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import rsa.GenerateKeys;
import rsa.ProcessMessage;
import rsa.RsaEncryption;


public class MessengerController {
  
  String serverAddress = "";
  BufferedReader in;
  PrintWriter out;
  static BigMath[] encryptionPair = new BigMath[2];
  static BigMath[] decryptionPair = new BigMath[2];
  BigMath[] ciphertextBig;
  BigMath[] plaintextBig;
  private String name = "";
  private String serverKey = "";
  BigMath diffie;
  String sharedKey = "";
  String[] clientTwoKey = new String[2];
  BigMath[] clientTwoPublicKeys = new BigMath[2];
  String clientTwoName;
  BigMath ga = null;
  DiffieHellman df = new DiffieHellman();
  int rsaBitLen = 0;
  
  @FXML
  private Button buttonEnter;
  
  @FXML
  private TextField inputIP;
  
  @FXML
  private TextField inputMessage;
  
  @FXML
  private TextArea messageArea;
  
  @FXML
  private TextField inputName;
  
  @FXML
  private Pane loadingPane;
  
  @FXML
  private Button buttonSend;
  
  @FXML
  private TextArea ciphertextArea;
  
  @FXML
  private TextField desKeyTextField;
  
  @FXML
  private Label desLabel;
  
  @FXML
  private RadioButton radioButton240;
  
  @FXML
  private RadioButton radioButton1024;
  
  @FXML
  private RadioButton radioButton2048;
  
  @FXML
  private ToggleGroup tGroup;
  
  @FXML
  private Label labelTitle;
  
  @FXML
  private TextArea pkTextArea;
  
  @FXML
  private TextArea pvTextArea;
  
  @FXML
  private Label pkLabel;
  
  @FXML
  private Label pvLabel;
  
  @FXML
  private Label serverLabel;
  
  @FXML
  private Button buttonDisconnect;
  
  @FXML
  private Label labelBigMath;
  
  @FXML
  private Circle circle1;
  
  @FXML
  private Circle circle2;
  
  @FXML
  private Circle circle3;
  
  @FXML
  private Pane paneFront;
  
  @FXML
  private Button buttonGenDes;
  
  @FXML
  private Button buttonRegenRsa;
  
  @FXML
  private Button buttonExit;
  
  @FXML
  private Label labelWaiting;
  
  @FXML
  void enter(ActionEvent event) throws IOException, InterruptedException {
    Thread t1 = new Thread() {
      public void run() {
        //loading.gif obtained from https://gifer.com/en/7plQ no author
        loadingPane.setVisible(true);
        labelWaiting.setVisible(true);
        radioButton240.setVisible(false);
        radioButton1024.setVisible(false);
        radioButton2048.setVisible(false);
        labelTitle.setVisible(false);
        paneFront.setVisible(false);
        circle1.setVisible(false);
        circle2.setVisible(false);
        circle3.setVisible(false);
      }
    };
    t1.start();
    t1.join();

    serverAddress = inputIP.getText();
    name = inputName.getText();
    int rsaBitLength = getRadioButton();

    Thread t2 = new Thread(new Runnable() {
      public void run() {
        System.out.println(serverAddress);
        System.out.println("Generating Client keys...");
        //Generate the server keys.
        GenerateKeys gen = new GenerateKeys();
        LargePrime lp = new LargePrime();
        
        //Generate the large primes.
        BigMath primeP = lp.generatePrime(rsaBitLength);
        BigMath primeQ = lp.generatePrime(rsaBitLength);

        //Generate the keys.
        gen.generate(primeP, primeQ);
        encryptionPair = gen.getEncryptionKeys();
        decryptionPair = gen.getDecryptionKeys();
        System.out.println("Client keys generated: " + encryptionPair[0].toRawString());

        try {
          MessengerController.this.run(serverAddress);

          //hide connection UI elements
          inputName.setVisible(false);
          inputIP.setVisible(false);
          buttonEnter.setVisible(false);
          buttonExit.setVisible(false);

        } catch (IOException e) {
          e.printStackTrace();
        }  
      }
    });
    t2.start();
  }
  

  private void run(String serverAddress) throws IOException {
  
    Socket socket = new Socket(serverAddress, 9001);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //response
    out = new PrintWriter(socket.getOutputStream(), true); //send
    
    new Thread(task).start();
  }
  
  @FXML
  void sendMessage(ActionEvent event) {
    DES des = new DES();
    System.out.println("shared key at send: " + sharedKey);
    
    String encryptedMessage = des.encrypt(inputMessage.getText(), sharedKey);
    System.out.println("cipher at send: " + encryptedMessage);
    String challenge = name;
    RsaEncryption rsa = new RsaEncryption();
    String signature = rsa.toString(rsa.encryption(challenge, decryptionPair)).substring(1);
    out.println("MESSAGE " + signature + " " + encryptedMessage);
    inputMessage.setText("");
  }
  
  @FXML
  void disconnect(ActionEvent event) {
    out.println("DISCONNECT");
  }
  
  @FXML
  void exitWindow(ActionEvent event) {
    System.exit(0);
  }
  
  @FXML
  void genDes(ActionEvent event) {
    DiffieHellman df2 = new DiffieHellman();
    BigMath ga2 = df2.stepOne();
    out.println("REGENDES " + ga.toRawString() + " " + ga2.toRawString());
    ga = ga2;
    df = df2;
  }
  
  @FXML
  void genRSA(ActionEvent event) {
    BigMath[] rsaOldPublic = encryptionPair;
    MessengerModel.resetKeys(rsaBitLen);
    pkTextArea.clear();
    pkTextArea.setText(encryptionPair[0].toRawString());
    pvTextArea.clear();
    pvTextArea.setText(decryptionPair[0].toRawString());
    out.println("RESETRSA," + rsaOldPublic[0].toRawString() + " " + rsaOldPublic[1].toRawString() + "," + encryptionPair[0].toRawString() + " "
        + encryptionPair[1].toRawString());
    messageArea.appendText("GENERATED ");
  }
  
  Task<Void> task = new Task<Void>() {
    @Override
    public Void call() throws IOException {
      int count = 0;
      
      
      while (true) {
        String line = in.readLine();
        if (line.startsWith("SUBMITNAME")) {
          System.out.println("SubmitName " + inputName.getText());
          out.println(name);
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
          ciphertextArea.appendText(splitStr[2] + splitStr[3]);
          
          DES des = new DES();
          String decrypted = des.decrypt(splitStr[3], sharedKey);
          System.out.println(decrypted);
          
          messageArea.appendText(signature + ": " + decrypted + "\n");
          
        } else if (line.startsWith("SERVERKEY")) {
          serverKey = line.substring(9);
          System.out.println("Client side server key: " + serverKey);
          
        } else if (line.startsWith("KEYACCEPTED")) {
          System.out.println("Key accepted.");
          
        } else if (line.startsWith("DIFFIE")) {
          ga = df.stepOne();
          out.println(ga.toRawString());
        } else if (line.startsWith("STEPTWODIFFIE")) {
        	messageArea.appendText("GENERATED NEW DES KEY USING DIFFIE-HELLMAN...\n");
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
          messageArea.appendText("NEW RSA KEY...\n");
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
          clientTwoPublicKeys[1] = new BigMath(clientTwoKey[1]);
        } else if (line.startsWith("CLIENTTWONAME")) {
          String clientTwoNames = line.substring(14);
          String[] splitStr = clientTwoNames.split("\\s+");
          System.out.println("array to string names: " + Arrays.toString(splitStr));
          if (splitStr[1].equals(name)) {
            clientTwoName = splitStr[2];
          } else {
            clientTwoName = splitStr[1];
          }
          setMessengerUI();
        } else if (line.startsWith("DISCONNECT")) {
          System.out.println(name + " disconnecting");
          System.exit(0);
        }
      }
    }
  };
  
  public void setKey(String key) {
    this.sharedKey = key;
    desKeyTextField.setText("");
    desKeyTextField.setText(key);
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
      System.out.println("Not Equal to challenge");
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
   * Hide elementes in the UI.
   */
  public void setMessengerUI() {
    loadingPane.setVisible(false);
    labelWaiting.setVisible(false);
    buttonSend.setVisible(true);
    inputMessage.setVisible(true);
    messageArea.setVisible(true);
    ciphertextArea.setVisible(true);
    desKeyTextField.setVisible(true);
    desLabel.setVisible(true);
    pkTextArea.setVisible(true);
    pkTextArea.setText(encryptionPair[0].toRawString());
    pvTextArea.setVisible(true);
    pvTextArea.setText(decryptionPair[0].toRawString());
    pkLabel.setVisible(true);
    pvLabel.setVisible(true);
    serverLabel.setVisible(true);
    buttonDisconnect.setVisible(true);
    buttonRegenRsa.setVisible(true);
    buttonGenDes.setVisible(true);
  }
  
  /**
   * Get the value of the selected radio button.
   * 
   * @return the RSA system that should be used based
   *     on what radio button was selected.
   */
  public int getRadioButton() {
    RadioButton selectedButton = (RadioButton) tGroup.getSelectedToggle();
    String value = selectedButton.getText();
    value = value.substring(4);
    
    if (value.equals("240")) {
      rsaBitLen = 398;
      return 398;
    } else if (value.equals("1024")) {
      rsaBitLen = 512;
      return 512;
    } else if (value.equals("2048")) {
      rsaBitLen = 1024;
      return 1024;
    }
    return 398;
  }
  
  public void textFlowAppend(String c) {
    Text text = new Text(c);
    //ctf.getChildren().add(text);
  }
  
  public void appendCiphertext(String c) {
    ciphertextArea.appendText(c);
  }
  
  public void appendPlaintext(String p) {
    messageArea.appendText(p);
  }
  
  public String getAddress() {
    return serverAddress;
  }
  
  public static void setEncryptionPair(BigMath[] a) {
    encryptionPair = a;
  }
  
  public static void setDecryptionPair(BigMath[] a) {
    decryptionPair = a;
  }
}
