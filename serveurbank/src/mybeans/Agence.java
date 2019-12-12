package mybeans;

import java.io.Serializable;

public class Agence implements Serializable{
	
	private int numagence;
	
	private String nomagence;
	
	
    // Les getters et setters
	public int getNumagence() {
		return numagence;
	}

	public void setNumagence(int numagence) {
		this.numagence = numagence;
	}

	public String getNomagence() {
		return nomagence;
	}

	public void setNomagence(String nomagence) {
		this.nomagence = nomagence;
	}
	
	
	

}
