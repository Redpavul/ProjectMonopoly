package Data;

public enum CouleurPropriete
{
	bleuFonce("bleuFonce"), 
	orange("orange"), 
	mauve("mauve"), 
	violet("violet"), 
	bleuCiel("bleuCiel"), 
	jaune("jaune"), 
	vert("vert"), 
	rouge("rouge");
	
    private final String label;
    
    private CouleurPropriete(String label) { 
        this.label = label; 
    }
	
	public String toString() { return this.label; }   
}