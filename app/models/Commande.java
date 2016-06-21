package models;

import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.format.DateTimeFormatter;

import java.util.Map;
import java.util.stream.Collectors;

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
	private Model modele;
	private String modele_id;

	private String auteur_id;

	private Map<String, String> oeuvresTraitees_id;

	private Map<String, String> traitements_attendus_id;
	
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

//	public LocalDate getDateCommande() {
//		Instant instant = Instant.ofEpochMilli(dateCommande.getTime());
//		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
//		return res;
//	}
//
//	public void setDateCommande(LocalDate dateCommande) {
//		Instant instant = dateCommande.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
//		Date res = Date.from(instant);
//		this.dateCommande = res;
//	}
//
//	public LocalDate getDateDebutProjet() {
//		Instant instant = Instant.ofEpochMilli(dateDebutProjet.getTime());
//		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
//		return res;
//	}
//
//	public void setDateDebutProjet(LocalDate dateDebutProjet) {
//		Instant instant = dateDebutProjet.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
//		Date res = Date.from(instant);
//		this.dateDebutProjet = res;
//	}
//
//	public LocalDate getDateFinProjet() {
//		Instant instant = Instant.ofEpochMilli(dateFinProjet.getTime());
//		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
//		return res;
//	}
//
//	public void setDateFinProjet(LocalDate dateFinProjet) {
//		Instant instant = dateFinProjet.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
//		Date res = Date.from(instant);
//		this.dateFinProjet = res;
//	}

	public Map<String, String> getTraitements_attendus_id() {
		return traitements_attendus_id;
	}

	public void setTraitements_attendus_id(Map<String, String> traitements_attendus_id) {
		this.traitements_attendus_id = traitements_attendus_id;
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

	public void setDateCommande(LocalDate dateCommande) {
		this.dateCommande = dateCommande.format(DateTimeFormatter.ISO_INSTANT);
	}

	public void setDateDebutProjet(LocalDate dateDebutProjet) {
		this.dateDebutProjet = dateDebutProjet.format(DateTimeFormatter.ISO_INSTANT);
	}

	public void setDateFinProjet(LocalDate dateFinProjet) {
		this.dateFinProjet = dateFinProjet.format(DateTimeFormatter.ISO_INSTANT);
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
		
		Path base = modele.getCheminVersModel().getParent();
		String name = modele.getCheminVersModel().getFileName().toString();
		
		String nameVertical = name.split("\\.")[0] + "_vertical." + name.split("\\.")[1];
		
		
		return base.resolve(nameVertical);
	}

	public void setModele(Model modele) {

		this.modele = modele;
		this.modele_id = modele.get_id().toString();
	}

	public void setAuteur(Auteur auteur) {
		this.auteur_id = auteur.get_id().toString();
	}

	public String getModele_id() {
		return modele_id;
	}

	public void setModele_id(ObjectId modele_id) {
		this.modele_id = modele_id.toString();
		this.modele = MongoAccess.request("modele", this.modele_id).as(Model.class);
	}

	public String getAuteur_id() {
		return auteur_id;
	}

	public void setAuteur_id(ObjectId auteur_id) {
		this.auteur_id = auteur_id.toString();
	}


	public Map<String, String> getOeuvresTraitees_id() {
		return oeuvresTraitees_id;
	}

	public void setOeuvresTraitees_id(Map<String, String> oeuvresTraitees_id) {
		this.oeuvresTraitees_id = oeuvresTraitees_id;
	}
//
//
//    public List<String> getOeuvresTraitees_string() {
//        return oeuvresTraitees_id.keySet()
//                                 .stream()
//                                 .sorted()
//                                 .collect(Collectors.toList());
//    }
}
