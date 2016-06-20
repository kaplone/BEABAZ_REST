package controllers;

import models.*;
import org.bson.types.ObjectId;
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

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        try {

            switch (collection){

                /**
                 *  mongoCursor vers mapper ?
                 */


                case "auteur" : MongoCursor<Auteur> m_auteur = MongoAccess.request(collection).as(Auteur.class);
                                 stringResult = mapper.writeValueAsString(m_auteur);
                                 break;
                case "client" : MongoCursor<Client> m_client = MongoAccess.request(collection).as(Client.class);
                                 stringResult = mapper.writeValueAsString(m_client);
                                 break;
                case "commande" : MongoCursor<Commande> m_commande = MongoAccess.request(collection).as(Commande.class);
                                  ArrayList<Commande> listeCommande = new ArrayList<>();
                                  while (m_commande.hasNext()){
                                      listeCommande.add(m_commande.next());
                                  }
                                 stringResult = mapper.writeValueAsString(listeCommande);
                                 break;
                case "complement" : MongoCursor<Complement> m_complement = MongoAccess.request(collection).as(Complement.class);
                                 stringResult = mapper.writeValueAsString(m_complement);
                                 break;
                case "fichier" : MongoCursor<Fichier> m_fichier = MongoAccess.request(collection).as(Fichier.class);
                                 stringResult = mapper.writeValueAsString(m_fichier);
                                 break;
                case "matiere" : MongoCursor<Matiere> m_matiere = MongoAccess.request(collection).as(Matiere.class);
                                 stringResult = mapper.writeValueAsString(m_matiere);
                                 break;
                case "modele" :  MongoCursor<Model> m_modele = MongoAccess.request(collection).as(Model.class);
                                 stringResult = mapper.writeValueAsString(m_modele);
                                 break;
                case "oeuvre" : MongoCursor<Oeuvre> m_oeuvre = MongoAccess.request(collection).as(Oeuvre.class);
                                stringResult = mapper.writeValueAsString(m_oeuvre);
                                break;
                case "oeuvreTraitee" : MongoCursor<OeuvreTraitee> m_oeuvreTraitee = MongoAccess.request(collection).as(OeuvreTraitee.class);
                                stringResult = mapper.writeValueAsString(m_oeuvreTraitee);
                                break;
                case "produit" : MongoCursor<Produit> m_produit = MongoAccess.request(collection).as(Produit.class);
                                stringResult = mapper.writeValueAsString(m_produit);
                                break;
                case "tacheTraitement" : MongoCursor<TacheTraitement> m_tacheTraitement = MongoAccess.request(collection).as(TacheTraitement.class);
                                stringResult = mapper.writeValueAsString(m_tacheTraitement);
                                break;
                case "technique" : MongoCursor<Technique> m_technique = MongoAccess.request(collection).as(Technique.class);
                                stringResult = mapper.writeValueAsString(m_technique);
                                break;
                case "traitement" : MongoCursor<Traitement> m_traitement = MongoAccess.request(collection).as(Traitement.class);
                                ArrayList<Traitement> listeTraitement= new ArrayList<>();
                                while (m_traitement.hasNext()){
                                    listeTraitement.add(m_traitement.next());
                                }
                                stringResult = mapper.writeValueAsString(m_traitement);
                                break;
            }

            return ok(new ObjectMapper().readTree(stringResult));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return badRequest();


        }
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

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        try {

            switch (collection){

                case "auteur" : Auteur m_auteur = MongoAccess.request(collection, new ObjectId(id)).as(Auteur.class);
                    stringResult = mapper.writeValueAsString(m_auteur);
                    break;
                case "client" : Client m_client = MongoAccess.request(collection, new ObjectId(id)).as(Client.class);
                    stringResult = mapper.writeValueAsString(m_client);
                    break;
                case "commande" : Commande m_commande = MongoAccess.request(collection, new ObjectId(id)).as(Commande.class);
                    stringResult = mapper.writeValueAsString(m_commande);
                    break;
                case "complement" : Complement m_complement = MongoAccess.request(collection, new ObjectId(id)).as(Complement.class);
                    stringResult = mapper.writeValueAsString(m_complement);
                    break;
                case "fichier" : Fichier m_fichier = MongoAccess.request(collection, new ObjectId(id)).as(Fichier.class);
                    stringResult = mapper.writeValueAsString(m_fichier);
                    break;
                case "matiere" : Matiere m_matiere = MongoAccess.request(collection, new ObjectId(id)).as(Matiere.class);
                    stringResult = mapper.writeValueAsString(m_matiere);
                    break;
                case "modele" :  Model m_modele = MongoAccess.request(collection, new ObjectId(id)).as(Model.class);
                    stringResult = mapper.writeValueAsString(m_modele);
                    break;
                case "oeuvre" : Oeuvre m_oeuvre = MongoAccess.request(collection, new ObjectId(id)).as(Oeuvre.class);
                    stringResult = mapper.writeValueAsString(m_oeuvre);
                    break;
                case "oeuvreTraitee" : OeuvreTraitee m_oeuvreTraitee = MongoAccess.request(collection, new ObjectId(id)).as(OeuvreTraitee.class);
                    stringResult = mapper.writeValueAsString(m_oeuvreTraitee);
                    break;
                case "produit" : Produit m_produit = MongoAccess.request(collection, new ObjectId(id)).as(Produit.class);
                    stringResult = mapper.writeValueAsString(m_produit);
                    break;
                case "tacheTraitement" : TacheTraitement m_tacheTraitement = MongoAccess.request(collection, new ObjectId(id)).as(TacheTraitement.class);
                    stringResult = mapper.writeValueAsString(m_tacheTraitement);
                    break;
                case "technique" : Technique m_technique = MongoAccess.request(collection, new ObjectId(id)).as(Technique.class);
                    stringResult = mapper.writeValueAsString(m_technique);
                    break;
                case "traitement" : Traitement m_traitement = MongoAccess.request(collection, new ObjectId(id)).as(Traitement.class);
                    stringResult = mapper.writeValueAsString(m_traitement);
                    break;
            }

            return ok(new ObjectMapper().readTree(stringResult));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return badRequest();


        }
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

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        try {

            switch (collection){

                case "auteur" : Auteur m_auteur = MongoAccess.request(collection, champ, valeur).as(Auteur.class);
                    stringResult = mapper.writeValueAsString(m_auteur);
                    break;
                case "client" : Client m_client = MongoAccess.request(collection, champ, valeur).as(Client.class);
                    stringResult = mapper.writeValueAsString(m_client);
                    break;
                case "commande" : Commande m_commande = MongoAccess.request(collection, champ, valeur).as(Commande.class);
                    stringResult = mapper.writeValueAsString(m_commande);
                    break;
                case "complement" : Complement m_complement = MongoAccess.request(collection, champ, valeur).as(Complement.class);
                    stringResult = mapper.writeValueAsString(m_complement);
                    break;
                case "fichier" : Fichier m_fichier = MongoAccess.request(collection, champ, valeur).as(Fichier.class);
                    stringResult = mapper.writeValueAsString(m_fichier);
                    break;
                case "matiere" : Matiere m_matiere = MongoAccess.request(collection, champ, valeur).as(Matiere.class);
                    stringResult = mapper.writeValueAsString(m_matiere);
                    break;
                case "modele" :  Model m_modele = MongoAccess.request(collection, champ, valeur).as(Model.class);
                    stringResult = mapper.writeValueAsString(m_modele);
                    break;
                case "oeuvre" : Oeuvre m_oeuvre = MongoAccess.request(collection, champ, valeur).as(Oeuvre.class);
                    stringResult = mapper.writeValueAsString(m_oeuvre);
                    break;
                case "oeuvreTraitee" : OeuvreTraitee m_oeuvreTraitee = MongoAccess.request(collection, champ, valeur).as(OeuvreTraitee.class);
                    stringResult = mapper.writeValueAsString(m_oeuvreTraitee);
                    break;
                case "produit" : Produit m_produit = MongoAccess.request(collection, champ, valeur).as(Produit.class);
                    stringResult = mapper.writeValueAsString(m_produit);
                    break;
                case "tacheTraitement" : TacheTraitement m_tacheTraitement = MongoAccess.request(collection, champ, valeur).as(TacheTraitement.class);
                    stringResult = mapper.writeValueAsString(m_tacheTraitement);
                    break;
                case "technique" : Technique m_technique = MongoAccess.request(collection, champ, valeur).as(Technique.class);
                    stringResult = mapper.writeValueAsString(m_technique);
                    break;
                case "traitement" : Traitement m_traitement = MongoAccess.request(collection, champ, valeur).as(Traitement.class);
                    stringResult = mapper.writeValueAsString(m_traitement);
                    break;

            }

            return ok(new ObjectMapper().readTree(stringResult));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return badRequest();


        }
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

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        try {

            switch (collection){

                case "auteur" : MongoCursor<Auteur> m_auteur = MongoAccess.requestAll(collection, champ, valeur).as(Auteur.class);
                    stringResult = mapper.writeValueAsString(m_auteur);
                    break;
                case "client" : MongoCursor<Client> m_client = MongoAccess.requestAll(collection, champ, valeur).as(Client.class);
                    stringResult = mapper.writeValueAsString(m_client);
                    break;
                case "commande" : MongoCursor<Commande> m_commande = MongoAccess.requestAll(collection, champ, valeur).as(Commande.class);
                    stringResult = mapper.writeValueAsString(m_commande);
                    break;
                case "complement" : MongoCursor<Complement> m_complement = MongoAccess.requestAll(collection, champ, valeur).as(Complement.class);
                    stringResult = mapper.writeValueAsString(m_complement);
                    break;
                case "fichier" : MongoCursor<Fichier> m_fichier = MongoAccess.requestAll(collection, champ, valeur).as(Fichier.class);
                    stringResult = mapper.writeValueAsString(m_fichier);
                    break;
                case "matiere" : MongoCursor<Matiere> m_matiere = MongoAccess.requestAll(collection, champ, valeur).as(Matiere.class);
                    stringResult = mapper.writeValueAsString(m_matiere);
                    break;
                case "modele" :  MongoCursor<Model> m_modele = MongoAccess.requestAll(collection, champ, valeur).as(Model.class);
                    stringResult = mapper.writeValueAsString(m_modele);
                    break;
                case "oeuvre" : MongoCursor<Oeuvre> m_oeuvre = MongoAccess.requestAll(collection, champ, valeur).as(Oeuvre.class);
                    stringResult = mapper.writeValueAsString(m_oeuvre);
                    break;
                case "oeuvreTraitee" : MongoCursor<OeuvreTraitee> m_oeuvreTraitee = MongoAccess.requestAll(collection, champ, valeur).as(OeuvreTraitee.class);
                    stringResult = mapper.writeValueAsString(m_oeuvreTraitee);
                    break;
                case "produit" : MongoCursor<Produit> m_produit = MongoAccess.requestAll(collection, champ, valeur).as(Produit.class);
                    stringResult = mapper.writeValueAsString(m_produit);
                    break;
                case "tacheTraitement" : MongoCursor<TacheTraitement> m_tacheTraitement = MongoAccess.requestAll(collection, champ, valeur).as(TacheTraitement.class);
                    stringResult = mapper.writeValueAsString(m_tacheTraitement);
                    break;
                case "technique" : MongoCursor<Technique> m_technique = MongoAccess.requestAll(collection, champ, valeur).as(Technique.class);
                    stringResult = mapper.writeValueAsString(m_technique);
                    break;
                case "traitement" : MongoCursor<Traitement> m_traitement = MongoAccess.requestAll(collection, champ, valeur).as(Traitement.class);
                    stringResult = mapper.writeValueAsString(m_traitement);
                    break;

            }

            return ok(new ObjectMapper().readTree(stringResult));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return badRequest();
        }

    }

}
