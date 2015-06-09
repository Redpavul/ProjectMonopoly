package Data;

import java.util.Scanner;



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

	public void construire(Monopoly monopoly) {
		Joueur j = monopoly.getJoueurs().getFirst();
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
				int nbHotelDispo = monopoly.getNbHotelsDispo();
				int nbMaisonDispo = monopoly.getNbMaisonsDispo();
				if (nbMaison == 4) {
					if (nbHotelDispo > 0) {
						System.out.println("Acheter un hotel sur cette propriete ? (oui/non)");
						String reponse;
						do {
							reponse = sc.nextLine();
						} while (!reponse.equals("oui")  && !reponse.equals("non"));

						if (reponse.equals("oui")) {
							j.setCash(arg-prix);
							this.setNbMaisons(0);
							monopoly.setNbMaisonsDispo(nbMaisonDispo+4);
							this.setNbHotels(1);
							monopoly.setNbHotelsDispo(nbHotelDispo-1);
							System.out.println("Vous avez construit un hotel");
						} else {
							System.out.println("vous n'avez pas construit d'hotel");
						}
					}
				} else {
					if (nbMaisonDispo > 0) {
						System.out.println("Cette propriété possède " + this.getNbMaisons() + " maisons");
						System.out.println("Acheter une maison sur cette propriete ? (oui/non)");
						String reponse;
						do {
							reponse = sc.nextLine();
						} while (!reponse.equals("oui")  && !reponse.equals("non"));
						if (reponse.equals("oui")) {
							j.setCash(arg-prix);
							this.setNbMaisons(nbMaison+1);
							monopoly.setNbMaisonsDispo(nbMaisonDispo-1);
							System.out.println("Vous avez construit une maison");
						} else {
							System.out.println("vous n'avez pas construit de maison");
						}
					}
				}
			} else {
				System.out.println("Vous n'avez pas le droit de construire");
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




}
