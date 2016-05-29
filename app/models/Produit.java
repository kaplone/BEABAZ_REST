package models;

import utils.MongoAccess;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Produit extends Commun {

	private String nom_complet;

	public Produit(){

	}
	
	public static void update(Produit t){
		MongoAccess.update("produit", t);
	}
	
    public static Produit save(Produit t){
		t = (Produit) MongoAccess.save("produit", t);
		return t;
	}
    
    public static void insert(Produit t){
		MongoAccess.insert("produit", t);
	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String detail) {
		this.nom_complet = detail;
	}
}