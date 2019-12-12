package mybeans;

import java.io.Serializable;

public class Operation implements Serializable {
	private int numoper;
	private String liboper;
	private String sensoper;
	private String dateoper;
	private double montant;
	private int numcompte;
	public int getNumoper() {
		return numoper;
	}
	public void setNumoper(int numoper) {
		this.numoper = numoper;
	}
	public String getLiboper() {
		return liboper;
	}
	public void setLiboper(String liboper) {
		this.liboper = liboper;
	}
	public String getSensoper() {
		return sensoper;
	}
	public void setSensoper(String sensoper) {
		this.sensoper = sensoper;
	}
	public String getDateoper() {
		return dateoper;
	}
	public void setDateoper(String dateoper) {
		this.dateoper = dateoper;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public int getNumcompte() {
		return numcompte;
	}
	public void setNumcompte(int numcompte) {
		this.numcompte = numcompte;
	}
	
	
	

}
