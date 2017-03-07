package models;

import java.util.HashMap;
import java.util.Map;
import utils.Connexion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Traitement extends Commun{

	private String nom_complet;
	
	private Map<String, String> produits;
	
	private Map<String, String> complements;
	
	public Traitement(){
		this.produits = new HashMap<>();
		this.complements = new HashMap<>();
	}
	
	public void update(){

		Connexion.getConnetion(getToken()).update("traitement", this);
	}

    public Traitement save(){

		return (Traitement) Connexion.getConnetion(getToken()).save("traitement", this);
	}


	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String detail) {
		this.nom_complet = detail;
	}

	public Map<String, String> getProduits() {
		return produits;
	}

	public void setProduits(Map<String, String> complements) {
		this.produits = complements;
	}

	public Map<String, String> getComplements() {
		return complements;
	}

	public void setComplements(Map<String, String> complements) {
		this.complements = complements;
	}
}
