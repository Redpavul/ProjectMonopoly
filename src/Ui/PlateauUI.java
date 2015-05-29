package Ui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Data.Carreau;
import Jeu.IHM;
import Jeu.Monopoly;

public class PlateauUI extends JPanel
{

	protected IHM ihm;
	private Monopoly monopoly;
	
	public PlateauUI(IHM ihm, Monopoly monopoly)
	{
		super();
		this.ihm= ihm;
		this.monopoly=monopoly;
		initUIComponents();
		setBackground(Color.gray);
		
		
		
	}

	private void initUIComponents()
	{
		Carreau[] list = monopoly.getListCarreaux();
		for(int i=0;i<40;i++)
		{
			Carreau c = list[i];
			
			this.add(new JTextField(c.getNomCarreau()));
		}
		
				 
			
		
	}
	
	
}
