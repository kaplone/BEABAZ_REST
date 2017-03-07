package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Complement extends Commun{

	@Override
	public Complement save() {
		return null;
	}

	@Override
	public void update() {
	}

	private String nom_complet;

	public String getNom_complet() {
		return nom_complet;
	}

	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}

}

