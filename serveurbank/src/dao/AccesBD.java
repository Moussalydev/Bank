package dao;
import mybeans.*;

import java.sql.*;
import java.util.ArrayList;




public class AccesBD 
{
	private Connection con=null;
    private PreparedStatement st=null;
    private PreparedStatement stat=null;
    private PreparedStatement statement=null;
    private ResultSet rs=null;
   
  
    
   public AccesBD()
   {
	   try
       {
           Class.forName("com.mysql.jdbc.Driver");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/transaction","root","");

       }
       catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }

   }
   
   //ici on ajoute un agence
   public void ajouterAgence(Agence a)
   {
	   try
	   {
		   st= con.prepareStatement("insert into agence(nomagence) values(?)");
           st.setString(1,a.getNomagence());
        
           st.executeUpdate();
            
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }

   }
   //on affiche les agences que l'on a crée
   
   public ArrayList<Agence> afficherAgences()
   {
	   ArrayList <Agence> liste= new ArrayList <Agence>();
	   try
	   {
		   st =con.prepareStatement("select * from agence");
           rs=st.executeQuery();
           while(rs.next())
           {
            Agence a = new Agence();
            a.setNumagence(rs.getInt("numagence"));
            a.setNomagence(rs.getString("nomagence"));

            liste.add(a);
            }
          
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }
	   return liste;
   }
   //On ajoute le compte
   
   public void ajouterCompte(Compte cp)
   {
	   try
	   {
		   st= con.prepareStatement("insert into compte(numcompte,libcompte,sens,solde,numcli) values(?,?,?,?,?)");
           st.setInt(1,cp.getNumcompte());
           st.setString(2, cp.getLibcompte());
           st.setString(3, cp.getSens());
           st.setDouble(4, cp.getSolde());
           st.setInt(5,cp.getNumcli());
           
        
           st.executeUpdate();
            
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }

   }
   
   //On affiche le compte déjà crée
   public ArrayList<Compte> afficherComptes()
   {
	   ArrayList <Compte> liste= new ArrayList <Compte>();
	   try
	   {
		   st =con.prepareStatement("select * from compte");
           rs=st.executeQuery();
           while(rs.next())
           {
            Compte cp = new Compte();
           
     
            cp.setNumcompte(rs.getInt("numcompte"));
            cp.setLibcompte(rs.getString("libcompte"));
            cp.setSens(rs.getString("sens"));
            cp.setSolde(rs.getDouble("solde"));
            cp.setNumcli(rs.getInt("numcli"));
             
            
      
            liste.add(cp);
            }
          
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }
	   return liste;
   }
   //ici on ajoute un client
   public void ajouterClient(Client c)
   {
	  
	   try
	   {
		   st= con.prepareStatement("insert into client(numcli,nomcli,prenomcli,numagence) values(?,?,?,?)");
           st.setInt(1,c.getNumcli());
           st.setString(2,c.getNomcli());
           st.setString(3, c.getPrenomcli());
           st.setInt(4, c.getNumagence());
        
           st.executeUpdate();
            
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }

   }
   //affichage des clients
   public ArrayList<Client> afficherClients()
   {
	   ArrayList <Client> liste= new ArrayList <Client>();
	   try
	   {
		   st =con.prepareStatement("select * from client");
           rs=st.executeQuery();
           while(rs.next())
           {
            Client c = new Client();
           
            
            c.setNumcli(rs.getInt("numcli"));
            c.setNomcli(rs.getString("nomcli"));
            c.setPrenomcli(rs.getString("prenomcli"));
            c.setNumagence(rs.getInt("numagence"));
            
            liste.add(c);
            
            }
          
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }
	   return liste;
   }
   
   //On enrégistre les opérations

   public void ajouterOperation(Operation op)
   {
	   Compte cp = new Compte();   
	   
	   try
	   {
		   
		   st= con.prepareStatement("insert into operation(numoper,liboper,sensoper,dateoper,montant,numcompte) values(?,?,?,?,?,?)");
           st.setInt(1,op.getNumoper());
           st.setString(2,op.getLiboper());
           st.setString(3,op.getSensoper());
           st.setString(4,op.getDateoper());
           st.setDouble(5,op.getMontant());
           st.setInt(6,op.getNumcompte());
           
           //mettre à jour
           if(op.getSensoper().equals("debiter")){                        
               stat = con.prepareStatement("update compte set solde="+"solde+"+op.getMontant()+"where numcompte="+op.getNumcompte());
               stat.executeUpdate();
           }
           
           //fin de la mise à jour
           
     
           st.executeUpdate();
            
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }
	   
	   
	   
	   
   }
   //affichage des operations
   public ArrayList<Operation> afficherOperation()
   {
	   ArrayList <Operation> liste= new ArrayList <Operation>();
	   try
	   {
		   st =con.prepareStatement("select * from operation");
           rs=st.executeQuery();
           while(rs.next())
           {
            Operation op = new Operation();
     
            op.setNumoper(rs.getInt("numoper"));
            op.setLiboper(rs.getString("liboper"));
            op.setDateoper(rs.getString("dateoper"));
            op.setMontant(rs.getDouble("montant"));
            op.setNumcompte(rs.getInt("numcompte"));
            
            liste.add(op);
            }
          
	   }
	   catch(Exception ex)
       {
           System.out.println("!!!!"+ex.getMessage());
       }
	   return liste;
   }
  
   
   
}
