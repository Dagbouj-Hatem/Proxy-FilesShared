package client;

import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import proxy.FileProxyIn; 

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Naming;

public class Client {

	private JFrame frmClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frmClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClient = new JFrame();
		frmClient.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\acer\\eclipse-workspace\\Proxy\\images\\icon.png"));
		frmClient.setResizable(false);
		frmClient.setTitle("Client");
		frmClient.setBounds(100, 100, 450, 300);
		frmClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClient.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// File shared Name
				String filename="LIghthouse.jpg";
				// file Save Dialog 
				JFileChooser ff =  new JFileChooser();
				ff.showSaveDialog(null);  
					// file extension
					String extension = "";
					int i = filename.lastIndexOf('.');
					if (i > 0) {
					    extension = filename.substring(i);
					} 
				// get file name & path 
				String path=ff.getSelectedFile().getAbsolutePath(); 
				 
				try{ 		
					// connect  to proxy
					FileProxyIn server= (FileProxyIn) Naming.lookup("rmi://127.0.0.1/fp");
					System.out.println("Download ....");  
					byte  P[]= server.download(filename);
		            try
		            {  // creation  du  fichier
		                FileOutputStream fout=new FileOutputStream(path+extension);
		                // ajouter  le contenu
		                fout.write(P);
		                // fgermetture de fichier 
		                fout.close();
		                System.out.println("Download file Size :"+ P.length);

		            }catch(Exception e1)
		            {
		                System.out.println(e1);
		            }
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		label.setIcon(new ImageIcon("C:\\Users\\acer\\eclipse-workspace\\Proxy\\images\\telecharger.png"));
		label.setBounds(37, 69, 362, 117);
		frmClient.getContentPane().add(label);
	}

}
