package Ui;

import Data.Carreau;
import Data.CarreauArgent;
import Data.CarreauPropriete;
import Data.Joueur;
import Data.ProprieteAConstruire;
import Jeu.IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;



public class InfosUI  extends JPanel
{

	protected IHM ihm ;
	private JButton maker;
	private JButton jouer;
	private JButton construire;
	private JPanel joueurs;
	private JPanel caseSelectionee;
	private JPanel bouttons;
	private JLabel space;
	private JTextArea propriete;
	private JTextArea logs;
	public int selec;
	private boolean typeSelec;
	
    public int getSelec() {
		return selec;
	}
    
	public void setSelec(int selec) {
		this.selec = selec;
	}
	
	public Carreau getInfoSelec() {
    	return ihm.getMonopoly().getListCarreaux()[getSelec()];
		
	}



	public InfosUI(IHM ihm) {
        super();
        setBackground(Color.gray);
        this.ihm=ihm;
        joueurs = new JPanel();
        caseSelectionee = new JPanel();
        bouttons = new JPanel();
        space = new JLabel("");
        
        propriete= new JTextArea();
        propriete.setEditable(false);
        logs= new JTextArea();
        logs.setEditable(false);
        typeSelec=false;
        initUIComponents();
    }
    
    private void initUIComponents() 
    {
        this.setLayout(new GridLayout(3, 1));
        joueurs.setLayout(new GridLayout(1, 6));
        bouttons.setLayout(new GridLayout(1, 3));
        caseSelectionee.setLayout(new GridLayout(1,2));
        
        int j = 1 ;
        //On ajoute le bouton au content pane de la JFrame
        for( Joueur i : ihm.getMonopoly().getJoueurs())
        {
        	joueurs.add(new JLabel("<html>Joueur n°"+j+ "<br>"+
        						"Nom :" + i.getNomJoueur() +" <br>"+
        						"cash : " +i.getCash() +"<br> </html>"));
        	j++;
        }
    		while(j<6)
    		{
    			joueurs.add(new JLabel(""));
    			j++;
    		}

    	
    	maker = new JButton("Made by...");
    	construire = new JButton(" contruire");
    	jouer = new JButton("jouer !");
    	
    	//this.setLayout(new BorderLayout());
    	bouttons.add(jouer);
    	bouttons.add(construire);
    	bouttons.add(maker);
    	
    	maker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message;
                

                JOptionPane.showMessageDialog(
                        null,
                        "Créé par : \n" +
                        "Chef de projet : REYMANN Paul\n" +
                        "Codeur Alpha		: CONGIO  Jorane\n" +
                        "IHMan 			: GLIERE  Swann\n" +
                        "Codeur Bravo		: ARTAUD  Matthieu\n" +
                        "Codeur Echo		: LALANDE Fabien\n", 
                        "", 
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
    	construire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(typeSelec==false){
            		
	            	
	                String message;
	                
	
	                JOptionPane.showMessageDialog(
	                        null,
	                        "Pas de propriété selectionner ! \n"+
	                        "veulliez en selectionner une ", 
	                        "", 
	                        JOptionPane.PLAIN_MESSAGE);
            	}else{ //construire(); 
            		}
            	
            }
        });

    	Carreau c = getInfoSelec();
    	if (getSelec()==0 ||getSelec()==10 ||getSelec()==20 ||getSelec()==30 ||getSelec()==2 ||getSelec()==7 ||getSelec()==17 ||getSelec()==22 ||getSelec()==33 ||getSelec()==36 ){
        	propriete.append("nom : "+c.getNomCarreau()+"\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n");
    	}else if(getSelec()==4 ||getSelec()==38){
        	propriete.append("nom : "+c.getNomCarreau()+"\n" +
                    "prix : "+((CarreauArgent) c).getMontant()+"\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n");
    	}else if(getSelec()==5 ||getSelec()==15||getSelec()==25 ||getSelec()==35 ||getSelec()==12 ||getSelec()==28){
        	propriete.append("nom : "+c.getNomCarreau()+"\n" +
                    "prix : "+((CarreauArgent) c).getMontant()+"\n" +
                    "Codeur Alpha		: CONGIO  Jorane\n" +
                    "IHMan 			: GLIERE  Swann\n" +
                    "Codeur Bravo		: ARTAUD  Matthieu\n" +
                    "Codeur Echo		: LALANDE Fabien\n");
    	}else{
    	propriete.append("nom : \n" +
                        "Chef de projet : REYMANN Paul\n" +
                        "Codeur Alpha		: CONGIO  Jorane\n" +
                        "IHMan 			: GLIERE  Swann\n" +
                        "Codeur Bravo		: ARTAUD  Matthieu\n" +
                        "Codeur Echo		: LALANDE Fabien\n");
    	}
    	caseSelectionee.add(propriete);
    	

    	selection();
    	caseSelectionee.add(propriete);
    //	caseSelectionee.add(space);
    	caseSelectionee.add(logs);

    	this.add(joueurs);
    	this.add(caseSelectionee);
    	this.add(bouttons);
    }
    public void selection() 
    {
    Carreau c = getInfoSelec();
	if (getSelec()==30 ||getSelec()==2 ||getSelec()==7 ||getSelec()==17 ||getSelec()==22 ||getSelec()==33 ||getSelec()==36 ){
    	propriete.setText("nom : "+c.getNomCarreau()+"\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");
    	this.typeSelec=false;
	}else if(getSelec()==0 ||getSelec()==10 ||getSelec()==20 ||getSelec()==4 ||getSelec()==38){
    	propriete.setText("nom : "+c.getNomCarreau()+"\n" +
                "prix : "+((CarreauArgent) c).getMontant()+"\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n");
    	this.typeSelec=false;
	}else if(getSelec()==5 ||getSelec()==15||getSelec()==25 ||getSelec()==35 ||getSelec()==12 ||getSelec()==28){
		String proprio;
		if(((CarreauPropriete) c).getProprietaire()!=null){
    		
    		proprio=((CarreauPropriete) c).getProprietaire().getNomJoueur();
    	}else{
    		proprio="banque";}
		propriete.setText("nom : "+c.getNomCarreau()+"\n" +
                "prix : "+((CarreauPropriete) c).getPrixAchat()+"\n" +
                "Propiétaire : "+proprio+"\n" +
                "\n" +
                "\n" +
                "\n");
		this.typeSelec=false;
	}else{
		int montant;
		String proprio;
		if (((ProprieteAConstruire) c).getNbHotels() == 0) {
			montant = ((ProprieteAConstruire) c).getLoyerMaison()[((ProprieteAConstruire) c).getNbMaisons()];
		    } else {
			montant = ((ProprieteAConstruire) c).getLoyerMaison()[5];
		    }
    	if(((CarreauPropriete) c).getProprietaire()!=null){
    		proprio=((CarreauPropriete) c).getProprietaire().getNomJoueur();
    	}else{
    		proprio="banque";}
    	propriete.setText("nom : "+c.getNomCarreau()+"\n" +
                "prix : "+((ProprieteAConstruire) c).getPrixAchat()+"\n" +
                "Propiétaire : "+proprio+"\n" +
                "nb maison : "+((ProprieteAConstruire) c).getNbMaisons()+"\n" +
                "nb hotel : "+((ProprieteAConstruire) c).getNbHotels()+"\n" +
                "loyer : "+montant+"\n" +
                "prix de construction : "+((ProprieteAConstruire) c).getGroupePropriete().getPrixMaison()+"\n" +
                "\n");
    	this.typeSelec=true;
	}
    }
        	
}
