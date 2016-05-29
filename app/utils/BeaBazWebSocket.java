package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import play.mvc.Controller;
import play.mvc.WebSocket;
import play.mvc.LegacyWebSocket;

import models.Produit;

import com.fasterxml.jackson.*;

import java.io.IOException;

/**
 * Created by kaplone on 24/04/16.
 */
public class BeaBazWebSocket extends Controller {

    Produit obj = new Produit();



//    public LegacyWebSocket<String> socket() {
//        return WebSocket.withActor(MyWebSocketActor::props);
//    }

    public LegacyWebSocket<String> socket() {

        return WebSocket.whenReady((in, out) -> {

            // For each event received on the socket,
            in.onMessage(a -> retour(a, out));

            // When the socket is closed.
            in.onClose(() -> System.out.println("Disconnected"));
        });
    }

    public void retour(String in, play.mvc.WebSocket.Out<String> out){

        MongoAccess.connect();


        System.out.println(in);

        ObjectMapper mapper = new ObjectMapper();

        System.out.println("try");

        try{
            obj = mapper.readValue(in, Produit.class);
            System.out.println("mapping");
            obj = Produit.save(obj);
            System.out.println("save");
            System.out.println(obj.getNom());
            System.out.println("_id dans socket : " + obj.get_id());
            out.write(mapper.writeValueAsString(obj));
        }
        catch (IOException ioe){
            System.out.println("erreur");
            ioe.printStackTrace();
        }

    }
}



