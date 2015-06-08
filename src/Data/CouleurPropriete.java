package Data;

public enum CouleurPropriete
{
	bleuFonce("bleuFonce"), 
	rouge("rouge"), 
	mauve("mauve"), 
	vert("vert"), 
	bleuCiel("bleuCiel"), 
	jaune("jaune"), 
	violet("violet"), 
	orange("orange");
	
    private final String label;
    
    private CouleurPropriete(String label) { 
        this.label = label; 
    }
	
    public String toString()
    {
	String couleur = "";
	if(this == bleuFonce)
	    couleur = "34";
	else if(this == orange)
	    couleur = "33";
	else if(this == mauve)
	    couleur = "35";
	else if(this == violet)
	    couleur = "35";
	else if(this == bleuCiel)
	    couleur = "36";
	else if(this == jaune)
	    couleur = "33";
	else if(this == vert)
	    couleur = "32";
	else if(this == rouge)
	    couleur = "31";
	 return "\033[" + couleur + "m" + super.toString() + "\033[0m";
    }   
}