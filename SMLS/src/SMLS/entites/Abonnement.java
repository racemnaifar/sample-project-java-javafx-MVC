package SMLS.entites;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.IntegerProperty;

public class Abonnement {
	private Integer numAbon;
	private Date dateDeb;
	private Date dateFin;
	private Integer numAb;
	
	public Abonnement(Integer numAbon, Date dateDeb, Date dateFin, Integer numAb) {
		super();
		this.numAbon = numAbon;
		this.dateDeb = dateDeb;
		this.dateFin = dateFin;
		this.numAb = numAb;
	}

	public Integer getNumAbon() {
		return numAbon;
	}

	public void setNumAbon(Integer numAbon) {
		this.numAbon = numAbon;
	}

	public Date getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(Date dateDeb) {
		this.dateDeb = dateDeb;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getNumAb() {
		return numAb;
	}

	public void setNumAb(Integer numAb) {
		this.numAb = numAb;
	}
	
	
	
}
