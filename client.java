import java.io.*;
import java.net.Socket;

public class client {
    
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public client()
    {

        try {
            
            System.out.println("Sending request to server");
            //  we creare a request socket where we pass the server ip address and port sending request to server
            socket = new Socket("127.0.0.1", 7777);
           


            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // i will read datat from br
  
            out = new PrintWriter(socket.getOutputStream());
  
            startReading();
            startWriting();
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void startReading()
    {
        // thread- read krke deta  rhega
        // step to create thread using lambda expression
        Runnable r1= () -> {
           // thread implementation
           
           System.out.println("Reading started.");
           while(true)
           {
            // reada data from buffer reader object
               try {
                 String msg = br.readLine();
                 
                 if(msg.equals("exit"))
                   {
                    System.out.println("Client has terminate the chat");
                    break;
                   }
                // print msg in console 
                System.out.println("client:" + msg); 
                }
                catch (Exception e){
                e.printStackTrace();
                }
                
            }
        };
      new Thread(r1).start();
    }

    public void startWriting()
    {
         // thread-  data user lega and send krega client tak
         System.out.println("Writer started..");
         Runnable r2= () -> {
            while(true)
            {
                 try{
                  //taking data from console and storing it in a buffer  
                  BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                  // reading data from buffer
                  String content = br1.readLine();
                  out.println(content);
                  out.flush();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
         };
         // create a thread object anf passed thread 
         new Thread(r2).start(); 
    }

    public static void main(String[] args) {
        System.out.println("this is client side ");
        new client();
    }
}
