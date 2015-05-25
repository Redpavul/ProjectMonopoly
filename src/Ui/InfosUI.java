package Ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import Jeu.IHM;



public class InfosUI  extends JPanel
{

	protected IHM ihm ;
	private JButton maker;
	
    public InfosUI(IHM ihm) {
        super();
        this.ihm=ihm;
        initUIComponents();
    }
    
    private void initUIComponents() 
    {
        this.setLayout(new GridLayout(3, 3));
        //On ajoute le bouton au content pane de la JFrame
        this.add(new JLabel("Joueur n°1 : "));//insert Joueur ici et faire une boucle selon le nombre de joueur
        this.add(new JLabel("Joueur n°2 : "));
        this.add(new JLabel("Joueur n°3 : "));
        this.add(new JLabel("Joueur n°4 : "));
        this.add(new JLabel("Joueur n°5 : "));
        this.add(new JLabel("Joueur n°6 : "));
    	
    	
    	maker = new JButton("Made by...");
    	//this.setLayout(new BorderLayout());
    	this.add(maker,BorderLayout.AFTER_LAST_LINE);
    	
    	maker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message;
                

                JOptionPane.showMessageDialog(
                        null,
                        "Créé par : \n" +
                        "Chef de projet : REYMANN Paul\n" +
                        "Secrétaire		: CONGIO  Jorane\n" +
                        "IHMan 			: GLIERE  Swann\n" +
                        "Codeur n°1		: ARTAUD  Matthieu\n" +
                        "Codeur n°2		: LALANDE Fabien\n", 
                        "", 
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
    	

    }
}
