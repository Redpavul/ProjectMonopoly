package Data;

import Jeu.Monopoly;

public class Joueur
{

	private Monopoly monopoly;
	private Compagnie[] compagnies;
	private Gare[] gares;
	private Carreau positionCourante;
	private ProprieteAConstruire[] proprietesAConstruire;
	private String nomJoueur;
	private int cash = 1500;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Joueur(Carreau positionCourante, String nomJoueur, int cash)
	{
		super();
		this.positionCourante = positionCourante;
		this.nomJoueur = nomJoueur;
		this.cash = cash;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
	public ProprieteAConstruire[] getProprietesAConstruire() {
		return proprietesAConstruire;
	}
	public void setProprietesAConstruire(
			ProprieteAConstruire[] proprietesAConstruire) {
		this.proprietesAConstruire = proprietesAConstruire;
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
	
	

}

