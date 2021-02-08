package SMLS.entites;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Utilisateur {
	private StringProperty nomUtil;
	private StringProperty mp;
	
	
	public Utilisateur(String nomUtil, String mp) {
		this.nomUtil = new SimpleStringProperty(nomUtil);
		this.mp = new SimpleStringProperty(mp);
	}

	public StringProperty nomUtilProperty() {
		return this.nomUtil;
	}
	
	public String getNomUtil() {
		return this.nomUtilProperty().get();
	}
	
	public void setNomUtil(final String nomUtil) {
		this.nomUtilProperty().set(nomUtil);
	}
	
	public StringProperty mpProperty() {
		return this.mp;
	}
	
	public String getMp() {
		return this.mpProperty().get();
	}
	
	public void setMp(final String mp) {
		this.mpProperty().set(mp);
	}
	
	
	

}
