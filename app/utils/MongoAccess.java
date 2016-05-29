package utils;

import java.net.UnknownHostException;

import org.bson.types.ObjectId;
import org.jongo.Distinct;
import org.jongo.Find;
import org.jongo.FindOne;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.Update;

import models.Auteur;
import models.Client;
import models.Commande;
import models.Commun;
import models.Settings;
import models.Traitement;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import enums.Classes;

public class MongoAccess {
	
	static MongoClient mc;
	static DB db;
	static Jongo jongo;
	static MongoCollection collec;
	
	public static void connect(){
	
//		try {

		LoadConfig.loadSettings();

		MongoClientURI uri  = new MongoClientURI(String.format("mongodb://%s:%s@%s:%s/%s",
				Settings.getLogin(),
				Settings.getPass(),
				Settings.getAdresse(),
				Settings.getPort(),
				Settings.getBase()));

		//MongoClientURI uri  = new MongoClientURI("mongodb://127.0.0.1/test4");
			MongoClient client = new MongoClient(uri);
			db = client.getDB(uri.getDatabase());	
			jongo = new Jongo(db);

//		}
//		catch (UnknownHostException UHE){
//					System.out.println("erreur " + UHE);
//		}
	}
	
	public static boolean checkIfExists (String table, String field, String valeur) {

		collec = jongo.getCollection(table);
		return  collec.findOne(String.format("{\"%s\" : \"%s\"}", field, valeur)).as(Classes.valueOf(table).getUsedClass()) != null;
		//return  collec.findOne(String.format("{\"%s\" : \"%s\"}", field, valeur)).as(Client.class) != null;
		
		
	}
	
    public static Find request(String table) {	
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find();

		return find;
	}
    
    public static Find request(String table, ObjectId id) {	
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find("{_id :  #}", id);

		return find;
	}
    
    public static Find request(String table, Commande commande) {	
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find("{commande._id :  #}", commande.get_id());

		return find;
	}
    
    public static Find request(String table, String field, ObjectId objectId) {	
    	
    	System.out.println("table : " + table);
    	System.out.println("field : " + field);
    	System.out.println("objectId : " + objectId);
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find("{# :  #}", field, objectId);

		return find;
	}
    
    
    public static Distinct distinct(String table, String distinct, String field, ObjectId objectId) {	
    	
    	System.out.println("table : " + table);
    	System.out.println("field : " + field);
    	System.out.println("objectId : " + objectId);
		
		Distinct find = null;
		collec = jongo.getCollection(table);
		find = collec.distinct(distinct).query("{# :  #}", field, objectId);
		
		//System.out.println();

		return find;
	}
    
    public static Find request(String table, String field, ObjectId objectId, String object) {	
    	
    	System.out.println("table : " + table);
    	System.out.println("field : " + field);
    	System.out.println("objectId : " + objectId);
    	System.out.println("object : " + object);
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find("{# :  #}", field, objectId).projection("{# : 1, _id : 0", object);

		return find;
	}
    
    public static Find request(String table, String field, Commande commande) {	
    	
    	System.out.println(field);
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find("{commande :  #}", commande.get_id());

		return find;
	}
    
    public static Find request(String table, Client client) {	
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find("{client._id : #}", client.get_id());

		return find;
	}
    
    public static Find request(String table, Traitement traitement) {	
		
		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find("{traitement : #}", traitement.get_id());

		return find;
	}
	
	public static FindOne request(String table, String field, String valeur) {	
		
		FindOne one = null;
		collec = jongo.getCollection(table);
		one = collec.findOne(String.format("{\"%s\" : \"%s\"}", field, valeur));

		return one;
	}

	public static Find requestAll(String table, String field, String valeur) {

		Find find = null;
		collec = jongo.getCollection(table);
		find = collec.find(String.format("{\"%s\" : \"%s\"}", field, valeur));

		return find;
	}
	
    public static FindOne request(String table, String field1, String valeur1, String field2, String valeur2) {	
		
		FindOne one = null;
		collec = jongo.getCollection(table);
		one = collec.findOne(String.format("{\"%s\" : \"%s\", \"%s\" : \"%s\"}", field1, valeur1, field2, valeur2));

		return one;
	}
	
	public static void insert (String table, Object m) {
		collec = jongo.getCollection(table);
		collec.insert(m);
		
	}
	
	public static Commun save (String table, Commun m) {

		System.out.println("dans mongoaccess");
		System.out.println(jongo);
		collec = jongo.getCollection(table);
		System.out.println("dans mongoaccess getCollection()");

		collec.save(m);

		System.out.println("_id dans MongoAccess : " + m.get_id());

		return m;
		
	}

	public static void drop() {
		collec.drop();
		
	}

	public static void update(String table, Commun c) {
		collec = jongo.getCollection(table);	
		System.out.println("mise a jour _id : " + c.get_id());
		collec.update("{_id : #}", c.get_id()).with(c);
	}


}
