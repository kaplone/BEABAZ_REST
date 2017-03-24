package enums;

public enum Progression {
	
	FAIT_ ("images/coche_ok.png"),
	TODO_ ("images/coche_todo.png"),
	NULL_ ("images/coche_null.png");

    private String f;
	
	Progression(String i) {
		
		f = i;
	}
	
	public String getUsedImage() {
		return f;
	}

}
