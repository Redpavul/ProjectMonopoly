package Data;


public abstract class CarreauPropriete extends Carreau
{

	private Joueur proprietaire;

	private int prixAchat;

	
	public CarreauPropriete(int prixAchat, String nomCarreau, int numeroCarreau)
	{
		super(nomCarreau, numeroCarreau);
		this.prixAchat = prixAchat;
	}

    public Joueur getProprietaire() {
	return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
	this.proprietaire = proprietaire;
    }

    public int getPrixAchat() {
	return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
	this.prixAchat = prixAchat;
    }

}