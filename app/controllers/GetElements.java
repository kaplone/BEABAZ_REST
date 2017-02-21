package controllers;

import models.*;
import org.bson.types.ObjectId;
import org.jongo.Find;
import org.jongo.FindOne;
import org.jongo.MongoCursor;
import play.mvc.*;

import utils.Connexion;
import utils.MongoAccess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by kaplone on 01/05/16.
 */
public class GetElements extends Controller {

    private MongoAccess access = null;

    public Result getAll(String collection) throws InvalidAlgorithmParameterException, UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        return ok(new ObjectMapper().readTree(mapping(access.request(collection), collection).toString()));

    }

    public Result getId(String collection, String id) throws InvalidAlgorithmParameterException, UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        return ok(new ObjectMapper().readTree(mapping(access.request(collection, new ObjectId(id)), collection).toString()));


    }

    public Result getField(String collection, String champ, String valeur) throws InvalidAlgorithmParameterException, UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        valeur = valeur.replace("%20", " ");

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        return ok(new ObjectMapper().readTree(mapping(access.request(collection, champ, valeur), collection).toString()));

    }

    public Result getFieldAll(String collection, String champ, String valeur) throws InvalidAlgorithmParameterException, UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        valeur = valeur.replace("%20", " ");

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        return ok(new ObjectMapper().readTree(mapping(access.requestAll(collection, champ, valeur), collection).toString()));


    }

    public Result getFieldRegex(String collection, String champ, String valeur, String regex) throws InvalidAlgorithmParameterException, UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        valeur = valeur.replace("%20", " ");

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        boolean regex_bool = Boolean.valueOf(regex);

        return  ok(new ObjectMapper().readTree(mapping(access.request(collection, champ, valeur, regex_bool), collection).toString()));


    }

    public Result getFields(String collection, String champ1, String valeur1, String champ2, String valeur2) throws InvalidAlgorithmParameterException, UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        valeur1 = valeur1.replace("%20", " ");
        valeur2 = valeur2.replace("%20", " ");

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        return ok(new ObjectMapper().readTree(mapping(access.request(collection, champ1, valeur1, champ2, valeur2), collection).toString()));

    }

    public String mapping(FindOne find, String collection) {

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        try {

            switch (collection){

                case "auteur" : Auteur m_auteur = find.as(Auteur.class);
                    stringResult = mapper.writeValueAsString(m_auteur);
                    m_auteur.setToken(access.getToken());
                    break;
                case "client" : Client m_client = find.as(Client.class);
                    stringResult = mapper.writeValueAsString(m_client);
                    m_client.setToken(access.getToken());
                    break;
                case "commande" : Commande m_commande = find.as(Commande.class);
                    stringResult = mapper.writeValueAsString(m_commande);
                    m_commande.setToken(access.getToken());
                    break;
                case "complement" : Complement m_complement = find.as(Complement.class);
                    stringResult = mapper.writeValueAsString(m_complement);
                    m_complement.setToken(access.getToken());
                    break;
                case "fichier" : Fichier m_fichier = find.as(Fichier.class);
                    stringResult = mapper.writeValueAsString(m_fichier);
                    m_fichier.setToken(access.getToken());
                    break;
                case "matiere" : Matiere m_matiere = find.as(Matiere.class);
                    stringResult = mapper.writeValueAsString(m_matiere);
                    m_matiere.setToken(access.getToken());
                    break;
                case "model" :  Model m_modele = find.as(Model.class);
                    stringResult = mapper.writeValueAsString(m_modele);
                    m_modele.setToken(access.getToken());
                    break;
                case "oeuvre" : Oeuvre m_oeuvre = find.as(Oeuvre.class);
                    stringResult = mapper.writeValueAsString(m_oeuvre);
                    m_oeuvre.setToken(access.getToken());
                    break;
                case "oeuvreTraitee" : OeuvreTraitee m_oeuvreTraitee = find.as(OeuvreTraitee.class);
                    stringResult = mapper.writeValueAsString(m_oeuvreTraitee);
                    m_oeuvreTraitee.setToken(access.getToken());
                    break;
                case "produit" : Produit m_produit = find.as(Produit.class);
                    stringResult = mapper.writeValueAsString(m_produit);
                    m_produit.setToken(access.getToken());
                    break;
                case "tacheTraitement" : TacheTraitement m_tacheTraitement = find.as(TacheTraitement.class);
                    stringResult = mapper.writeValueAsString(m_tacheTraitement);
                    m_tacheTraitement.setToken(access.getToken());
                    break;
                case "technique" : Technique m_technique = find.as(Technique.class);
                    stringResult = mapper.writeValueAsString(m_technique);
                    m_technique.setToken(access.getToken());
                    break;
                case "traitement" : Traitement m_traitement = find.as(Traitement.class);
                    stringResult = mapper.writeValueAsString(m_traitement);
                    m_traitement.setToken(access.getToken());
                    break;

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //return badRequest();
        }

        return stringResult;

    }


    public String mapping(Find find, String collection){

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        try {

            switch (collection){

                case "auteur" : MongoCursor<Auteur> m_auteur = find.as(Auteur.class);
                    stringResult = mapper.writeValueAsString(m_auteur);
                    break;
                case "client" : MongoCursor<Client> m_client = find.as(Client.class);
                    stringResult = mapper.writeValueAsString(m_client);
                    break;
                case "commande" : MongoCursor<Commande> m_commande = find.as(Commande.class);
                    stringResult = mapper.writeValueAsString(m_commande);
                    break;
                case "complement" : MongoCursor<Complement> m_complement = find.as(Complement.class);
                    stringResult = mapper.writeValueAsString(m_complement);
                    break;
                case "fichier" : MongoCursor<Fichier> m_fichier = find.as(Fichier.class);
                    stringResult = mapper.writeValueAsString(m_fichier);
                    break;
                case "matiere" : MongoCursor<Matiere> m_matiere = find.as(Matiere.class);
                    stringResult = mapper.writeValueAsString(m_matiere);
                    break;
                case "model" :  MongoCursor<Model> m_modele = find.as(Model.class);
                    stringResult = mapper.writeValueAsString(m_modele);
                    break;
                case "oeuvre" : MongoCursor<Oeuvre> m_oeuvre = find.as(Oeuvre.class);
                    stringResult = mapper.writeValueAsString(m_oeuvre);
                    break;
                case "oeuvreTraitee" : MongoCursor<OeuvreTraitee> m_oeuvreTraitee = find.as(OeuvreTraitee.class);
                    stringResult = mapper.writeValueAsString(m_oeuvreTraitee);
                    break;
                case "produit" : MongoCursor<Produit> m_produit = find.as(Produit.class);
                    stringResult = mapper.writeValueAsString(m_produit);
                    break;
                case "tacheTraitement" : MongoCursor<TacheTraitement> m_tacheTraitement = find.as(TacheTraitement.class);
                    stringResult = mapper.writeValueAsString(m_tacheTraitement);
                    break;
                case "technique" : MongoCursor<Technique> m_technique = find.as(Technique.class);
                    stringResult = mapper.writeValueAsString(m_technique);
                    break;
                case "traitement" : MongoCursor<Traitement> m_traitement = find.as(Traitement.class);
                    stringResult = mapper.writeValueAsString(m_traitement);
                    break;

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //return badRequest();
        }

            return stringResult;

    }

}
