package Data;



import java.awt.event.ActionEvent;



import Jeu.Monopoly;



public class CarreauArgent extends CarreauAction

{



	private int montant;

	public CarreauArgent(String nomCarreau, int numeroCarreau, int montant, Monopoly monopoly){

		super(nomCarreau, numeroCarreau,monopoly);

		this.setMontant(montant);

	}

	public void setMontant(int montant) {

		this.montant = montant;

	}

	public int getMontant(){

		return this.montant;

	}

        
        //Effet d'un évenement selon la case
	public void action(){

		Joueur j =getMonopoly().getJoueurs().getFirst();
		if(getMontant()!=0){
			System.out.println(getNomCarreau()+" : "+getMontant()+"€");
			j.setCash(j.getCash()+getMontant());
		}

	}



}
