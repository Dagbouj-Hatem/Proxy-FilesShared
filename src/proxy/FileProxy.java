package proxy;

import java.io.FileOutputStream;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.FileServerInterface;

public class FileProxy extends UnicastRemoteObject implements FileProxyIn {
	// serveur 
	public FileServerInterface serveur; 
	// constructor 
	public FileProxy() throws RemoteException 
	{ 
		super(); 
		try{  
			
			//  connecter au serveur			
			this.serveur= (FileServerInterface) Naming.lookup("rmi://127.0.0.1/fs");
			System.out.println("Connecter au serveur ...");  
            
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	// download method 
	@Override
	public byte[] download(String filename) throws RemoteException {
		return serveur.download(filename);
	}
	

}
