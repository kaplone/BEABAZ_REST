package controllers;

import models.Produit;
import play.mvc.*;
import play.libs.Json;
import utils.Connexion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * Created by kaplone on 01/05/16.
 */
public class AddElement extends Controller {

    public Result addOne(String collection) {

        Connexion.getConnetion().connect();

        ObjectMapper mapper = new ObjectMapper();

        String stringResult = "";

        JsonNode json = request().body().asJson();

        System.out.println(json);

        try {

            switch (collection){
                case "produit" : Produit m = Json.fromJson(json, Produit.class);
                                 Produit.save(m);
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