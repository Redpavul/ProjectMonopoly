package Data;

import Jeu.Monopoly;
import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {

    private Monopoly monopoly;
    private Compagnie[] compagnies;
    private Gare[] gares;
    private Carreau positionCourante;
    private ArrayList<CarreauPropriete> proprietes;
    private CouleurPropriete couleur;
    private String nomJoueur;
    private int cash = 1500;
    private int des;
    private boolean prison;
    private int carteSortieDePrison;
    private HashMap<Groupe,Integer> groupeDePropriete; //Hashmap qui permet de savoir combien de propriété d'un groupe à un joueur

    public Joueur(Carreau positionCourante, String nomJoueur, int des, CouleurPropriete couleur) {
        super();
        this.positionCourante = positionCourante;
        this.nomJoueur = nomJoueur;
        this.des = des;
	this.couleur = couleur;
       proprietes = new ArrayList<CarreauPropriete>();
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    public Compagnie[] getCompagnies() {
        return compagnies;
    }

    public void setCompagnies(Compagnie[] compagnies) {
        this.compagnies = compagnies;
    }

    public Gare[] getGares() {
        return gares;
    }

    public void setGares(Gare[] gares) {
        this.gares = gares;
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
        this.prison = isPrison;
    }

    public ArrayList<CarreauPropriete> getProprietes() {
	return proprietes;
    }

    public void setProprietes(ArrayList<CarreauPropriete> proprietesAConstruire) {
	this.proprietes = proprietesAConstruire;
    }

	public int getCarteSortieDePrison() {
		return carteSortieDePrison;
	}

	public void setCarteSortieDePrisoProprieteAConstruiren(int carteSortieDePrison) {
		this.carteSortieDePrison = carteSortieDePrison;
	}

    public HashMap<Groupe,Integer> getGroupeDePropriete() {
	return groupeDePropriete;
    }

    public void setGroupeDePropriete(HashMap<Groupe,Integer> groupeDePropriete) {
	this.groupeDePropriete = groupeDePropriete;
    }
    
    public void afficherProprietesJoueur()
    {
	for(CarreauPropriete p : proprietes)
	{
	    System.out.println(p.getNomCarreau());
	}
    }
    
    public CarreauPropriete choix(int indice)
    {
	
	int i = 1 ;
	for(CarreauPropriete p : proprietes)
	{
	    System.out.println(p.getNomCarreau());
	    if(i==indice)
	    {
		return p;
	    }
	    i++;
	} 
	return null;

    }

}
