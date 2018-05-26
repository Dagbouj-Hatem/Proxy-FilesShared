package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileServerInterface extends Remote {

	// download file method  
	public byte[] download(String filename)throws RemoteException; 
}
