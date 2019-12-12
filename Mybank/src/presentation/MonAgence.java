package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mybeans.Agence;

public class MonAgence  extends JFrame implements ActionListener{
	private JLabel nomagence,logo;
	private JTextField chnomagence;
	private JButton aj,qt,aff,cpt,cli,op;
	private JPanel pan1,pan2;
	private Socket socket;
	DataOutputStream out;
	DataInputStream in;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    
    public MonAgence(){
    	
    	nomagence=new JLabel("Agence:");
    	logo= new JLabel("");
    	logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyaissata\\Desktop\\photos\\banque.jpg"));
    	
		chnomagence= new JTextField(15);
		
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aff= new JButton("Afficher");
		cpt = new JButton("Comptes");
		cli = new JButton("Clients");
		op = new JButton("operation");
		
		
		aj.addActionListener(this);
		aff.addActionListener(this);
		qt.addActionListener(this);
		cpt.addActionListener(this);
		cli.addActionListener(this);
		op.addActionListener(this);
		
		
		pan1=new JPanel();
		pan2=new JPanel();
		//pan1.setLayout(new GridLayout(4,2));
		
		pan1.add(logo);
		pan1.add(nomagence);
		pan1.add(chnomagence);
		
	
		pan2.add(aj);
		pan2.add(aff);
		pan2.add(qt);
		pan2.add(cpt);
		pan2.add(cli);
		pan2.add(op);
		
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("Enregistrer une agence");
		setSize(650,400);
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
			
			Agence a;
			 try
			 {
			 

		    	    oos.writeObject("ajoutAgence");
		        	oos.flush();
		        	String snomag=chnomagence.getText();
		    	  
		    	    a = new Agence();
		    		a.setNomagence(snomag);
		    		oos.writeObject(a);
		    		oos.flush();
		    		chnomagence.setText("");
		    		
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
					 oos.writeObject("listerAgence");	
			         oos.flush();
			         ArrayList<Agence> liste=(ArrayList<Agence>)ois.readObject();
			         new ListeAgences(liste);
			        
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
				else if(e.getSource()==cpt){
					new MonCompte().setVisible(true);
					
				}
				else if(e.getSource()==cli){
					new MonClient().setVisible(true);
				}
				else if(e.getSource()==op){
					new MonOperation().setVisible(true);
				}
				
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MonAgence();

	}

	

}
