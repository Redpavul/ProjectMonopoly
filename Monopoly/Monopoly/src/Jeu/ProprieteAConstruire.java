package Jeu;

public class ProprieteAConstruire extends CarreauPropriete
{

	private Groupe groupePropriete;
	private int nbMaisons = 0;
	private int nbHotels = 0;
	private int[] loyerMaison;
	private int prixMaison;
	private int nbMaison;
	
	public ProprieteAConstruire(Groupe groupePropriete, int nbMaisons, int nbHotels, int[] loyerMaison, int prixMaison, int nbMaison) 
	{

		this.groupePropriete = groupePropriete;
		this.nbMaisons = nbMaisons;
		this.nbHotels = nbHotels;
		this.loyerMaison = loyerMaison;
		this.prixMaison = prixMaison;
		this.nbMaison = nbMaison;
	}

	public Groupe getGroupePropriete() 
	{
		return groupePropriete;
	}

	public void setGroupePropriete(Groupe groupePropriete) 
	{
		this.groupePropriete = groupePropriete;
	}

	public int getNbMaisons() 
	{
		
		return nbMaisons;
	}

	public void setNbMaisons(int nbMaisons)
	{
		this.nbMaisons = nbMaisons;
	}

	public int getNbHotels() 
	{
		return nbHotels;
	}

	public void setNbHotels(int nbHotels) 
	{
		this.nbHotels = nbHotels;
	}

	public int[] getLoyerMaison() 
	{
		return loyerMaison;
	}

	public void setLoyerMaison(int[] loyerMaison) 
	{
		this.loyerMaison = loyerMaison;
	}

	public int getPrixMaison() 
	{
		return prixMaison;
	}

	public void setPrixMaison(int prixMaison) 
	{
		this.prixMaison = prixMaison;
	}

	public int getNbMaison() 
	{
		return nbMaison;
	}

	public void setNbMaison(int nbMaison) 
	{
		this.nbMaison = nbMaison;
	}
	
	

}