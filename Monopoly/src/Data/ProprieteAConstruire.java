package Data;


public class ProprieteAConstruire extends CarreauPropriete
{

	private Groupe groupePropriete;
	private int nbMaisons = 0;
	private int nbHotels = 0;
	private int[] loyerMaison;
	private int prixMaison;
	private int prixHotel;
	
	
	public ProprieteAConstruire(int prixAchat, String nomCarreau, int numeroCarreau, Groupe groupePropriete, int[] loyerMaison, int prixMaison, int prixHotel) 
	{
		super(prixAchat, nomCarreau, numeroCarreau);
		this.groupePropriete = groupePropriete;
		this.loyerMaison = loyerMaison;
		this.prixMaison = prixMaison;
		this.prixHotel = prixHotel;
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

	public int getPrixHotel() 
	{
		return prixHotel;
	}

	public void setPrixHotel(int prixHotel) 
	{
		this.prixHotel = prixHotel;
	}
	
	

}