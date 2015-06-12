package Jeu;

import Data.Carreau;
import Data.CarreauAction;
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
		    listGroupes.remove(a);
		    String nomCarreau = data.get(i)[2];
		    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
		    int prixAchat = Integer.parseInt(data.get(i)[4]);

		    ProprieteAConstruire p = new ProprieteAConstruire(prixAchat, nomCarreau, numeroCarreau, a, loyers, prixMaison, prixHotel);

		    a.getProprietes().add(p);
		    listGroupes.put(data.get(i)[3], a);

		    listCarreaux[numeroCarreau - 1] = p;

		} //Gares
		else if (caseType.compareTo("G") == 0) {
		    //System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
		    String nomCarreau = data.get(i)[2];
		    int prixAchat = Integer.parseInt(data.get(i)[3]);
		    Gare gare = new Gare(prixAchat, nomCarreau, numeroCarreau);
		    listCarreaux[numeroCarreau - 1] = gare;
		} //Compagnies
		else if (caseType.compareTo("C") == 0) {
		    //System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
		    String nomCarreau = data.get(i)[2];
		    int prixAchat = Integer.parseInt(data.get(i)[3]);
		    Compagnie c = new Compagnie(prixAchat, nomCarreau, numeroCarreau);
		    listCarreaux[numeroCarreau - 1] = c;
		} //CaseTirage
		else if (caseType.compareTo("CT") == 0) {
		    //	System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
		    String nomCarreau = data.get(i)[2];
		    CarreauTirage ct = new CarreauTirage(nomCarreau, numeroCarreau, this);
		    listCarreaux[numeroCarreau - 1] = ct;
		} //Case argent
		else if (caseType.compareTo("CA") == 0) {
		    //System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
		    String nomCarreau = data.get(i)[2];
		    int montant = Integer.parseInt(data.get(i)[3]);
		    CarreauArgent ca = new CarreauArgent(nomCarreau, numeroCarreau, montant, this);
		    listCarreaux[numeroCarreau - 1] = ca;
		} //Case mouvement
		else if (caseType.compareTo("CM") == 0) {
		    //System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
		    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
		    String nomCarreau = data.get(i)[2];
		    CarreauMouvement cm = new CarreauMouvement(nomCarreau, numeroCarreau, this);
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
	String changement;
	CouleurPropriete couleur;
	int nbJoueur = 0;
	String stringNbJoueur;
	char ch;//Variable servant � stocker temporairement la r�ponse de l'utilisateur

	System.out.println("\n****************************************************************");
	System.out.println("                                                                ");
	System.out.println("                         MONOPOLY                               ");
	System.out.println("                                                                ");
	System.out.println("****************************************************************\n\n");
	System.out.print("\tNombre de joueurs : ");
	while (nbJoueur == 0)//Tant que la variable nbJoueur n'a pas �t� modifi�e, on continue la boucle
	{
	    stringNbJoueur = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
	    if (stringNbJoueur.length() == 1) {
		ch = stringNbJoueur.charAt(0);/*ch contient le premier caract�re entr�. 
		 S'il ne se trouve pas entre 49 et  54 (code ascii), 
		 c'est que l'utilisateur a rentr� une valeur qui n'est pas valable.*/

		if ((ch > 49 && ch < 55)) {
		    nbJoueur = ch - 48;
		} else {
		    System.out.print("\tChoisissez un nombre entre 2 et 6 : ");
		}
	    } else {
		System.out.print("\tChoisissez un nombre entre 2 et 6 : ");
	    }

	}

	Joueur[] joueursTemp = new Joueur[nbJoueur];/* Tableau contenant les
	 joueurs dans l'ordre de cr�ation*/

	CouleurPropriete[] coul = CouleurPropriete.values();//On d�clare un tableau pour assigner une couleur aux joueurs

	//On initialise le tableau, en v�rifiant que le nom ne d�passe pas 10 caract�res (utile pour l'iHM)
	System.out.println("Vous allez devoir entrer le nom des joueurs. \nL'ordre du tour se fera en fonction de leur score au dés. \nEntrez les noms des joueurs : \n");
	for (int i = 0; i < nbJoueur; i++) {
	    des1 = roll();
	    des2 = roll();
	    couleur = coul[i];
	    System.out.print("Nom du joueur n°" + (i + 1) + " : ");
	    changement = sc.next();
	    if (changement.length() > 10) {
		nom = changement.substring(0, 10); // Maximum de dix caract�res pour le log en IHM
	    } else {
		nom = changement;
	    }
	    System.out.println(changement + " lance les dés et obtient un " + des1 + " et un " + des2
		    + " soit au total " + (des1 + des2) + ".");
	    System.out.println("Il aura comme couleur " + couleur.toStringCouleur() + "\n");
	    Joueur j = new Joueur(listCarreaux[0], nom, couleur);
	    j.setDes(des1 + des2);
	    joueursTemp[i] = j;

	}

	triBulleDecroissant(joueursTemp); //On trie la liste des joueurs selon leur score aux d�s

	//On ajoute les joueurs dans la liste des joueurs (dans le bon ordre)
	System.out.println("Les joueurs joueront dans cet ordre :  ");
	for (Joueur i : joueursTemp) {
	    joueurs.add(i);
	    System.out.print(i.getNomJoueurCouleur() + "\t");
	}

    }

    public void triBulleDecroissant(Joueur tableau[]) {//Fonction permettant de trier le tableau des joueurs
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

    //Fonction servant � faire la boucle de jeu : continue tant que deux joueurs n'ont pas perdu
    private void boucleDeJeu() {
	Joueur j;
	while (!isEndGame()) {
	    j = joueurs.getFirst();

	    jouerUnCoup(j);
	    if (j == joueurs.getFirst()) {

		joueurs.addLast(joueurs.pollFirst()); //On remet le joueur à la fin de la LinkedList .
	    }
	}

	System.out.println("\tLe joueur gagnant est : " + joueurs.getFirst().getNomJoueur());
    }

    private void jouerUnCoup(Joueur j) {
	Scanner sc = new Scanner(System.in);
	String choix;

	System.out.println("\n\n******************************************************************");
	System.out.println("                    Tour de " + j.getNomJoueurCouleur() + "      ");
	System.out.println("******************************************************************\n");
	System.out.println("Entrer dans le mode Scenario ? (oui/non)");

	do {
	    choix = sc.nextLine();
	    if (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non") && !choix.equalsIgnoreCase("o") && !choix.equalsIgnoreCase("n") ) {
		System.out.println("Veuillez entrer oui ou non : ");
	    }
	} while (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non") && !choix.equalsIgnoreCase("o") && !choix.equalsIgnoreCase("n"));
	if (choix.equalsIgnoreCase("oui") || choix.equalsIgnoreCase("o")) {
	    triche(j);
	    if (j.getPositionCourante() instanceof CarreauAction) {
		((CarreauAction) j.getPositionCourante()).action();
	    }
	    if (j.isPrison()) {
		actionPrison();
	    } else {

		actionTour(j);
	    }
	} else if (j.isPrison()) // Si le joueur est en prison
	{
	    this.actionPrison();
	} else // Si le joueur n'est pas en prison
	{
	    this.lancerDesAvancer();
	}

    }

    public void lancerDesAvancer() {
	Joueur j = joueurs.getFirst();
	//afficherInfosJoueurs();
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
	    System.out.println(j.getNomJoueurCouleur() + " lance les des : " + des1 + "+" + des2 + " = " + des);
	    if (des1 == des2) {
		System.out.println("Vous avez fait un double !");
	    }
	    ancienCar = j.getPositionCourante().getNumeroCarreau();
	    newCar = (ancienCar + j.getDes());//numCar = case courante du joueur + son score au dés
	    deplacerJoueur(ancienCar, newCar, j); //On met le joueur a sa nouvelle position sur le plateau.
	    if (j.getPositionCourante() instanceof CarreauAction) {
		((CarreauAction) j.getPositionCourante()).action();
	    }
	    actionTour(j);
	    compteur++;
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
	String choix;
	ProprieteAConstruire p;
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
	System.out.println("Lancé : " + des);
	if (toursPrison < 3) {
	    if (des1 != des2) {
		if (j.getCarteSortieDePrison() > 0) {
		    System.out.println("Vous possèdez une carte vous permettant de sortir de prison. L'utiliser ? (oui/non)");
		    reponse = sc.nextLine();
		    do {
			if (reponse.equals("oui") || reponse.equalsIgnoreCase("o")) {
			    j.setPrison(false);
			    System.out.println("Vous avez utilisé votre carte et sortez donc de prison.");
			    j.setCarteSortieDePrison(j.getCarteSortieDePrison() - 1);
			    if(getCarteSortieDePrisonCaisse()){
			    	setCarteSortieDePrisonChance(true);
			    }else{setCarteSortieDePrisonCaisse(true);}
			} else if (reponse.equals("non") || reponse.equalsIgnoreCase("n")) {
			    System.out.println("Vous restez en prison.");
			    if (!j.getProprietes().isEmpty()) {
				System.out.println("Voulez vous construire?(oui/non)");
				do {
				    choix = sc.nextLine();
				    if (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non") && !choix.equalsIgnoreCase("o") && !choix.equalsIgnoreCase("n")) {
					System.out.println("Veuillez entrer oui ou non : ");
				    }
				} while (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non") && !choix.equalsIgnoreCase("o") && !choix.equalsIgnoreCase("n"));
				j.afficherProprietesJoueur();
				System.out.print("Faites vos choix : ");
				int longueur = 0;
				for (ProprieteAConstruire p1 : j.getProprietes()) {
					longueur++;
				}
				char ch = 48;
				char ch2 = 48;
				int i = -1;
				String stringI="11";
				while (i <= 0 || i>longueur)
				{
					if((i==0 || i>longueur)&&((ch > 47 && ch < 57 && ch2 > 47 && ch2 < 57 && stringI.length() <= 2 && stringI.length() >= 1))){System.out.print("Choisissez un nombre valide : ");}
				    stringI = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
				    if (stringI.length() == 1 || stringI.length() == 2) {
					ch = stringI.charAt(0);
					ch2 = 48;
					if(stringI.length()==2){ch2=stringI.charAt(1);}
					if ((ch > 47 && ch < 57) && ch2 > 47 && ch2 < 57) {
						if(stringI.length()==2){
							i = (ch2 - 48)+10*(ch-48);
						}else{
							i = ch - 48;
						}
					} else {
					    System.out.print("Choisissez un nombre valide : ");
					}
				    } else {
					System.out.print("Choisissez un nombre valide : ");
				    }

				}
				p = (ProprieteAConstruire) j.choix(i);
				p.construire(this);
			    }
			} else {
			    System.out.println("Mauvaise saisie. Entrez oui ou non.");
			}
		    } while (!reponse.equals("oui") && !reponse.equals("non") && !reponse.equalsIgnoreCase("o") && !reponse.equalsIgnoreCase("n"));
		} else {
		    System.out.println("Vous restez en prison.");
		    if (!j.getProprietes().isEmpty()) {
			System.out.println("Voulez vous construire?(oui/non)");
			do {
			    choix = sc.nextLine();
			    if (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non") && !choix.equalsIgnoreCase("o") && !choix.equalsIgnoreCase("n")) {
				System.out.println("Veuillez entrer oui ou non : ");
			    }
			} while (!choix.equalsIgnoreCase("oui") && !choix.equalsIgnoreCase("non") && !choix.equalsIgnoreCase("o") && !choix.equalsIgnoreCase("n"));
			j.afficherProprietesJoueur();
			System.out.print("Faites vos choix : ");
			int longueur = 0;
			for (ProprieteAConstruire p1 : j.getProprietes()) {
				longueur++;
			}
			int i = -1;
			char ch=48; 
			char ch2=48;
			String stringI = "11";
				while (i <= 0 || i>longueur )
				{
				if((i==0 || i>longueur)&&((ch > 47 && ch < 57 && ch2 > 47 && ch2 < 57 && stringI.length() <= 2 && stringI.length() >= 1))){System.out.print("Choisissez un nombre valide : ");}
			    stringI = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
			    if (stringI.length() == 1 || stringI.length()==2) {
				ch = stringI.charAt(0);

				ch2 = 48;
				if(stringI.length()==2){ch2=stringI.charAt(1);}
				if ((ch > 47 && ch < 57) && ch2 > 47 && ch2 < 57) {
					if(stringI.length()==2){
						i = (ch2 - 48)+10*(ch-48);
					}else{
						i = ch - 48;
					}
				} else {
				    System.out.print("Choisissez un nombre valide : ");
				}
			    } else {
				System.out.print("Choisissez un nombre valide : ");
			    }

			}
			p = (ProprieteAConstruire) j.choix(i);
			p.construire(this);
		    }
		}
	    } else {
		j.setPrison(false);
		j.setNbToursPrison(0);
		System.out.println("Vous avez fait un double et sortez de prison.");
		lancerDesAvancer();

	    }
	} else {
	    j.setPrison(false);
	    j.setNbToursPrison(0);
	    j.setCash(j.getCash() - 50);
	    System.out.println("Vous avez passé plus de 3 tours en prison. Vous devez 50€ à la banque et sortez de prison.");
	    lancerDesAvancer();

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
	ProprieteAConstruire p;
	CarreauPropriete cp;
	afficherInfosJoueurs();
	do {
	    System.out.println("\n\t1  - Acheter la case sur laquelle on se trouve");
	    System.out.println("\t2  - Construire\n");
	    System.out.println("******************************************************************");
	    System.out.println("\t0  - Quitter");
	    System.out.println("******************************************************************");
	    System.out.print("\tVotre Choix : ");
	    choix = -1;
	    String stringChoix;
	    char ch=47;
	    while (choix == -1)//Tant que la variable nbJoueur n'a pas �t� modifi�e, on continue la boucle
	    {
		stringChoix = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
		if (stringChoix.length() == 1) {
		    ch = stringChoix.charAt(0);/*ch contient le premier caract�re entr�. 
		     S'il ne se trouve pas entre 49 et  54 (code ascii), 
		     c'est que l'utilisateur a rentr� une valeur qui n'est pas valable.*/

		    if ((ch > 47 && ch < 51)) {
			choix = ch - 48;
		    } else {
			System.out.print("\tChoisissez un nombre entre 0 et 2 : ");
		    }
		} else {
		    System.out.print("\tChoisissez un nombre entre 0 et 2 : ");
		}

	    }
	    switch (choix) {
		case 1: {
		    if (j.getPositionCourante() instanceof CarreauPropriete) {
			if (((CarreauPropriete) j.getPositionCourante()).getProprietaire() == null) {
			    ((CarreauPropriete) j.getPositionCourante()).acheterPropriete(j);
			}
		    } else {
			System.out.println("Vous ne pouvez pas acheter cette case ! ");
		    }

		    break;
		}

		case 2: {
		    int taille = j.afficherProprietesJoueur();
		    if (taille != 0) {
			System.out.print("Faites vos choix : ");
			int longueur = 0;
			for (ProprieteAConstruire p1 : j.getProprietes()) {
				longueur++;
			}
			char ch2 = 48;
			int i = -1;
			String stringI="11";
			while (i <= 0 || i>longueur){
				if((i==0 || i>longueur)&&((ch > 47 && ch < 57 && ch2 > 47 && ch2 < 57 && stringI.length() <= 2 && stringI.length() >= 1))){System.out.print("Choisissez un nombre valide : ");}
			    stringI = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
			    if (stringI.length() == 1 || stringI.length() == 2) {
				ch = stringI.charAt(0);

				ch2 = 48;
				if(stringI.length()==2){ch2=stringI.charAt(1);}
				if ((ch > 47 && ch < 57) && ch2 > 47 && ch2 < 57) {
					if(stringI.length()==2){
						i = (ch2 - 48)+10*(ch-48);
					}else{
						i = ch - 48;
					}
				} else {
				    System.out.print("Choisissez un nombre valide : ");
				}
			    } else {
				System.out.print("Choisissez un nombre valide : ");
			    }

			}
			p = (ProprieteAConstruire) j.choix(i);
			p.construire(this);
		    } else {
			System.out.println("Vous ne pouvez pas construire sur cette case"); // Soit carte chance, caisse communauté, soit aucune propriété possèdée
		    }
		    break;

		}
		default:
		    break;
	    } // switch

	    if (j.getPositionCourante() instanceof CarreauPropriete) {
		cp = (CarreauPropriete) j.getPositionCourante();
		if (cp.getProprietaire() != null && cp.getProprietaire() != j) {
		    cp.payerLoyer(j);
		}

	    }

	} while (choix != 0);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void triche(Joueur j) {
	Scanner sc = new Scanner(System.in);
	int numCase;
	int choix ;
	String reponse, rep;
	int i = 1;
	char ch;
	CarreauTirage CT = new CarreauTirage(null, 15, this);

	do {
	    choix = -1;
	    System.out.println("\n******************************************************************");
	    System.out.println("                         Mode Scenario");
	    System.out.println("******************************************************************\n");
	    System.out.println("\t1  - Changer la case sur laquelle le joueur se trouve");
	    System.out.println("\t2  - Mettre le joueur en prison");
	    System.out.println("\t3  - Faire passer le joueur par la case départ");
	    System.out.println("\t4  - Choisir une carte à faire piocher au joueur");
	    System.out.println("\t5  - Avoir toutes les propriétés d'un groupe\n");
	    System.out.println("******************************************************************");
	    System.out.println("\t0  - Quitter");
	    System.out.println("******************************************************************");
	    System.out.print("\tVotre Choix : ");

	    //Tant que la variable nbJoueur n'a pas �t� modifi�e, on continue la boucle
	    do{
		
		rep = sc.nextLine();//On r�cup�re la r�ponse de l'utilisateur
		if (rep.length() == 1) {
		    ch = rep.charAt(0);/*ch contient le premier caract�re entr�. 
		     S'il ne se trouve pas entre 49 et  54 (code ascii), 
		     c'est que l'utilisateur a rentr� une valeur qui n'est pas valable.*/

		    if ((ch > 47 && ch < 54)) {
			
			choix = ch - 48;
		    } else {
			System.out.print("\tChoisissez un nombre entre 0 et 5 : ");
		    }
		} else {
		    System.out.print("\tChoisissez un nombre entre 0 et 5 : ");
		}

	    }while (choix < 0 || choix >5);

	    switch (choix) {
		case 1: {
		    System.out.println("Veuillez choisir sur quel numéro de case placer le joueur : ");
			int longueur = 40;
			numCase = -1;
			ch=48; 
			char ch2=48;
			String stringI = "11";
			while (numCase <= 0 || numCase>longueur ){
				if((numCase==0 || numCase>longueur)&&((ch > 47 && ch < 57 && ch2 > 47 && ch2 < 57 && stringI.length() <= 2 && stringI.length() >= 1))){System.out.print("Choisissez un nombre valide : ");}
			    stringI = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
			    if (stringI.length() == 1 || stringI.length()==2) {
				ch = stringI.charAt(0);

				ch2 = 48;
				if(stringI.length()==2){ch2=stringI.charAt(1);}
				if ((ch > 47 && ch < 57) && ch2 > 47 && ch2 < 57) {
					if(stringI.length()==2){
						numCase = (ch2 - 48)+10*(ch-48);
					}else{
						numCase = ch - 48;
					}
				} else {
				    System.out.print("Choisissez un nombre valide : ");
				}
			    } else {
				System.out.print("Choisissez un nombre valide : ");
			    }

			}
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
		    System.out.println("Le joueur : " + j.getNomJoueurCouleur()
			    + " est passé par la case départ et a donc gagné 200 €");
		    break;
		}
		case 4: {
		    do {
		    System.out.print("Choisir parmi les cartes communauté ou chance (entrez communauté ou chance) : ");
			    
			reponse = sc.nextLine();
			
		    } while (!reponse.equalsIgnoreCase("communauté") && !reponse.equalsIgnoreCase("chance"));

		    if (reponse.equalsIgnoreCase("communauté")) {
			CT.afficherCommu();
			System.out.print("Veuillez entrer un chiffre entre 1 et 16 : ");
			do {
				int longueur = 16;
				choix = -1;
				ch=48; 
				char ch2=48;
				String stringI = "11";
				while (choix <= 0 || choix>longueur )
				{
					if((choix==0 || choix>longueur)&&((ch > 47 && ch < 57 && ch2 > 47 && ch2 < 57 && stringI.length() <= 2 && stringI.length() >= 1))){System.out.print("Choisissez un nombre valide : ");}
				    stringI = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
				    if (stringI.length() == 1 || stringI.length()==2) {
					ch = stringI.charAt(0);

					ch2 = 48;
					if(stringI.length()==2){ch2=stringI.charAt(1);}
					if ((ch > 47 && ch < 57) && ch2 > 47 && ch2 < 57) {
						if(stringI.length()==2){
							choix = (ch2 - 48)+10*(ch-48);
						}else{
							choix = ch - 48;
						}
					} else {
					    System.out.print("Choisissez un nombre valide : ");
					}
				    } else {
					System.out.print("Choisissez un nombre valide : ");
				    }

				}
			    if (choix < 1 && choix > 16) {
				System.out.print("Veuillez entrer un chiffre entre 1 et 16 : ");
			    }
			} while (choix < 1 && choix > 16);
			CT.effetCaisse(choix);
		    } else {
			CT.afficherChance();
			System.out.print("Veuillez entrer un chiffre entre 1 et 16 : ");
			do {
				int longueur = 16;
				choix = -1;
				ch=48; 
				char ch2=48;
				String stringI = "11";
				while (choix <= 0 || choix>longueur )
				{
					if((choix==0 || choix>longueur)&&((ch > 47 && ch < 57 && ch2 > 47 && ch2 < 57 && stringI.length() <= 2 && stringI.length() >= 1))){System.out.print("Choisissez un nombre valide : ");}
				    stringI = sc.nextLine(); //On r�cup�re la r�ponse de l'utilisateur
				    if (stringI.length() == 1 || stringI.length()==2) {
					ch = stringI.charAt(0);

					ch2 = 48;
					if(stringI.length()==2){ch2=stringI.charAt(1);}
					if ((ch > 47 && ch < 57) && ch2 > 47 && ch2 < 57) {
						if(stringI.length()==2){
							choix = (ch2 - 48)+10*(ch-48);
						}else{
							choix = ch - 48;
						}
					} else {
					    System.out.print("Choisissez un nombre valide : ");
					}
				    } else {
					System.out.print("Choisissez un nombre valide : ");
				    }

				}
			    if (choix < 1 && choix > 16) {
				System.out.print("Veuillez entrer un chiffre entre 1 et 16 : ");
			    }
			} while (choix < 1 && choix > 16);
			CT.effetChance(choix);
		    }
		    break;
		}
		case 5: {

		    for (Groupe g : listGroupes.values()) {
			System.out.println(g.getCouleur().toStringCouleur());
		    }
		    reponse="erreur";
		    while(listGroupes.get(reponse)==null){
		    System.out.println("Choisissez une couleur :");
		    reponse = sc.nextLine();
		    if(reponse.equalsIgnoreCase("orange")){reponse="orange";}
		    else if(reponse.equalsIgnoreCase("bleuFonce")){reponse="bleuFonce";}
		    else if(reponse.equalsIgnoreCase("vert")){reponse="vert";}
		    else if(reponse.equalsIgnoreCase("jaune")){reponse="jaune";}
		    else if(reponse.equalsIgnoreCase("violet")){reponse="violet";}
		    else if(reponse.equalsIgnoreCase("bleuCiel")){reponse="bleuCiel";}
		    else if(reponse.equalsIgnoreCase("rouge")){reponse="rouge";}
		    else if(reponse.equalsIgnoreCase("mauve")){reponse="mauve";}
		    //System.out.println(reponse);
		    //System.out.println(listGroupes.get(reponse.toLowerCase()));
		    }
		    for (ProprieteAConstruire tmp : listGroupes.get(reponse).getProprietes()) {
			j.setCash(j.getCash() + tmp.getPrixAchat());
			tmp.acheterPropriete(j);
		    }
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
	    System.out.println(j.getNomJoueurCouleur() + "est passe par la case depart et a gagne 200 euros");
	}

	if (nouveau == 40) {
	    j.setPositionCourante(this.getListCarreaux()[nouveau - 1]);
	} else {
	    j.setPositionCourante(this.getListCarreaux()[(nouveau % 40) - 1]);
	    //On fait un modulo 40 pour placer le joueur sur la bonne case

	}
	System.out.println(j.getNomJoueurCouleur() + " se trouve maintenant "
		+ "sur la case n° " + j.getPositionCourante().getNumeroCarreau() + " : " + j.getPositionCourante().getNomCarreau());
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	System.out.println();
	for (Joueur i : joueurs) {
	    System.out.println(i.getNomJoueurCouleur() + " : case n°"
		    + i.getPositionCourante().getNumeroCarreau()
		    + ", " + i.getCash() + " €, couleur " + i.getCouleur().toStringCouleur());

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

    public boolean getCarteSortieDePrisonChance() {
	return carteSortieDePrisonChance;
    }

    public void setCarteSortieDePrisonChance(boolean carteSortieDePrisonChance) {
	this.carteSortieDePrisonChance = carteSortieDePrisonChance;
    }

    public boolean getCarteSortieDePrisonCaisse() {
	return carteSortieDePrisonCaisse;
    }

    public void setCarteSortieDePrisonCaisse(boolean carteSortieDePrisonCaisse) {
	this.carteSortieDePrisonCaisse = carteSortieDePrisonCaisse;
    }
}
