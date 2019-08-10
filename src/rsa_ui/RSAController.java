package rsa_ui;

import java.math.BigInteger;

import big_math.BigMath;
import big_math.LargePrime;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import rsa.GenerateKeys;
import rsa.ProcessMessage;
import rsa.RsaEncryption;

public class RSAController {
  
  BigMath[] encryptionPair = new BigMath[2];
  BigMath[] decryptionPair = new BigMath[2];
  BigMath[] ciphertextBig;
  BigMath[] plaintextBig;
  private String ciphertextString = "";

  @FXML
  private Button buttonGenerate;
  
  @FXML
  private TextFlow encryptionKey;
  
  @FXML
  private TextFlow decryptionKey;

  @FXML
  private Button buttonEncrypt;
  
  @FXML
  private TextFlow ciphertextFlow;
  
  @FXML
  private TextArea inputMessage;
  
  @FXML
  private Button buttonDecrypt;
  
  @FXML
  private Button buttonCopy;
  
  @FXML
  private Button buttonShowKey;
  
  @FXML
  private Button buttonLoading;
  
  @FXML
  void generate(ActionEvent event) {
    encryptionKey.getChildren().clear();
    decryptionKey.getChildren().clear();
    decryptionKey.setVisible(false);
    buttonGenerate.setDisable(true);
    buttonLoading.setVisible(true);
    
    GenerateKeys gen = new GenerateKeys();
    LargePrime lp = new LargePrime();
    int bitLength = 1024;
    
    //Generate the large primes.
    BigMath primeP = lp.generatePrime(bitLength);
    BigMath primeQ = lp.generatePrime(bitLength);
    
    System.out.println("\n" + primeP.toString() + "\n");
    
    //Generate the keys.
    gen.generate(primeP, primeQ);
    this.encryptionPair = gen.getEncryptionKeys();
    this.decryptionPair = gen.getDecryptionKeys();
    
    Text encryptionKeyString = new Text("Public key:\n" + encryptionPair[0].toString());
    encryptionKey.getChildren().add(encryptionKeyString);

    Text decryptionKeyString = new Text("Private key:\n" + decryptionPair[0].toString());
    decryptionKey.getChildren().add(decryptionKeyString);
    
    buttonEncrypt.setDisable(false);
    buttonShowKey.setDisable(false);
    buttonGenerate.setDisable(false);
    buttonLoading.setVisible(false);
  }
  
  @FXML
  void encryptMessage(ActionEvent event) {
    
    ciphertextFlow.getChildren().clear();
    RsaEncryption rsa = new RsaEncryption();
    
    String plaintext = inputMessage.getText();
    this.ciphertextBig = rsa.encryption(plaintext, encryptionPair);
    
    ciphertextString = "";
    
    for (int i = 0; i < ciphertextBig.length; i++) {
      ciphertextString += ciphertextBig[i].toString();
    }
    
    Text ciphertextText = new Text("Ciphertext:\n" + ciphertextString);
    ciphertextFlow.getChildren().add(ciphertextText);
    
    inputMessage.clear();
    buttonDecrypt.setDisable(false);
    buttonCopy.setDisable(false);
    buttonGenerate.setDisable(true);
  }
  
  @FXML
  void decryptMessage(ActionEvent event) {
    ciphertextFlow.getChildren().clear();
    RsaEncryption rsa = new RsaEncryption();
    
    plaintextBig = rsa.decryption(ciphertextBig, decryptionPair);
    ProcessMessage pm = new ProcessMessage();
    String decryptedString = pm.toString(plaintextBig);
    
    Text ciphertextText = new Text("Decrypted:\n" + decryptedString);
    ciphertextFlow.getChildren().add(ciphertextText);
    
    buttonGenerate.setDisable(false);
  }
  
  @FXML
  void copyToClip(ActionEvent event) {
  
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();
    StringSelection strSel = new StringSelection(ciphertextString);
    clipboard.setContents(strSel, null);
  }
  
  @FXML
  void showKey(ActionEvent event) {
    
    if (decryptionKey.isVisible()) {
      decryptionKey.setVisible(false);
    } else {
      decryptionKey.setVisible(true);
    }
    
  }
}
