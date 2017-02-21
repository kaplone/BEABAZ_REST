package models;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.bson.types.ObjectId;
import utils.Connexion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.Progression;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TacheTraitement extends Commun{
	
	private Progression fait_;
	private Date date;
	private String traitement_id;
	private String complement;
	private Map<String, String> produitsLies;
    
    private boolean supp; 
    
    public TacheTraitement(){
    	produitsLies = new HashMap<>();
    }
    
//    public static void update(TacheTraitement c){
//
//		Connexion.getConnetion().update("tacheTraitement", c);
//	}
//
//    public static void save(TacheTraitement c){
//
//		Connexion.getConnetion().save("tacheTraitement", c);
//
//	}
    
    public void addProduit(Produit p){
    	
    	if (! produitsLies.keySet().contains(p.getNom())){
    		produitsLies.put(p.getNom(), p.get_id());
    	}
    	
    }
    
    public void addProduit(String p){
    	
    	Produit p_ = Connexion.getConnetion(getToken()).request("produit", "nom", p).as(Produit.class);
    	p_.setToken(getToken());
    	this.addProduit(p_);
    	
    }
    
    public void addProduit(ObjectId id){
    	Produit p_ = Connexion.getConnetion(getToken()).request("produit", id).as(Produit.class);
    	p_.setToken(getToken());
    	this.addProduit(p_);
    }
    
    public void deleteProduit(Produit p){

    	for (String p_ : produitsLies.keySet()){
    		if (p.getNom().equals(p_)){
    			produitsLies.remove(p_);
    			break;
    		}
    	} 	
    }
    
    public void deleteProduit(String p){

    	for (String p_ : produitsLies.keySet()){
    		if (p_.equals(p)){
    			produitsLies.remove(p_);
    			break;
    		}
    	} 	
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
	}
	
	public Traitement getTraitement(){
    	Traitement t_ = Connexion.getConnetion(getToken()).request("traitement", traitement_id).as(Traitement.class);
    	t_.setToken(getToken());
    	 return t_;
	}
	
	public ImageView getIcone_progression() {
		
		
        Image image = new Image(fait_.getUsedImage());
        
        ImageView usedImage = new ImageView();
        usedImage.setFitHeight(15);
        usedImage.setPreserveRatio(true);
        usedImage.setImage(image);
		
		return usedImage;
	}

	public boolean isSupp() {
		return supp;
	}

	public void setSupp(boolean supp) {
		this.supp = supp;
	}
	
	public String getNom_complet(){
		return this.getTraitement().getNom_complet() + this.getComplement() != null ? " " + this.getComplement() : "";
	}
	
	public Progression getFait_(){
		return fait_;
	}
	public void setFait_(Progression p){
		fait_ = p;
	}

	public Set<String> getProduitsLies_names() {
		return produitsLies.keySet();
	}
	
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
	
	

}
