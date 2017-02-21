package controllers;

import models.Produit;
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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by kaplone on 01/05/16.
 */
public class AddElement extends Controller {

    private MongoAccess access = null;

    public Result addOne(String collection) throws UnsupportedOperationException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        byte[] bytes = request().getHeader("monToken").getBytes();

        access = Connexion.getConnetion(bytes);

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        JsonNode json = request().body().asJson();

        System.out.println(json);

        try {

            switch (collection){
                case "produit" : Produit m = Json.fromJson(json, Produit.class);
                                 m.save();
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