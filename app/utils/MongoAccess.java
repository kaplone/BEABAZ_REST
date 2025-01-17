package utils;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import enums.Classes;
import models.*;
import org.bson.types.ObjectId;
import org.jongo.*;

import java.util.regex.Pattern;

public class MongoAccess {

    DB db;
    Jongo jongo;
    MongoCollection collec;
    String token;

    public Jongo connect(String login, String pass, String base, String token) {

        this.token = token;

        LoadConfig.loadSettings();

        MongoClientURI uri = new MongoClientURI(String.format("mongodb://%s:%s@%s:%s/%s",
                login,
                pass,
                Settings.getAdresse(),
                Settings.getPort(),
                base));

        MongoClient client = new MongoClient(uri);
        db = client.getDB(uri.getDatabase());
        jongo = new Jongo(db);

        return jongo;
    }

    public boolean checkIfExists (String table, String field, String valeur) {

        collec = jongo.getCollection(table);
        return  collec.findOne(String.format("{\"%s\" : \"%s\"}", field, valeur)).as(Classes.valueOf(table).getUsedClass()) != null;
        //return  collec.findOne(String.format("{\"%s\" : \"%s\"}", field, valeur)).as(Client.class) != null;


    }

    public Find request(String table) {

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find();

        return find;
    }

    public FindOne request(String table, ObjectId id) {

        FindOne find = null;
        collec = jongo.getCollection(table);
        find = collec.findOne("{_id :  #}", id);

        return find;
    }

    public FindOne request(String table, String id) {

        FindOne find = null;
        collec = jongo.getCollection(table);
        find = collec.findOne("{_id :  #}", new ObjectId(id));

        return find;
    }

    public Find request(String table, Commande commande) {

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find("{commande._id :  #}", commande.get_id_obj());

        return find;
    }

    public Find request(String table, String field, ObjectId objectId) {

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find("{# :  #}", field, objectId);

        return find;
    }


    public Distinct distinct(String table, String distinct, String field, ObjectId objectId) {

        Distinct find = null;
        collec = jongo.getCollection(table);
        find = collec.distinct(distinct).query("{# :  #}", field, objectId);

        //System.out.println();

        return find;
    }

    public Find request(String table, String field, ObjectId objectId, String object) {

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find("{# :  #}", field, objectId).projection("{# : 1, _id : 0", object);

        return find;
    }

    public Find request(String table, String field, Commande commande) {

//    	System.out.println(field);

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find("{commande_id :  #}", commande.get_id_obj());

        return find;
    }

    public Find request(String table, Client client) {

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find("{client._id : #}", client.get_id_obj());

        return find;
    }

    public Find request(String table, Traitement traitement) {

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find("{traitement : #}", traitement.get_id_obj());

        return find;
    }

    public FindOne request(String table, String field, String valeur) {

        FindOne one = null;
        collec = jongo.getCollection(table);

        if (field.contains("_id")) {
            one = collec.findOne(String.format("{\"%s\": # }", field), new ObjectId(valeur));
        }
        else {
            one = collec.findOne(String.format("{\"%s\" : \"%s\"}", field, valeur));
        }

        return one;
    }

    public Find requestAll(String table, String field, String valeur) {

        Find find = null;
        collec = jongo.getCollection(table);

        if (field.contains("_id")) {
            find = collec.find(String.format("{\"%s\" : # }", field), new ObjectId(valeur));
        }
        else {
            find = collec.find(String.format("{\"%s\" : \"%s\"}", field, valeur));
        }

        return find;
    }
    public Find requestAll(String table, String field, ObjectId objectId) {

        Find find = null;
        collec = jongo.getCollection(table);
        find = collec.find(String.format("{\"%s\" : %s}", field, objectId));

        return find;
    }


    public FindOne request(String table, String field1, String valeur1, String field2, String valeur2) {

        FindOne one = null;
        collec = jongo.getCollection(table);
        one = collec.findOne(String.format("{\"%s\" : \"%s\", \"%s\" : \"%s\"}", field1, valeur1, field2, valeur2));

        return one;
    }

    public FindOne request(String table, String field, String valeur, boolean regex) {

        FindOne one = null;
        collec = jongo.getCollection(table);

        String query = String.format("{%s : #}", field);
        String reg = String.format("^%s.PR.1.JPG", valeur);

//		System.out.println(query);
//		System.out.println(reg);


        one = collec.findOne(query, Pattern.compile(reg));
        //{$regex: #}}", "jo.*"


        return one;
    }

    public void insert (String table, Object m) {
        collec = jongo.getCollection(table);
        collec.insert(m);

    }

    public Commun save (String table, Commun m) {

        collec = jongo.getCollection(table);
        collec.insert(m);
        return m;

    }

    public void drop() {
        collec.drop();

    }

    public void update(String table, Commun c) {
        collec = jongo.getCollection(table);

        collec.update("{_id : #}", c.get_id_obj()).with(c);
    }

    public String getToken() {
        return token;
    }


}
