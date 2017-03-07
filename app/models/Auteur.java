package models;

import utils.Connexion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Auteur extends Commun{
	
	private String nom_complet;
	
	public void update(){

		Connexion.getConnetion(getToken()).update("auteur", this);
	}

    public Auteur save(){

		return (Auteur) Connexion.getConnetion(getToken()).save("auteur", this);

	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}
}
