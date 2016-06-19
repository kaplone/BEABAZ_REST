package models;

import java.util.ArrayList;
import java.util.Map;

import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import utils.MongoAccess;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends Commun{
    
	
	private String nom_complet;
	private String adresse_rue;
	private String adresse_cp;
	private String adresse_ville;
	private String remarques;
	
	
	private Map<String, ObjectId> commandes_id;

	public static void update(Client c){

		MongoAccess.update("client", c);
	}
	
    public static void save(Client c){
		
		MongoAccess.save("client", c);
		
	}
    
    public Client get(){
		
		return this;
		
	}
    
    public String toString(){
    	
    	return this.getNom();
    }

	public String getRemarques() {
		return remarques;
	}

	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}

	public Map<String, ObjectId> getCommandes_id() {
		return commandes_id;
	}

	public void setCommandes_id(Map<String, ObjectId> commandes) {
		this.commandes_id = commandes;
	}

	public String getAdresse_rue() {
		return adresse_rue;
	}

	public void setAdresse_rue(String adresse_rue) {
		this.adresse_rue = adresse_rue;
	}

	public String getAdresse_cp() {
		return adresse_cp;
	}

	public void setAdresse_cp(String adresse_cp) {
		this.adresse_cp = adresse_cp;
	}

	public String getAdresse_ville() {
		return adresse_ville;
	}

	public void setAdresse_ville(String adresse_ville) {
		this.adresse_ville = adresse_ville;
	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}
	
	
	

}
