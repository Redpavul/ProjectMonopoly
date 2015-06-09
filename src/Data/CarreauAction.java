package Data;

public abstract class CarreauAction extends Carreau
{
	public CarreauAction(String nomCarreau, int numeroCarreau)
	{
		super(nomCarreau, numeroCarreau);
	}
	public void action(){
		System.out.print("marche pas");
	}
}
