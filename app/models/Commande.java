package models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utils.Connexion;

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

	private Map<String, String> modele;
	private Map<String, String> auteur;
    private Map<String, Model> modele_obj;
    private Map<String, Auteur> auteur_obj;

	private List<Map<String, String>> oeuvresTraitees;

	private List<Map<String, String>> traitements_attendus;

    public Commande(){
        super();
    }

    @Override
	public void update(){
    	Connexion.getConnetion(getToken()).update("commande", this);
	}
	@Override
    public Commande save(){
		return (Commande) Connexion.getConnetion(getToken()).save("commande", this);
	}
    
    public Commande get(){
		return this;
	}

	public List<Map<String, Traitement>> getTraitements_attendus() {

        List<Map<String, Traitement>> traitements_attendusObj = new ArrayList<>();

        for(Map<String, String> m : traitements_attendus){
            String traitement_attendu_string = m.get("traitement_attendu_string").toString();
            String traitement_attendu_id = m.get("traitement_attendu_id").toString();
            Traitement traitement_attenduObj = Connexion.getConnetion(getToken()).request("traitement", new ObjectId(traitement_attendu_id)).as(Traitement.class);
            traitement_attenduObj.setToken(getToken());
            Map<String, Traitement> map = new HashMap<>();
            map.put(traitement_attendu_string, traitement_attenduObj);
            traitements_attendusObj.add(map);
        }
    	return traitements_attendusObj;
	}

	public void setTraitements_attendus(List<Map<String, String>> traitements_attendus) {
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

	public void setModele(Map<String, String> modele) {
		this.modele = modele;
	}

	public Map<String, Model> getModele_obj() {

        modele_obj = new HashMap<>();
        Model modeleObj = null;

        try{
            String modele_string = modele.get("modele_string").toString();
            if (modele.containsKey("modele_id")){
                String modele_id = modele.get("modele_id").toString();
                modeleObj = Connexion.getConnetion(getToken()).request("model", new ObjectId(modele_id)).as(Model.class);
            }
            else {
                modeleObj = Connexion.getConnetion(getToken()).request("model", "nom", modele_string).as(Model.class);
                String modele_id = modeleObj.get_id();
                modele.put("modele_id", modele_id);
            }


            modeleObj.setToken(getToken());
            modele_obj.put(modele_string, modeleObj);
        }
        catch (NullPointerException npe) {

        }
		return modele_obj;
	}

    public Map<String, String> getModele() {
        return modele;
    }

	public void setAuteur(Map<String, String> auteur) {
		this.auteur = auteur;
	}

	public Map<String, Auteur> getAuteur_obj() {

        auteur_obj = new HashMap<>();
        Auteur auteurObj = null;

        try {
            String auteur_string = auteur.get("auteur_string").toString();
            if(auteur.containsKey("auteur_id")){
                String auteur_id = auteur.get("auteur_id").toString();
                auteurObj = Connexion.getConnetion(getToken()).request("auteur", new ObjectId(auteur_id)).as(Auteur.class);
            }
            else {
                auteurObj = Connexion.getConnetion(getToken()).request("auteur", "nom", auteur_string).as(Auteur.class);
                String auteur_id = auteurObj.get_id();
                auteur.put("auteur_id", auteur_id);
            }

            auteurObj.setToken(getToken());
            auteur_obj.put(auteur_string, auteurObj);

        }
        catch (NullPointerException npe){

        }
    	return auteur_obj;
	}

    public Map<String, String> getAuteur() {
        return auteur;
    }

	public List<Map<String, String>> getOeuvresTraitees() {
		return oeuvresTraitees;
	}

	public void setOeuvresTraitees(List<Map<String, String>> oeuvresTraitees) {
		this.oeuvresTraitees = oeuvresTraitees;
	}
}
