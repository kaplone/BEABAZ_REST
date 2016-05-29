package controllers;

import models.Settings;
import org.bson.types.ObjectId;
import org.jongo.MongoCursor;
import models.Produit;
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

    public Result getAll(String collection) {

        MongoAccess.connect();

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        try {

            switch (collection){
                case "produit" : MongoCursor<Produit> m = MongoAccess.request(collection).as(Produit.class);
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

    public Result getId(String collection, String id) {

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
