package Jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class  Monopoly {
    
    
	private Carreau carreaux;
	private Joueur[] joueurs;
	private Interface interf;
	private int nbMaisons = 32;
	private int nbHotels = 12;
    
	
	public Monopoly(String dataFilename)
	{
		buildGamePlateau(dataFilename);
		buildGroupe(dataFilename);
	}
	
	private void buildGamePlateau(String dataFilename)
	{
		try{
			ArrayList<String[]> data = readDataFile(dataFilename, ",");
			
			//TODO: create cases instead of displaying
			for(int i=0; i<data.size(); ++i){
				String caseType = data.get(i)[0];
				if(caseType.compareTo("P") == 0){
					System.out.println("Propriété :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("G") == 0){
					System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("C") == 0){
					System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("CT") == 0){
					System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("CA") == 0){
					System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else if(caseType.compareTo("CM") == 0){
					System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
				}
				else
					System.err.println("[buildGamePleateau()] : Invalid Data type");
			}
			
		} 
		catch(FileNotFoundException e){
			System.err.println("[buildGamePlateau()] : File is not found!");
		}
		catch(IOException e){
			System.err.println("[buildGamePlateau()] : Error while reading file!");
		}
		
	}
	
	private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException
	{
		ArrayList<String[]> data = new ArrayList<String[]>();
		
		BufferedReader reader  = new BufferedReader(new FileReader(filename));
		String line = null;
		while((line = reader.readLine()) != null){
			data.add(line.split(token));
		}
		reader.close();
		
		return data;
	}
	
	private void buildGroupe(String dataFilename)
	{
		

		
		for(CouleurPropriete c : CouleurPropriete.values())
		{
			System.out.println("Le groupe de couleur "+c.toString()+" a bien été créée");
			Groupe a = new Groupe(new ArrayList<ProprieteAConstruire>(),c);
		}
		
		try{
			ArrayList<String[]> data = readDataFile(dataFilename, ",");
			
	
			for(int i=0; i<data.size(); ++i){
				String caseType = data.get(i)[0];
				if(caseType.compareTo("P") == 0){
					String caseCoul = data.get(i)[3];
					if(caseCoul.compareTo("mauve")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
					else if (caseCoul.compareTo("bleuCiel")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
					else if (caseCoul.compareTo("violet")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
					else if (caseCoul.compareTo("orange")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
					else if (caseCoul.compareTo("rouge")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
					else if (caseCoul.compareTo("jaune")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
					else if (caseCoul.compareTo("vert")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
					else if (caseCoul.compareTo("bleuFoncé")==0)
					{
						System.out.println("Couleur de groupe :\t" + data.get(i)[3]);
					}
				}
				else ;
					//System.err.println("[buildGamePleateau()] : Invalid Data type");
				}
			
		} 
		catch(FileNotFoundException e){
			System.err.println("[buildGamePlateau()] : File is not found!");
		}
		catch(IOException e){
			System.err.println("[buildGamePlateau()] : Error while reading file!");
		}
	
	}

			
}


