package Jeu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Interface implements Runnable {

    private IHM ihm;
    
    public void run() 
    {
        
        Monopoly monopoly = new Monopoly("data.txt");
        ihm = new IHM(monopoly);
    }    
    


	
	
	public static void main(String[] args)
	{ 
		
		java.awt.EventQueue.invokeLater(new Interface());
		
	}
	

}