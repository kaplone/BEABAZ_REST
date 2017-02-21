package models;

import java.nio.file.Path;
import java.nio.file.Paths;
import utils.Connexion;

public class Model extends Commun{
	
    private String cheminVersModelSTR;
    
//    public static void update(Model c){
//
//		Connexion.getConnetion().update("model", c);
//	}
//
//    public static void save(Model c){
//
//		Connexion.getConnetion().save("model", c);
//
//	}

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
