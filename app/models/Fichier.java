package models;

import java.nio.file.Paths;
import utils.Connexion;

public class Fichier extends Commun{
	
	private String fichierLie;
	
	private String legende;
	
	private String oeuvre_id;
	
	public static void update(Fichier c){

		Connexion.getConnetion().update("fichier", c);
	}
	
    public static void save(Fichier c){
		
		Connexion.getConnetion().save("fichier", c);
		
	}
    
    @Override
    public String toString(){

    	return Paths.get(this.fichierLie).getFileName().toString();
    	
    }

	public String getFichierLie() {
		return fichierLie;
	}

	public void setFichierLie(String fichierLie) {
		this.fichierLie = fichierLie;
	}

	public String getLegende() {
		return legende;
	}

	public void setLegende(String legende) {
		this.legende = legende;
	}
	
	@Override
	public String getNom(){
		return Paths.get(this.fichierLie).getFileName().toString();
	}

	public String getOeuvre_id() {
		return oeuvre_id;
	}

	public void setOeuvre_id(String oeuvre_id) {
		this.oeuvre_id = oeuvre_id;
	}
	

}
