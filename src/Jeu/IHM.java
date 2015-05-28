package Jeu;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Ui.FenetreUI;
import Ui.InfosUI;
import Ui.PlateauUI;



public class IHM extends JPanel
{
	private PlateauUI plateau;
	private InfosUI infos;
	private FenetreUI fenetre;
	private Monopoly monopoly;
	
	public IHM (Monopoly monopoly)
	{
		this.monopoly=monopoly;
		initUI();
		
	}
	
	private void initUI() {
        /* Onglets */
        plateau = new PlateauUI(this, getMonopoly())
        {
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
 
                ImageIcon m = new ImageIcon("plateaumonop.jpg");
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0,this);
 
            }
        };
        infos = new InfosUI(this);
        

        /* Fenêtre principale */
        fenetre = new FenetreUI(this);
        fenetre.addTab(plateau, "Plateau de jeu");     // onglet plateau
        fenetre.addTab(infos, "Infos Joueurs"); // onglet infos
        fenetre.afficher();
    }

    /**
     * @return the monopoly
     */
    public Monopoly getMonopoly() {
        return monopoly;
    }

    /**
     * @param monopoly the monopoly to set
     */
    public void setMonopoly(Monopoly monopoly) {
        this.monopoly = monopoly;
    }
}
