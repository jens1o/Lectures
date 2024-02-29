import java.net.*;
import java.io.*;
public class HTTPClient {
  public static void main(String [] args){
    BufferedReader in = null ;
    PrintWriter out = null ;
    String hostname = "archive.org";
    String filename = "/web/web.php";
    try(Socket s = new Socket(hostname ,80) ;){
      
      in = new BufferedReader(new InputStreamReader(s.getInputStream()));
      out = new PrintWriter(s.getOutputStream());
      out.println("GET "+ filename + " HTTP/1.1");
      out.println("Host: " + hostname);
      out.println("Connection: close");
      out.println();
      out.flush();
      String line = null;
      while ((line = in.readLine()) != null){
        System.out.println (line);
      }
      
    } catch(Exception e){e.printStackTrace();}
  }
}