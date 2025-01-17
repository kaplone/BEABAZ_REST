package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.*;

import java.util.*;


/**
 * Created by kaplone on 24/03/17.
 */
public class AdaptationJson {

    public JsonNode adaptationVersOeuvre(JsonNode jsonOrigine, MongoAccess access){

        if (jsonOrigine.get("auteur") != null){

            String auteurStr = jsonOrigine.get("auteur").asText();

            Auteur auteurObj = access.request("auteur", "nom", auteurStr).as(Auteur.class);

            if (auteurObj == null){
                auteurObj = new Auteur();
                auteurObj.setNom(auteurStr);
                auteurObj.setCreated_at(new Date().toString());
                auteurObj.setToken(access.getToken());
                auteurObj = auteurObj.save();
            }

            ((ObjectNode) jsonOrigine).put("auteur", auteurObj.get_id());
        }


        String listeMatieresStr = jsonOrigine.get("matieres") != null ? jsonOrigine.get("matieres").asText() : "";
        if (listeMatieresStr != null){

            List<String> listeMatieres = Arrays.asList(listeMatieresStr.split(","));
            ObjectNode on = JsonNodeFactory.instance.objectNode();

            if (! listeMatieres.isEmpty()){

                listeMatieres.forEach(a -> {
                    String b = a.trim();

                    if (! b.equals("")){
                        Matiere matiereObj = access.request("matiere", "nom", b).as(Matiere.class);

                        if (matiereObj == null){
                            matiereObj = new Matiere();
                            matiereObj.setNom(b);
                            matiereObj.setCreated_at(new Date().toString());
                            matiereObj.setToken(access.getToken());
                            matiereObj = matiereObj.save();
                        }

                        on.put(b, matiereObj.get_id());
                    }
                });

            }

            ((ObjectNode) jsonOrigine).set("matieresUtilisees_id", on);
        }

        String listeTechniquesStr = jsonOrigine.get("techniques") != null ? jsonOrigine.get("techniques").asText() : "";
        if (listeTechniquesStr != null){

            List<String> listeTechniques = Arrays.asList(listeTechniquesStr.split(","));
            ObjectNode on = JsonNodeFactory.instance.objectNode();

            if (! listeTechniques.isEmpty()) {

                listeTechniques.forEach(a -> {
                    String b = a.trim();

                    if (! b.equals("")){
                        Technique techniqueObj = access.request("technique", "nom", b).as(Technique.class);

                        if (techniqueObj == null){
                            techniqueObj = new Technique();
                            techniqueObj.setNom(b);
                            techniqueObj.setCreated_at(new Date().toString());
                            techniqueObj.setToken(access.getToken());
                            techniqueObj = techniqueObj.save();
                        }

                        on.put(b, techniqueObj.get_id());
                    }
                });
            }

            ((ObjectNode) jsonOrigine).set("techniquesUtilisees_id", on);

        }

        return jsonOrigine;
    }

    public JsonNode adaptationVersOeuvreTraitee(JsonNode jsonOrigine, Oeuvre o, MongoAccess access){

        ((ObjectNode) jsonOrigine).put("oeuvre_id", o.get_id());
        ((ObjectNode) jsonOrigine).put("cote", o.getCote_archives_6s());
        ((ObjectNode) jsonOrigine).put("key1", o.getTitre_de_l_oeuvre());
        ((ObjectNode) jsonOrigine).put("key2", o.getDimensions());
        ((ObjectNode) jsonOrigine).put("nom", o.getNom());

        ((ObjectNode) jsonOrigine).put("etat", "BON");
        ((ObjectNode) jsonOrigine).put("progressionOeuvreTraitee", "TODO_");

        ArrayNode alterations_string = JsonNodeFactory.instance.arrayNode();
        ArrayNode listeTraitementsAttendus = JsonNodeFactory.instance.arrayNode();
        ArrayNode produits = JsonNodeFactory.instance.arrayNode();

        String alterationsStr = jsonOrigine.get("alterations depot") != null ? jsonOrigine.get("alterations depot").asText() : "";
        List<String> alterations_raw = Arrays.asList(alterationsStr.split("\\. "));
        if (! alterations_raw.isEmpty()){
            alterations_raw.forEach(a -> {
                String b = a.trim();
                if (! b.equals("")) {
                    alterations_string.add(b);
                }
            });
        }

        alterationsStr = jsonOrigine.get("alterations physiques") != null ? jsonOrigine.get("alterations physiques").asText() : "";
        alterations_raw = Arrays.asList(alterationsStr.split("\\. "));
        if (! alterations_raw.isEmpty()){
            alterations_raw.forEach(a -> {
                String b = a.trim();
                if (! b.equals("")) {
                    alterations_string.add(b);
                }
            });
        }

        alterationsStr = jsonOrigine.get("alterations chimiques") != null ? jsonOrigine.get("alterations chimiques").asText() : "";
        alterations_raw = Arrays.asList(alterationsStr.split("\\. "));
        if (! alterations_raw.isEmpty()){
            alterations_raw.forEach(a -> {
                String b = a.trim();
                if (! b.equals("")) {
                    alterations_string.add(b);
                }
            });
        }


        alterationsStr = jsonOrigine.get("alterations techniques") != null ? jsonOrigine.get("alterations techniques").asText() : "";
        alterations_raw = Arrays.asList(alterationsStr.split("\\. "));
        if (! alterations_raw.isEmpty()){
            alterations_raw.forEach(a -> {
                String b = a.trim();
                if (! b.equals("")) {
                    alterations_string.add(b);
                }
            });
        }

        ((ObjectNode) jsonOrigine).set("alterations_string", alterations_string);


        // traitement suppression elements // traitement consolidation // traitement depoussierage //
        String traitementsStr = jsonOrigine.get("traitement depoussierage") != null ? jsonOrigine.get("traitement depoussierage").asText() : "";
        List<String> traitements_raw = Arrays.asList(traitementsStr.split("\\. "));
        if (! traitements_raw.isEmpty()){
            listeTraitementsAttendus = ajouterTraitement(traitements_raw, access, listeTraitementsAttendus);
        }

        traitementsStr = jsonOrigine.get("traitement suppression elements") != null ? jsonOrigine.get("traitement suppression elements").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split("\\. "));
        if (! traitements_raw.isEmpty()){
            listeTraitementsAttendus = ajouterTraitement(traitements_raw, access, listeTraitementsAttendus);
        }

        traitementsStr = jsonOrigine.get("traitements aqueux") != null ? jsonOrigine.get("traitements aqueux").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split("\\. "));
        if (! traitements_raw.isEmpty()){
            listeTraitementsAttendus = ajouterTraitement(traitements_raw, access, listeTraitementsAttendus);
        }

        traitementsStr = jsonOrigine.get("traitement consolidation") != null ? jsonOrigine.get("traitement consolidation").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split("\\. "));
        if (! traitements_raw.isEmpty()){
            listeTraitementsAttendus = ajouterTraitement(traitements_raw, access, listeTraitementsAttendus);
        }

        traitementsStr = jsonOrigine.get("traitement mise a plat") != null ? jsonOrigine.get("traitement mise a plat").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split("\\. "));
        if (! traitements_raw.isEmpty()){
            listeTraitementsAttendus = ajouterTraitement(traitements_raw, access, listeTraitementsAttendus);
        }

        traitementsStr = jsonOrigine.get("traitement retouche") != null ? jsonOrigine.get("traitement retouche").asText() : "";
        traitements_raw = Arrays.asList(traitementsStr.split("\\. "));
        if (! traitements_raw.isEmpty()){
            listeTraitementsAttendus = ajouterTraitement(traitements_raw, access, listeTraitementsAttendus);
        }

        ((ObjectNode) jsonOrigine).set("traitementsAttendus", listeTraitementsAttendus);

        String produitsStr = jsonOrigine.get("produits utilisés") != null ? jsonOrigine.get("produits utilisés").asText() : "";
        List<String> produits_raw = Arrays.asList(produitsStr.split("\\. "));
        produits_raw.forEach(a -> {
            String b = a.trim();
            produits.add(b);
        });

        ((ObjectNode) jsonOrigine).set("produits_string", produits);

        return jsonOrigine;
    }

    public ArrayNode ajouterTraitement(List<String> traitements_raw, MongoAccess access, ArrayNode listeTraitementsAttendus){

        traitements_raw.forEach(a -> {
            String b = a.trim();
            if (! b.equals("")){
                ObjectNode traitementAttendu_node = JsonNodeFactory.instance.objectNode();

                traitementAttendu_node.put("traitementAttendu_string", b);

                TacheTraitement tacheTraitementObj = new TacheTraitement(b, access);

                if (tacheTraitementObj == null  || tacheTraitementObj.getNom() == null){
                    tacheTraitementObj = new TacheTraitement();
                    tacheTraitementObj.setNom(b);
                    tacheTraitementObj.setNom_complet(b);
                }
                tacheTraitementObj.setToken(access.getToken());
                tacheTraitementObj.setCreated_at(new Date().toString());
                tacheTraitementObj.setFait_("TODO_");
                tacheTraitementObj = tacheTraitementObj.save();

                traitementAttendu_node.put("traitementAttendu_id", tacheTraitementObj.get_id());

                listeTraitementsAttendus.add(traitementAttendu_node);
            }
        });

        return listeTraitementsAttendus;

    }
}
