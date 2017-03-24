package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Auteur;
import models.Matiere;
import models.Technique;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kaplone on 24/03/17.
 */
public class AdaptationJson {

    public JsonNode adaptationVersOeuvre(JsonNode jsonOrigine, MongoAccess access){

        String auteurStr = jsonOrigine.get("auteur").asText();

        Auteur auteurObj = access.request("auteur", "nom", auteurStr).as(Auteur.class);

        if (auteurObj == null){
            auteurObj = new Auteur();
            auteurObj.setNom(auteurStr);
            auteurObj.setToken(access.getToken());
            auteurObj = auteurObj.save();
        }

        ((ObjectNode) jsonOrigine).put("auteur", auteurObj.get_id());

        String listeMatieresStr = jsonOrigine.get("matieres").asText();
        if (listeMatieresStr != null){

            List<String> listeMatieres = Arrays.asList(listeMatieresStr.split(","));

            ObjectNode on = JsonNodeFactory.instance.objectNode();

            listeMatieres.forEach(a -> {
                String b = a.trim();
                Matiere matiereObj = access.request("matiere", "nom", b).as(Matiere.class);

                if (matiereObj == null){
                    matiereObj = new Matiere();
                    matiereObj.setNom(b);
                    matiereObj.setToken(access.getToken());
                    matiereObj = matiereObj.save();
                }

                on.put(b, matiereObj.get_id());
            });

            ((ObjectNode) jsonOrigine).set("matieresUtilisees_id", on);
        }

        String listeTechniquesStr = jsonOrigine.get("techniques").asText();
        if (listeTechniquesStr != null){

            List<String> listeTechniques = Arrays.asList(listeTechniquesStr.split(","));

            ObjectNode on = JsonNodeFactory.instance.objectNode();

            listeTechniques.forEach(a -> {
                String b = a.trim();
                Technique techniqueObj = access.request("technique", "nom", b).as(Technique.class);

                if (techniqueObj == null){
                    techniqueObj = new Technique();
                    techniqueObj.setNom(b);
                    techniqueObj.setToken(access.getToken());
                    techniqueObj = techniqueObj.save();
                }

                on.put(b, techniqueObj.get_id());
            });

            ((ObjectNode) jsonOrigine).set("techniquesUtilisees_id", on);

        }

        return jsonOrigine;
    }

    public JsonNode adaptationVersOeuvreTraitee(JsonNode jsonOrigine, String oeuvre_id, MongoAccess access){

        ((ObjectNode) jsonOrigine).put("oeuvre_id", oeuvre_id);

        return jsonOrigine;

    }
}
