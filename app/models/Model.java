package models;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.annotation.JsonIgnore;
import utils.Connexion;

public class Model extends Commun{
	
    private String cheminVersModelSTR;
    
    public void update(){

		Connexion.getConnetion(getToken()).update("model", this);
	}

    public Model save(){

		return (Model) Connexion.getConnetion(getToken()).save("model", this);

	}

	@JsonIgnore
	public Path getCheminVersModel() {
		return Paths.get(cheminVersModelSTR);
	}

	public String getCheminVersModelSTR() {
		return cheminVersModelSTR;
	}

	public void setCheminVersModelSTR(String cheminVersModelSTR) {
		this.cheminVersModelSTR = cheminVersModelSTR;
	}

}
