import java.io.*;
import java.net.*;


public class ClientHandler implements Runnable {
    Socket socket;
    int clientId;
    Database db;
    //declare variables
    //Constructor
    public ClientHandler (Socket socket, int clientId, Database db) {
        this.socket = socket;
        this.clientId = clientId;
        this.db = db;
    }

    public void run() {

        System.out.println("ClientHandler started...");
        try {
            String clientMessage;
            int titlesNum;
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter  outToClient = new PrintWriter(socket.getOutputStream(),true);

            while(true) {
                clientMessage = inFromClient.readLine();
                if(clientMessage==null || clientMessage.equals("stop"))break;
                System.out.println("Client sent the artist name " + clientMessage);
                titlesNum = db.getTitles(clientMessage);
                outToClient.println("Number of titles: " + titlesNum + " records found");
            }
            outToClient.println("Connection closed, Goodbye!");
            inFromClient.close();
            outToClient.close();
            System.out.println("Client " + clientId + " has disconnected");
            socket.close();


        } catch (IOException e) {
        }

            /*System.out.println("ClientHandler started...");
              Create I/O streams to read/write data, PrintWriter and BufferedReader
              Receive messages from the client and send replies, until the user types "stop"
                  System.out.println("Client sent the artist name " + clientMessage);
                  Request the number of titles from the db
                  Send reply to Client:
                  outToClient.println("Number of titles: " + titlesNum + " records found");

              System.out.println("Client " + clientId + " has disconnected");
              outToClient.println("Connection closed, Goodbye!");
              Close I/O streams and socket*/
    }
}
