package models;

import utils.Connexion;

public class Matiere extends Commun {
	
	private String nom_complet;
	
	public static void update(Matiere t){

		Connexion.getConnetion().update("matiere", t);
	}
	
    public static void save(Matiere t){
		
		Connexion.getConnetion().save("matiere", t);
		
	}
    
    public static void insert(Matiere t){
		
		Connexion.getConnetion().insert("matiere", t);
		
	}
	
	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}

}
