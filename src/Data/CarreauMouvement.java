package Data;

import Jeu.Monopoly;

public class CarreauMouvement extends CarreauAction
{
	public CarreauMouvement(String nomCarreau, int numeroCarreau,Monopoly monopoly)
	{
		super(nomCarreau, numeroCarreau, monopoly);
	}
	
	
	
	public void action(){
	    
	    Joueur j = getMonopoly().getJoueurs().getFirst();
	    
	    j.isPrison();
	}
}