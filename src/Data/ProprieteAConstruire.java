package Data;

import java.util.Scanner;

<<<<<<< HEAD
public class ProprieteAConstruire extends CarreauPropriete {

    private Groupe groupePropriete;
    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int[] loyerMaison;

    public ProprieteAConstruire(int prixAchat, String nomCarreau, int numeroCarreau, Groupe groupePropriete, int[] loyerMaison, int prixMaison, int prixHotel) {
	super(prixAchat, nomCarreau, numeroCarreau);
	this.groupePropriete = groupePropriete;
	this.loyerMaison = loyerMaison;

    }

    public void construire() {
	Joueur j2 = this.getProprietaire();
	Joueur j = j2.getMonopoly().getJoueurs().getFirst();
	Groupe gr = this.getGroupePropriete();
	int nbMaison = this.getNbMaisons();
	boolean estConstructible = true;
	for (ProprieteAConstruire prop : gr.getProprietes()) {
	    Joueur pr = prop.getProprietaire();
	    int nbMais = prop.getNbMaisons();
	    int nbHot = prop.getNbHotels();

	    if (pr != j) {
		estConstructible = false;
	    }
	    if (nbMais < nbMaison && nbHot == 0) {
		estConstructible = false;
	    }
=======

public class ProprieteAConstruire extends CarreauPropriete
{

	private Groupe groupePropriete;
	private int nbMaisons = 0;
	private int nbHotels = 0;
	private int[] loyerMaison;
	
	
	public ProprieteAConstruire(int prixAchat, String nomCarreau, int numeroCarreau, Groupe groupePropriete, int[] loyerMaison, int prixMaison, int prixHotel) 
	{
		super(prixAchat, nomCarreau, numeroCarreau);
		this.groupePropriete = groupePropriete;
		this.loyerMaison = loyerMaison;
		
	}

	public void construire() {
		Joueur j = this.getProprietaire();
		j.getMonopoly().getJoueurs().getFirst();
		Groupe gr = this.getGroupePropriete();
		int nbMaison = this.getNbMaisons();
		boolean estConstructible = true;
		for (ProprieteAConstruire prop : gr.getProprietes()) {
			Joueur pr = prop.getProprietaire();
			int nbMais = prop.getNbMaisons();
			int nbHot = prop.getNbHotels();
			
			if (pr != j) {
				estConstructible = false;
			}
			if (nbMais < nbMaison && nbHot == 0) {
				estConstructible = false;
			}
		}

		if (estConstructible == true) {
			int prix = gr.getPrixMaison();
			int nbHotel = this.getNbHotels();
			int arg = j.getCash();
			Scanner sc = new Scanner(System.in);
			if (nbHotel == 0 && arg >= prix) {
				int nbHotelDispo = j.getMonopoly().getNbHotelsDispo();
				int nbMaisonDispo = j.getMonopoly().getNbMaisonsDispo();
				if (nbMaison == 4) {
					if (nbHotelDispo > 0) {
						System.out.println("Acheter un hotel sur cette propriete ? (oui/non)");
						String reponse;
						do {
							reponse = sc.nextLine();
						} while (reponse != "oui" && reponse != "non");
						
						if (reponse == "oui") {
							j.setCash(arg-prix);
							this.setNbMaisons(0);
							j.getMonopoly().setNbMaisonsDispo(nbMaisonDispo+4);
							this.setNbHotels(1);
							j.getMonopoly().setNbHotelsDispo(nbHotelDispo-1);
						}
					}
				} else {
					if (nbMaisonDispo > 0) {
						System.out.println("Acheter une maison sur cette propriete ? (oui/non)");
						String reponse;
						do {
							reponse = sc.nextLine();
						} while (reponse != "oui" && reponse != "non");
						if (reponse == "oui") {
							j.setCash(arg-prix);
							this.setNbMaisons(nbMaison+1);
							j.getMonopoly().setNbMaisonsDispo(nbMaisonDispo-1);
						}
					}
				}
			}
		}
	}
	
	public Groupe getGroupePropriete() 
	{
		return groupePropriete;
>>>>>>> 3e9100097d3d4e1cd96951dc68d59c04264f0733
	}

	if (estConstructible == true) {
	    int prix = gr.getPrixMaison();
	    int nbHotel = this.getNbHotels();
	    int arg = j.getCash();
	    Scanner sc = new Scanner(System.in);
	    if (nbHotel == 0 && arg >= prix) {
		int nbHotelDispo = j.getMonopoly().getNbHotelsDispo();
		int nbMaisonDispo = j.getMonopoly().getNbMaisonsDispo();
		if (nbMaison == 4) {
		    if (nbHotelDispo > 0) {
			System.out.println("Acheter un hotel sur cette propriete ? (oui/non)");
			String reponse;
			do {
			    reponse = sc.nextLine();
			} while (reponse != "oui" && reponse != "non");

			if (reponse == "oui") {
			    j.setCash(arg - prix);
			    this.setNbMaisons(0);
			    j.getMonopoly().setNbMaisonsDispo(nbMaisonDispo + 4);
			    this.setNbHotels(1);
			    j.getMonopoly().setNbHotelsDispo(nbHotelDispo - 1);
			}
		    }
		} else {
		    if (nbMaisonDispo > 0) {
			System.out.println("Acheter une maison sur cette propriete ? (oui/non)");
			String reponse;
			do {
			    reponse = sc.nextLine();
			} while (reponse != "oui" && reponse != "non");
			if (reponse == "oui") {
			    j.setCash(arg - prix);
			    this.setNbMaisons(nbMaison + 1);
			    j.getMonopoly().setNbMaisonsDispo(nbMaisonDispo - 1);
			}
		    }
		}
	    }
	}
    }

    public Groupe getGroupePropriete() {
	return groupePropriete;
    }

    public void setGroupePropriete(Groupe groupePropriete) {
	this.groupePropriete = groupePropriete;
    }

    public int getNbMaisons() {

	return nbMaisons;
    }

    public void setNbMaisons(int nbMaisons) {
	this.nbMaisons = nbMaisons;
    }

<<<<<<< HEAD
    public int getNbHotels() {
	return nbHotels;
    }

    public void setNbHotels(int nbHotels) {
	this.nbHotels = nbHotels;
    }

    public int[] getLoyerMaison() {
	return loyerMaison;
    }

    public void setLoyerMaison(int[] loyerMaison) {
	this.loyerMaison = loyerMaison;
    }
=======


	
>>>>>>> 3e9100097d3d4e1cd96951dc68d59c04264f0733

}
