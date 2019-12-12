package presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mybeans.Client;

public class MonClient  extends JFrame implements ActionListener{
	private JLabel numcli;
	private JLabel nomcli;
	private JLabel prenomcli;
	private JLabel numagence;
	
	private JTextField chnumcli;
	private JTextField chnomcli;
	private JTextField chprenomcli;
	private JTextField chnumagence;
	
	private JButton aj,qt,aff;
	private JPanel pan1,pan2;
	private Socket socket;
	DataOutputStream out;
	DataInputStream in;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    
    public MonClient(){
    	numcli=new JLabel("Numero client:");
		chnumcli= new JTextField();
		nomcli=new JLabel("Nom client:");
		chnomcli= new JTextField();
		prenomcli=new JLabel("Prenom client:");
		chprenomcli= new JTextField();
		
		numagence=new JLabel("Numero agence:");
		chnumagence= new JTextField();
		
		
		
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aff= new JButton("Afficher");
		aj.addActionListener(this);
		aff.addActionListener(this);
		qt.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan1.setLayout(new GridLayout(4,2));
		
		pan1.add(numcli);
		pan1.add(chnumcli);
		pan1.add(nomcli);
		pan1.add(chnomcli);
		pan1.add(prenomcli);
		pan1.add(chprenomcli);
		pan1.add(numagence);
		pan1.add(chnumagence);
		
	
		pan2.add(aj);
		pan2.add(aff);
		pan2.add(qt);
		
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("Enregistrer un Client");
		setSize(500,200);
		setLocationRelativeTo(null);
		setVisible(true);
		try
		{
		socket= new Socket("localhost",8000);
		out = new DataOutputStream(socket.getOutputStream());
	    in = new DataInputStream(socket.getInputStream());
	    oos=new ObjectOutputStream(socket.getOutputStream());;
	    ois=new ObjectInputStream(socket.getInputStream());
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
    	
    	
    	
    }
    
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==aj)
		{
			
			Client c;
			 try
			 {
			 

		    	    oos.writeObject("ajoutClient");
		        	oos.flush();
		        	String snumcli=chnumcli.getText();
		        	int inumcli = Integer.parseInt(snumcli);
		        	String snomcli =chnomcli.getText();
		        	String sprenomcli = chprenomcli.getText();
		        	String snumagence =chnumagence.getText();
		        	int inumagence = Integer.parseInt(snumagence);
		        	
		    	    c = new Client();
		    	    c.setNumcli(inumcli);
		    	    c.setNomcli(snomcli);
		    	    c.setPrenomcli(sprenomcli);
		    	    c.setNumagence(inumagence);
		    	    
		    
		    		
		    		oos.writeObject(c);
		    		oos.flush();
		    		
		    		
		    }
			 catch(Exception ex)
			 {
				 System.out.println(ex.getMessage());
			 }
		}
		
		else
			
			if (e.getSource()==aff)
			{
				try
				{
					 oos.writeObject("listerClient");	
			         oos.flush();
			         ArrayList<Client> liste=(ArrayList<Client>)ois.readObject();
			         new ListeClients(liste);
			        
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		
			else
				if (e.getSource()==qt)
				{
					try
					{
					 oos.writeObject("fin");
			    	 oos.flush();
			    	 socket.close();
					 dispose();
					System.exit(0);
				    }
					catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
				}	
		
	      }

}
