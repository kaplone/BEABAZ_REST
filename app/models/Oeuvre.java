package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import utils.Connexion;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import utils.MongoAccess;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Oeuvre extends Commun{
	
	private String key1;
    private String key2;
	
	private String n_d_origine,
	               cote_archives,
	               cote_archives_6s,
	               ville,
	               quartier,
	               titre_de_l_oeuvre,
	               date, 
	               dimensions,
	               _observations,
	               field_25,
	               inscriptions_au_verso,
	               format_de_conditionnement;
	
	private String auteur;
	
	private Map<String, String> matieresUtilisees_id;
	private Map<String, String> techniquesUtilisees_id;

	private List<String> techniquesUtilisees_names;
	private List<String> matieresUtilisees_names;
	
	public Oeuvre(){
		matieresUtilisees_id = new HashMap<>();
		techniquesUtilisees_id = new HashMap<>();
	}
	
	@Override
	public String toString(){
		return this.cote_archives;
	}
	
	@Override
	public String getNom(){

        if(getCote_archives_6s().equals("SN")){
            return String.format("   SN - %s" , titre_de_l_oeuvre);
        }
        else if(getCote_archives_6s().length() < 5 ){
            try {
                return String.format("%04d - %s", Integer.parseInt(getCote_archives_6s() != "" ? getCote_archives_6s() : "0") , titre_de_l_oeuvre);
            }
            catch (NumberFormatException nfe) {
                return String.format("%04d - %s", Integer.parseInt(getCote_archives_6s() != "" ? getCote_archives_6s().split("\\.")[0] : "0") , titre_de_l_oeuvre);
            }
        }
        else {
            return String.format("%s - %s", getCote_archives_6s(), titre_de_l_oeuvre);
        }
	}
	
	public void update(){

		Connexion.getConnetion(getToken()).update("oeuvre", this);
	}

    public Oeuvre save(){

		return (Oeuvre) Connexion.getConnetion(getToken()).save("oeuvre", this);

	}
    
    public void addMatiere(String m, String oid){
    	
        if (! matieresUtilisees_id.keySet().contains(m)){
    		matieresUtilisees_id.put(m, oid);
    	}
    	
    }
    
    public void deleteMatiere(String m){
    	
    	for (String m_ : matieresUtilisees_id.keySet()){
    		if (m.equals(m_)){
    			matieresUtilisees_id.remove(m_);
    			break;
    		}
    	} 	
    }
    
    public void addTechnique(String t, String oid){
    	

    	if (! techniquesUtilisees_id.keySet().contains(t)){
    		techniquesUtilisees_id.put(t, oid);
    	}
    	
    }
    
    public void deleteTechnique(String t){

    	for (String t_ : techniquesUtilisees_id.keySet()){
    		if (t.equals(t_)){
    			techniquesUtilisees_id.remove(t_);
    			break;
    		}
    	} 	
    }

	public String getN_d_origine() {
		return n_d_origine;
	}

	public void setN_d_origine(String n_d_origine) {
		this.n_d_origine = n_d_origine;
	}

	public String getCote_archives_6s() {
		return cote_archives_6s != null ? cote_archives_6s : "0";
	}

	public void setCote_archives_6s(String cote_archives_6s) {
		this.cote_archives_6s = cote_archives_6s;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getTitre_de_l_oeuvre() {
		return titre_de_l_oeuvre;
	}

	public void setTitre_de_l_oeuvre(String titre_de_l_oeuvre) {
		this.titre_de_l_oeuvre = titre_de_l_oeuvre;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public String get_observations() {
		return _observations;
	}

	public void set_observations(String _observations) {
		this._observations = _observations;
	}

	public String getField_25() {
		return field_25;
	}

	public void setField_25(String field_25) {
		this.field_25 = field_25;
	}

	public String getInscriptions_au_verso() {
		return inscriptions_au_verso;
	}

	public void setInscriptions_au_verso(String inscriptions_au_verso) {
		this.inscriptions_au_verso = inscriptions_au_verso;
	}

	public String getFormat_de_conditionnement() {
		return format_de_conditionnement;
	}

	public void setFormat_de_conditionnement(String format_de_conditionnement) {
		this.format_de_conditionnement = format_de_conditionnement;
	}

	public Auteur getAuteur() {
        if (auteur != null){
            return Connexion.getConnetion(getToken()).request("auteur", new ObjectId(auteur)).as(Auteur.class);
        }
		else {
            return null;
        }
	}



	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}


	public Set<String> getMatieresUtilisees_names() {
		return matieresUtilisees_id.keySet();
	}

	public void AddMatiereUtilisee(Matiere matiereUtilisee) {
		this.matieresUtilisees_id.put(matiereUtilisee.getNom(), matiereUtilisee.get_id());
	}

	public Set<String> getTechniquesUtilisees_names() {
		return techniquesUtilisees_id.keySet();
	}

	public void setTechniquesUtilisees_names(List<String> techniquesUtilisees_names) {
    	this.techniquesUtilisees_names = techniquesUtilisees_names;
	}

	public void convertTechniquesUtilisees_name(){
    	techniquesUtilisees_id = new HashMap<>();

		MongoAccess access = Connexion.getConnetion(getToken());

    	if(! techniquesUtilisees_names.isEmpty()){
    		techniquesUtilisees_names.forEach(a ->
    				techniquesUtilisees_id.put(a, access.request("technique", "nom", a)
							.as(Technique.class).get_id()));
		}
	}

	public void setMatieresUtilisees_names(List<String> matieresUtilisees_names) {
		this.matieresUtilisees_names = matieresUtilisees_names;
	}

	public void convertMatieresUtilisees_name(){

		matieresUtilisees_id = new HashMap<>();

		MongoAccess access = Connexion.getConnetion(getToken());

		if(! matieresUtilisees_names.isEmpty()){
			 matieresUtilisees_names.forEach(a ->
				 matieresUtilisees_id.put(a, access.request("matiere", "nom", a)
						.as(Matiere.class).get_id()));
		}
	}

	public void addTechniqueUtilisee(Technique techniqueUtilisee) {
		this.techniquesUtilisees_id.put(techniqueUtilisee.getNom(), techniqueUtilisee.get_id());
	}

	public Map<String, String> getMatieresUtilisees_id() {

		System.out.println("getMatieresUtilisees_id() : " + matieresUtilisees_id);
    	return matieresUtilisees_id;
	}

	public void setMatieresUtilisees_id(Map<String, String> matieresUtilisees_id) {

    	System.out.println("setMatieresUtilisees_id() : " + matieresUtilisees_id);
		this.matieresUtilisees_id = matieresUtilisees_id;
	}

	public Map<String, String> getTechniquesUtilisees_id() {

		System.out.println("getTechniquesUtilisees_id() : " + techniquesUtilisees_id);
		return techniquesUtilisees_id;
	}

	public void setTechniquesUtilisees_id(Map<String, String> techniquesUtilisees_id) {
		System.out.println("setTechniquesUtilisees_id() : " + techniquesUtilisees_id);
		this.techniquesUtilisees_id = techniquesUtilisees_id;
	}
	
	public String getTechniquesUtilisees_noms_complets(){
		
		return getTechniquesUtilisees_id().entrySet()
                                          .stream()
                                          .map(a -> {
                                          	  Technique t_ = Connexion.getConnetion(getToken()).request("technique", new ObjectId(a.getValue()))
                                                               .as(Technique.class);
                                          	  t_.setToken(getToken());
                                          	  return t_.getNom_complet();
                                          })
                                          .collect(Collectors.joining(", "));
	}
	
    public String getMatieresUtilisees_noms_complets(){
		
		return getMatieresUtilisees_id().entrySet()
                                        .stream()
                                        .map(a -> {
                                        	Matiere m_ = Connexion.getConnetion(getToken()).request("matiere", new ObjectId(a.getValue()))
													.as(Matiere.class);
                                        	m_.setToken(getToken());
											return m_.getNom_complet();
										})
                                        .collect(Collectors.joining(", "));
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
	

	
}
