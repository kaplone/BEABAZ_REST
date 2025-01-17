package models;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import utils.Connexion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.Progression;
import utils.MongoAccess;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TacheTraitement extends Commun{
	
	private Progression fait_;
	private Date date;
	private String traitement_id;
	private String complement;
	private Map<String, String> produitsLies;
	private String nom_complet;
	private Traitement traitement;
    
    private boolean supp; 
    
    public TacheTraitement(){
    	produitsLies = new HashMap<>();
    }

	public TacheTraitement(String traitementStr, MongoAccess access){
		traitement = access.request("traitement", "nom", traitementStr).as(Traitement.class);
		produitsLies = new HashMap<>();
	}
    
    public void update(){

		Connexion.getConnetion(getToken()).update("tacheTraitement", this);
	}

    public TacheTraitement save(){

		return (TacheTraitement) Connexion.getConnetion(getToken()).save("tacheTraitement", this);

	}

	public String getFait() {
		return fait_.toString();
		
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getComplement() {
		return complement;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getTraitement_id() {
		return traitement_id;
	}

	public void setTraitement_id(String traitement) {
		this.traitement_id = traitement;
		System.out.println("String : " + this.traitement_id);
	}
	public void setTraitement_id(ObjectId id) {
		this.traitement_id = id.toString();
		System.out.println("ObjectId : " + this.traitement_id);
	}
	
//	public Traitement getTraitement(){
//    	Traitement t_ = Connexion.getConnetion(getToken()).request("traitement", traitement_id).as(Traitement.class);
//    	t_.setToken(getToken());
//    	 return t_;
//	}

	public boolean isSupp() {
		return supp;
	}

	public void setSupp(boolean supp) {
		this.supp = supp;
	}
	
	public String getNom_complet(){
		return this.nom_complet;
	}
	public void setNom_complet(String nom_complet){
		this.nom_complet  = nom_complet;

	}
	
	public Progression getFait_(){
		return fait_;
	}
	public void setFait_(Progression p){
		fait_ = p;
	}
	public void setFait_(String p){
		fait_ = Progression.valueOf(p);
	}

	public Set<String> getProduitsLies_names() {
		return produitsLies.keySet();
	}

//	public void setProduitsLies_names(Map<String, String> produitsLies) {
//		this.produitsLies = produitsLies;
//	}
	
	public  Collection<String> getProduitsLies_id() {
		return produitsLies.values();
	}

	public void addProduitLie(Produit produitLie) {
		this.produitsLies.put(produitLie.getNom(), produitLie.get_id());
	}

	public Map<String, String> getProduitsLies() {
		return produitsLies;
	}

	public void setProduitsLies(Map<String, String> produitsLies) {
		this.produitsLies = produitsLies;
	}

	public Traitement getTraitement() {
		return traitement;
	}

	public void setTraitement(Traitement traitement) {
		this.traitement = traitement;
	}
}
