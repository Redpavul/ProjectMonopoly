package Data;

import Jeu.Monopoly;

public class CarreauTirage extends CarreauAction
{

		//super(nomCarreau, numeroCarreau);
		private int [] tab;
		//private Monopoly monopoly;
		private int position;
		private int nbDeCarte;
		private boolean carteSortieDePrison;
		public CarreauTirage(String nom, int num, int id){
			super(nom,num);
			//this.setMonopoly(monopoly);
			this.setPosition(1);
			int nbcarte=16;
			this.setNbDeCarte(nbcarte);
			this.setCarteSortieDePrison(true);
			int [] tab3 = new int [nbcarte+1];
			int [] tab2 = new int [nbcarte+1];
			for(int a = 1;a<=nbcarte;a++){//on crée un paquet de carte trié
				tab3[a]=a;
			}
				/*System.out.println("");
				for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab3[c]+"|");
				}*/

			//System.out.println("");
			int alea;
			for(int a = 1;a<=nbcarte;a++){//on pioche aléatoirement chaque carte 
										  //du premier paquer vers un second paquet
				alea=(int)(Math.random()*(nbcarte-a+1))+1;
				tab2[a]=tab3[alea];
				/*System.out.println("");
				for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab2[c]+"|");
				}*/
				for(int b = alea;b<nbcarte;b++){
					tab3[b]=tab3[b+1];
				}
				tab3[nbcarte]=0;
				/*
	        	System.out.println("");
	        	for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab3[c]+"|");
				}*/
			}
			this.setTab(tab2);
		}
		public int[] getTab() {
			return tab;
		}
		public void setTab(int[] tab) {
			this.tab = tab;
		}
		public int getPosition() {
			return this.position;
		}
		public void setPosition(int position){
			this.position = position;
		}
		public int getNbDeCarte() {
			return this.nbDeCarte;
		}
		public void setNbDeCarte(int nbDeCarte) {
			this.nbDeCarte = nbDeCarte;
		}
		public Monopoly getMonopoly() {
			return monopoly;
		}
		public void setMonopoly(Monopoly monopoly) {
			this.monopoly = monopoly;
		}
		public boolean getCarteSortieDePrison() {
			return carteSortieDePrison;
		}
		public void setCarteSortieDePrison(boolean carteSortieDePrison) {
			this.carteSortieDePrison = carteSortieDePrison;
		}
		public void pioche(){
			while(effet(getPosition())){
				setPosition(getPosition()+1);
				if(getPosition()>getNbDeCarte()){
					setPosition(1);
				}
			}
		}
		public boolean effet(int id){
			boolean ok=true;
			if(id ==1){ok = effet0();}
			else if(id ==2){effet0();}
			else if(id ==3){effet0();}
			else if(id ==4){effet0();}
			else if(id ==5){effet0();}
			else if(id ==6){effet0();}
			else if(id ==7){effet0();}
			else if(id ==8){effet0();}
			else if(id ==9){effet0();}
			else if(id ==10){effet0();}
			else if(id ==11){effet0();}
			else if(id ==12){effet0();}
			else if(id ==13){effet0();}
			else if(id ==14){effet0();}
			else if(id ==15){effet0();}
			else if(id ==16){effet0();}
			return ok;
		}
		public boolean effet0(){
			return false;
		}
			
		/*
		public boolean effet1(){
			if(getCarteSortieDePrison()){
				Joueur j =getMonopoly().getJoueurs().getFirst();
				j.setCarteSortieDePrison=(j.getCarteSortieDePrison+1);
				System.out.println("vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
				setCarteSortieDePrison(false);
				return true;
			}else{return false;}
			
		}
		public void effet2(){
			System.out.println("Reculez de trois cases.");
			getMonopoly().getJoueurs().getFirst().reculer(3);
		}
		public void effet3(){
			System.out.println("Vous êtes imposés pour les réparations de voirie à raison de :");
			System.out.println("  -40€ par maison");
			System.out.println("  -115€ par hôtel");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			int nbMaison=0;
			int nbHotel=0;
			for(ProprieteAConstruire pro : j.getProprietesAConstruire()){
				nbMaison=nbMaison+pro.getNbMaisons();
				nbHotel=nbHotel+pro.getNbHotels();
			}
			int aPayer=nbMaison*40+nbHotel*115;
			System.out.println("");
			System.out.println("Vous devez payer "+aPayer+"€");
			j.setCash(j.getCash()-aPayer);
		}
		public void effet4(){
			System.out.println("Amende pour excès de vitesse 15€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-15);
		}
		public void effet5(){
			System.out.println("Faites des réparations :");
			System.out.println("  -25€ par maison");
			System.out.println("  -100€ par hôtel");
		}
		public void effet6(){
			System.out.println("Amende pour ivresse 20€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-20);
		}
		public void effet7(){
			System.out.println("Avancer jusqu'à la case Départ");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.avance(40-j.getPositionCourante().getNumeroCarreau());
		}
		public void effet8(){
			System.out.println("Aller en prison, ne passez pas par la case Départ");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.avanceEnPrison();//fonction qui fait avancer un joueur directement à la case départ.
		}
		public void effet9(){
			System.out.println("Rendez-vous à l'Avenue Henri-Martin");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(25-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(25-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(25+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		public void effet10(){
			System.out.println("Rendez-vous à la gare de Lyon");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(16-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(16-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(16+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		public void effet11(){
			System.out.println("Payer pour frais de scolarité 150€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-150);
		}
		public void effet12(){
			System.out.println("Vous avez gagnez le prix de mots croisés. Recevez 100€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+100);
		}
		public void effet13(){
			System.out.println("La Banque vous verse un dividende de 50€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+50);
		}
		public void effet14(){
			System.out.println("Rendez-vous à la Rue de la Paix.");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(39-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(39-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(39+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		public void effet15(){
			System.out.println("Votre immeuble et votre prêt rapportent. Vous devez toucher 150€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+150);
		}
		public void effet16(){
			System.out.println("Accédez au Boulevard de la Villette.");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(12-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(12-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(12+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		}*/
	

		
	}