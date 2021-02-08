package SMLS.entites;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Guichet {
	
	private IntegerProperty numG;
	private StringProperty nomG;
	private StringProperty typeG;
	
	public Guichet(Integer numG, String nomG, String typeG) {
		super();
		this.numG = new SimpleIntegerProperty(numG);
		this.nomG = new SimpleStringProperty(nomG);
		this.typeG = new SimpleStringProperty(typeG);
	}

	public final IntegerProperty numGProperty() {
		return this.numG;
	}
	

	public final int getNumG() {
		return this.numGProperty().get();
	}
	

	public final void setNumG(final int numG) {
		this.numGProperty().set(numG);
	}
	

	public final StringProperty nomGProperty() {
		return this.nomG;
	}
	

	public final String getNomG() {
		return this.nomGProperty().get();
	}
	

	public final void setNomG(final String nomG) {
		this.nomGProperty().set(nomG);
	}
	

	public final StringProperty typeGProperty() {
		return this.typeG;
	}
	

	public final String getTypeG() {
		return this.typeGProperty().get();
	}
	

	public final void setTypeG(final String typeG) {
		this.typeGProperty().set(typeG);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Guichet [numG=" + numG + ", nomG=" + nomG + ", typeG=" + typeG + "]";
	}
	
	
	
	

}
