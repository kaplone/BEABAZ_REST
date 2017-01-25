package controllers;

import models.*;
import org.bson.types.ObjectId;
import org.jongo.Find;
import org.jongo.MongoCursor;
import play.mvc.*;

import utils.LoadConfig;
import utils.MongoAccess;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by kaplone on 01/05/16.
 */
public class GetElements extends Controller {

    public Result getAll(String collection) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        LoadConfig.loadSettings();

        String key = Settings.getKey();

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(request().getHeader("monToken").getBytes());

        System.out.println("Encrypted : " + new String(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));

        System.out.println("Decrypted : " + decrypted);


        Settings.setLogin(decrypted.split(" ")[0].trim());
        Settings.setPass(decrypted.split(" ")[1].trim());
        Settings.setBase(decrypted.split(" ")[2].trim());

        MongoAccess.connect();

        return ok(new ObjectMapper().readTree(mapping(MongoAccess.request(collection), collection).toString()));

    }

    public Result getId(String collection, String id) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        LoadConfig.loadSettings();

        String key = Settings.getKey();

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(request().getHeader("monToken").getBytes());

        System.out.println("Encrypted : " + new String(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));

        System.out.println("Decrypted : " + decrypted);


        Settings.setLogin(decrypted.split(" ")[0].trim());
        Settings.setPass(decrypted.split(" ")[1].trim());
        Settings.setBase(decrypted.split(" ")[2].trim());

        MongoAccess.connect();

        return ok(new ObjectMapper().readTree(MongoAccess.request(collection, new ObjectId(id)).toString()));


    }

    public Result getField(String collection, String champ, String valeur) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        LoadConfig.loadSettings();

        String key = Settings.getKey();

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(request().getHeader("monToken").getBytes());

        System.out.println("Encrypted : " + new String(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));

        System.out.println("Decrypted : " + decrypted);


        Settings.setLogin(decrypted.split(" ")[0].trim());
        Settings.setPass(decrypted.split(" ")[1].trim());
        Settings.setBase(decrypted.split(" ")[2].trim());

        MongoAccess.connect();

        return ok(new ObjectMapper().readTree(MongoAccess.request(collection, champ, valeur).toString()));

    }

    public Result getFieldAll(String collection, String champ, String valeur) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        LoadConfig.loadSettings();

        String key = Settings.getKey();

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(request().getHeader("monToken").getBytes());

        System.out.println("Encrypted : " + new String(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));

        System.out.println("Decrypted : " + decrypted);


        Settings.setLogin(decrypted.split(" ")[0].trim());
        Settings.setPass(decrypted.split(" ")[1].trim());
        Settings.setBase(decrypted.split(" ")[2].trim());

        MongoAccess.connect();

        return ok(new ObjectMapper().readTree(MongoAccess.requestAll(collection, champ, valeur).toString()));


    }

    public Result getFieldRegex(String collection, String champ, String valeur, String regex) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        LoadConfig.loadSettings();

        String key = Settings.getKey();

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(request().getHeader("monToken").getBytes());

        System.out.println("Encrypted : " + new String(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));

        System.out.println("Decrypted : " + decrypted);


        Settings.setLogin(decrypted.split(" ")[0].trim());
        Settings.setPass(decrypted.split(" ")[1].trim());
        Settings.setBase(decrypted.split(" ")[2].trim());

        boolean regex_bool = Boolean.valueOf(regex);

        MongoAccess.connect();

        return  ok(new ObjectMapper().readTree(MongoAccess.request(collection, champ, valeur, regex_bool).toString()));


    }

    public Result getFields(String collection, String champ1, String valeur1, String champ2, String valeur2) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        LoadConfig.loadSettings();

        String key = Settings.getKey();

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(request().getHeader("monToken").getBytes());

        System.out.println("Encrypted : " + new String(encrypted));

        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        String decrypted = new String(cipher.doFinal(encrypted));

        System.out.println("Decrypted : " + decrypted);


        Settings.setLogin(decrypted.split(" ")[0].trim());
        Settings.setPass(decrypted.split(" ")[1].trim());
        Settings.setBase(decrypted.split(" ")[2].trim());

        MongoAccess.connect();
        return ok(new ObjectMapper().readTree(MongoAccess.request(collection, champ1, valeur1, champ2, valeur2).toString()));

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
