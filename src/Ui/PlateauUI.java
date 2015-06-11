package Ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Data.Carreau;
import Data.Joueur;
import Jeu.IHM;
import Jeu.Monopoly;

public class PlateauUI extends JPanel
{

	protected IHM ihm;
	private Monopoly monopoly;
	private int[] positionprecedante;
	private BufferedImage myPicture;
	private JLabel[] images;

	
	
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
		
		positionprecedante=new int[ihm.getMonopoly().getJoueurs().size()];
		for(int i = 0; i<ihm.getMonopoly().getJoueurs().size();i++){
			positionprecedante[i]=0;
		}
		
		for(int i=0;i<40;i++)
		{
			JButton s=new JButton("");
			Carreau c = list[i];
			//c.getNomCarreau();
			
			String test =""+i;
			s.setName(test);
			s.setOpaque(false);
			s.setContentAreaFilled(false);
			s.setBorderPainted(true);
			 this.setLayout(null);
			 if (i==0){

				s.setBounds(813, 813, 118, 118);
			 }else if(i>0 && i<10){

				 s.setBounds(813-(i*74), 813, 74, 118);
				 
			 }else if (i==10){

				 s.setBounds(30, 813, 118, 118);
			 }else if (i>10 && i<20){

				 s.setBounds(30, 813-((i-10)*74), 118, 74);
				 
			 }else if (i==20){

				 s.setBounds(30, 30, 118, 118);
			 }else if(i>20 && i<30){

				 s.setBounds(74+((i-20)*74), 30, 74, 118);
			 }else if (i==30){

				 s.setBounds(813, 30, 118, 118);
			 }else if (i>30){

				 s.setBounds(813, 74+((i-30)*74), 118, 74);
				 
			 }
		    	s.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                String message;
		                
		                System.out.println("" +s.getName());
		                ihm.getInfos().setProprieteselectionner(s.getName().toString());
		                ihm.getInfos().setSelec( Integer.parseInt(s.getName()));
		                ihm.getInfos().selection();
		            }
		        });
			 
			 

			
			this.add(s);





		}
		
		images=new JLabel[ihm.getMonopoly().getJoueurs().size()];
		for(int i = 0; i<ihm.getMonopoly().getJoueurs().size();i++){
			images[i]= new JLabel();
		}
		
		for(int i=0;i<ihm.getMonopoly().getJoueurs().size();i++){
		myPicture = null;
		try {
			myPicture = ImageIO.read(new File("pion"+(i+1)+".png"));
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			images[i]=picLabel;
			if(i<3){
				images[i].setBounds(845+(i*25), 845,25, 25);
			}else{
				images[i].setBounds( 845+((i-3)*25), 870,25, 25);
			}
			add(images[i]);
			
		}
 
			
		
	}
	public void deplacePion(int carreau){
	//	int tmp=positionprecedante[ihm.getMonopoly().getJoueurs().getFirst().getNum()];
		int i=ihm.getMonopoly().getJoueurs().getFirst().getNum();
		
		for(int j=0;j<ihm.getMonopoly().getJoueurs().size();j++){
			if(carreau>0 && carreau<10){
					
				
				if(j<3){
					images[j].setBounds( 738+(j*25)-((carreau-1)*74), 865,25, 25);
					
				
				}else{
				
					images[j].setBounds( 738+((j-3)*25)-((carreau-1)*74), 890,25, 25);
				}
			}else if(carreau==10){
				if(j<3){
					images[j].setBounds(738+(j*25)-((carreau-1)*74), 900,25, 25 );
					
				
				}else{
				
					images[j].setBounds( 700-((carreau-1)*74), 865-((j-3)*25),25, 25);
				}
			}else if(carreau>10 && carreau<20){
				if(j<3){
					images[j].setBounds( 40+(j*25), 750-((carreau-11)*74),25, 25);
					
				
				}else{
				
					images[j].setBounds( 40+((j-3)*25), 775-((carreau-11)*74),25, 25);
				}
				
			}else if(carreau==20){
				if(j<3){
					images[j].setBounds( 40+(j*25), 720-((carreau-11)*74),25, 25);
					
				
				}else{
				
					images[j].setBounds( 40+((j-3)*25), 745-((carreau-11)*74),25, 25);
					
				}
			}else if(carreau>20 && carreau<30){
				if(j<3){
					images[j].setBounds( 148+(j*25)+((carreau-21)*74), 50,25, 25);
					
				
				}else{
				
					images[j].setBounds( 148+((j-3)*25)+((carreau-21)*74), 75,25, 25);
				}
				
			}else if(carreau==30){
				if(j<3){
					images[j].setBounds( 180+(j*25)+((carreau-21)*74), 50,25, 25);
					
				
				}else{
				
					images[j].setBounds( 180+((j-3)*25)+((carreau-21)*74), 75,25, 25);
				}
				
			}else if(carreau>30 && carreau<40){
				if(j<3){
					images[j].setBounds( 846+(j*25), 160+((carreau-31)*74),25, 25);
					
				
				}else{
				
					images[j].setBounds( 846+((j-3)*25), 185+((carreau-31)*74),25, 25);
				}
				
			}else if(carreau==0){
				
				if(i<3){
					images[i].setBounds(845+(i*25), 845,25, 25);
				}else{
					images[i].setBounds( 845+((i-3)*25), 870,25, 25);
				}
			}else{
				if(j<3){
					images[j].setBounds(70+(j*25), 835,25, 25 );
					
				
				}else{
				
					images[j].setBounds( 70+((j-3)*25), 860,25, 25);
				}

			}
		}
		
	}

	public int[] getPositionprecedante() {
		return positionprecedante;
	}

	public void setPositionprecedante(int[] positionprecedante) {
		this.positionprecedante = positionprecedante;
		
	}
	
	
}
