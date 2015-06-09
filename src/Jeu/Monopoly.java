package Jeu;

import Data.Carreau;
import Data.CarreauArgent;
import Data.CarreauMouvement;
import Data.CarreauPropriete;
import Data.CarreauTirage;
import Data.Compagnie;
import Data.CouleurPropriete;
import Data.Gare;
import Data.Groupe;
import Data.Joueur;
import Data.ProprieteAConstruire;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Monopoly {

	private Interface interf;
	private int nbMaisonsDispo = 32;
	private int nbHotelsDispo = 12;
	private HashMap<String, Groupe> listGroupes = new HashMap();//Contient la liste des groupes
	private Carreau[] listCarreaux = new Carreau[40];
	private LinkedList<Joueur> joueurs;
	private int[] tabChance;
	private int[] tabCaisse;
	private int positionChance = 1;
	private int nbDeCarteChance = 16;
	private int positionCaisse = 1;
	private int nbDeCarteCaisse = 16;
	private boolean carteSortieDePrisonChance = true;
	private boolean carteSortieDePrisonCaisse = true;

	public Monopoly(String dataFilename) {
		setJoueurs(new LinkedList<Joueur>());
		buildGamePlateau(dataFilename);
		//System.out.print("Paquet carte Chance: ");
		this.setTabChance(creerPaquet(this.getNbDeCarteChance()));
		//System.out.print("Paquet carte Caisse: ");
		this.setTabCaisse(creerPaquet(this.getNbDeCarteCaisse()));
		initialiserPartie();
		boucleDeJeu();
		this.setTabChance(creerPaquet(this.getNbDeCarteChance()));
		this.setTabCaisse(creerPaquet(this.getNbDeCarteCaisse()));

	}

	public int[] creerPaquet(int nbCarte) {
		int[] tab3 = new int[nbCarte + 1];
		int[] tab2 = new int[nbCarte + 1];
		for (int a = 1; a <= nbCarte; a++) {//on cr�e un paquet de carte tri�
			tab3[a] = a;
		}
		int alea;
		for (int a = 1; a <= nbCarte; a++) {//on pioche al�atoirement chaque carte 
			//du premier paquer vers un second paquet
			alea = (int) (Math.random() * (nbCarte - a + 1)) + 1;
			tab2[a] = tab3[alea];
			for (int b = alea; b < nbCarte; b++) {
				tab3[b] = tab3[b + 1];
			}
			tab3[nbCarte] = 0;
		}
		/*for(int c = 1;c<=nbCarte;c++){
		  System.out.print(""+c+","+tab2[c]+"|");
		  }
		  System.out.println("");*/
		return (tab2);
	}

	//Fonction permettant de cr�er le plateau de jeu
	private void buildGamePlateau(String dataFilename) {
		//Création des groupes : 1 groupe par couleur
		for (CouleurPropriete c : CouleurPropriete.values()) {
			Groupe g = new Groupe(new ArrayList<ProprieteAConstruire>(), c);//On passe une arrayListe vide, car pour l'instant le groupe ne poss�de pas de propri�t�s
			listGroupes.put(c.toString(), g);

		}
		try {
			ArrayList<String[]> data = readDataFile(dataFilename, ",");

			//cr�ation des diff�rentes cases du plateau
			for (int i = 0; i < data.size(); ++i) {
				String caseType = data.get(i)[0];
				//Propri�t�s
				if (caseType.compareTo("P") == 0) {
					//System.out.println("Propri�t� :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);

					int[] loyers = {Integer.parseInt(data.get(i)[5]), Integer.parseInt(data.get(i)[6]),
						Integer.parseInt(data.get(i)[7]), Integer.parseInt(data.get(i)[8]),
						Integer.parseInt(data.get(i)[9]), Integer.parseInt(data.get(i)[10])};
					int prixMaison = Integer.parseInt(data.get(i)[11]);
					int prixHotel = Integer.parseInt(data.get(i)[12]);
					Groupe a = listGroupes.get(data.get(i)[3]);
					String nomCarreau = data.get(i)[2];
					int numeroCarreau = Integer.parseInt(data.get(i)[1]);
					int prixAchat = Integer.parseInt(data.get(i)[4]);
					
					
					
					ProprieteAConstruire p = new ProprieteAConstruire(prixAchat, nomCarreau, numeroCarreau, a, loyers, prixMaison, prixHotel);

					listCarreaux[numeroCarreau - 1] = p;

				} //Gares
				else if (caseType.compareTo("G") == 0) {
					//System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
					int numeroCarreau = Integer.parseInt(data.get(i)[1]);
					String nomCarreau = data.get(i)[2];
					int prixAchat = Integer.parseInt(data.get(i)[3]);
					Gare gare = new Gare(prixAchat, nomCarreau, numeroCarreau);
					listCarreaux[numeroCarreau - 1] = gare;
				} 
				//Compagnies
				else if (caseType.compareTo("C") == 0) {
					//System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
					int numeroCarreau = Integer.parseInt(data.get(i)[1]);
					String nomCarreau = data.get(i)[2];
					int prixAchat = Integer.parseInt(data.get(i)[3]);
					Compagnie c = new Compagnie(prixAchat, nomCarreau, numeroCarreau);
					listCarreaux[numeroCarreau - 1] = c;
				}
				//CaseTirage
				else if (caseType.compareTo("CT") == 0) {
					//	System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
					int numeroCarreau = Integer.parseInt(data.get(i)[1]);
					String nomCarreau = data.get(i)[2];
					CarreauTirage ct = new CarreauTirage(nomCarreau, numeroCarreau);
					listCarreaux[numeroCarreau - 1] = ct;
				} 

				//Case argent
				else if (caseType.compareTo("CA") == 0) {
					//System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
					int numeroCarreau = Integer.parseInt(data.get(i)[1]);
					String nomCarreau = data.get(i)[2];
					int montant = Integer.parseInt(data.get(i)[3]);
					CarreauArgent ca = new CarreauArgent(nomCarreau, numeroCarreau,montant);
					listCarreaux[numeroCarreau - 1] = ca;
				} 

				//Case mouvement
				else if (caseType.compareTo("CM") == 0) {
					//System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
					int numeroCarreau = Integer.parseInt(data.get(i)[1]);
					String nomCarreau = data.get(i)[2];
					CarreauMouvement cm = new CarreauMouvement(nomCarreau, numeroCarreau);
					listCarreaux[numeroCarreau - 1] = cm;
				} else {
					System.err.println("[buildGamePleateau()] : Invalid Data type");
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("[buildGamePlateau()] : File is not found!");
		} catch (IOException e) {
			System.err.println("[buildGamePlateau()] : Error while reading file!");
		}
		/*for(int i=1;i<=40;i++)
		  {
		  Carreau c = listCarreaux[i];
		  System.out.println(c.getNomCarreau());
		  }*/
	}

	private void initialiserPartie() {

		// Inscription des Joueurs
		int des1;
		int des2;
		Scanner sc = new Scanner(System.in);
		String nom;
		CouleurPropriete couleur;
		int nbJoueur=0;
		System.out.print("Nombre de joueurs : ");
		while(nbJoueur == 0)
		{
			String stringNbJoueur = sc.nextLine();
			char ch= stringNbJoueur.charAt(0);//ch>48  ch<58  1--9
			if((ch>49&&ch<55)&&stringNbJoueur.length()==1){
				nbJoueur=ch-48;
				System.out.println("Ch"+nbJoueur);
			}else{
				System.out.print("Choisissez un nombre entre 2 et 6 : ");
			}
		}
		Joueur[] joueursTemp = new Joueur[nbJoueur];// Tableau contenant les
		// joueurs dans l'ordre de création
		// Création des joueurs et lancers de des

		CouleurPropriete[] coul = CouleurPropriete.values();

		for (int i = 0; i < nbJoueur; i++) {
			des1 = roll();
			des2 = roll();
			couleur = coul[i];
			System.out.println("Nom du joueur n°" + (i + 1) + " : ");
			nom = sc.nextLine();
			System.out.println("Il a obtenu " + des1 + " et " + des2
					+ " soit au total " + (des1 + des2) + ".");
			Joueur j = new Joueur(listCarreaux[0], nom, (des1 + des2), couleur);
			joueursTemp[i] = j;
		}

		triBulleDecroissant(joueursTemp);

		//On ajoute les joueurs dans la liste des joueurs (dans le bon ordre)
		for (Joueur i : joueursTemp)
		{
			joueurs.add(i);
		}
	}

	public static void triBulleDecroissant(Joueur tableau[]) {
		int longueur = tableau.length;
		Joueur tampon;
		boolean permut;

		do {
			// hypoth�se : le tableau est tri�
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				// Teste si 2 �l�ments successifs sont dans le bon ordre ou non
				if (tableau[i].getDes() < tableau[i + 1].getDes()) {
					// s'ils ne le sont pas, on �change leurs positions
					tampon = tableau[i];
					tableau[i] = tableau[i + 1];
					tableau[i + 1] = tampon;
					permut = true;
				}
			}
		} while (permut);
	}

	private void boucleDeJeu() {
		Joueur j;
		while (!isEndGame()) {
			j = joueurs.getFirst();

			jouerUnCoup(j);
			if (j == joueurs.getFirst()) {

				joueurs.addLast(joueurs.pollFirst()); //On remet le joueur à la fin de la LinkedList .
			}
		}

		System.out.println("Le joueur gagnant est : " + joueurs.getFirst().getNomJoueur());
	}

	private void jouerUnCoup(Joueur j) {

		if (j.isPrison()) // Si le joueur est en prison
		{
			this.actionPrison();
		}
		else // Si le joueur n'est pas en prison
		{
			this.lancerDesAvancer();
		}
	}

	public void lancerDesAvancer() {
		Joueur j = joueurs.getFirst();
		afficherInfosJoueurs();
		int des1;
		int des2;
		int des;
		int ancienCar;
		int newCar;
		int compteur = 0;
		do {//Boucle de jeu, à continuer tant que le joueur ne fait pas trois doubles d'affilé

			des1 = roll();
			des2 = roll();
			des = des1 + des2;
			j.setDes(des);
			System.out.println("Lancé de dés : " + des1 + "+" + des2 + " = " + des);
			ancienCar = j.getPositionCourante().getNumeroCarreau();
			newCar = (ancienCar + j.getDes());//numCar = case courante du joueur + son score au dés
			deplacerJoueur(ancienCar, newCar, j); //On met le joueur à sa nouvelle position sur le plateau.
			actionTour(j);
			compteur++;
			actionTour(j);
		} while (des1 == des2 && compteur < 3);

		if (compteur == 3) //Si le joueur fait trois doubles d'affilé, il va en prison
		{
			j.setPositionCourante(this.getListCarreaux()[10]); //Le joueur va en prison
			j.setPrison(true);
			System.out.println("Vous avez fait trois doubles de suite. En prison ! ");
		}
	}

	private void actionPrison() {
		Joueur j = joueurs.getFirst();
		int toursPrison;
		int des1;
		int des2;
		int des;
		String reponse;
		Scanner sc = new Scanner(System.in);
		System.out.println("                    Vous êtes en prison ! ");
		System.out.println("Vous devez faire un double ou utiliser une carte pour en sortir.");
		afficherInfosJoueurs();
		j.setNbToursPrison(j.getNbToursPrison() + 1); //On vérifie le nombre de tours que le joueur a passé en prison
		toursPrison = j.getNbToursPrison();
		System.out.println("Vous êtes en prison depuis " + toursPrison + " tours");

		des1 = roll();
		des2 = roll();
		des = des1 + des2;
		System.out.println("Lancé : " + (des1 + des2));
		if (toursPrison < 3) {
			if (des1 != des2) {
				if (j.getCarteSortieDePrison() > 0) {
					System.out.println("Vous possèdez une carte vous permettant de sortir de prison. L'utiliser ? (oui/non)");
					reponse = sc.nextLine();
					do {
						if (reponse == "oui") {
							j.setPrison(false);
							System.out.println("Vous avez utilisé votre carte et sortez donc de prison.");
						} else if (reponse == "non") {
							System.out.println("Vous restez en prison.");
						} else {
							System.out.println("Mauvaise saisie. Entrez oui ou non.");
						}
					} while (reponse != "oui" || reponse != "non");
				} else {
					System.out.println("Vous restez en prison.");
				}
			} else {
				j.setPrison(false);
				j.setNbToursPrison(0);
				System.out.println("Vous avez fait un double et sortez de prison.");
				lancerDesAvancer();

			}
		}
	}

	private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
		ArrayList<String[]> data = new ArrayList<String[]>();

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = null;
		while ((line = reader.readLine()) != null) {
			data.add(line.split(token));
		}
		reader.close();

		return data;
	}

	public void actionTour(Joueur j) {
		Scanner sc = new Scanner(System.in);
		int choix;
		Object c = j.getPositionCourante();
		ProprieteAConstruire p;
		boolean choix1=false;

		do {
			System.out.println("\n******************************************************************");
			System.out.println("                    Tour de " + j.getNomJoueur() + "      ");
			System.out.println("******************************************************************");
			System.out.println("                                                                 *");
			if (c instanceof CarreauPropriete) {
				CarreauPropriete cp = (CarreauPropriete) this.listCarreaux[j.getPositionCourante().getNumeroCarreau() - 1];
				if (cp.getProprietaire() != null && (j.getCash() >= cp.getPrixAchat()));
				{
					System.out.println("*       1  - Acheter la case sur laquelle on se trouve           *");
					choix1 = true;
				}
			}
			System.out.println("*       2  - Construire			                         *");
			System.out.println("*       3  - Entrer dans le mode triche				 *");
			System.out.println("                                                                 *");
			System.out.println("******************************************************************");
			System.out.println("        0  - Fin du tour                                         ");
			System.out.println("******************************************************************");
			System.out.print("      Votre Choix : ");
			choix = -1;

			while(choix==-1)
			{
				String stringChoix = sc.nextLine();
				char ch= stringChoix.charAt(0);//ch>48  ch<58  1--9
				if((ch>47&&ch<52&&(choix1||ch!=49))&&stringChoix.length()==1){
					choix=ch-48;
					System.out.println("Ch"+choix);
				}
				else if(!choix1 && ch == '1'){
				}else{
					System.out.print("Choisissez un nombre entre 0 et 3 : ");
				}
			}

			switch (choix) {
				case 1: {
							arrivPropriete(j);
							break;
						}

				case 2: {

							j.afficherProprietesJoueur();
							System.out.println("Faites vos choix");
							int i = sc.nextInt();
							p = (ProprieteAConstruire) j.choix(i);

							p.construire(this);
							break;
						}

				case 3: {
							triche(j);
							break;
						}

				default:
						break;
			} // switch
		} while (choix != 0);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void triche(Joueur j) {
		Scanner sc = new Scanner(System.in);
		int choix, numCase;

		System.out.println("Vous êtes en mode triche. Vous pouvez choisir sur quelle case placer le joueur courant et gérer plusieurs choses \n ");
		do {
			System.out.println("\n******************************************************************");
			System.out.println("*                           Mode triche                          *");
			System.out.println("******************************************************************");
			System.out.println("                                                                 *");
			System.out.println("      * 1  - Changer la case sur laquelle le joueur se trouve    *");
			System.out.println("      * 2  - Mettre le joueur en prison                          *");
			System.out.println("      * 3  - Faire passer le joueur par la case départ           *");
			System.out.println("      * 4  - Choisir une carte à faire piocher au joueur         *");
			System.out.println("                                                                 *");
			System.out.println("******************************************************************");
			System.out.println("      * 0  - Quitter                                             *");
			System.out.println("******************************************************************");
			System.out.print("      Votre Choix : ");

			choix = sc.nextInt();
			switch (choix) {
				case 1: {
							System.out.println("Veuillez choisir sur quel numéro de case placer le joueur : ");
							numCase = sc.nextInt();
							deplacerJoueur(numCase, j);
							break;
						}

				case 2: {
							System.out.println("Prison !");
							j.setPrison(true);
							break;
						}

				case 3: {
							j.setCash(j.getCash() + 200);
							System.out.println("Le joueur : " + j.getNomJoueur()
									+ " est passé par la case départ et a donc gagné 200 €");
							break;
						}
				case 4: {

							break;
						}
				default:
						break;
			} // switch
		} while (choix != 0);
	}

	public void deplacerJoueur(int numCase, Joueur joueur) {//Permet de déplacer un joueur d'après un numéro entré par l'utilisateur (mode triche)
		Scanner sc = new Scanner(System.in);
		while (numCase > 40 || numCase < 1) {
			System.out.println("Mauvaise saisie.Veuillez recommencer : ");
			numCase = sc.nextInt();
		}
		joueur.setPositionCourante(listCarreaux[numCase - 1]);

	}

	public void deplacerJoueur(int ancien, int nouveau, Joueur j) {//Permet de déplacer un joueur après un lancer de dés

		//Permet de savoir si le joueur est passé par la case départ
		if (isPasseDepart(ancien, nouveau)) {
			j.setCash(j.getCash() + 200);
			System.out.println(j.getNomJoueur() + "est passé par la case départ et a gagné 200€");
		}

		if (nouveau == 40) {
			j.setPositionCourante(this.getListCarreaux()[nouveau - 1]);
		} else {
			j.setPositionCourante(this.getListCarreaux()[(nouveau % 40) - 1]);
			//On fait un modulo 40 pour placer le joueur sur la bonne case

		}
		System.out.println(j.getNomJoueur() + " se trouve maintenant "
				+ "sur la case n° " + j.getPositionCourante().getNumeroCarreau());
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void payer(Joueur j, int montant) {
		CarreauPropriete c = (CarreauPropriete) this.listCarreaux[j.getPositionCourante().getNumeroCarreau() - 1];
		//CarreauPropriete c = (CarreauPropriete) this.listCarreaux[j.getPositionCourante().getNumeroCarreau() - 1];

		if (c.getProprietaire() == null) {
			if (j.getCash() >= montant) {

				System.out.println("Vous devez " + montant + "€ à la banque !");
				paye(j, montant);
			}
		} else {
			Joueur j2 = c.getProprietaire();
			if (j.getCash() <= montant && j != j2) {
				System.out.println("\nVous n'avez pas assez d'argent pour payer, vous avez perdu ! Vous avez pu payer " + j.getCash() + "€ à " + j2.getNomJoueur() + "\n");
				loyer(j2, j.getCash());
				joueurs.removeFirst();
			} else if (j != j2) {
				paye(j, montant);
				loyer(j2, montant);

			}
		}

	}

	public void paye(Joueur j, int montant) {
		j.setCash(j.getCash() - montant);

	}

	public void payer(Joueur j, int montant, Joueur j2) {
		if (j.getCash() <= montant) {
			System.out.println("\nVous n'avez pas assez d'argent pour payer, vous avez perdu ! Vous avez pu payer " + j.getCash() + "€ à " + j2.getNomJoueur() + "\n");
			loyer(j2, j.getCash());
			joueurs.removeFirst();
		} else if (j != j2) {
			System.out.println("" + j.getNomJoueur() + "Vous devez " + montant + "€ à " + j2.getNomJoueur() + " !");
			paye(j, montant);
			loyer(j2, montant);
		}
	}

	public void loyer(Joueur j, int montant) {
		j.setCash(j.getCash() + montant);

	}

	public void arrivPropriete(Joueur j) {
		int prix;
		boolean bon = true;
		Scanner sca = new Scanner(System.in);
		CarreauPropriete c = (CarreauPropriete) this.listCarreaux[j.getPositionCourante().getNumeroCarreau() - 1];
		if (c.getProprietaire() == null) {

			prix = c.getPrixAchat();
			if (j.getCash() >= prix) {
				System.out.println(j.getPositionCourante());
				System.out.println("joueur " + j.getNomJoueur() + " voulez vous acheter la propriété " + c.getNomCarreau() + " pour un prix de " + prix + " ? (oui/non)");
				while (bon) {
					String choix = sca.nextLine();
					if (choix.contentEquals("oui")) {
						bon = false;
						payer(j, prix);
						c.setProprietaire(j);
						j.getProprietes().add(c);
					} else if (choix.contentEquals("non")) {
						bon = true;
					}
				}

			}
		} else {
			Joueur j2 = c.getProprietaire();
			int montant;
			ProprieteAConstruire p = (ProprieteAConstruire) c;
			if (p.getNbHotels() == 0) {
				montant = p.getLoyerMaison()[p.getNbMaisons()];
			} else {
				montant = p.getLoyerMaison()[5];
			}
			if (j != j2) {
				System.out.println("joueur " + j.getNomJoueur() + " vous êtes arrivé sur le/la " + c.getNomCarreau() + " qui appartiens a " + j2.getNomJoueur() + " vous lui devez " + montant + "€ ");
				payer(j, montant);
			}

		}

	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Get/Set
	public LinkedList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(LinkedList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

	public Interface getInterf() {
		return interf;
	}

	public void setInterf(Interface interf) {
		this.interf = interf;
	}

	public int getNbMaisonsDispo() {
		return nbMaisonsDispo;
	}

	public void setNbMaisonsDispo(int nbMaisonsDispo) {
		this.nbMaisonsDispo = nbMaisonsDispo;
	}

	public int getNbHotelsDispo() {
		return nbHotelsDispo;
	}

	public void setNbHotelsDispo(int nbHotelsDispo) {
		this.nbHotelsDispo = nbHotelsDispo;
	}

	public HashMap<String, Groupe> getListGroupes() {
		return listGroupes;
	}

	public void setListGroupes(HashMap<String, Groupe> listGroupes) {
		this.listGroupes = listGroupes;
	}

	public Carreau[] getListCarreaux() {
		return listCarreaux;
	}

	public void setListCarreaux(Carreau[] listCarreaux) {
		this.listCarreaux = listCarreaux;
	}

	public void afficherInfosJoueurs() {
		for (Joueur i : joueurs) {
			System.out.println(i.getNomJoueur() + " : case n°"
					+ i.getPositionCourante().getNumeroCarreau()
					+ ", " + i.getCash() + " €, couleur " + i.getCouleur());

		}
	}

	public int[] getTabChance() {
		return tabChance;
	}

	public void setTabChance(int[] tabChance) {
		this.tabChance = tabChance;
	}

	public int[] getTabCaisse() {
		return tabCaisse;
	}

	public void setTabCaisse(int[] tabCaisse) {
		this.tabCaisse = tabCaisse;
	}

	public int getPositionChance() {
		return this.positionChance;
	}

	public void setPositionChance(int positionChance) {
		this.positionChance = positionChance;
	}

	public int getNbDeCarteChance() {
		return this.nbDeCarteChance;
	}

	public void setNbDeCarteChance(int nbDeCarteChance) {
		this.nbDeCarteChance = nbDeCarteChance;
	}

	public int getPositionCaisse() {
		return this.positionCaisse;
	}

	public void setPositionCaisse(int positionCaisse) {
		this.positionCaisse = positionCaisse;
	}

	public int getNbDeCarteCaisse() {
		return this.nbDeCarteCaisse;
	}

	public void setNbDeCarteCaisse(int nbDeCarteCaisse) {
		this.nbDeCarteCaisse = nbDeCarteCaisse;
	}

	private boolean isEndGame() {
		return joueurs.size() == 1;//renvoie vrai si il ne reste plus qu'un joueur dans la liste.
	}

	public boolean isPasseDepart(int ancienneCase, int nouvelleCase) {//Permet de savoir si le joueur est passé par la case départ 
		//(retourne vrai si le joueur est passé par la caseDépart)
		return ancienneCase > nouvelleCase;
	}

	public int roll() {
		return (int) (Math.random() * 6) + 1;
	}
}
