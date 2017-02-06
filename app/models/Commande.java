package models;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.ObjectId;
import utils.MongoAccess;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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
    private Model modeleObj;

	private Map<String, Object> modele;
	private String modele_string;
	private String modele_id;
	private Entry<String,String> modele_map;

    @JsonIgnore
    private Auteur auteurObj;

	private Map<String, Object> auteur;
	private String auteur_string;
	private String auteur_id;
	private Entry<String,String> auteur_map;

	private List<Map<String, String>> oeuvresTraitees;

	private List<Map<String, Object>> traitements_attendus;

    public Commande(){
        super();
        System.out.println("Constructeur vide de Models.Commande");
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
        System.out.println(this.traitements_attendus);

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

	//
	//Caused by: java.lang.IllegalArgumentException: Can not set java.time.LocalDate field models.Commande.dateCommande to java.util.Date
    //

//	public void setDateCommande(LocalDate dateCommande) {
//		this.dateCommande = dateCommande.format(DateTimeFormatter.ISO_INSTANT);
//	}
//
//	public void setDateDebutProjet(LocalDate dateDebutProjet) {
//		this.dateDebutProjet = dateDebutProjet.format(DateTimeFormatter.ISO_INSTANT);
//		System.out.println("this.dateDebutProjet : " + this.dateDebutProjet);
//
//	}
//
//	public void setDateFinProjet(LocalDate dateFinProjet) {
//		this.dateFinProjet = dateFinProjet.format(DateTimeFormatter.ISO_INSTANT);
//	}
//
//	public void setDateCommande(Date dateCommande) {
//		this.dateCommande = dateCommande.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(DateTimeFormatter.ISO_INSTANT);
//		;//.format(DateTimeFormatter.ISO_INSTANT);
//	}

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

		return dateCommande;//.format(DateTimeFormatter.ISO_INSTANT);
	}

	public String getDateDebutProjet() {

		return dateDebutProjet;//.format(DateTimeFormatter.ISO_INSTANT);
	}

	public String getDateFinProjet() {

		return dateFinProjet;//.format(DateTimeFormatter.ISO_INSTANT);
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

		this.modele = modele;
		this.modele_id = modele.get("modele_id").toString();
		this.modele_string = modele.get("modele_string").toString();
        this.modeleObj = MongoAccess.request("modele", this.modele_id).as(Model.class);

        System.out.println(this.modele.toString());
        System.out.println(this.modele_string);
        System.out.println(this.modele_id);
	}

	public void setAuteur(Map<String, Object> auteur) {

		this.auteur = auteur;
		this.auteur_id = auteur.get("auteur_id").toString();
		this.auteur_string = auteur.get("auteur_string").toString();
        this.auteurObj = MongoAccess.request("auteur", this.auteur_id).as(Auteur.class);

        System.out.println(this.auteur.toString());
        System.out.println(this.auteur_string);
        System.out.println(this.auteur_id);

	}

	public String getModele_id() {

        return modele_id;
	}

	public void setModele_id(ObjectId modele_id) {

		this.modele_id = modele_id.toString();
        System.out.println(this.modele_id);
	}

	public String getAuteur_id() {

        return auteur_id;
	}

	public void setAuteur_id(ObjectId auteur_id) {

        this.auteur_id = auteur_id.toString();
	}


	public List<Map<String, String>> getOeuvresTraitees() {
		return oeuvresTraitees;
	}

	public void setOeuvresTraitees(List<Map<String, String>> oeuvresTraitees) {
		this.oeuvresTraitees = oeuvresTraitees;
        System.out.println(this.oeuvresTraitees);
	}
}
