package Ui;

import java.awt.BorderLayout;

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
	private JTabbedPane tabs;
	private JPanel panel;
	
    public FenetreUI(IHM ihm) {
        super("Monopoly");
        
        this.ihm = ihm;
        
        initUIComponents();

        
    }
    
    
    public void afficher() {
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);
        setVisible(true);                        
    }
    
    public void addTab(JPanel onglet, String titre) {
    	panel = new JPanel();
        tabs.addTab(titre, onglet);
    }
    private void initUIComponents() {
        /*
         * Contenu avec des onglets
         */
        tabs = new JTabbedPane();
        tabs.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (tabs.getSelectedIndex() == 0) {
                                    
                } else {
                                                     
                }
            }
        });
        
        setLayout(new BorderLayout());        
        add(tabs, BorderLayout.CENTER);

    }
}
