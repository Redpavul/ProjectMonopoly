package Ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
			//String s = c.getNomCarreau();
			JButton s=new JButton(c.getNomCarreau());
			s.setOpaque(false);
			s.setContentAreaFilled(false);
			s.setBorderPainted(true);
			
			 this.setLayout(null);
			 if (i==0){

					s.setBounds(783, 783, 118, 118);
			 }else if(i>0 && i<10){

				 s.setBounds(783-(i*74), 783, 74, 118);
				 
			 }else if (i==10){

				 s.setBounds(0, 783, 118, 118);
			 }else if (i>10 && i<20){

				 s.setBounds(0, 783-((i-10)*74), 118, 74);
				 
			 }else if (i==20){

				 s.setBounds(0, 0, 118, 118);
			 }else if(i>20 && i<30){

				 s.setBounds(44+((i-20)*74), 0, 74, 118);
			 }else if (i==30){

				 s.setBounds(783, 0, 118, 118);
			 }else if (i>30){

				 s.setBounds(783, 44+((i-30)*74), 118, 74);
			 }
			 
			 
			 
//			if(i==0 || i==10 || i==20 || i==30){
//				s.setPreferredSize(new Dimension(118, 118));
//				s.setBounds(783, 783, 118, 118);
//			}else{
//				s.setPreferredSize(new Dimension(76, 120));
//			}

			
			this.add(s);





		}
		
				 
			
		
	}
	
	
}
