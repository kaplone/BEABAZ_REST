package models;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utils.MongoAccess;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.types.ObjectId;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commande  extends Commun{


	
	private String nom_affichage;
	
	private String nom_complet;
	
	private String complement;
	
	private String remarques;

	private String dateCommande;

	private String dateDebutProjet;

	private String dateFinProjet;

    @JsonIgnore
	private Map<String, Model> modele;

    @JsonIgnore
	private Map<String, Auteur> auteur;

	private List<Map<String, String>> oeuvresTraitees;

	private List<Map<String, Object>> traitements_attendus;

    public Commande(){
        super();
    }
	
	public static void update(Commande c){
		MongoAccess.update("commande", c);
	}
	
    public static void save(Commande c){
		MongoAccess.save("commande", c);
	}
    
    public Commande get(){
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

	public List<Map<String, Object>> getTraitements_attendus_id() {

        return traitements_attendus;
	}

	public void setTraitements_attendus(List<Map<String, Object>> traitements_attendus) {
		this.traitements_attendus = traitements_attendus;
	}

	public String getNom_affichage() {
		return nom_affichage;
	}

	public void setNom_affichage(String nom_affichage) {
		this.nom_affichage = nom_affichage;
	}

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}


	public void setDateDebutProjet(String dateDebutProjet) {
		this.dateDebutProjet = dateDebutProjet;
	}

	public void setDateFinProjet(String dateFinProjet) {
		this.dateFinProjet = dateFinProjet;
	}

	public void setDateCommande(String dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getDateCommande() {

		return dateCommande;
	}

	public String getDateDebutProjet() {
		return dateDebutProjet;
	}

	public String getDateFinProjet() {

		return dateFinProjet;
	}

	@JsonIgnore
	public Path getModeleVertical() {

		Model modelObj = new Model(); // faire une vraie requete sur l'objet
		
		Path base = modelObj.getCheminVersModel().getParent();
		String name = modelObj.getCheminVersModel().getFileName().toString();
		
		String nameVertical = name.split("\\.")[0] + "_vertical." + name.split("\\.")[1];
		
		
		return base.resolve(nameVertical);
	}

	public void setModele(Map<String, Object> modele) {
		String modele_string = modele.get("modele_string").toString();
		System.out.println(modele_string);
		ObjectId modele_id = (ObjectId) modele.get("modele_id");
        System.out.println(modele_id);
		Model modeleObj = MongoAccess.request("modele", modele_id).as(Model.class);
        System.out.println(modeleObj.getCheminVersModel());
		Map<String, Model> map = new HashMap<>();
		map.put(modele_string, modeleObj);
		this.modele = map;
	}


	public void setAuteur(Map<String, Object> auteur) {
		ObjectId auteur_id = (ObjectId) auteur.get("auteur_id");
		String auteur_string = auteur.get("auteur_string").toString();
		Auteur auteurObj = MongoAccess.request("auteur", auteur_id).as(Auteur.class);
		Map<String, Auteur> map = new HashMap<>();
		map.put(auteur_string, auteurObj);
		this.auteur = map;

	}

	public List<Map<String, String>> getOeuvresTraitees() {
		return oeuvresTraitees;
	}

	public void setOeuvresTraitees(List<Map<String, String>> oeuvresTraitees) {
		this.oeuvresTraitees = oeuvresTraitees;
	}
}
