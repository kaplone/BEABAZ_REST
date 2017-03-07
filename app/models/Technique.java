package models;

import utils.Connexion;

public class Technique extends Commun{
	
	private String nom_complet;
	
	public void update(){

		Connexion.getConnetion(getToken()).update("technique", this);
	}

    public Technique save(){

		return (Technique) Connexion.getConnetion(getToken()).save("technique", this);
	}


	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}
	
	

}
