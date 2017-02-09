package utils;

import models.Settings;

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

import org.jongo.*;

/**
 * Created by kaplone on 09/02/17.
 */
public class Connexion {

    private static Jongo connect = null;
    private static MongoAccess access = null;

    public static MongoAccess getConnetion(byte [] bytes) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        if (connect == null){
            LoadConfig.loadSettings();

            String key = Settings.getKey();

            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] encrypted = decoder.decode(bytes);

            //System.out.println("Encrypted : " + new String(encrypted));

            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(encrypted));

            //System.out.println("Decrypted : " + decrypted);


            Settings.setLogin(decrypted.split(" ")[0].trim());
            Settings.setPass(decrypted.split(" ")[1].trim());
            Settings.setBase(decrypted.split(" ")[2].trim());

            access = new MongoAccess();

            connect = access.connect();
        }

        return access;
    }

    public static MongoAccess getConnetion(){
        return access;
    }

    }
