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

import mybeans.Operation;

public class MonOperation  extends JFrame implements ActionListener{
	private JLabel numoper;
	private JLabel liboper;
	private JLabel sensoper;
	private JLabel dateoper;
	private JLabel montant;
	private JLabel numcompte;
	private JTextField chnumoper;
	private JTextField chliboper;
	private JComboBox chsensoper;
	private JTextField chdateoper;
	private JTextField chmontant;
	private JTextField chnumcompte;
	private JButton aj,qt,aff,cli;
	private JPanel pan1,pan2;
	private Socket socket;
	DataOutputStream out;
	DataInputStream in;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    
    
    public MonOperation(){
    	numoper=new JLabel("Numero:");
		chnumoper= new JTextField();
		
		liboper=new JLabel("Libelle:");
		chliboper= new JTextField();
		
		sensoper=new JLabel("Sens:");
		chsensoper= new JComboBox(new Object[]{"","debiter", "crediter"});
		
		dateoper =new JLabel("Date:");
		chdateoper= new JTextField();
		
		montant =new JLabel("Montant:");
		chmontant= new JTextField();
		
		numcompte=new JLabel("Numcompte:");
		chnumcompte= new JTextField();
		
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
		pan1.setLayout(new GridLayout(10,2));
		
		pan1.add(numoper);
		pan1.add(chnumoper);
		pan1.add(liboper);
		pan1.add(chliboper);
		pan1.add(sensoper);
		pan1.add(chsensoper);
		pan1.add(dateoper);
		pan1.add(chdateoper);
		pan1.add(montant);
		pan1.add(chmontant);
		pan1.add(numcompte);
		pan1.add(chnumcompte);
	
		pan2.add(aj);
		pan2.add(aff);
		pan2.add(qt);
		pan2.add(cli);
		
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("Enregistrer une operation");
		setSize(500,400);
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
			
			Operation op;
			 try
			 {
			 

		    	    oos.writeObject("ajoutOperation");
		        	oos.flush();
		        	String snumoper=chnumoper.getText();
		        	int inumoper = Integer.parseInt(snumoper);
		        	String sliboper=chliboper.getText();
		        	String ssensoper = chsensoper.getSelectedItem().toString();
		        	String smontant = chmontant.getText();
		        	String ddate= chdateoper.getText();
		        	double dmontant = Double.parseDouble(smontant);
		        	String snumcompte= chnumcompte.getText();
		        	int  inumcompte= Integer.parseInt(snumcompte);
		        	
		    	  
		    	    op = new Operation();
		    	    
		    	    op.setNumoper(inumoper);
		    	    op.setLiboper(sliboper);
		    	    op.setSensoper(ssensoper);
		    	    op.setDateoper(ddate);
		    	    op.setMontant(dmontant);
		    	    op.setNumcompte(inumcompte);
		    		
		    		oos.writeObject(op);
		    		oos.flush();
		    		chnumcompte.setText("");
		    		
		    		
		    		
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
					 oos.writeObject("listerOperation");	
			         oos.flush();
			         ArrayList<Operation> liste=(ArrayList<Operation>)ois.readObject();
			         new ListeOperations(liste);
			        
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
