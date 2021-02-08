package SMLS.entites;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TarifTicket {
	
	private IntegerProperty numZone;
	private StringProperty typTic;
	private FloatProperty prixTic;
	
	public TarifTicket(Integer numZone, String typTic,  Float prixTic) {
		super();
		this.numZone = new SimpleIntegerProperty(numZone);
		this.typTic = new SimpleStringProperty(typTic);
		this.prixTic = new SimpleFloatProperty(prixTic);
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
	

	public final StringProperty typTicProperty() {
		return this.typTic;
	}
	

	public final String getTypTic() {
		return this.typTicProperty().get();
	}
	

	public final void setTypTic(final String typTic) {
		this.typTicProperty().set(typTic);
	}
		

	public final FloatProperty prixTicProperty() {
		return this.prixTic;
	}
	

	public final float getPrixTic() {
		return this.prixTicProperty().get();
	}
	

	public final void setPrixTic(final float prixTic) {
		this.prixTicProperty().set(prixTic);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarifTicket [numZone=" + numZone + ", typTic=" + typTic + ",  prixTic=" + prixTic
				+ "]";
	}
	
	
	

	
}
