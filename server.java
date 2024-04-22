
import java.net.*;
import java.io.*;

class server {

    ServerSocket server;
    Socket socket;
    
    // to read data 
    BufferedReader br;
    //to write out in output stram
    PrintWriter out;

    public server()
    {
        try {
          server = new ServerSocket(7777); // this will create a socket but where to run that socket we need to pass  port;
          System.out.println("server ready to accept connection");
          System.out.println("waiting...");
          socket= server.accept(); //  client sending a request of connection to the socket and server will accept this connection and in return server receive aobject of socker from client 
        

          //byte data from getinput stram  will get converted in to char by input stream reader and that will store by buffer reader
          br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          // i will read datat from br

          out = new PrintWriter(socket.getOutputStream());

          startReading();
          startWriting();
        }
        catch( Exception e) {
          e.printStackTrace();
        }

    }

    public void startReading()
    {
        
    }

    public void startWriting()
    {

    }
    
    public static void main(String[] args)
    {
        System.out.println("Going to start server");
        new server(); //creating a server object and constructor intitliased
    }
}