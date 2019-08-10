package server;

import big_math.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import rsa.*;

//Edited code from http://cs.lmu.edu by no name.
public class ChatServer {

  private static final int port = 9001; //port number
  private static Set<String> names = new HashSet<>(); //all names in chat
  private static Set<PrintWriter> writers = new HashSet<>(); //print writers for all clients
  private static Set<String> publicKeys = new HashSet<>();
  private static Set<String> ga = new HashSet<>();
  private static BigMath[] encryptionPair = new BigMath[2];
  private static BigMath[] decryptionPair = new BigMath[2];
  
  /**
   * Runs the server for clients to communicate.
   * 
   * @param args the values sent through command line.
   * @throws Exception if the server can't start.
   */
  public static void main(String[] args) throws Exception {
    names.clear();
    writers.clear();
    publicKeys.clear();
    ga.clear();
    
    Thread t1 = new Thread(new Runnable() {
      public void run() {
        System.out.println("Generating server keys...");
        //Generate the server keys.
        GenerateKeys gen = new GenerateKeys();
        LargePrime lp = new LargePrime();

        int bitLength = 398;
        //Generate the large primes.
        BigMath primeP = lp.generatePrime(bitLength);
        BigMath primeQ = lp.generatePrime(bitLength);

        //Generate the keys.
        gen.generate(primeP, primeQ);
        encryptionPair = gen.getEncryptionKeys();
        decryptionPair = gen.getDecryptionKeys();
        System.out.println("Server keys generated: " + encryptionPair[0].toRawString());
      }
    });  
    t1.start();
    System.out.println("Server running.");
    try (ServerSocket listener = new ServerSocket(port)) {
      while (true) {
        new Handler(listener.accept()).start();
      }
    }
  }
  
  public static class Handler extends Thread {
    private String name;
    private Socket socket;
    private String key;
    private BufferedReader in;
    private PrintWriter out;
    private int connectedCounter;
    private String df;

    public Handler(Socket socket) {
      this.socket = socket;
    }
    
    /**
     * Runs the server.
     */
    public void run() {
      try {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        //loop until the name of user is recieved.
        while (true) {
          out.println("SUBMITNAME");
          name = in.readLine();
          if (name == null) {
            return;
          }
          synchronized (names) {
            if (!names.contains(name)) {
              names.add(name);
              break;
            }
            System.out.println("same name");
          }
        }

        //Once name is initialised, send the public key of server. And name accepted verification.
        out.println("SERVERKEY" + encryptionPair[0].toRawString()
            + " " + encryptionPair[1].toRawString());
        out.println("NAMEACCEPTED");
        this.connectedCounter++;
        System.out.println(out);
        writers.add(out);

        //Ask for client's public key. Loop until recieved.
        while (true) {
          System.out.println("testsubmitkey");
          out.println("SUBMITKEY");
          key = in.readLine();

          if (key == null) {
            return;
          }

          synchronized (publicKeys) {
            if (!publicKeys.contains(key)) {
              publicKeys.add(key);
              break;
            }
          }
        }

        //Send a key accepted message when client successfully sends key.
        System.out.println("hola");
        out.println("KEYACCEPTED");
        System.out.println(publicKeys.toString());

        //wait until there are two clients and perform diffie hellman
        while (ga.size() < 2) {
          out.println("DIFFIE");
          df = in.readLine();
          ga.add(df);
        }

        //send back the diffie hellman values for step two of DH.
        //after this both clients will have generated a shared secret key.
        df = "";
        for (String aa: ga) {
          df = df + " " + aa;
        }

        for (PrintWriter writer : writers) {
          writer.println("STEPTWODIFFIE " + df);
        }

        //Send the public keys.
        String sig = "";
        for (String sigI: publicKeys) {
          sig = sig + " " + sigI;
        }

        for (PrintWriter writer : writers) {
          writer.println("PUBLICKEYS " + sig);
        }

        //send all names in the server for authentication
        String namesStr = "";
        for (String namesI : names) {
          namesStr = namesStr + " " + namesI;
        }

        for (PrintWriter writer : writers) {
          writer.println("CLIENTTWONAME " + namesStr);
        }

        //listen for messages.
        while (true) {
          String input = in.readLine();
          if (input == null) {
            return;
          }
          
          if (input.startsWith("DISCONNECT")) {
            for (PrintWriter writer : writers) {
              writer.println("DISCONNECT");
            }
            String[] args = new String[0];
            main(args);
            break;
          }
          
          if (input.startsWith("RESETRSA")) {
            String[] splited = input.split(",");
            if (publicKeys.contains(splited[1])) {
              publicKeys.remove(splited[1]);
              publicKeys.add(splited[2]);
            }
            
            //Send the public keys.
            String sign = "";
            for (String sigI: publicKeys) {
              sign = sign + " " + sigI;
            }

            for (PrintWriter writer : writers) {
              writer.println("PUBLICKEYS " + sign);
            }
          }
          
          if (input.startsWith("REGENDES")) {
            String[] splited = input.split("\\s+");
            System.out.println("split arr: " + Arrays.toString(splited));
            System.out.println("ga: " + ga.toString());

            if (ga.contains(splited[1])) {
              ga.remove(splited[1]);
              ga.add(splited[2]);
              System.out.println("ga: " + ga.toString());
            }

            df = "";
            for (String aa: ga) {
              df = df + " " + aa;
            }

            for (PrintWriter writer : writers) {
              writer.println("STEPTWODIFFIE " + df);
            }
          }
          
          if (input.startsWith("@")) {
            String[] arr = input.split(" ", 2);
            arr[0] = arr[0].substring(1);

            for (PrintWriter writer : writers) {
              writer.println("MESSAGE " + name + ": " + arr[1]);
            }
            
          } else if (input.startsWith("MESSAGE")) {
            input = input.substring(8);
            for (PrintWriter  writer : writers) {
              writer.println("MESSAGE " + name + ": " + input);
            }
          }
        }
      } catch (IOException e) {
        System.out.println(e);
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
        if (name != null) {
          names.remove(name);
        }
        if (out != null) {
          writers.remove(out);
        }
        try {
          socket.close();
        } catch (IOException e) {
          System.out.println("");
        }
      }
    }

    public int getConnectedCounter() {
      return this.connectedCounter;
    }
  }
}
