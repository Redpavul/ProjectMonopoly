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

    public Joueur getProprietaire() {
	return proprietaire;
    }

    public void setProprietaire(Joueur proprietaire) {
	this.proprietaire = proprietaire;
    }

    public int getLoyerBase() {
	return loyerBase;
    }

    public void setLoyerBase(int loyerBase) {
	this.loyerBase = loyerBase;
    }

    public int getPrixAchat() {
	return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
	this.prixAchat = prixAchat;
    }

    public int getLoyer() {
	return loyer;
    }

    public void setLoyer(int loyer) {
	this.loyer = loyer;
    }

}