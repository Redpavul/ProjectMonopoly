package Ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Jeu.IHM;





public class FenetreUI extends JFrame
{
	
	private IHM ihm;

	private JPanel panel;
	
    public FenetreUI(IHM ihm) {
    	
        super("Monopoly");
        initUIComponents();
        this.ihm = ihm;
        
        
    }
    
        private void initUIComponents() {

    	panel = new JPanel();
        add(panel);
        
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(2,1)); 
        setVisible(true); 

    }
    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true); 
    }
    


}
