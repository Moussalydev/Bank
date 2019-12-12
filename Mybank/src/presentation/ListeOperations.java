package presentation;

import javax.swing.*;

import mybeans.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class ListeOperations extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Operation> liste;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
	
	public ListeOperations(ArrayList <Operation> liste)
	{
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		 this.liste=liste;
		  sc  = new JScrollPane();
		  table = new JTable();
		  sc.setViewportView(table);
		  DefaultTableModel modele = (DefaultTableModel)table.getModel();
		  modele.addColumn("Numero operation");
		  modele.addColumn("Type");
		  modele.addColumn("Sens");
		  modele.addColumn("date");
		  modele.addColumn("Montant");
		  modele.addColumn("Compte");
		  
		  		
		  int ligne=0;
		  for (Operation op : liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(op.getNumoper()),ligne,0);
			  modele.setValueAt(op.getLiboper(), ligne, 1);
			  modele.setValueAt(op.getSensoper(),ligne,2);
			  modele.setValueAt(op.getDateoper(),ligne, 3);
			  modele.setValueAt(op.getMontant(),ligne, 4);
			  modele.setValueAt(op.getNumcompte(),ligne, 5);
			  ligne++;

		  }
		 
		  setTitle("Tableau des operations");
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
    		new MonOperation();
    	}
    }
	
}
