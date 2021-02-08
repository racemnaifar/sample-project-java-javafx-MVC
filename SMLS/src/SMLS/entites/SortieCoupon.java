package SMLS.entites;

import java.sql.Time;
import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SortieCoupon {
	
	private IntegerProperty numSC;
	private ObjectProperty<Date> dateSC;
	private ObjectProperty<Time> heureSC;
	
	public SortieCoupon(Integer numSC, Date dateSC, Time heureSC) {
		super();
		this.numSC = new SimpleIntegerProperty(numSC);
		this.dateSC = new SimpleObjectProperty<Date>(dateSC);
		this.heureSC = new SimpleObjectProperty<Time>(heureSC);
	}

	public final IntegerProperty numSCProperty() {
		return this.numSC;
	}
	

	public final int getNumSC() {
		return this.numSCProperty().get();
	}
	

	public final void setNumSC(final int numSC) {
		this.numSCProperty().set(numSC);
	}
	

	public final ObjectProperty<Date> dateSCProperty() {
		return this.dateSC;
	}
	

	public final Date getDateSC() {
		return this.dateSCProperty().get();
	}
	

	public final void setDateSC(final Date dateSC) {
		this.dateSCProperty().set(dateSC);
	}
	

	public final ObjectProperty<Time> heureSCProperty() {
		return this.heureSC;
	}
	

	public final Time getHeureSC() {
		return this.heureSCProperty().get();
	}
	

	public final void setHeureSC(final Time heureSC) {
		this.heureSCProperty().set(heureSC);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SortieCoupon [numSC=" + numSC + ", dateSC=" + dateSC + ", heureSC=" + heureSC + "]";
	}
	

	
	
	
	
}
