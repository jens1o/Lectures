package rmi;

import java.rmi.Naming;

public class TimeRegistrar {

  /** @param args args[0] has to specify the hostname. */
  public static void main(String[] args) throws Exception {
    String host = args[0];
    TimeServer timeServer = new TimeServer();
    Naming.rebind("rmi://" + host + "/ServerTime", timeServer);
  }
}