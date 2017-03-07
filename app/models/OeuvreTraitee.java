package models;

import java.util.*;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;

import enums.EtatFinal;
import enums.Progression;
import utils.Connexion;

public class OeuvreTraitee extends Commun {
	
	private String commande_id;
	
	private String oeuvre_id;
	
	private List<Map<String, String>> traitementsAttendus;

	private EtatFinal etat;
	private String complement_etat;
	
	private List<String> alterations;
	
	private List<Map<String, String>> fichiers;
	
	private Progression progressionOeuvreTraitee;
	
    private String observations;
    
    private String key1;
    private String key2;
    
    private String cote;
    
    public OeuvreTraitee(){
    	
    	traitementsAttendus = new ArrayList<>();
    	alterations = new ArrayList<>();
    	fichiers = new ArrayList<>();
    	
    }

	public void update(){
		Connexion.getConnetion(getToken()).update("oeuvreTraitee", this);
	}

    public OeuvreTraitee save(){
		return (OeuvreTraitee) Connexion.getConnetion(getToken()).save("oeuvreTraitee", this);
	}

	public void setEtat(EtatFinal etat) {
		this.etat = etat;
	}

	public List<Map<String, String>> getFichiers() {

		return fichiers;
	}
	
	public Fichier getFichierAffiche () {

    	String fichier_id = null;
		Fichier fichier = null;

    	for (Map<String, String> file : fichiers){
    		if (file.get("fichier_string").endsWith("_PR_1_JPG")){
				fichier_id = file.get("fichier_id");
			}
		}

		if (fichier_id != null){
			fichier = Connexion.getConnetion(getToken()).request("fichier", new ObjectId(fichier_id)).as(Fichier.class);
			fichier.setToken(getToken());
		}

		return fichier;
		
	}

	public void setFichiers(List<Map<String, String>> fichiers) {
		this.fichiers = fichiers;
	}

	public Progression getProgressionOeuvreTraitee() {
		return progressionOeuvreTraitee;
	}
	
	public void setProgressionOeuvreTraitee(Progression progressionOeuvreTraitee) {
		this.progressionOeuvreTraitee = progressionOeuvreTraitee;
	}
	public Set<String> getTraitementsAttendus_names() {

    	return traitementsAttendus.stream()
				                  .map(a -> a.get("traitementAttendu_string"))
				                  .collect(Collectors.toSet());
	}

	public Set<String> getTraitementsAttendus_id() {

		return traitementsAttendus.stream()
								  .map(a -> a.get("traitementAttendu_id"))
				                  .collect(Collectors.toSet());
	}

	public Set<TacheTraitement> getTraitementsAttendus_obj() {

		return traitementsAttendus.stream()
				.map(a -> retrouveTacheTraitement(a.get("traitementAttendu_id")))
				.collect(Collectors.toSet());
	}

	public TacheTraitement retrouveTacheTraitement(ObjectId id){

		TacheTraitement tt = Connexion.getConnetion(getToken()).request("tacheTraitement", id).as(TacheTraitement.class);
		tt.setToken(getToken());
		return tt;
	}

	public TacheTraitement retrouveTacheTraitement(String id){

		return retrouveTacheTraitement(new ObjectId(id));
	}


	public void addTraitementAttendu(Traitement traitementAttendu) {
		this.addTraitementAttendu(traitementAttendu.getNom(), traitementAttendu.get_id());
	}
	public void addTraitementAttendu(String nom, String id) {

    	Map<String, String> map = new HashMap<>();
    	map.put("traitementAttendu_id", id);
    	map.put("traitementAttendu_string", nom);

    	this.traitementsAttendus.add(map);
	}

	public List<String> getAlterations_string() {
		return alterations;
	}
	public void setAlterations_string(ArrayList<String> alterations) {
		this.alterations = alterations;
	}
	public EtatFinal getEtat() {
		return etat;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	} 

	public String getComplement_etat() {
		return complement_etat;
	}
	public void setComplement_etat(String complement_etat) {
		this.complement_etat = complement_etat;
	}
	
	public Oeuvre getOeuvre(){

    	Oeuvre oeuvre_temp = Connexion.getConnetion(getToken()).request("oeuvre", oeuvre_id).as(Oeuvre.class);
    	oeuvre_temp.setToken(getToken());
		
		return oeuvre_temp;
	}

	public String getOeuvre_id() {
		return oeuvre_id;
	}

	public void setOeuvre_id(String oeuvre_id) {
		this.oeuvre_id = oeuvre_id;
	}

	public void setTraitementsAttendus(List<Map<String, String>> traitementsAttendus) {
		this.traitementsAttendus = traitementsAttendus;
	}

	public void setFichiers_id(List<Map<String, String>> fichiers) {
		this.fichiers = fichiers;
	}

	public String getCommande_id() {
		return commande_id;
	}

	public void setCommande_id(String commande_id) {
		this.commande_id = commande_id;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getCote() {
		return cote;
	}

	public void setCote(String cote) {
		this.cote = cote;
	}
	
	
	
}
