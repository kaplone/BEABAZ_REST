package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jongo.marshall.jackson.oid.MongoObjectId;

import utils.MongoAccess;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Traitement extends Commun{

	private String nom_complet;
	
	private Map<String, String> produits;
	
	private Map<String, String> complements;
	
	public Traitement(){
		this.produits = new HashMap<>();
		this.complements = new HashMap<>();
	}
	
	public static void update(Traitement t){

		MongoAccess.update("traitement", t);
	}
	
    public static void save(Traitement t){
		
		MongoAccess.save("traitement", t);
		
	}
    
    public static void insert(Traitement t){
		
		MongoAccess.insert("traitement", t);
		
	}
    
    public void addProduit(Produit p){
    	
    	if (! produits.keySet().contains(p.getNom())){
    		produits.put(p.getNom(), p.get_id().toString());
    	}
    	
    }
    
    public void deleteProduit(Produit p){
    	
    	String produit_ = null;
    	
    	for (String p_ : produits.keySet()){
    		if (p.getNom().equals(p_)){
    			produit_ = p_;
    			break;
    		}
    	}
    	produits.remove(produit_);
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
}
