package models;

import org.bson.types.ObjectId;

import java.util.Date;

public class Commun {

    public Commun(){
    }

	private String _id;

	private Date created_at;
	private Date updated_at;
	private Date deleted_at;
	
	private String nom;
	private String remarques;

    public String get_id() {
        return _id.toString();
    }

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Date getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(Date deleted_at) {
		this.deleted_at = deleted_at;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRemarques() {
		return remarques;
	}

	public void setRemarques(String remarques) {
		this.remarques = remarques;
	}
	
	public String toString(){
		return this.nom;
	}

}
