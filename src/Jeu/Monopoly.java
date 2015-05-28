package Jeu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import Data.Carreau;
import Data.CarreauArgent;
import Data.CarreauMouvement;
import Data.Compagnie;
import Data.CouleurPropriete;
import Data.Gare;
import Data.Groupe;
import Data.Joueur;
import Data.ProprieteAConstruire;

public class Monopoly {

    private Interface interf;
    private int nbMaisons = 32;
    private int nbHotels = 12;
    private HashMap<String, Groupe> listGroupes = new HashMap();//Contient la liste des groupes
    private Groupe g;
    //private HashMap<Integer, Carreau> listCarreaux = new HashMap();
    private Carreau[] listCarreaux = new Carreau[40];
    private LinkedList<Joueur> joueurs;

    public Monopoly(String dataFilename) {
        setJoueurs(new LinkedList<Joueur>());
        buildGamePlateau(dataFilename);
        initialiserPartie();
        boucleDeJeu();
        //
    }

    //Fonction permettant de cr�er le plateau de jeu
    private void buildGamePlateau(String dataFilename) {
        //Création des groupes : 1 groupe par couleur
        for (CouleurPropriete c : CouleurPropriete.values()) {
            g = new Groupe(new ArrayList<ProprieteAConstruire>(), c);//On passe une arrayListe vide, car pour l'instant le groupe ne poss�de pas de propri�t�s
            listGroupes.put(c.toString(), g);

        }
        try {
            ArrayList<String[]> data = readDataFile(dataFilename, ",");

            //cr�ation des diff�rentes cases du plateau
            for (int i = 0; i < data.size(); ++i) {
                String caseType = data.get(i)[0];
                //Propri�t�s
                if (caseType.compareTo("P") == 0) {
					//System.out.println("Propri�t� :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);

                    int[] loyers = {Integer.parseInt(data.get(i)[5]), Integer.parseInt(data.get(i)[6]),
                        Integer.parseInt(data.get(i)[7]), Integer.parseInt(data.get(i)[8]),
                        Integer.parseInt(data.get(i)[9]), Integer.parseInt(data.get(i)[10])};
                    int prixMaison = Integer.parseInt(data.get(i)[11]);
                    int prixHotel = Integer.parseInt(data.get(i)[12]);
                    Groupe g = listGroupes.get(data.get(i)[3]);
                    String nomCarreau = data.get(i)[2];
                    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
                    int prixAchat = Integer.parseInt(data.get(i)[4]);
                    ProprieteAConstruire p = new ProprieteAConstruire(prixAchat, nomCarreau, numeroCarreau, g, loyers, prixMaison, prixHotel);

                    listCarreaux[numeroCarreau-1] = p;

                } //Gares
                else if (caseType.compareTo("G") == 0) {
                    //System.out.println("Gare :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
                    String nomCarreau = data.get(i)[2];
                    int prixAchat = Integer.parseInt(data.get(i)[3]);
                    Gare g = new Gare(prixAchat, nomCarreau, numeroCarreau);
                    listCarreaux[numeroCarreau-1] = g;
                } //Compagnies
                else if (caseType.compareTo("C") == 0) {
                    //System.out.println("Compagnie :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
                    String nomCarreau = data.get(i)[2];
                    int prixAchat = Integer.parseInt(data.get(i)[3]);
                    Compagnie c = new Compagnie(prixAchat, nomCarreau, numeroCarreau);
                    listCarreaux[numeroCarreau-1] = c;

                } //Case tirage
                else if (caseType.compareTo("CT") == 0) {
                    //System.out.println("Case Tirage :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
                    String nomCarreau = data.get(i)[2];
                    CarreauArgent ct = new CarreauArgent(nomCarreau, numeroCarreau);
                    listCarreaux[numeroCarreau-1] = ct;
                } //Case argent
                else if (caseType.compareTo("CA") == 0) {
                    //System.out.println("Case Argent :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
                    String nomCarreau = data.get(i)[2];
                    CarreauArgent ca = new CarreauArgent(nomCarreau, numeroCarreau);
                    listCarreaux[numeroCarreau-1] = ca;

                } //Case mouvement
                else if (caseType.compareTo("CM") == 0) {
                    //System.out.println("Case Mouvement :\t" + data.get(i)[2] + "\t@ case " + data.get(i)[1]);
                    int numeroCarreau = Integer.parseInt(data.get(i)[1]);
                    String nomCarreau = data.get(i)[2];
                    CarreauMouvement cm = new CarreauMouvement(nomCarreau, numeroCarreau);
                    listCarreaux[numeroCarreau-1] = cm;
                } else {
                    System.err.println("[buildGamePleateau()] : Invalid Data type");
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("[buildGamePlateau()] : File is not found!");
        } catch (IOException e) {
            System.err.println("[buildGamePlateau()] : Error while reading file!");
        }
        /*for(int i=1;i<=40;i++)
         {
         Carreau c = listCarreaux[i];
         System.out.println(c.getNomCarreau());
         }*/
    }

    private void initialiserPartie() {

        // Inscription des Joueurs
        int des1,des2;
        Scanner sc = new Scanner(System.in);
        int nbJoueur;

        System.out.println("Nombre de joueurs :");
        nbJoueur = sc.nextInt();
        Joueur[] joueursTemp = new Joueur[nbJoueur];//Tableau contenant les joueurs dans l'ordre de cr�ation

        // Cr�ation des joueurs et lancers de des
        String nom;
        nom = sc.nextLine(); // Permet de r�initialiser le scanner, qui contient le caract�re \n, car on a utilis� un nextInt()
        Joueur j;
        for (int i = 0; i < nbJoueur; i++) {
            des1 = roll();
            des2 = roll();
            System.out.println("Nom du joueur n°" + (i + 1) + " : ");
            nom = sc.nextLine();
            roll();//Il faudra g�rer dans cette fonction les cas où les joueur fait un double
            System.out.println("Il a obtenu " + des1 + " et " + des2 + " soit au total " + (des1 + des2) + ".");
            j = new Joueur(listCarreaux[1], nom, (des1 + des2));
            joueursTemp[i] = j;
        }

        triBulleDecroissant(joueursTemp);

        for (Joueur i : joueursTemp) {
            System.out.println(i.getNomJoueur());
            joueurs.add(i);
        }
    }

    public static void triBulleDecroissant(Joueur tableau[]) {
        int longueur = tableau.length;
        Joueur tampon;
        boolean permut;

        do {
            // hypoth�se : le tableau est tri�
            permut = false;
            for (int i = 0; i < longueur - 1; i++) {
                // Teste si 2 �l�ments successifs sont dans le bon ordre ou non
                if (tableau[i].getDes() < tableau[i + 1].getDes()) {
                    // s'ils ne le sont pas, on �change leurs positions
                    tampon = tableau[i];
                    tableau[i] = tableau[i + 1];
                    tableau[i + 1] = tampon;
                    permut = true;
                }
            }
        } while (permut);
    }

    private void jouerUnCoup(Joueur j) 
    {
        
        
        int des,des1,des2,numCar ;
        int compteur = 0;
        
        if(j.isPrison() )//si le joueur est en prison
        {
            des1 = roll();
            des2 = roll();
            des = des1 + des2;

            j.setDes(des);
            System.out.println("Tour de " + j.getNomJoueur() + " :");
            System.out.println("Pour sortir de prison, il faut faire un double");
            System.out.println("Lancé de dés : " + des1 + "+" + des2 + " = " + des);
            if(des1 == des2)
            {
                j.setPrison(false);
            }
        }
        else
        {
        do
        {
        
            des1 = roll();
            des2 = roll();
            des = des1 + des2;

            j.setDes(des);
            System.out.println("Tour de " + j.getNomJoueur() + " :");
            System.out.println("Lancé de dés : " + des1 + "+" + des2 + " = " + des);
            numCar = j.getPositionCourante().getNumeroCarreau() + j.getDes();

            if(des+numCar > 40)
            {
               j.setCash(j.getCash()+200);
               System.out.println("Le joueur : " + j.getNomJoueur() + " est passé par la case départ et a donc gagné 200 €");

            }

            if(numCar == 40)
            {
                j.setPositionCourante(this.getListCarreaux()[numCar - 1]);
                System.out.println("Nouvelle position : " + j.getPositionCourante().getNomCarreau());
            }
            else
            {
                 j.setPositionCourante(this.getListCarreaux()[numCar % 40]);//Modulo 40 pour que le joueur ne dépasse pas la case 40
            }
            System.out.println("Nouvelle position : " + j.getPositionCourante().getNomCarreau());
            j.setCash(j.getCash()-300);
            for (Joueur i : joueurs) 
            {
                System.out.println(i.getNomJoueur() + " : case n°" + i.getPositionCourante().getNumeroCarreau() + ", " + i.getCash() + " €, couleur " + i.getCouleur());
                        // AJOUTER nbMaison + NbHotels	 
            }
            compteur++ ;
        }while(des1 == des2 && compteur < 3);
        
        if(compteur == 3)
        {
            j.setPositionCourante(this.getListCarreaux()[10]); //Le joueur va en prison
            j.setPrison(true);
            System.out.println("En prison ! ");
        }
        }
        
    }

    private void boucleDeJeu() 
    {
        Joueur j ;
        while( !isEndGame())
        {
            j = joueurs.getFirst();
            jouerUnCoup(j);
            joueurs.remove(j);
            //On remet le joueur à la fin de la LinkedList si il n'a pas perdu.
            if(j.getCash() > 0)
            {
                joueurs.addLast(j);              
            }             
        }
        
        System.out.println("Le joueur gagnant est : " + joueurs.getFirst().getNomJoueur());
    }
    
    private boolean isEndGame()
    {         
        return joueurs.size()==1;//renvoiyer vrai si il ne reste plus qu'un joueur dans la liste.
    }

    private ArrayList<String[]> readDataFile(String filename, String token) throws FileNotFoundException, IOException {
        ArrayList<String[]> data = new ArrayList<String[]>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line = null;
        while ((line = reader.readLine()) != null) {
            data.add(line.split(token));
        }
        reader.close();

        return data;
    }

    public int roll() 
    {
        return (int) (Math.random() * 6) + 1;
    
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Get/Set
    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(LinkedList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Interface getInterf() {
        return interf;
    }

    public void setInterf(Interface interf) {
        this.interf = interf;
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public void setNbMaisons(int nbMaisons) {
        this.nbMaisons = nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }

    public void setNbHotels(int nbHotels) {
        this.nbHotels = nbHotels;
    }


    public HashMap<String, Groupe> getListGroupes() {
        return listGroupes;
    }

    public void setListGroupes(HashMap<String, Groupe> listGroupes) {
        this.listGroupes = listGroupes;
    }

    public Groupe getG() {
        return g;
    }

    public void setG(Groupe g) {
        this.g = g;
    }

    public Carreau[] getListCarreaux() {
        return listCarreaux;
    }

    public void setListCarreaux(Carreau[] listCarreaux) {
        this.listCarreaux = listCarreaux;
    }

}
