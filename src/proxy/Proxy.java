package proxy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import server.FileServer;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;

public class Proxy {

	private JFrame frmProxy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Proxy window = new Proxy();
					window.frmProxy.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Proxy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProxy = new JFrame();
		frmProxy.setResizable(false);
		frmProxy.setTitle("Proxy");
		frmProxy.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\acer\\eclipse-workspace\\Proxy\\images\\icon.png"));
		frmProxy.setBounds(100, 100, 450, 300);
		frmProxy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmProxy.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/* 
				 *  Cette partie : Démarrage du  proxy sur 127.0.0.1:1098 
				 */ 
				try{ 
					
					FileProxy fp=new FileProxy(); 		
					Naming.rebind("rmi://127.0.0.1/fp", fp);
					System.out.println("Le Proxy est démarrer ...");
					
				}catch(Exception e){
					System.out.println("Problème de démarrage dans le serveur ");
					e.printStackTrace();
				}
			}
		});
		label.setIcon(new ImageIcon("C:\\Users\\acer\\eclipse-workspace\\Proxy\\images\\demarrer.png"));
		label.setBounds(49, 61, 320, 117);
		frmProxy.getContentPane().add(label);
	}

}
