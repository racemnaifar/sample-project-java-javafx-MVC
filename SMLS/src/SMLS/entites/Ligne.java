package SMLS.entites;


public class Ligne {
	
	private Integer numLig;
	private String nomter;
	
	public Ligne(Integer numLig, String nomter) {
		super();
		this.numLig = numLig;
		this.nomter = nomter;
	}

	public Integer getNumLig() {
		return numLig;
	}

	public void setNumLig(Integer numLig) {
		this.numLig = numLig;
	}

	public String getNomter() {
		return nomter;
	}

	public void setNomter(String nomter) {
		this.nomter = nomter;
	}

	@Override
	public String toString() {
		return "Ligne [numLig=" + numLig + ", nomter=" + nomter + "]";
	}
	
	
	
	
	
}
