package server;


// imports 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class FileServer extends UnicastRemoteObject implements FileServerInterface {
	
	protected FileServer() throws RemoteException {
		super(); 
		
	}
	
	// envoyé le fichier au proxy 
	@Override
	 public byte[] download(String filename)throws RemoteException
    {
        File f=new File(filename);
        // teste si le fichier existe
        if(!f.exists())
        	return null;
        try
        {
        	// declaration  du  tableau  d'octet 
            byte P[]=new byte[(int)f.length()];
            // read File 
            FileInputStream fin=new FileInputStream(f);
            //  convertir le fichier en tableau d'octets
              fin.read(P);
              // fermeture de fichier 
            fin.close();
            // retourne le tableau d'octets
            return P;

        }catch (FileNotFoundException e)
        { // si le fichier n'existe pas : afffichage d'erreur
            System.out.println(e);
            return null;
        }catch (IOException e)
        { 	// si'il existe un problème INPUT/ OUTPUT
        	System.out.println(e);
            return null;

        }
    }
}
