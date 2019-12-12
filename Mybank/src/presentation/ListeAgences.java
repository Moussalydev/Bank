package presentation;

import javax.swing.*;

import mybeans.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class ListeAgences extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Agence> liste;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
	
	public ListeAgences(ArrayList <Agence> liste)
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
		  
		  		
		  int ligne=0;
		  for (Agence ag : liste)
		  {
			  modele.addRow(new Object[0]);
			 
			  modele.setValueAt(String.valueOf(ag.getNumagence()),ligne,0);
			  modele.setValueAt(ag.getNomagence(), ligne, 1);
			 
			  ligne++;
			  
		  }
		 
		  setTitle("Liste des employes");
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
    		new MonAgence();
    	}
    }
	
}
