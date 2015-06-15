package Data;


public abstract class CarreauPropriete extends Carreau
{

	private Joueur proprietaire;

	protected int prixAchat;
        protected int loyer;

	
    public CarreauPropriete(int prixAchat, String nomCarreau, int numeroCarreau){
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
    
    //Permet d'acheter un propriété au joueur qui a lancé l'achat
    public abstract void acheterPropriete(Joueur j);
    
    //Permet de faire payer le loyer au joueur qui est tombé sur une case qui appartient déjà à quelqu'un
    public abstract void payerLoyer(Joueur j);
    
    //Fait payer à un joueur donner une somme donnée
    public void payer(Joueur j, int montant) {
	
	//CarreauPropriete c = (CarreauPropriete) this.listCarreaux[j.getPositionCourante().getNumeroCarreau() - 1];
    if (this.getProprietaire() == null) {
        if (j.getCash() >= montant) {

		System.out.println("Vous devez " + montant + "€ à la banque !");
		j.setCash(j.getCash()-montant);
	    }
	} else {
	    Joueur j2 = this.getProprietaire();
	    if (j.getCash() <= montant && j != j2) {
		System.out.println("\nVous n'avez pas assez d'argent pour payer, vous avez perdu ! Vous avez pu payer " + j.getCash() + "€ à " + j2.getNomJoueur() + "\n");
                j2.setCash(j2.getCash()+j.getCash());
		monopoly.getJoueurs().removeFirst();
	    } else if (j != j2) {
		j.setCash(j.getCash()-montant);
                j2.setCash(j2.getCash()+montant);

	    }
	}

    }

}