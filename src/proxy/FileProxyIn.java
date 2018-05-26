package proxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileProxyIn extends Remote {

	// download file method  
	public byte[] download(String filename)throws RemoteException; 
}
