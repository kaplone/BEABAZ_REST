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

}
