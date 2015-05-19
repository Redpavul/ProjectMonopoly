package Jeu;

import java.util.ArrayList;

public class Groupe
{

	private ArrayList<ProprieteAConstruire> proprietes;
	private CouleurPropriete couleur;
	
	
	public Groupe(ArrayList<ProprieteAConstruire> proprietes, CouleurPropriete couleur) 
	{
		this.proprietes = new ArrayList<ProprieteAConstruire>() ;
		this.couleur = couleur;
	}

	
	
	public ArrayList<ProprieteAConstruire> getProprietes() 
	{
		return proprietes;
	}
	
	public void setProprietes(ArrayList<ProprieteAConstruire> proprietes) 
	{
		this.proprietes = proprietes;
	}
	

	public CouleurPropriete getCouleur() 
	{
		return couleur;
	}
	

	public void setCouleur(CouleurPropriete couleur) 
	{
		this.couleur = couleur;
	}

	
	
}