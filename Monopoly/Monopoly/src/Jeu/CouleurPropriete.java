package Jeu;

public enum CouleurPropriete
{
	bleuFonce("Bleu fonc\u00E9"), 
	orange("Orange"), 
	mauve("Mauve"), 
	violet("Violet"), 
	bleuCiel("Bleu ciel"), 
	jaune("Jaune"), 
	vert("Vert"), 
	rouge("Rouge");
	
    private final String label;
    
    private CouleurPropriete(String label) { 
        this.label = label; 
    }
	
	public String toString() { return this.label; }   
}