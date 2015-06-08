package Data;

import java.util.ArrayList;

public class Groupe
{

	private ArrayList<ProprieteAConstruire> proprietes;
	private CouleurPropriete couleur;
	private int prixMaison;
	
	
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
	
	public int getPrixMaison() 
	{
		return prixMaison;
	}

	public void setPrixMaison(int prixMaison) 
	{
		this.prixMaison = prixMaison;
	}
	
}