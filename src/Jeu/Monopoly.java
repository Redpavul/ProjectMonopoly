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

    public Monopoly(String dataFilename,Interface interf) {
        this.interf=interf;
	setJoueurs(new LinkedList<Joueur>());
	buildGamePlateau(dataFilename);
	//System.out.print("Paquet carte Chance: ");
	this.setTabChance(creerPaquet(this.getNbDeCarteChance()));
	//System.out.print("Paquet carte Caisse: ");
	this.setTabCaisse(creerPaquet(this.getNbDeCarteCaisse()));
	
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

    public boolean isEndGame() {
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
