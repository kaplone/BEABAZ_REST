package models;

import utils.Connexion;

public class Technique extends Commun{
	
	private String nom_complet;
	
//	public static void update(Technique t){
//
//		Connexion.getConnetion().update("technique", t);
//	}
//
//    public static void save(Technique t){
//
//		Connexion.getConnetion().save("technique", t);
//
//	}
//
//    public static void insert(Technique t){
//
//		Connexion.getConnetion().insert("technique", t);
//
//	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}
	
	

}
