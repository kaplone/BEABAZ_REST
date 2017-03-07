package models;

import utils.Connexion;

public class Matiere extends Commun {
	
	private String nom_complet;
	
	public void update(){

		Connexion.getConnetion(getToken()).update("matiere", this);
	}

    public Matiere save(){

		return (Matiere) Connexion.getConnetion(getToken()).save("matiere", this);

	}

	
	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}

}
