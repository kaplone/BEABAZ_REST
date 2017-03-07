package controllers;

import com.typesafe.config.ConfigException;
import models.*;
import play.api.http.HttpErrorHandlerExceptions;
import play.api.http.HttpErrorHandlerExceptions$;
import play.mvc.*;
import play.libs.Json;
import utils.Connexion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
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
public class AddElement extends Controller {

    private MongoAccess access = null;
    private ObjectMapper mapper = null;
    private String stringResult = null;
    private JsonNode json = null;
    private Commun m = null;

    public Result addOne(String collection) throws InvalidAlgorithmParameterException,UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        mapper = new ObjectMapper();

        stringResult = "";

        json = request().body().asJson();

        System.out.println("Dans AddElement : \n" + json);

        try {

            switch (collection){
                case "produit" :         m = Json.fromJson(json, Produit.class);
                                         break;
                case "auteur" :          m = Json.fromJson(json, Auteur.class);
                                         break;
                case "client" :          m = Json.fromJson(json, Client.class);
                                         break;
                case "commande" :        m = Json.fromJson(json, Commande.class);
                                         break;
                case "complement" :      m = Json.fromJson(json, Complement.class);
                                         break;
                case "fichier" :         m = Json.fromJson(json, Fichier.class);
                                         break;
                case "matiere" :         m = Json.fromJson(json, Matiere.class);
                                         break;
                case "model" :           m = Json.fromJson(json, Model.class);
                                         break;
                case "oeuvre" :          m = Json.fromJson(json, Oeuvre.class);
                                         break;
                case "oeuvreTraitee" :   m = Json.fromJson(json, OeuvreTraitee.class);
                                         break;
                case "tacheTraitement" : m = Json.fromJson(json, TacheTraitement.class);
                                         break;
                case "technique" :       m = Json.fromJson(json, Technique.class);
                                         break;
                case "traitement" :      m = Json.fromJson(json, Traitement.class);
                                         break;
            }

            m.setToken(access.getToken());
            m.setCreated_at(new Date().toString());
            m = m.save();
            stringResult = mapper.writeValueAsString(m);

            return ok(new ObjectMapper().readTree(stringResult));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return badRequest();
        }
        catch (ConfigException.Null npe){
            npe.printStackTrace();
            return badRequest();
        }
    }
}