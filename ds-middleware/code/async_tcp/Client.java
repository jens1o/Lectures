package async_tcp;

import java.net.*;
import java.io.*;
import java.util.Queue;
import java.util.ArrayDeque;

public class Client {

    private static void sendMsg(String msg) throws IOException {
        try (Socket s = new Socket("localhost"/* hostname */, 9999/* serverPort */)) {
            BufferedReader networkIn = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter networkOut = new PrintWriter(s.getOutputStream());
            networkOut.println(msg);
            networkOut.flush();
        }
    }

    private static Queue<String> queue = new ArrayDeque<>();

    public static void log(String msg) {
        try {
            synchronized (queue) {
                if (!queue.isEmpty()) {
                    queue.add(msg); // we never want to sent them out of order
                } else {
                    sendMsg(msg);
                }
            }
        } catch (IOException ioe) {
            System.err.println("[Info]: can't log: " + ioe);
            queue.add(msg);
        }
    }

    public static void startThread() throws Exception {
        Thread.ofVirtual().start(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    /* HERE, we don't care! */ }
                synchronized (queue) {
                    while (!queue.isEmpty()) {
                        var msg = queue.peek(); // we have to keep the message in the queue
                        try {
                            sendMsg(msg);
                            queue.poll(); // remove the message from the queue
                        } catch (IOException ioe) {
                            System.err.println("[Info]: still can't log: " + ioe);
                            break;
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        startThread();

        BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String theLine = userIn.readLine();
            if (theLine == null)
                break;
            log(theLine);
        }
    }
}
