package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

public class Server {

	private JFrame frmServeur;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server window = new Server();
					window.frmServeur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Server() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmServeur = new JFrame();
		frmServeur.setResizable(false);
		frmServeur.setTitle("Serveur");
		frmServeur.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\acer\\eclipse-workspace\\Proxy\\images\\icon.png"));
		frmServeur.setBounds(100, 100, 450, 300);
		frmServeur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmServeur.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/* 
				 *  Cette partie : Démarrage du  serveur sur 127.0.0.1:1099 
				 */ 
				try{
					
					java.rmi.registry.LocateRegistry.createRegistry(1099);
					
					FileServer fs=new FileServer(); 		
					Naming.rebind("rmi://127.0.0.1/fs", fs);
					System.out.println("Le Serveur est démarrer ...");
					
				}catch(Exception e){
					System.out.println("Problème de démarrage dans le serveur ");
					e.printStackTrace();
				}
			}
		});
		label.setIcon(new ImageIcon("C:\\Users\\acer\\eclipse-workspace\\Proxy\\images\\demarrer.png"));
		label.setBounds(53, 58, 320, 117);
		frmServeur.getContentPane().add(label);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
