package SMLS.entites;


public class Arret {
	
	private String numAr;
	private Integer numZone;
	private Integer numLig;
	private Integer numSt;
	
	public Arret(String numAr, Integer numZone, Integer numLig, Integer numSt) {
		super();
		this.numAr = numAr;
		this.numZone = numZone;
		this.numLig = numLig;
		this.numSt = numSt;
	}

	public String getNumAr() {
		return numAr;
	}

	public void setNumAr(String numAr) {
		this.numAr = numAr;
	}

	public Integer getNumZone() {
		return numZone;
	}

	public void setNumZone(Integer numZone) {
		this.numZone = numZone;
	}

	public Integer getNumLig() {
		return numLig;
	}

	public void setNumLig(Integer numLig) {
		this.numLig = numLig;
	}

	public Integer getNumSt() {
		return numSt;
	}

	public void setNumSt(Integer numSt) {
		this.numSt = numSt;
	}

	@Override
	public String toString() {
		return "Arret [numAr=" + numAr + ", numZone=" + numZone + ", numLig=" + numLig + ", numSt=" + numSt + "]";
	}
	
	
	
	
	
}
