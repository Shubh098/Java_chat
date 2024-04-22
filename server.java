
import java.net.*;
import java.io.*;

class server {

    ServerSocket server;
    Socket socket;
    
    // to read data 
    BufferedReader br;
    //to write out in output stram
    PrintWriter out;
    
    // Constructor
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
   
    // to do simultaneously reading and writign we require the threading concept now
    /**
     * 
     */
    public void startReading()
    {
        // thread- read krke deta  rhega
        // step to create thread using lambda expression
        Runnable r1= () -> {
           // thread implementation
           
           System.out.println("Reading started.");
           try {
           while(true)
           {
            // reada data from buffer reader object
                 String msg = br.readLine();
                 
                 if(msg.equals("exit"))
                   {
                    System.out.println("Client has terminate the chat");
                    socket.close();
                    break;
                   }
                // print msg in console 
                System.out.println("client:" + msg); 
                
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        };
      new Thread(r1).start();
    }

    public void startWriting()
    {
         // thread-  data user lega and send krega client tak
         System.out.println("Writer started..");
         Runnable r2= () -> {
            try {
            while(true && !socket.isClosed())
            {
                
                  //taking data from console and storing it in a buffer  
                  BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                  // reading data from buffer
                  String content = br1.readLine();

                  out.println(content);
                  out.flush();
                 
                  if (content.equals("exit"))
                  {
                    socket.close();
                  }
            }
            }
            catch(Exception e){
            e.printStackTrace();
           }
         };
         // create a thread object anf passed thread 
         new Thread(r2).start(); 
    }

    public static void main(String[] args)
    {
        System.out.println("Going to start server");
        new server(); //creating a server object and constructor intitliased
    }
}