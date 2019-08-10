Source code located within the RSAProject/src folder.
Initial reports included in the RSAProject/reports folder.
Unit tests located in the RSAProject/tests folder.
Speed tests are in the "speed_test" package in the RSAProject/src folder.
Runnable jar files are located in the RSAProject folder.
Final report is located in the RSAProject folder.
The generated documentation is located in the doc folder.


HOW TO RUN THIS ECLISPE PROJECT:
JAVA JRE MUST BE INSTALLED.
THIS PROJECT ONLY WORKS ON WINDOWS.
To run the prototype classes no extra libraries are needed, just need to run the prototype class in eclipse..

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

YOUTUBE LINK OF SOFTWARE WORKING: https://youtu.be/jRgUvIGkhg8

Running the Server
The server is already hosted online on the IP 138.68.168.181, so it doesn’t need to be run, but if the
you wanted to test the server, the java file to run the server is in the “Server” package in the “src”
folder. Right click on ChatServer.java and select run in eclipse to launch the server. If the server is run locally, the
user would have to change the address in the messenger to “localhost”.


Running the Messenger
Run the program by double clicking on the SecureChat JAR file. The program will ask for a hostname, which
is 138.68.168.181 to connect to the server online, the user’s preferred username and the version of RSA
that the user would like to use.
Once the enter button has been pressed, the program will wait until the other client connects before
joining the chat. The window can be moved anywhere by holding the right mouse button on any area of
the program and dragging it.

Once joined to the chat:
Simply enter a message and click the green “send” button to send an encrypted message to the other user.
The panel on the right of the UI shows the user’s RSA key pair, what the server sees, and the DES key
generated through Diffie-Hellman. The program also alows the user to change their RSA key pair or perform
Diffie-Hellman again with the other user to generate another DES key.

ENSURE THAT THE DISCONNECT BUTTON IS PRESSED WHEN YOU WANT TO QUIT THE APPLICATION.

PrototypeRSA.jar can be run to show the protoype of RSA using BigMath.
