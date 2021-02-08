package SMLS.entites;

import java.sql.Time;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
	
	private IntegerProperty numBM;
	private ObjectProperty<Date> dateAch;
	private ObjectProperty<Time> heureAch;
	private StringProperty typTic;
	private ObjectProperty<Date> dateET;
	private ObjectProperty<Time> heureET;
	private ObjectProperty<Date> dateST;
	private ObjectProperty<Time> heureST;
	
	public Ticket(Integer numBM, Date dateAch, Time heureAch, String typTic, Date dateET, Time heureET,
			Date dateST, Time heureST) {
		super();
		this.numBM = new SimpleIntegerProperty(numBM);
		this.dateAch = new SimpleObjectProperty<Date>(dateAch);
		this.heureAch = new SimpleObjectProperty<Time>(heureAch);
		this.typTic = new SimpleStringProperty(typTic);
		this.dateET = new  SimpleObjectProperty<Date>(dateET);
		this.heureET = new  SimpleObjectProperty<Time>(heureET);
		this.dateST = new  SimpleObjectProperty<Date>(dateST);
		this.heureST = new  SimpleObjectProperty<Time>(heureST);
	}

	public final IntegerProperty numBMProperty() {
		return this.numBM;
	}
	

	public final int getNumBM() {
		return this.numBMProperty().get();
	}
	

	public final void setNumBM(final int numBM) {
		this.numBMProperty().set(numBM);
	}
	

	public final ObjectProperty<Date> dateAchProperty() {
		return this.dateAch;
	}
	

	public final Date getDateAch() {
		return this.dateAchProperty().get();
	}
	

	public final void setDateAch(final Date dateAch) {
		this.dateAchProperty().set(dateAch);
	}
	

	public final ObjectProperty<Time> heureAchProperty() {
		return this.heureAch;
	}
	

	public final Time getHeureAch() {
		return this.heureAchProperty().get();
	}
	

	public final void setHeureAch(final Time heureAch) {
		this.heureAchProperty().set(heureAch);
	}
	

	public final StringProperty typTicProperty() {
		return this.typTic;
	}
	

	public final String getTypTic() {
		return this.typTicProperty().get();
	}
	

	public final void setTypTic(final String typTic) {
		this.typTicProperty().set(typTic);
	}
	

	public final ObjectProperty<Date> dateETProperty() {
		return this.dateET;
	}
	

	public final Date getDateET() {
		return this.dateETProperty().get();
	}
	

	public final void setDateET(final Date dateET) {
		this.dateETProperty().set(dateET);
	}
	

	public final ObjectProperty<Time> heureETProperty() {
		return this.heureET;
	}
	

	public final Time getHeureET() {
		return this.heureETProperty().get();
	}
	

	public final void setHeureET(final Time heureET) {
		this.heureETProperty().set(heureET);
	}
	

	public final ObjectProperty<Date> dateSTProperty() {
		return this.dateST;
	}
	

	public final Date getDateST() {
		return this.dateSTProperty().get();
	}
	

	public final void setDateST(final Date dateST) {
		this.dateSTProperty().set(dateST);
	}
	

	public final ObjectProperty<Time> heureSTProperty() {
		return this.heureST;
	}
	

	public final Time getHeureST() {
		return this.heureSTProperty().get();
	}
	

	public final void setHeureST(final Time heureST) {
		this.heureSTProperty().set(heureST);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ticket [numBM=" + numBM + ", dateAch=" + dateAch + ", heureAch=" + heureAch + ", typTic=" + typTic
				+ ", dateET=" + dateET + ", heureET=" + heureET + ", dateST=" + dateST + ", heureST=" + heureST + "]";
	}
	
	
	

}
