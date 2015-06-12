package Data;

import static Data.CouleurPropriete.*;
import Jeu.Monopoly;
import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {

    private Monopoly monopoly;
    private Carreau positionCourante;
    private ArrayList<ProprieteAConstruire> proprietes;
    private ArrayList<Compagnie> compagnies;
    private ArrayList<Gare> gares;
    private CouleurPropriete couleur;
    private String nomJoueur;
    private int cash = 1500;
    private int des;
    private int nbToursPrison = 0;
    private boolean prison = false;
    private int carteSortieDePrison;
    private HashMap<Groupe, Integer> groupeDePropriete; //Hashmap qui permet de savoir combien de propriété d'un groupe à un joueur
    

    public Joueur(Carreau positionCourante, String nomJoueur, CouleurPropriete couleur) {
	super();
	this.positionCourante = positionCourante;
	this.nomJoueur = nomJoueur;
	this.des = des;
	this.couleur = couleur;
	proprietes = new ArrayList<ProprieteAConstruire>();
	gares = new ArrayList<Gare>();
	compagnies = new ArrayList<Compagnie>();
    }

    public Monopoly getMonopoly() {
	return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
	this.monopoly = monopoly;
    }


    public void setCompagnies(Compagnie[] compagnies) {
	this.setCompagnies(compagnies);
    }


    public void setGares(Gare[] gares) {
	this.setGares(gares);
    }

    public Carreau getPositionCourante() {
	return positionCourante;
    }

    public void setPositionCourante(Carreau positionCourante) {
	this.positionCourante = positionCourante;
    }

    public String getNomJoueur() {
	return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
	this.nomJoueur = nomJoueur;
    }

    public int getCash() {
	return cash;
    }

    public void setCash(int cash) {
	this.cash = cash;
    }

    public int getDes() {
	return des;
    }

    public void setDes(int des) {
	this.des = des;
    }

    public CouleurPropriete getCouleur() {
	return couleur;
    }

    public void setCouleur(CouleurPropriete couleur) {
	this.couleur = couleur;
    }

    public boolean isPrison() {
	return prison;
    }

    public void setPrison(boolean isPrison) {
    if(isPrison){getPositionCourante().getMonopoly().deplacerJoueur(11, this);}
	this.prison = isPrison;
    }

    public ArrayList<ProprieteAConstruire> getProprietes() {
	return proprietes;
    }

    public void setProprietes(ArrayList<ProprieteAConstruire> proprietesAConstruire) {
	this.proprietes = proprietesAConstruire;
    }

    public void setCarteSortieDePrisoProprieteAConstruiren(int carteSortieDePrison) {
	this.setCarteSortieDePrison(carteSortieDePrison);
    }

    public HashMap<Groupe, Integer> getGroupeDePropriete() {
	return groupeDePropriete;
    }

    public void setGroupeDePropriete(HashMap<Groupe, Integer> groupeDePropriete) {
	this.groupeDePropriete = groupeDePropriete;
    }




    public int afficherProprietesJoueur() {

    	int i = 0;
	for (ProprieteAConstruire p : getProprietes()) {
	    i++;
	    System.out.println("[" + i + "] " + p.getNomCarreau());
	}
	return i;
    }

    public ProprieteAConstruire choix(int indice) {

	int i = 1;
	for (ProprieteAConstruire p : getProprietes()) {
	    System.out.println(p.getNomCarreau());
	    if (i == indice) {
		return p;
	    }
	    i++;
	}
	return null;

    }

    public int getNbToursPrison() {
	return nbToursPrison;
    }

    public void setNbToursPrison(int nbToursPrison) {
	this.nbToursPrison = nbToursPrison;
    }

    public void setCarteSortieDePrison(int carteSortieDePrison) {
	this.carteSortieDePrison = carteSortieDePrison;
    }

    public int getCarteSortieDePrison() {
	return carteSortieDePrison;
    }

    public void setCompagnies(ArrayList<Compagnie> compagnies) {
	this.compagnies = compagnies;
    }

    public void setGares(ArrayList<Gare> gares) {
	this.gares = gares;
    }

    public ArrayList<Compagnie> getCompagnies() {
	return compagnies;
    }

    public ArrayList<Gare> getGares() {
	return gares;
    }
    
    public String getNomJoueurCouleur()
    {
    String couleur = "";
	if(this.couleur == bleuFonce)
	    couleur = "34";
	else if(this.couleur == mauve)
	    couleur = "35";
	else if(this.couleur == bleuCiel)
	    couleur = "36";
	else if(this.couleur == jaune)
	    couleur = "33";
	else if(this.couleur == vert)
	    couleur = "32";
	else if(this.couleur == rouge)
	    couleur = "31";
	 return "\033[" + couleur + "m" + nomJoueur + "\033[0m";
    }
    
    
}
