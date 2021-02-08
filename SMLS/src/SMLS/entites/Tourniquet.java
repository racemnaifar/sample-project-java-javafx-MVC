package SMLS.entites;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Tourniquet {
	private IntegerProperty numTour;

	public Tourniquet(Integer numTour) {
		super();
		this.numTour =  new SimpleIntegerProperty(numTour);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tourniquet [numTour=" + numTour + "]";
	}

	

}
