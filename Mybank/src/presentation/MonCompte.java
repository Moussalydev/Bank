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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mybeans.Compte;

public class MonCompte  extends JFrame implements ActionListener{
	private JLabel numcompte;
	private JLabel libcompte;
	private JLabel sens;
	private JLabel solde;
	private JLabel numcli;
	
	private JTextField chnumcompte;
	private JTextField chlibcompte;
	private JComboBox chsens;
	private JTextField chsolde;
	private JTextField chnumcli;
	private JButton aj,qt,aff,cli;
	private JPanel pan1,pan2;
	private Socket socket;
	DataOutputStream out;
	DataInputStream in;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    
    public MonCompte(){
    	
    	numcompte=new JLabel("Numero:");
		chnumcompte= new JTextField();
		
		libcompte=new JLabel("Libelle:");
		chlibcompte= new JTextField();
		
		sens=new JLabel("Sens:");
		chsens= new JComboBox(new Object[]{"","debit", "credit"});
		
		solde =new JLabel("Solde:");
		chsolde= new JTextField();
		
		numcli=new JLabel("Numclient:");
		chnumcli= new JTextField();
		
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aff= new JButton("Afficher");
		cli= new JButton("Clients");
		
		aj.addActionListener(this);
		aff.addActionListener(this);
		qt.addActionListener(this);
		cli.addActionListener(this);
		
		pan1=new JPanel();
		pan2=new JPanel();
		pan1.setLayout(new GridLayout(6,2));
	   
		pan1.add(numcompte);
		pan1.add(chnumcompte);
		pan1.add(libcompte);
		pan1.add(chlibcompte);
		pan1.add(sens);
		pan1.add(chsens);
		pan1.add(solde);
		pan1.add(chsolde);
		pan1.add(numcli);
		pan1.add(chnumcli);
	
		pan2.add(aj);
		pan2.add(aff);
		pan2.add(qt);
		pan2.add(cli);
		
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("Enregistrer un Compte");
		setSize(700,300);
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
			
			Compte cp;
			 try
			 {
			 

		    	    oos.writeObject("ajoutCompte");
		        	oos.flush();
		        	String snumcompte=chnumcompte.getText();
		        	int inumcompte = Integer.parseInt(snumcompte);
		        	String slibcompte=chlibcompte.getText();
		        	String ssens = chsens.getSelectedItem().toString();
		        	
		        	String ssolde = chsolde.getText();
		        	double dsolde = Double.parseDouble(ssolde);
		        	String snumcli = chnumcli.getText();
		        	int inumcli = Integer.parseInt(snumcli);
		    	  
		    	    cp = new Compte();
		    	    
		    	    cp.setNumcompte(inumcompte);
		    	    cp.setLibcompte(slibcompte);
		    	    cp.setSens(ssens);
		    	    cp.setSolde(dsolde);
		    	    cp.setNumcli(inumcli);
		    		
		    		oos.writeObject(cp);
		    		oos.flush();
		    		chnumcompte.setText("");
		    		chlibcompte.setText("");
		   
		    		chsolde.setText("");
		    		chnumcli.setText("");
		    		
		    		
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
					 oos.writeObject("listerCompte");	
			         oos.flush();
			         ArrayList<Compte> liste=(ArrayList<Compte>)ois.readObject();
			         new ListeComptes(liste);
			        
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
			else if(e.getSource()==cli){
				new MonClient().setVisible(true);
				
			}
		
	}

	

}
