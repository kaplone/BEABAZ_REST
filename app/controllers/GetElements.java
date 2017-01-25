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

        return ok(new ObjectMapper().readTree(MongoAccess.request(collection).toString()));

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

}
