package models;

public class Settings {
	
	private static String adresse;
	private static String login;
	private static String pass;
	private static String version;
	private static String base;
	private static String key;
	private static String port;
	
	public Settings(){
		this(null, null, null, null);
	}
		
	public Settings(String adresse, String version, String key, String port) {
		Settings.adresse = adresse;
		Settings.version = version;
		Settings.key = key;
		Settings.port = port;
	}

	public static String getAdresse() {
		return adresse;
	}

	public static void setAdresse(String adresse) {
		Settings.adresse = adresse;
	}

	public static String getVersion() {
		return version;
	}

	public static void setVersion(String version) {
		Settings.version = version;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		Settings.key = key;
	}

	public static String getPort() {
		return port;
	}
	public static void setPort(String port) {
		Settings.port = port;
	}
}
