
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class TimeServer extends UnicastRemoteObject implements Time {
  
  public TimeServer() throws RemoteException {
    super();
  }

  public Date getTime() {
    return new Date();
  }
}