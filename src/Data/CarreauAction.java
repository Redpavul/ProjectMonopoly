package Data;

import Jeu.Monopoly;

public abstract class CarreauAction extends Carreau
{
	public CarreauAction(String nomCarreau, int numeroCarreau, Monopoly monopoly)	{
		super(nomCarreau, numeroCarreau);
		   setMonopoly(monopoly);
	}
	
        //Active les événements en fonctions de la case Action où l'on se trouve
        public abstract void action();
}
