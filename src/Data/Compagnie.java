package Data;

import Jeu.Monopoly;


public class Compagnie extends CarreauPropriete
{
	public Compagnie(int prixAchat, String nomCarreau, int numeroCarreau)
	{
		super(prixAchat, nomCarreau, numeroCarreau);
                prixAchat = 150;
        }
	
	public void calculLoyerCompagnie() {
		Joueur jProprio = this.getProprietaire();
		int nb = jProprio.getCompagnies().length;
		Joueur j = this.getMonopoly().getJoueurs().getFirst();
		int des = j.getDes();
		int prix;
		if (nb == 1) {
			prix = des * 4;
		} else {
			prix = des * 10;
		}
		System.out.println(jProprio + " possède " + nb + " compagnies, vous avez fait " + des + " aux dés, vous devez donc lui payer " + prix);
		
		int c = j.getCash();
		j.getMonopoly().payer(j, prix, jProprio); // loyer est inclus
	}
        
}