package models;

import utils.Connexion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Auteur extends Commun{
	
	private String nom_complet;
	
	public static void update(Auteur c){

		Connexion.getConnetion().update("auteur", c);
	}
	
    public static void save(Auteur c){
		
		Connexion.getConnetion().save("auteur", c);
		
	}

}
