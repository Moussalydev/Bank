package mybeans;

import java.io.Serializable;

public class Client implements Serializable {

  private int numcli;
  private String nomcli;
  private String prenomcli;
  private int numagence;
  
	public int getNumcli() {
		return numcli;
	}
	public void setNumcli(int numcli) {
		this.numcli = numcli;
	}
	public String getNomcli() {
		return nomcli;
	}
	public void setNomcli(String nomcli) {
		this.nomcli = nomcli;
	}
	public String getPrenomcli() {
		return prenomcli;
	}
	public void setPrenomcli(String prenomcli) {
		this.prenomcli = prenomcli;
	}
	public int getNumagence() {
		return numagence;
	}
	public void setNumagence(int numagence) {
		this.numagence = numagence;
	}
  
  
}
