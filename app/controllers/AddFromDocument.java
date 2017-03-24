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
import java.util.Date;

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

    private int compte;


    public Result add() throws InvalidAlgorithmParameterException,UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        mapper = new ObjectMapper();
        adaptationJson = new AdaptationJson();

        json = request().body().asJson();

        compte = 0;

        System.out.println("Dans AddFromDocument : \n" + json);

        System.out.println( json.get("document").size());
        System.out.println( json.get("document"));

        json.get("document").forEach(a -> {

            System.out.println(a);

            try {

                jsonOeuvre = adaptationJson.adaptationVersOeuvre(a.get("oeuvre"), access);

                o = Json.fromJson(jsonOeuvre, Oeuvre.class);
                o.setToken(access.getToken());
                o.setCreated_at(new Date().toString());
                o = o.save();

                jsonOeuvreTraitee = adaptationJson.adaptationVersOeuvreTraitee(a.get("oeuvreTraitee"), o.get_id(), access);

                ot = Json.fromJson(jsonOeuvreTraitee, OeuvreTraitee.class);
                ot.setOeuvre_id(o.get_id());
                ot.setToken(access.getToken());
                ot.setCreated_at(new Date().toString());
                ot = ot.save();

                compte++;
            }catch (ConfigException.Null npe) {
                npe.printStackTrace();
            }
        });

        return ok(String.format("%d objets créés", compte));
    }
}