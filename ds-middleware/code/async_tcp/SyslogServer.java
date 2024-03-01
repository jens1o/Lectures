package async_tcp;

import java.net.*;
import java.io.*;

public class SyslogServer {
    public static void main(String[] args) {
        BufferedReader in = null;
        try {
            ServerSocket server = new ServerSocket(9999);
            while (true) {
                try (Socket con = server.accept()) {
                    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    System.out.println("[Logging] " + in.readLine());
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
