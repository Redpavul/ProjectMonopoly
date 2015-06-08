package Data;

public class CarreauArgent extends CarreauAction
{

	private int montant;
	public CarreauArgent(String nomCarreau, int numeroCarreau)
	{
		super(nomCarreau, numeroCarreau);
	}
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}


}