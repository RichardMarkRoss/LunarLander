import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends NetworkHandler implements Runnable {


    @Override
    public void run() {
    try {
        
    ss = new ServerSocket(8080);
    //Blocks waiting for a connection
    Socket connectedSocket = ss.accept();
    InputStream nis = connectedSocket.getInputStream();
    OutputStream nos = connectedSocket.getOutputStream();
    in = new BufferedReader(new InputStreamReader(nis));
    pw = new PrintWriter(nos);
    
    //TODO:Do stuff, can use network handler....
           
    while(true) {
        if (ready()) {
           // System.out.println("TEST");
          System.out.println(readPacket().toString());
         }
     }
    
    }

    
    catch (Exception IOException) {
        throw new RuntimeException();
    }

}
    
}