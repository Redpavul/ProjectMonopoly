package Data;

import java.util.Scanner;


public class Gare extends CarreauPropriete
{
        private int montant ;
	public Gare(int prixAchat, String nomCarreau, int numeroCarreau)
	{
		super(prixAchat, nomCarreau, numeroCarreau);
                prixAchat = 200;
	}
	
	public void calculLoyerGare() {
		Joueur jProprio = getProprietaire();
		int nb = jProprio.getGares().size();
		int p = nb * 50;
		System.out.println(jProprio + " est proprietaire de " + nb + " gares, vous devez donc lui payer " + p + " €");
		Joueur j = monopoly.getJoueurs().getFirst();
		int c = j.getCash();
		int total = c - p;
                montant = p;
		System.out.println("Il vous restera " + total + " €"); 
		j.setCash(j.getCash()-p);
		jProprio.setCash(jProprio.getCash()+p);
	}
        
        


        
	public void acheterPropriete(Joueur j){
		int prix;
	boolean bon = true;
	Scanner sca = new Scanner(System.in);
	    prix = this.getPrixAchat();
	    if (j.getCash() >= prix) {
		System.out.println(j.getPositionCourante());
		System.out.println("joueur " + j.getNomJoueur() + " voulez vous acheter la gare " +
                        this.getNomCarreau() + " pour un prix de " + prixAchat + " ? (oui/non)");
		while (bon) {
		    String choix = sca.nextLine();
		    if (choix.contentEquals("oui")) {
			bon = false;
			payer(j, prix);
			setProprietaire(j);
			j.getGares().add(this);
		    } else if (choix.contentEquals("non")) {
			bon = true;
		    }
		}

	    }
	} 
	public void payerLoyer(Joueur j) 
	{
	    Joueur j2 = this.getProprietaire();
	    
	    if (j != j2) {
                calculLoyerGare();
		System.out.println("joueur " + j.getNomJoueur() + " vous êtes arrivé sur la " + this.getNomCarreau() + " qui appartiens a " + j2.getNomJoueur() + " vous lui devez " + montant + "€ ");
		
	    }

	}

    }
        
        
