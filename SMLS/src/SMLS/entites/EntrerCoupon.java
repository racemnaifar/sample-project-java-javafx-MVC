package SMLS.entites;

import java.sql.Time;
import java.util.Date;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class EntrerCoupon {

	private IntegerProperty numEC;
	private ObjectProperty<Date> dateEC;
	private ObjectProperty<Time> heureEC;
	
	public EntrerCoupon(Integer numEC, Date dateEC, Time heureEC) {
		super();
		this.numEC = new SimpleIntegerProperty(numEC);
		this.dateEC = new SimpleObjectProperty<Date>(dateEC);
		this.heureEC = new SimpleObjectProperty<Time>(heureEC);
	}
	public final IntegerProperty numECProperty() {
		return this.numEC;
	}
	
	public final int getNumEC() {
		return this.numECProperty().get();
	}
	
	public final void setNumEC(final int numEC) {
		this.numECProperty().set(numEC);
	}
	
	public final ObjectProperty<Date> dateECProperty() {
		return this.dateEC;
	}
	
	public final Date getDateEC() {
		return this.dateECProperty().get();
	}
	
	public final void setDateEC(final Date dateEC) {
		this.dateECProperty().set(dateEC);
	}
	
	public final ObjectProperty<Time> heureECProperty() {
		return this.heureEC;
	}
	
	public final Time getHeureEC() {
		return this.heureECProperty().get();
	}
	
	public final void setHeureEC(final Time heureEC) {
		this.heureECProperty().set(heureEC);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntrerCoupon [numEC=" + numEC + ", dateEC=" + dateEC + ", heureEC=" + heureEC + "]";
	}
	
	
	
	
}
