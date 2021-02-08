package SMLS.entites;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Station {
	private Integer numSt;
	private String nomSt;
	
	public Station(Integer numSt, String nomSt) {
		super();
		this.numSt = numSt;
		this.nomSt = nomSt;
	}

	public Integer getNumSt() {
		return numSt;
	}

	public void setNumSt(Integer numSt) {
		this.numSt = numSt;
	}

	public String getNomSt() {
		return nomSt;
	}

	public void setNomSt(String nomSt) {
		this.nomSt = nomSt;
	}

	@Override
	public String toString() {
		return "Station [numSt=" + numSt + ", nomSt=" + nomSt + "]";
	}
	
	

}
