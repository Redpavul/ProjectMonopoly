package Data;

import Jeu.Monopoly;
import java.util.Scanner;

public class Compagnie extends CarreauPropriete {

    public Compagnie(int prixAchat, String nomCarreau, int numeroCarreau) {
	super(prixAchat, nomCarreau, numeroCarreau);
	prixAchat = 150;
    }

    //Calcul du loyer d'une compagnie
    public void calculLoyerCompagnie() {
	Joueur jProprio = this.getProprietaire();
	int nb = jProprio.getCompagnies().size();
	Joueur j = monopoly.getJoueurs().getFirst();
	int des = j.getDes();
	int prix;
	if (nb == 1) {
	    prix = des * 4;
	} else {
	    prix = des * 10;
	}
	//System.out.println(jProprio + " possède " + nb + " compagnies, vous avez fait " + des + " aux dés, vous devez donc lui payer " + prix);
	loyer = prix;
	j.setCash(j.getCash() - prix);
	jProprio.setCash(jProprio.getCash() + prix);
    }

    //Permet d'acheter un propriété au joueur qui a lancé l'achat
    public void acheterPropriete(Joueur j){

	int prix;
	boolean bon = true;
	Scanner sca = new Scanner(System.in);
	    prix = this.getPrixAchat();
	    if (j.getCash() >= prix) {
		System.out.println(j.getPositionCourante());
		System.out.println("joueur " + j.getNomJoueur() + " voulez vous acheter la gare " + this.getNomCarreau() + " pour un prix de " + prixAchat + " ? (oui/non)");
		while (bon) {
		    String choix = sca.nextLine();
		    if (choix.contentEquals("oui") || choix.equalsIgnoreCase("o")) {
			bon = false;
			payer(j, prix);
			setProprietaire(j);
			j.getCompagnies().add(this);
		    } else if (choix.contentEquals("non") || choix.equalsIgnoreCase("n")) {
			bon = false;
		    }
		}

	    }
	}   
    
    //Permet de faire payer le loyer au joueur qui est tombé sur une case qui appartient déjà à quelqu'un
    public void payerLoyer(Joueur j) {
	    Joueur j2 = this.getProprietaire();

	    if (j != j2) {
		calculLoyerCompagnie();
		System.out.println("joueur " + j.getNomJoueur() + " vous êtes arrivé sur la " + this.getNomCarreau() + " qui appartiens a " + j2.getNomJoueur() + " vous lui devez " + loyer + "€ ");

	    }

	}

    
}
