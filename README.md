
# SecureChat

## Test folder location: /tests/

## Introduction

This messenger can perform cryptographic protocols using a hand made big number calculator called "BigMath" to perform quick mathematical operations with little computational resources. The messenger uses RSA as a digital signature system, Diffie-Hellman for to generate a secret shared key, DES for message encryption and hashes, providing confidentiality, integrity and authentication.

<br>

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.



### Prerequisites

```
Java
```

<br>

### File Structure

- Source code located within the RSAProject/src folder.
- Initial reports included in the RSAProject/reports folder.
- Unit tests located in the RSAProject/tests folder.
- Speed tests are in the "speed_test" package in the RSAProject/src folder.
- Runnable jar files are located in the RSAProject folder.
- Final report is located in the RSAProject folder.
- The generated documentation is located in the doc folder.

<br>

### Installing

This is a Windows-only project and works best with Eclipse.
To run the MessengerMainGUI.java file (which is my main class for the project) you will need to install
the following software:
- javafx
- e(fx)
- xtext
- scenebuilder
A tutorial on how to install this software: https://www.youtube.com/watch?v=2PxU7q9xl38

OR:
If you don't want to install these packages and just want to run the program, I included a
runnable java file called SecureChat.jar that runs the program without installation. It's found
within the main RSAProject folder.

<br>


## Running the tests

**Tests are located within the /tests folder as a tests.py files.**
Using Junit testing, right click the test file in Eclipse and run the test.

<br>

## Running the chat server

The java file to run the server is in the "Server" package in the /src folder. Right click on ChatServer.java and select run in eclipse to launch the server. If the server is run locally, the user would have to change the address in the messenger to "localhost".

<br>

## Running the Messenger

Run the program by double clicking on the SecureChat JAR file. The program will ask for a hostname, which
is "localhost" or another specified IP, the user enters their preferred username and the version of RSA
that they would like to use.
Once the enter button has been pressed, the program will wait until the other client connects before joining the chat, there needs to be two people connecting to the server to join the chat. The window can be moved anywhere by holding the right mouse button on any area of the program and dragging it.

Once joined to the chat:
Simply enter a message and click the green "send" button to send an encrypted message to the other user.
The panel on the right of the UI shows the user's RSA key pair, what the server sees, and the DES key
generated through Diffie-Hellman. The program also alows the user to change their RSA key pair or perform
Diffie-Hellman again with the other user to generate another DES key.

ENSURE THAT THE DISCONNECT BUTTON IS PRESSED WHEN YOU WANT TO QUIT THE APPLICATION.

PrototypeRSA.jar can be run to show the protoype of RSA using BigMath.

<br>

## Coding style

* [Checkstyle](https://checkstyle.sourceforge.io) -  Java style guide.


<br>

## Authors

- **Abi Nand Kumar** -------- [Abi-NK](https://github.com/Abi-NK))
