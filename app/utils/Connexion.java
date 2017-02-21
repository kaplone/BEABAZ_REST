package utils;

import models.Settings;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

    private static final int dureeLimite = 10 * 60 ;

    private Jongo connect = null;
    private MongoAccess access = null;
    private Instant limiteValidite;
    private String token;

    private static Map<String, Connexion> accessMap;

    static {
        accessMap = new HashMap<>();
    }

    public static MongoAccess getConnetion(byte [] bytes) throws InvalidAlgorithmParameterException, UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException{

        String token = new String(bytes);

        if (accessMap.containsKey(token)){

            if (accessMap.get(token).getLimiteValidite().isBefore(Instant.now())){

                System.out.println("accès depuis le Map");
                return accessMap.get(token).getAccess();
            }
            else {
                System.out.println("entrée Map périmée");
                accessMap.remove(token);
            }
        }

        LoadConfig.loadSettings();
        String key = Settings.getKey();
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encrypted = decoder.decode(bytes);
        String decrypted = new String(cipher.doFinal(encrypted));

        System.out.println(decrypted);

        MongoAccess access_temp = new MongoAccess();
        Jongo connect_temp = access_temp.connect(
                decrypted.split(" ")[0].trim(),
                decrypted.split(" ")[1].trim(),
                decrypted.split(" ")[2].trim(),
                token
        );

        Connexion connexion_temp = new Connexion();
        connexion_temp.setAccess(access_temp);
        connexion_temp.setConnect(connect_temp);
        connexion_temp.setToken(token);

        accessMap.put(token, connexion_temp);

        System.out.println("nouvelle entrée Map");
        return access_temp;
    }

    public static MongoAccess getConnetion(String bytes) {

        try {
            return getConnetion(bytes.getBytes());
        }
        catch (InvalidAlgorithmParameterException |UnsupportedOperationException | IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e){
            return null;
        }

    }

        public Jongo getConnect(){
        return connect;
    }

    public void setConnect(Jongo jongo){
        connect = jongo;
    }

    public MongoAccess getAccess(){
        return access;
    }

    public void setAccess(MongoAccess mongoAccess){
        access = mongoAccess;
    }

    public Instant getLimiteValidite(){
        return limiteValidite.plusSeconds(dureeLimite);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
