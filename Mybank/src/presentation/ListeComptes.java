package presentation;

import javax.swing.*;

import mybeans.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class ListeComptes extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Compte> liste;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
	
	public ListeComptes(ArrayList <Compte> liste)
	{
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		 this.liste=liste;
		  sc  = new JScrollPane();
		  table = new JTable();
		  sc.setViewportView(table);
		  DefaultTableModel modele = (DefaultTableModel)table.getModel();
		  modele.addColumn("Numero Agence");
		  modele.addColumn("Nom de l'agence");
		  modele.addColumn("Sens solde");
		  modele.addColumn("Solde");
		  modele.addColumn("Numeroclient");
		 
		  
		  		
		  int ligne=0;
		  for (Compte cp : liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(cp.getNumcompte()),ligne,0);
			  modele.setValueAt(cp.getLibcompte(), ligne, 1);
			  modele.setValueAt(cp.getSens(),ligne,2);
			  modele.setValueAt(cp.getSolde(),ligne, 3);
			  modele.setValueAt(cp.getNumcli(),ligne, 4);
			  
			  ligne++;

		  }
		 
		  setTitle("Tableau des comptes");
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
