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

	private String dateCommande;

	private String dateDebutProjet;

	private String dateFinProjet;

    //@JsonIgnore
	private Map<String, Object> modele;

    //@JsonIgnore
	private Map<String, Object> auteur;

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

	public List<Map<String, Object>> getTraitements_attendus() {
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
    	System.out.println("dans setModele() ");
    	this.modele = modele;
	}

	public Map<String, Model> getModele() {
		System.out.println("dans getModele() ");

		String modele_string = modele.get("modele_string").toString();
		System.out.println(modele_string);
		String modele_id = modele.get("modele_id").toString();
		System.out.println(modele_id);
		Model modeleObj = MongoAccess.request("model", new ObjectId(modele_id)).as(Model.class);
		System.out.println(modeleObj.getCheminVersModel());
		Map<String, Model> map = new HashMap<>();
		map.put(modele_string, modeleObj);

		return map;
	}


	public void setAuteur(Map<String, Object> auteur) {
		System.out.println("dans setAuteur() ");
        this.auteur = auteur;
	}

	public Map<String, Auteur> getAuteur() {
		System.out.println("dans getAuteur;() ");

		String auteur_string = auteur.get("auteur_string").toString();
		System.out.println(auteur_string);
		String auteur_id = auteur.get("auteur_id").toString();
		System.out.println(auteur_id);
		Auteur auteurObj = MongoAccess.request("auteur", new ObjectId(auteur_id)).as(Auteur.class);
		System.out.println(auteurObj.getNom());
		Map<String, Auteur> map = new HashMap<>();
		map.put(auteur_string, auteurObj);

    	return map;
	}



	public List<Map<String, String>> getOeuvresTraitees() {
		return oeuvresTraitees;
	}

	public void setOeuvresTraitees(List<Map<String, String>> oeuvresTraitees) {
		this.oeuvresTraitees = oeuvresTraitees;
	}
}
