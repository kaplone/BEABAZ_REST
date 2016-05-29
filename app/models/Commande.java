package models;

import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import javafx.collections.ObservableList;

import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

import utils.MongoAccess;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.*;
import com.fasterxml.jackson.datatype.jsr310.deser.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Commande  extends Commun{
	
	private String nom_affichage;
	
	private String nom_complet;
	
	private String complement;
	
	private String remarques;

	private Date dateCommande;

	private Date dateDebutProjet;

	private Date dateFinProjet;

	@JsonIgnore
	private Model modele;
	private String modele_id;

	@JsonIgnore
	private Auteur auteur;
	private String auteur_id;

	@JsonIgnore
	private List<Traitement> traitements_attendus;
	private List<String> traitements_attendus_id;
	
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

	public LocalDate getDateCommande() {
		Instant instant = Instant.ofEpochMilli(dateCommande.getTime());
		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		return res;
	}

	public void setDateCommande(LocalDate dateCommande) {
		Instant instant = dateCommande.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date res = Date.from(instant);
		this.dateCommande = res;
	}

	public LocalDate getDateDebutProjet() {
		Instant instant = Instant.ofEpochMilli(dateDebutProjet.getTime());
		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		return res;
	}

	public void setDateDebutProjet(LocalDate dateDebutProjet) {
		Instant instant = dateDebutProjet.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date res = Date.from(instant);
		this.dateDebutProjet = res;
	}

	public LocalDate getDateFinProjet() {
		Instant instant = Instant.ofEpochMilli(dateFinProjet.getTime());
		LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		return res;
	}

	public void setDateFinProjet(LocalDate dateFinProjet) {
		Instant instant = dateFinProjet.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date res = Date.from(instant);
		this.dateFinProjet = res;
	}


	public List<Traitement> getTraitements_attendus() {
		return traitements_attendus;
	}

	public void setTraitements_attendus(ArrayList<Traitement> traitements_attendus2) {
		this.traitements_attendus = traitements_attendus2;
	}

	public List<String> getTraitements_attendus_id() {
		return traitements_attendus_id;
	}

	public void setTraitements_attendus_id(List<ObjectId> traitements_attendus_id) {
		this.traitements_attendus_id = traitements_attendus_id.stream()
				                                              .map(a -> a.toString())
				                                              .collect(Collectors.toList());

		this.traitements_attendus = traitements_attendus_id.stream()
				                                           .map(a -> MongoAccess.request("traitement", a).as(Traitement.class).next())
		                                                   .collect(Collectors.toList());
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

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public void setDateDebutProjet(Date dateDebutProjet) {
		this.dateDebutProjet = dateDebutProjet;
	}

	public void setDateFinProjet(Date dateFinProjet) {
		this.dateFinProjet = dateFinProjet;
	}

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
		this.auteur = auteur;
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
		this.auteur = MongoAccess.request("auteur", this.auteur_id).as(Auteur.class);
	}
}
