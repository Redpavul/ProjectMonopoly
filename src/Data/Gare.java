package Data;


public class Gare extends CarreauPropriete
{
	public Gare(int prixAchat, String nomCarreau, int numeroCarreau)
	{
		super(prixAchat, nomCarreau, numeroCarreau);
                prixAchat = 200;
	}
	
	public void calculLoyerGare() {
		Joueur jProprio = this.getProprietaire();
		int nb = jProprio.getGares().size();
		int p = nb * 50;
		System.out.println(jProprio + " est proprietaire de " + nb + " gares, vous devez donc lui payer " + p + " unitées");
		Joueur j = this.getMonopoly().getJoueurs().getFirst();
		int c = j.getCash();
		int total = c - p;
		System.out.println("Il vous restera " + total + " unitées");
		j.getMonopoly().payer(j, p, jProprio); // loyer est inclus
	}
}