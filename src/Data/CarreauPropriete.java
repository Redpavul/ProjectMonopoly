package Data;


public abstract class CarreauPropriete extends Carreau
{

	private Joueur proprietaire;
	private int loyerBase;
	private int prixAchat;
	private int loyer;
	
	public CarreauPropriete(int prixAchat, String nomCarreau, int numeroCarreau)
	{
		super(nomCarreau, numeroCarreau);
		this.prixAchat = prixAchat;
	}

}