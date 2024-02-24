package rmi;

import java.rmi.Naming;
import java.util.Date;

public class TimeClient {
  public static void main(String[] args) throws Exception {
    String host = args[0];
    Time timeServer = (Time) Naming.lookup("rmi://" + host + "/ServerTime");
    System.out.println("Time on " + host + " is " + timeServer.getTime());
  }
}