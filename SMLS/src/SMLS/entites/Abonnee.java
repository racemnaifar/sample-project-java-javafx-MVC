package SMLS.entites;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;


public class Abonnee {
	
	private Integer numAb;
	private String nom;
	private String prenom;
	private String adrAb;
	private Date dateNais;
	
	public Abonnee(Integer numAb, String nom, String prenom, String adrAb, Date dateNais) {
		super();
		this.numAb = numAb;
		this.nom = nom;
		this.prenom = prenom;
		this.adrAb = adrAb;
		this.dateNais = dateNais;
	}
	
	
	public Integer getNumAb() {
		return numAb;
	}

	public void setNumAb(Integer numAb) {
		this.numAb = numAb;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdrAb() {
		return adrAb;
	}

	public void setAdrAb(String adrAb) {
		this.adrAb = adrAb;
	}

	public Date getDateNais() {
		return dateNais;
	}

	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}


	
	
	
	
	
}
