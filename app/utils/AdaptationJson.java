package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Auteur;
import models.Matiere;
import models.TacheTraitement;
import models.Technique;

import java.util.*;


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

        String listeMatieresStr = jsonOrigine.get("matieres") != null ? jsonOrigine.get("matieres").asText() : "";
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

        String listeTechniquesStr = jsonOrigine.get("techniques") != null ? jsonOrigine.get("techniques").asText() : "";
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

    public JsonNode adaptationVersOeuvreTraitee(JsonNode jsonOrigine, String oeuvre_id, String coteOeuvre, String nomOeuvre, MongoAccess access){

        ((ObjectNode) jsonOrigine).put("oeuvre_id", oeuvre_id);
        ((ObjectNode) jsonOrigine).put("cote", coteOeuvre);
        ((ObjectNode) jsonOrigine).put("nom", nomOeuvre);

        ArrayNode alterations = JsonNodeFactory.instance.arrayNode();
        ArrayNode listeTraitementsAttendus = JsonNodeFactory.instance.arrayNode();
        ArrayNode produits = JsonNodeFactory.instance.arrayNode();

        String alterationsStr = jsonOrigine.get("alterations depot") != null ? jsonOrigine.get("alterations depot").asText() : "";
        List<String> alterations_raw = Arrays.asList(alterationsStr.split(","));
        alterations_raw.forEach(a -> {
            String b = a.trim();
            alterations.add(b);
        });

        alterationsStr = jsonOrigine.get("alterations physiques") != null ? jsonOrigine.get("alterations physiques").asText() : "";
        alterations_raw = Arrays.asList(alterationsStr.split(","));
        alterations_raw.forEach(a -> {
            String b = a.trim();
            alterations.add(b);
        });

        alterationsStr = jsonOrigine.get("alterations chimiques") != null ? jsonOrigine.get("alterations chimiques").asText() : "";
        alterations_raw = Arrays.asList(alterationsStr.split(","));
        alterations_raw.forEach(a -> {
            String b = a.trim();
            alterations.add(b);
        });

        alterationsStr = jsonOrigine.get("alterations techniques") != null ? jsonOrigine.get("alterations techniques").asText() : "";
        alterations_raw = Arrays.asList(alterationsStr.split(","));
        alterations_raw.forEach(a -> {
            String b = a.trim();
            alterations.add(b);
        });

        ((ObjectNode) jsonOrigine).set("alterations_string", alterations);


        // traitement suppression elements // traitement consolidation // traitement depoussierage //
        String traitementsStr = jsonOrigine.get("traitement depoussierage") != null ? jsonOrigine.get("traitement depoussierage").asText() : "";
        List<String> traitements_raw = Arrays.asList(traitementsStr.split(","));
        traitements_raw.forEach(a -> {
            String b = a.trim();

            ObjectNode traitementAttendu_node = JsonNodeFactory.instance.objectNode();

            traitementAttendu_node.put("traitementAttendu_string", b);

            TacheTraitement tacheTraitementObj = new TacheTraitement("b", access);
            tacheTraitementObj.setToken(access.getToken());
            tacheTraitementObj = tacheTraitementObj.save();

            traitementAttendu_node.put("traitementAttendu_id", tacheTraitementObj.get_id());

            listeTraitementsAttendus.add(traitementAttendu_node);
        });

        traitementsStr = jsonOrigine.get("traitement suppression elements") != null ? jsonOrigine.get("traitement suppression elements").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split(","));
        traitements_raw.forEach(a -> {
            String b = a.trim();

            ObjectNode traitementAttendu_node = JsonNodeFactory.instance.objectNode();

            traitementAttendu_node.put("traitementAttendu_string", b);

            TacheTraitement tacheTraitementObj = new TacheTraitement("b", access);
            tacheTraitementObj.setToken(access.getToken());
            tacheTraitementObj = tacheTraitementObj.save();

            traitementAttendu_node.put("traitementAttendu_id", tacheTraitementObj.get_id());

            listeTraitementsAttendus.add(traitementAttendu_node);
        });

        traitementsStr = jsonOrigine.get("traitements aqueux") != null ? jsonOrigine.get("traitements aqueux").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split(","));
        traitements_raw.forEach(a -> {
            String b = a.trim();

            ObjectNode traitementAttendu_node = JsonNodeFactory.instance.objectNode();

            traitementAttendu_node.put("traitementAttendu_string", b);

            TacheTraitement tacheTraitementObj = new TacheTraitement("b", access);
            tacheTraitementObj.setToken(access.getToken());
            tacheTraitementObj = tacheTraitementObj.save();

            traitementAttendu_node.put("traitementAttendu_id", tacheTraitementObj.get_id());

            listeTraitementsAttendus.add(traitementAttendu_node);
        });

        traitementsStr = jsonOrigine.get("traitement consolidation") != null ? jsonOrigine.get("traitement consolidation").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split(","));
        traitements_raw.forEach(a -> {
            String b = a.trim();

            ObjectNode traitementAttendu_node = JsonNodeFactory.instance.objectNode();

            traitementAttendu_node.put("traitementAttendu_string", b);

            TacheTraitement tacheTraitementObj = new TacheTraitement("b", access);
            tacheTraitementObj.setToken(access.getToken());
            tacheTraitementObj = tacheTraitementObj.save();

            traitementAttendu_node.put("traitementAttendu_id", tacheTraitementObj.get_id());

            listeTraitementsAttendus.add(traitementAttendu_node);
        });

        traitementsStr = jsonOrigine.get("traitement mise a plat") != null ? jsonOrigine.get("traitement mise a plat").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split(","));
        traitements_raw.forEach(a -> {
            String b = a.trim();

            ObjectNode traitementAttendu_node = JsonNodeFactory.instance.objectNode();

            traitementAttendu_node.put("traitementAttendu_string", b);

            TacheTraitement tacheTraitementObj = new TacheTraitement("b", access);
            tacheTraitementObj.setToken(access.getToken());
            tacheTraitementObj = tacheTraitementObj.save();

            traitementAttendu_node.put("traitementAttendu_id", tacheTraitementObj.get_id());

            listeTraitementsAttendus.add(traitementAttendu_node);
        });

        traitementsStr = jsonOrigine.get("traitement retouche") != null ? jsonOrigine.get("traitement retouche").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split(","));
        traitements_raw.forEach(a -> {
            String b = a.trim();

            ObjectNode traitementAttendu_node = JsonNodeFactory.instance.objectNode();

            traitementAttendu_node.put("traitementAttendu_string", b);

            TacheTraitement tacheTraitementObj = new TacheTraitement("b", access);
            tacheTraitementObj.setToken(access.getToken());
            tacheTraitementObj = tacheTraitementObj.save();

            traitementAttendu_node.put("traitementAttendu_id", tacheTraitementObj.get_id());

            listeTraitementsAttendus.add(traitementAttendu_node);
        });

        ((ObjectNode) jsonOrigine).set("traitementsAttendus", listeTraitementsAttendus);


        String produitsStr = jsonOrigine.get("produits utilisés") != null ? jsonOrigine.get("produits utilisés").asText() : "";
        List<String> produits_raw = Arrays.asList(produitsStr.split(","));
        produits_raw.forEach(a -> {
            String b = a.trim();
            produits.add(b);
        });

        ((ObjectNode) jsonOrigine).set("produits_string", produits);

        return jsonOrigine;

    }
}
