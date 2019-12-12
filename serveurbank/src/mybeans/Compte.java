package mybeans;

import java.io.Serializable;

public class Compte implements Serializable {
	
	private int numcompte;
	private String libcompte;
	private String sens;
	private double solde;
	private int numcli;
	
	

	public int getNumcompte() {
		return numcompte;
	}
	public void setNumcompte(int numcompte) {
		this.numcompte = numcompte;
	}
	public String getLibcompte() {
		return libcompte;
	}
	public void setLibcompte(String libcompte) {
		this.libcompte = libcompte;
	}
	public String getSens() {
		return sens;
	}
	public void setSens(String sens) {
		this.sens = sens;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public int getNumcli() {
		return numcli;
	}
	public void setNumcli(int numcli) {
		this.numcli = numcli;
	}
	
	
	

}
