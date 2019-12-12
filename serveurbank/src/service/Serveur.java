package service;

import java.net.*;
import java.util.*;

import javax.swing.*;
import mybeans.*;
import dao.*;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class Serveur extends JFrame implements ActionListener
{
	 private JTextArea zonerecep;
	 private JButton qt;
	 private JPanel pan1,pan2;

	 public Serveur()
	 {
	    zonerecep=new JTextArea(15,40);
	    qt=new JButton("Quitter");
	    setTitle(" Serveur TCP Multiclients");
	    pan1=new JPanel();
	    pan2=new JPanel();
	    pan1.add(new JScrollPane(zonerecep));
	    qt.addActionListener(this);
	    pan2.add(qt);
	    add(pan1,BorderLayout.CENTER);
	    add(pan2,BorderLayout.SOUTH);
	    setSize(500,600);
	    setLocationRelativeTo(null);
	    setVisible(true);


	     try
	    {
	        ServerSocket serv = new ServerSocket(8000);
	        zonerecep.append("Le Serveur a demarre "+"\n");
	        int numclient=1;
	        while(true)
	        {
	            Socket socket=serv.accept();
	            InetAddress adr = socket.getInetAddress();
	            String ipclient = adr.getHostAddress();
	            String nomclient= adr.getHostName();
	            zonerecep.append("client n°:"+numclient+" adresse ip:"+ipclient+"\n");
	            zonerecep.append("nom machine cliente: "+nomclient+"\n");
	            Service s = new Service(socket);
	            s.start();
	            numclient++;
	        }

	    }
	    catch(IOException ex)
	    {
	        System.out.println(ex.getMessage());
	    }
	 }

	 //class interne
	    class Service extends Thread
	    {
	       private  Socket socket;
	       private  AccesBD bd;
	        public Service(Socket socket)
	        {
	        	bd = new AccesBD();
	            this.socket=socket;
	            	   
	        }
	        public void run()
	        {
	            try
	            {
	       
		           ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		           ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
		           DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		           DataInputStream in = new DataInputStream(socket.getInputStream());
	       	
	           String mode;
	           do
	           {
	        	    
	             mode =(String)ois.readObject();
	             zonerecep.append("mode en cours d\'expoitation!!!: "+mode+"\n");
	             if (mode.equals("ajoutAgence"))
	             {
	                 
	                  Agence a = (Agence) ois.readObject();
                      bd.ajouterAgence(a);
                          
	             }
	             else 
	             if (mode.equals("ajoutCompte"))
	             {
	                 
	                 Compte cp = (Compte) ois.readObject();
                     bd.ajouterCompte(cp);
                         
	             }
	             
	             else
	             {
	                 if (mode.equals("listerAgence"))
	                 {
	                	 
	               	    ArrayList<Agence> liste = new ArrayList<Agence>();
	               	    liste=bd.afficherAgences();
	                    oos.writeObject(liste);
	                    oos.flush();
	                 }
	                 //lister les comptes
	                 else
	                 if (mode.equals("listerCompte"))
	                 {
	                	 

	               	    ArrayList<Compte> liste = new ArrayList<Compte>();
	               	    liste=bd.afficherComptes();
	                    oos.writeObject(liste);
	                    oos.flush();
	                 }
	                 //On ajoute les clients
	                 else 
	    	             if (mode.equals("ajoutClient"))
	    	             {
	    	                 
	    	                 Client c = (Client) ois.readObject();
	                         bd.ajouterClient(c);
	                             
	    	             }
	                    //On affiche les client
	    	          else
		                 if (mode.equals("listerClient"))
		                 {
		                	 
	
		               	    ArrayList<Client> liste = new ArrayList<Client>();
		               	    liste=bd.afficherClients();
		                    oos.writeObject(liste);
		                    oos.flush();
		                 }
		              else
		                 if (mode.equals("listerOperation"))
		                 {
		                	 
	
		               	    ArrayList<Operation> liste = new ArrayList<Operation>();
		               	    liste=bd.afficherOperation();
		                    oos.writeObject(liste);
		                    oos.flush();
		                 }
		                 
	    	           
	          
	                 else
	                 if (mode.equals("fin"))
	                 {
	                      zonerecep.append("Connexion terminee!!! pour un client"+"\n");
	                      oos.flush();
	                     
	                 }
	                 else
		                 if (mode.equals("ajoutOperation"))
	    	             {
		                	 
	    	                 
	    	                 Operation op = (Operation) ois.readObject();
	                         bd.ajouterOperation(op);
	                         
	                             
	    	             }
		             }
	           }
	           
	            while(true);
	           
	            }
	            catch(Exception ex)
	            {
	                System.out.println("****"+ex.getMessage());
	            }
	          
	       }
	      //fin classe interne
	  }
	    
	  public void actionPerformed(ActionEvent e)
	    {
	        dispose();
	        System.exit(0);
	    }

	    public static void main(String args[])
	    {
	        new Serveur();
	    }
	
}
