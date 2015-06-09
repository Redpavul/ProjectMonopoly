package Data;



import java.awt.event.ActionEvent;



import Jeu.Monopoly;



public class CarreauArgent extends CarreauAction

{



	private int montant;

	public CarreauArgent(String nomCarreau, int numeroCarreau, int montant)

	{

		super(nomCarreau, numeroCarreau);

		this.setMontant(montant);

	}

	public Monopoly getMonopoly() {

		return monopoly;

	}

	public void setMonopoly(Monopoly monopoly) {

		this.monopoly = monopoly;

	}

	public void setMontant(int montant) {

		this.montant = montant;

	}

	public int getMontant(){

		return this.montant;

	}

	public void action(){

		Joueur j =getMonopoly().getJoueurs().getFirst();

		j.setCash(j.getCash()+getMontant());

	}

}
