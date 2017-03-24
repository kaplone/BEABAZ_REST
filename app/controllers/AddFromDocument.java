package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.ConfigException;
import models.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import utils.AdaptationJson;
import utils.Connexion;
import utils.MongoAccess;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by kaplone on 01/05/16.
 */
public class AddFromDocument extends Controller {

    private MongoAccess access = null;
    private ObjectMapper mapper = null;

    private JsonNode json = null;
    private JsonNode jsonOeuvre = null;
    private Oeuvre o = null;
    private JsonNode jsonOeuvreTraitee = null;
    private OeuvreTraitee ot = null;

    private AdaptationJson adaptationJson;

    private Commande commande;

    private int compte;


    public Result add() throws InvalidAlgorithmParameterException,UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        mapper = new ObjectMapper();
        adaptationJson = new AdaptationJson();

        json = request().body().asJson();

        compte = 0;

        commande = access.request("commande", json.get("document").get(0).get("oeuvreTraitee").get("commande_id").asText()).as(Commande.class);

        List<Map<String, String>> ots = commande.getOeuvresTraitees() != null ? commande.getOeuvresTraitees() : new ArrayList<>();
        final Map<String, String> ot_map = new HashMap<>();


        System.out.println("Dans AddFromDocument : \n" + json);

        json.get("document").forEach(a -> {

            ot_map.clear();

            try {

                jsonOeuvre = adaptationJson.adaptationVersOeuvre(a.get("oeuvre"), access);

                o = Json.fromJson(jsonOeuvre, Oeuvre.class);
                o.setToken(access.getToken());
                o.setCreated_at(new Date().toString());
                o = o.save();

                jsonOeuvreTraitee = adaptationJson.adaptationVersOeuvreTraitee(a.get("oeuvreTraitee"),
                                                                                      o.get_id(),
                                                                                      o.getCote_archives_6s(),
                                                                                      o.getNom(),
                                                                                      access);

                ot = Json.fromJson(jsonOeuvreTraitee, OeuvreTraitee.class);
                ot.setOeuvre_id(o.get_id());
                ot.setToken(access.getToken());
                ot.setCreated_at(new Date().toString());
                ot = ot.save();

                ot_map.put("oeuvresTraitee_id", ot.get_id());
                ot_map.put("oeuvresTraitee_string", ot.getNom());
                ot_map.put("oeuvresTraitee_etat", "TODO_");

                ots.add(ot_map);

                compte++;
            }catch (ConfigException.Null npe) {
                npe.printStackTrace();
            }
        });

        commande.setToken(access.getToken());
        commande.setCreated_at(new Date().toString());
        commande.update();

        String stringResult = mapper.writeValueAsString(commande);

        return ok(new ObjectMapper().readTree(stringResult));
    }
}