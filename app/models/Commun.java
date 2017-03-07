package models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.bson.types.ObjectId;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Commun {

	@MongoId// auto
	@MongoObjectId
	private String _id;

	private String created_at;
	private String updated_at;
	private String deleted_at;

	@JsonIgnore
	private String token;
	
	private String nom;
	private String remarques;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id){
        this._id = _id;
    }

	@JsonIgnore
	public ObjectId get_id_obj() {
		return new ObjectId(_id);
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getDeleted_at() {
		return deleted_at;
	}

	public void setDeleted_at(String deleted_at) {
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

	@JsonIgnore
	public String toString(){
		return this.nom;
	}

	@JsonIgnore
	public String getToken() {
		return token;
	}

	@JsonIgnore
	public void setToken(String token) {
		this.token = token;
	}

	public abstract Commun save();
	public abstract void update();
}
