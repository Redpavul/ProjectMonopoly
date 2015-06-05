package Data;

import Jeu.Monopoly;

public class CarreauTirage extends CarreauAction
{

		//super(nomCarreau, numeroCarreau);
		private int [] tabChance;
		private int [] tabCaisse;
		//private Monopoly monopoly;
		private int positionChance;
		private int nbDeCarteChance;
		private int positionCaisse;
		private int nbDeCarteCaisse;
		private boolean carteSortieDePrisonChance;
		private boolean carteSortieDePrisonCaisse;
		private String type;
		public CarreauTirage(String nom, int num, int id, String rype){
			super(nom,num);
			setType(getType());
			//this.setMonopoly(monopoly);
			this.setPositionChance(1);
			int nbcarteChance=16;
			this.setNbDeCarteChance(nbcarteChance);
			this.setPositionCaisse(1);
			int nbcarteCaisse=16;
			this.setNbDeCarteCaisse(nbcarteCaisse);
			this.setCarteSortieDePrisonChance(true);
			this.setCarteSortieDePrisonCaisse(true);
			int [] tab3Chance = new int [nbcarteChance+1];
			int [] tab2Chance = new int [nbcarteChance+1];
			for(int a = 1;a<=nbcarteChance;a++){//on crée un paquet de carte trié
				tab3Chance[a]=a;
			}
				/*System.out.println("");
				for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab3[c]+"|");
				}*/

			//System.out.println("");
			int alea;
			for(int a = 1;a<=nbcarteChance;a++){//on pioche aléatoirement chaque carte 
										  //du premier paquer vers un second paquet
				alea=(int)(Math.random()*(nbcarteChance-a+1))+1;
				tab2Chance[a]=tab3Chance[alea];
				/*System.out.println("");
				for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab2[c]+"|");
				}*/
				for(int b = alea;b<nbcarteChance;b++){
					tab3Chance[b]=tab3Chance[b+1];
				}
				tab3Chance[nbcarteChance]=0;
				/*
	        	System.out.println("");
	        	for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab3[c]+"|");
				}*/
			}
			this.setTabChance(tab2Chance);
			int [] tab3Caisse = new int [nbcarteCaisse+1];
			int [] tab2Caisse = new int [nbcarteCaisse+1];
			for(int a = 1;a<=nbcarteCaisse;a++){//on crée un paquet de carte trié
				tab3Caisse[a]=a;
			}
				/*System.out.println("");
				for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab3[c]+"|");
				}*/

			//System.out.println("");
			int alea2;
			for(int a = 1;a<=nbcarteCaisse;a++){//on pioche aléatoirement chaque carte 
										  //du premier paquer vers un second paquet
				alea2=(int)(Math.random()*(nbcarteCaisse-a+1))+1;
				tab2Caisse[a]=tab3Caisse[alea2];
				/*System.out.println("");
				for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab2[c]+"|");
				}*/
				for(int b = alea2;b<nbcarteCaisse;b++){
					tab3Caisse[b]=tab3Caisse[b+1];
				}
				tab3Caisse[nbcarteCaisse]=0;
				/*
	        	System.out.println("");
	        	for(int c = 1;c<=nbcarte;c++){
					System.out.print(""+c+","+tab3[c]+"|");
				}*/
			}
			this.setTabCaisse(tab2Caisse);
		}
		public int[] getTabChance() {
			return tabChance;
		}
		public void setTabChance(int[] tabChance) {
			this.tabChance = tabChance;
		}
		public int[] getTabCaisse() {
			return tabCaisse;
		}
		public void setTabCaisse(int[] tabCaisse) {
			this.tabCaisse = tabCaisse;
		}
		public int getPositionChance() {
			return this.positionChance;
		}
		public void setPositionChance(int positionChance){
			this.positionChance = positionChance;
		}
		public int getNbDeCarteChance() {
			return this.nbDeCarteChance;
		}
		public void setNbDeCarteChance(int nbDeCarteChance) {
			this.nbDeCarteChance = nbDeCarteChance;
		}
		public int getPositionCaisse() {
			return this.positionCaisse;
		}
		public void setPositionCaisse(int positionCaisse){
			this.positionCaisse = positionCaisse;
		}
		public int getNbDeCarteCaisse() {
			return this.nbDeCarteCaisse;
		}
		public void setNbDeCarteCaisse(int nbDeCarteCaisse) {
			this.nbDeCarteCaisse = nbDeCarteCaisse;
		}
		public Monopoly getMonopoly() {
			return monopoly;
		}
		public void setMonopoly(Monopoly monopoly) {
			this.monopoly = monopoly;
		}
		public boolean getCarteSortieDePrisonChance() {
			return carteSortieDePrisonChance;
		}
		public void setCarteSortieDePrisonChance(boolean carteSortieDePrisonChance) {
			this.carteSortieDePrisonChance = carteSortieDePrisonChance;
		}
		public boolean getCarteSortieDePrisonCaisse() {
			return carteSortieDePrisonCaisse;
		}
		public void setCarteSortieDePrisonCaisse(boolean carteSortieDePrisonCaisse) {
			this.carteSortieDePrisonCaisse = carteSortieDePrisonCaisse;
		}
		public void pioche(){
			String type=getType();
			if(type=="Chance"){
				while(effetChance(getPositionChance())){
					setPositionChance(getPositionChance()+1);
					if(getPositionChance()>getNbDeCarteChance()){
						setPositionChance(1);
					}
				}
			}
			else{
				while(effetCaisse(getPositionCaisse())){
					setPositionCaisse(getPositionCaisse()+1);
					if(getPositionCaisse()>getNbDeCarteCaisse()){
						setPositionCaisse(1);
					}
				}
			}
		}
		public boolean effetChance(int id){
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
		public boolean effetCaisse(int id){
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
		private String getType() {
			return type;
		}
		private void setType(String type) {
			this.type = type;
		}
			
		/*
		public boolean effetChance1(){
			if(getMonopoly().getCarteSortieDePrisonChance()){
				System.out.println("Vous recevez une carte sortie de Prison");
				Joueur j =getMonopoly().getJoueurs().getFirst();
				j.setCarteSortieDePrison=(j.getCarteSortieDePrison+1);
				System.out.println("vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
				getMonopoly().setCarteSortieDePrisonChance(false);
				return true;
			}else{return false;}
			
		}
		public void effetChance2(){
			System.out.println("Reculez de trois cases.");
			getMonopoly().getJoueurs().getFirst().reculer(3);
		}
		public void effetChance3(){
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
		public void effetChance4(){
			System.out.println("Amende pour excès de vitesse 15€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-15);
		}
		public void effetChance5(){
			System.out.println("Faites des réparations :");
			System.out.println("  -25€ par maison");
			System.out.println("  -100€ par hôtel");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			int nbMaison=0;
			int nbHotel=0;
			for(ProprieteAConstruire pro : j.getProprietesAConstruire()){
				nbMaison=nbMaison+pro.getNbMaisons();
				nbHotel=nbHotel+pro.getNbHotels();
			}
			int aPayer=nbMaison*25+nbHotel*100;
			System.out.println("");
			System.out.println("Vous devez payer "+aPayer+"€");
			j.setCash(j.getCash()-aPayer);
		}
		public void effetChance6(){
			System.out.println("Amende pour ivresse 20€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-20);
		}
		public void effetChance7(){
			System.out.println("Avancer jusqu'à la case Départ");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.avance(40-j.getPositionCourante().getNumeroCarreau());
		}
		public void effetChance8(){
			System.out.println("Aller en prison, ne passez pas par la case Départ");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setPrison(true);//fonction qui fait avancer un joueur directement à la case départ.
		}
		public void effetChance9(){
			System.out.println("Rendez-vous à l'Avenue Henri-Martin");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(25-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(25-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(25+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		public void effetChance10(){
			System.out.println("Rendez-vous à la gare de Lyon");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(16-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(16-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(16+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		public void effetChance11(){
			System.out.println("Payer pour frais de scolarité 150€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-150);
		}
		public void effetChance12(){
			System.out.println("Vous avez gagnez le prix de mots croisés. Recevez 100€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+100);
		}
		public void effetChance13(){
			System.out.println("La Banque vous verse un dividende de 50€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+50);
		}
		public void effetChance14(){
			System.out.println("Rendez-vous à la Rue de la Paix.");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(39-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(39-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(39+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		public void effetChance15(){
			System.out.println("Votre immeuble et votre prêt rapportent. Vous devez toucher 150€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+150);
		}
		public void effetChance16(){
			System.out.println("Accédez au Boulevard de la Villette.");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(12-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(12-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(12+40-j.getPositionCourante().getNumeroCarreau());
			}
		
		}
		
		
		public boolean effetCaisse1(){
			if(getMonopoly().getCarteSortieDePrisonCaisse()){
				System.out.println("Vous recevez une carte sortie de Prison");
				Joueur j =getMonopoly().getJoueurs().getFirst();
				j.setCarteSortieDePrison=(j.getCarteSortieDePrison+1);
				System.out.println("vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
				getMonopoly().setCarteSortieDePrisonCaisse(false);
				return true;
			}else{return false;}
			
		}
		public void effeCaisset2(){
			System.out.println("Payer une amende de 10€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-10);
		}
		public void effetCaisse3(){
			System.out.println("C'est votre anniversaire, chaque joueur doit vous donner 10€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			
			for(Joueur joueur : getMonopoly().getJoueurs()){
				if(joueur!=j){
					joueur.paye(10);
					j.loyer(10);
				}
			}
		}
		public void effetCaisse4(){
			System.out.println("Erreur de la banque en votre faveur, recevez 200€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+200);
		}
		public void effetCaisse5(){
			System.out.println("Retournez à Belleville");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			if(2-j.getPositionCourante().getNumeroCarreau()>0){
				j.avance(2-j.getPositionCourante().getNumeroCarreau());
			}else{
				j.avance(2+40-j.getPositionCourante().getNumeroCarreau());
			}
		}
		public void effetCaisse6(){
			System.out.println("Payez la note du médecin 50€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-50);
		}
		public void effetCaisse7(){
			System.out.println("Les contributions vous remboursent la somme de 20€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+20);
		}
		public void effetCaisse8(){
			System.out.println("Payez à l'hôpital 100€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-100);
		}
		public void effetCaisse9(){
			System.out.println("Vous héritez : 100€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+20);
		}
		public void effetCaisse10(){
			System.out.println("Aller en prison, ne passez pas par la case Départ");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setPrison(true);//fonction qui fait avancer un joueur directement à la case départ.
		}
		public void effetCaisse11(){
			System.out.println("Payer votre Police d'Assurance : 50€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()-50);
		}
		public void effetCaisse12(){
			System.out.println("La vente de votre stock vous rapporte 50€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+50);
		}
		public void effetCaisse13(){
			System.out.println("Avancer jusqu'à la case Départ");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.avance(40-j.getPositionCourante().getNumeroCarreau());
		}
		public void effetCaisse14(){
			System.out.println("Recevez votre intérêt sur l'emprunt à 7% : 25€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+25);
		}
		public void effetCaisse15(){
			System.out.println("Recevez votre revenu annuel : 100€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+100);
		}
		public void effetCaisse16(){
			System.out.println("Vous avez gagné le deuxième prix de beauté: recevez 10€");
			Joueur j =getMonopoly().getJoueurs().getFirst();
			j.setCash(j.getCash()+10);
		}*/
	

		
	}