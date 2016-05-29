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
                case "produit" : MongoCursor<Produit> m_produit = MongoAccess.request(collection).as(Produit.class);
                                 stringResult = mapper.writeValueAsString(m_produit);
                                 break;
                case "commande" : MongoCursor<Commande> m_commande = MongoAccess.request(collection).as(Commande.class);
                                 stringResult = mapper.writeValueAsString(m_commande);
                                 break;
                case "auteur" : MongoCursor<Auteur> m_auteur = MongoAccess.request(collection).as(Auteur.class);
                                 stringResult = mapper.writeValueAsString(m_auteur);
                                 break;
                case "client" : MongoCursor<Client> m_client = MongoAccess.request(collection).as(Client.class);
                                 stringResult = mapper.writeValueAsString(m_client);
                                 break;
                case "complement" : MongoCursor<Complement> m_complement = MongoAccess.request(collection).as(Complement.class);
                                 stringResult = mapper.writeValueAsString(m_complement);
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
                case "produit" : Produit m = MongoAccess.request(collection, new ObjectId(id)).as(Produit.class).next();
                    stringResult = mapper.writeValueAsString(m);
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
                case "produit" : Produit m = MongoAccess.request(collection, champ, valeur).as(Produit.class);
                    stringResult = mapper.writeValueAsString(m);
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
                case "produit" : MongoCursor<Produit> m = MongoAccess.requestAll(collection, champ, valeur).as(Produit.class);
                    stringResult = mapper.writeValueAsString(m);
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
