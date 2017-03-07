package models;

import utils.Connexion;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Produit extends Commun {

	private String nom_complet;

	public Produit(){}
	
	public void update(){
		Connexion.getConnetion(getToken()).update("produit", this);
	}

    public Produit save(){
		return (Produit) Connexion.getConnetion(getToken()).save("produit", this);
	}

    public void insert(Produit t){
		Connexion.getConnetion(getToken()).insert("produit", this);
	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String detail) {
		this.nom_complet = detail;
	}
}
