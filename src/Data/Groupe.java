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
<<<<<<< HEAD
        {
                return prixMaison;
        }

        public void setPrixMaison(int prixMaison) 
        {
                this.prixMaison = prixMaison;
        }

=======
	{
		return prixMaison;
	}

	public void setPrixMaison(int prixMaison) 
	{
		this.prixMaison = prixMaison;
	}
	
>>>>>>> 3e9100097d3d4e1cd96951dc68d59c04264f0733
}