package SMLS.entites;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TarifCoupon {
	
	private IntegerProperty numZone;
	private StringProperty typCoup;
	private FloatProperty prixCoup;
	
	public TarifCoupon(Integer numZone, String typCoup,  Float prixCoup) {
		super();
		this.numZone = new SimpleIntegerProperty(numZone);
		this.typCoup = new SimpleStringProperty(typCoup);
		this.prixCoup = new SimpleFloatProperty(prixCoup);
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
	

	public final StringProperty typCoupProperty() {
		return this.typCoup;
	}
	

	public final String getTypCoup() {
		return this.typCoupProperty().get();
	}
	

	public final void setTypCoup(final String typCoup) {
		this.typCoupProperty().set(typCoup);
	}
	
	
	public final FloatProperty prixCoupProperty() {
		return this.prixCoup;
	}
	

	public final float getPrixCoup() {
		return this.prixCoupProperty().get();
	}
	

	public final void setPrixCoup(final float prixCoup) {
		this.prixCoupProperty().set(prixCoup);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarifCoupon [numZone=" + numZone + ", typCoup=" + typCoup + ", prixCoup="
				+ prixCoup + "]";
	}
	
	
	
	

}
