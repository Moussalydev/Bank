package presentation;

import javax.swing.*;

import mybeans.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class ListeClients extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Client> liste;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
	
	public ListeClients(ArrayList <Client> liste)
	{
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		 this.liste=liste;
		  sc  = new JScrollPane();
		  table = new JTable();
		  sc.setViewportView(table);
		  DefaultTableModel modele = (DefaultTableModel)table.getModel();
		  modele.addColumn("Numero client");
		  modele.addColumn("Nom client");
		  modele.addColumn("Prenom client");
		  modele.addColumn("Numero agence");
		  
		  
		  		
		  int ligne=0;
		  for (Client c : liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(c.getNumcli()),ligne,0);
			  modele.setValueAt(c.getNomcli(), ligne, 1);
			  modele.setValueAt(c.getPrenomcli(),ligne,2);
			  modele.setValueAt(c.getNumagence(),ligne, 3);
			  ligne++;

		  }
		 
		  setTitle("Tableau des clients");
		  setSize(550,500);
		  qt.addActionListener(this); 
		  panneau1.add(sc);
		  panneau2.add(qt);
		  add(panneau1,BorderLayout.NORTH);
		  add(panneau2,BorderLayout.SOUTH);
		  setLocationRelativeTo(null);
		  setVisible(true);
	}
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource()==qt)
    	{
    		dispose();
    		new MonCompte();
    	}
    }
	
}
