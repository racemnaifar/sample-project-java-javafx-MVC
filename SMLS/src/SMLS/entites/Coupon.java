package SMLS.entites;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Coupon {
	private IntegerProperty numCoup;
	private ObjectProperty<Date> dateAchat;
	private ObjectProperty<Date> dateDebVal;
	private ObjectProperty<Date> dateFinVal;
	private StringProperty modePai;
	private StringProperty typCoup;
	private IntegerProperty numZone;
	
	public Coupon(Integer numCoup, Date dateAchat, Date dateDebVal,
			Date dateFinVal, String modePai, String typCoup, Integer numZone) {
		super();
		this.numCoup = new SimpleIntegerProperty(numCoup);
		this.dateAchat = new SimpleObjectProperty<Date>(dateAchat);
		this.dateDebVal = new SimpleObjectProperty<Date>(dateDebVal);
		this.dateFinVal = new SimpleObjectProperty<Date>(dateFinVal);
		this.modePai = new SimpleStringProperty(modePai);
		this.typCoup = new SimpleStringProperty(typCoup);
		this.numZone = new SimpleIntegerProperty(numZone);
	}

	public final IntegerProperty numCoupProperty() {
		return this.numCoup;
	}
	

	public final int getNumCoup() {
		return this.numCoupProperty().get();
	}
	

	public final void setNumCoup(final int numCoup) {
		this.numCoupProperty().set(numCoup);
	}
	

	public final ObjectProperty<Date> dateAchatProperty() {
		return this.dateAchat;
	}
	

	public final Date getDateAchat() {
		return this.dateAchatProperty().get();
	}
	

	public final void setDateAchat(final Date dateAchat) {
		this.dateAchatProperty().set(dateAchat);
	}
	

	public final ObjectProperty<Date> dateDebValProperty() {
		return this.dateDebVal;
	}
	

	public final Date getDateDebVal() {
		return this.dateDebValProperty().get();
	}
	

	public final void setDateDebVal(final Date dateDebVal) {
		this.dateDebValProperty().set(dateDebVal);
	}
	

	public final ObjectProperty<Date> dateFinValProperty() {
		return this.dateFinVal;
	}
	

	public final Date getDateFinVal() {
		return this.dateFinValProperty().get();
	}
	

	public final void setDateFinVal(final Date dateFinVal) {
		this.dateFinValProperty().set(dateFinVal);
	}
	

	public final StringProperty modePaiProperty() {
		return this.modePai;
	}
	

	public final String getModePai() {
		return this.modePaiProperty().get();
	}
	

	public final void setModePai(final String modePai) {
		this.modePaiProperty().set(modePai);
	}
	

	public final StringProperty typCoupProperty() {
		return this.typCoup;
	}
	

	public final String getTypCoup() {
		return this.typCoupProperty().get();
	}
	

	public final void setTypCoup(final String typCoup) {
		this.typCoupProperty().set(typCoup);
	}
	

	public final IntegerProperty numZoneProperty() {
		return this.numZone;
	}
	

	public final int getNumZone() {
		return this.numZoneProperty().get();
	}
	

	public final void setNumZone(final int numZone) {
		this.numZoneProperty().set(numZone);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coupon [numCoup=" + numCoup + ", dateAchat=" + dateAchat + ", dateDebVal=" + dateDebVal
				+ ", dateFinVal=" + dateFinVal + ", modePai=" + modePai + ", typCoup=" + typCoup + ", numZone="
				+ numZone + "]";
	}
	
	
	
	
	



}
