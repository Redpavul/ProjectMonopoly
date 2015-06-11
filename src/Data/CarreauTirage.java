package Data;

import Jeu.Monopoly;

public class CarreauTirage extends CarreauAction {

    public CarreauTirage(String nom, int num, Monopoly monopoly) {
	super(nom, num, monopoly);

    }

    public void action() {
	String nom = getNomCarreau();
	if (nom.equalsIgnoreCase("Chance")) {
	    while (!effetChance(getMonopoly().getTabChance()[getMonopoly().getPositionChance()])) {
		getMonopoly().setPositionChance(getMonopoly().getPositionChance() + 1);
		if (getMonopoly().getPositionChance() > getMonopoly().getNbDeCarteChance()) {
		    getMonopoly().setPositionChance(1);
		}
	    }
	} else {
	    while (!effetCaisse(getMonopoly().getTabCaisse()[getMonopoly().getPositionCaisse()])) {
		getMonopoly().setPositionCaisse(getMonopoly().getPositionCaisse() + 1);
		if (getMonopoly().getPositionCaisse() > getMonopoly().getNbDeCarteCaisse()) {
		    getMonopoly().setPositionCaisse(1);
		}
	    }
	}
    }

    public boolean effetChance(int id) {
	boolean ok = true;
	if (id == 1) {
	    ok = effetChance1();
	} else if (id == 2) {
	    effetChance2();
	} else if (id == 3) {
	    effetChance3();
	} else if (id == 4) {
	    effetChance4();
	} else if (id == 5) {
	    effetChance5();
	} else if (id == 6) {
	    effetChance6();
	} else if (id == 7) {
	    effetChance7();
	} else if (id == 8) {
	    effetChance8();
	} else if (id == 9) {
	    effetChance9();
	} else if (id == 10) {
	    effetChance10();
	} else if (id == 11) {
	    effetChance11();
	} else if (id == 12) {
	    effetChance12();
	} else if (id == 13) {
	    effetChance13();
	} else if (id == 14) {
	    effetChance14();
	} else if (id == 15) {
	    effetChance15();
	} else if (id == 16) {
	    effetChance16();
	}
	return ok;
    }

    public boolean effetCaisse(int id) {
	boolean ok = true;
	if (id == 1) {
	    ok = effetCaisse1();
	} else if (id == 2) {
	    effetCaisse2();
	} else if (id == 3) {
	    effetCaisse3();
	} else if (id == 4) {
	    effetCaisse4();
	} else if (id == 5) {
	    effetCaisse5();
	} else if (id == 6) {
	    effetCaisse6();
	} else if (id == 7) {
	    effetCaisse7();
	} else if (id == 8) {
	    effetCaisse8();
	} else if (id == 9) {
	    effetCaisse9();
	} else if (id == 10) {
	    effetCaisse10();
	} else if (id == 11) {
	    effetCaisse11();
	} else if (id == 12) {
	    effetCaisse12();
	} else if (id == 13) {
	    effetCaisse13();
	} else if (id == 14) {
	    effetCaisse14();
	} else if (id == 15) {
	    effetCaisse15();
	} else if (id == 16) {
	    effetCaisse16();
	}
	return ok;
    }

    public boolean effet0() {
	return false;
    }

    public boolean effetChance1() {
	if (getMonopoly().getCarteSortieDePrisonChance()) {
	    System.out.println("Vous recevez une carte sortie de Prison");
	    Joueur j = getMonopoly().getJoueurs().getFirst();
	    j.setCarteSortieDePrison(j.getCarteSortieDePrison() + 1);
	    System.out.println("Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
	    getMonopoly().setCarteSortieDePrisonChance(false);
	    return true;
	} else {
	    return false;
	}

    }

    public void effetChance2() {
	System.out.println("Reculez de trois cases.");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	if (j.getPositionCourante().getNumeroCarreau() > 2) {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[j.getPositionCourante().getNumeroCarreau() - 3]);
	} else {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[j.getPositionCourante().getNumeroCarreau() + 37]);

	    System.out.println("Vous passez par la case dépar et recevez 200€");
	    j.setCash(j.getCash() + 200);
	}
    }

    public void effetChance3() {
	System.out.println("Vous êtes imposés pour les réparations de voirie à raison de :");
	System.out.println("  -40€ par maison");
	System.out.println("  -115€ par hôtel");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	int nbMaison = 0;
	int nbHotel = 0;
	for (ProprieteAConstruire pro : j.getProprietes()) {
	    nbMaison = nbMaison + pro.getNbMaisons();
	    nbHotel = nbHotel + pro.getNbHotels();
	}
	int aPayer = nbMaison * 40 + nbHotel * 115;
	System.out.println("");
	System.out.println("Vous devez payer " + aPayer + "à");
	j.setCash(j.getCash() - aPayer);
    }

    public void effetChance4() {
	System.out.println("Amende pour excès de vitesse 15€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() - 15);
    }

    public void effetChance5() {
	System.out.println("Faites des réparations :");
	System.out.println("  -25€ par maison");
	System.out.println("  -100€ par hôtel");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	int nbMaison = 0;
	int nbHotel = 0;
	for (ProprieteAConstruire pro : j.getProprietes()) {
	    nbMaison = nbMaison + pro.getNbMaisons();
	    nbHotel = nbHotel + pro.getNbHotels();
	}
	int aPayer = nbMaison * 25 + nbHotel * 100;
	System.out.println("");
	System.out.println("Vous devez payer " + aPayer + " à la banque");
	j.setCash(j.getCash() - aPayer);
    }

    public void effetChance6() {
	System.out.println("Amende pour ivresse 20€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() - 20);
    }

    public void effetChance7() {
	System.out.println("Avancer jusqu'à la case Départ et recevez 200€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setPositionCourante(getMonopoly().getListCarreaux()[40 - j.getPositionCourante().getNumeroCarreau()]);
	j.setCash(j.getCash()+200);
    }

    public void effetChance8() {
	System.out.println("Aller en prison, ne passez pas par la case Départ");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setPrison(true);//fonction qui fait avancer un joueur directement � la case d�part.
    }

    public void effetChance9() {
	System.out.println("Rendez-vous à l'Avenue Henri-Martin");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	if (25 - j.getPositionCourante().getNumeroCarreau() > 0) {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[25 - j.getPositionCourante().getNumeroCarreau()]);
	} else {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[40 + 25 - j.getPositionCourante().getNumeroCarreau()]);
		System.out.println("Vous passez par la case départ et recevez 200€");
		j.setCash(j.getCash() + 200);
	}
    }

    public void effetChance10() {
	System.out.println("Rendez-vous à la gare de Lyon");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	if (16 - j.getPositionCourante().getNumeroCarreau() > 0) {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[16 - j.getPositionCourante().getNumeroCarreau()]);
	} else {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[40 + 16 - j.getPositionCourante().getNumeroCarreau()]);
		System.out.println("Vous passez par la case départ et recevez 200€");
		j.setCash(j.getCash() + 200);
	}
    }

    public void effetChance11() {
	System.out.println("Payer pour frais de scolarité 150€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() - 150);
    }

    public void effetChance12() {
	System.out.println("Vous avez gagnez le prix de mots croisés. Recevez 100€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 100);
    }

    public void effetChance13() {
	System.out.println("La Banque vous verse un dividende de 50€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 50);
    }

    public void effetChance14() {
	System.out.println("Rendez-vous à la Rue de la Paix.");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	if (39 - j.getPositionCourante().getNumeroCarreau() > 0) {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[39 - j.getPositionCourante().getNumeroCarreau()]);
	} else {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[40 + 39 - j.getPositionCourante().getNumeroCarreau()]);
		System.out.println("Vous passez par la case départ et recevez 200€");
		j.setCash(j.getCash() + 200);
	}
    }

    public void effetChance15() {
	System.out.println("Votre immeuble et votre prêt rapportent. Vous devez toucher 150€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 150);
    }

    public void effetChance16() {
	System.out.println("Accédez au Boulevard de la Villette.");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	if (12 - j.getPositionCourante().getNumeroCarreau() > 0) {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[12 - j.getPositionCourante().getNumeroCarreau()]);
	} else {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[40 + 12 - j.getPositionCourante().getNumeroCarreau()]);
		System.out.println("Vous passez par la case départ et recevez 200€");
		j.setCash(j.getCash() + 200);
	}

    }

    public boolean effetCaisse1() {
	if (getMonopoly().getCarteSortieDePrisonCaisse()) {
	    System.out.println("Vous recevez une carte sortie de Prison");
	    Joueur j = getMonopoly().getJoueurs().getFirst();
	    j.setCarteSortieDePrison(j.getCarteSortieDePrison() + 1);
	    System.out.println("Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
	    getMonopoly().setCarteSortieDePrisonCaisse(false);
	    return true;
	} else {
	    return false;
	}

    }

    public void effetCaisse2() {
	System.out.println("Payer une amende de 10€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() - 10);
    }

    public void effetCaisse3() {
	System.out.println("C'est votre anniversaire, chaque joueur doit vous donner 10€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	Monopoly m = getMonopoly();
	for (Joueur joueur : getMonopoly().getJoueurs()) {
	    if (joueur != j) {
		int arg = joueur.getCash();
		if (arg >= 10) {
		    joueur.setCash(joueur.getCash() - 10);
		    j.setCash(j.getCash() + 10);
		} else {
		    joueur.setCash(joueur.getCash() - arg);
		    j.setCash(j.getCash() + arg);
		}
	    }
	}
    }

    public void effetCaisse4() {
	System.out.println("Erreur de la banque en votre faveur, recevez 200€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 200);
    }

    public void effetCaisse5() {
	System.out.println("Retournez à Belleville");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	if (2 - j.getPositionCourante().getNumeroCarreau() > 0) {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[2 - j.getPositionCourante().getNumeroCarreau()]);
	} else {
	    j.setPositionCourante(getMonopoly().getListCarreaux()[40 + 2 - j.getPositionCourante().getNumeroCarreau()]);
	}
    }

    public void effetCaisse6() {
	System.out.println("Payez la note du médecin 50€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() - 50);
    }

    public void effetCaisse7() {
	System.out.println("Les contributions vous remboursent la somme de 20€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 20);
    }

    public void effetCaisse8() {
	System.out.println("Payez à l'hôpital 100€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() - 100);
    }

    public void effetCaisse9() {
	System.out.println("Vous héritez : 100€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 20);
    }

    public void effetCaisse10() {
	System.out.println("Aller en prison, ne passez pas par la case Départ");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setPrison(true);//fonction qui fait avancer un joueur directement � la case d�part.
    }

    public void effetCaisse11() {
	System.out.println("Payer votre Police d'Assurance : 50€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() - 50);
    }

    public void effetCaisse12() {
	System.out.println("La vente de votre stock vous rapporte 50€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 50);
    }

    public void effetCaisse13() {
	System.out.println("Avancer jusqu'à la case Départ et recevez 200€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setPositionCourante(getMonopoly().getListCarreaux()[40 - j.getPositionCourante().getNumeroCarreau()]);
	j.setCash(j.getCash() + 200);
    }

    public void effetCaisse14() {
	System.out.println("Recevez votre interêt sur l'emprunt à 7% : 25€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 25);
    }

    public void effetCaisse15() {
	System.out.println("Recevez votre revenu annuel : 100€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 100);
    }

    public void effetCaisse16() {
	System.out.println("Vous avez gagné le deuxième prix de beauté: recevez 10€");
	Joueur j = getMonopoly().getJoueurs().getFirst();
	j.setCash(j.getCash() + 10);
    }

    public void afficherCommu() {
	System.out.println("1- Vous recevez une carte sortie de Prison");
	System.out.println("2- Payer une amende de 10€");
	System.out.println("3- C'est votre anniversaire, chaque joueur doit vous donner 10€");
	System.out.println("4- Erreur de la banque en votre faveur, recevez 200€");
	System.out.println("5- Retournez à Belleville");
	System.out.println("6- Payez la note du médecin 50€");
	System.out.println("7- Les contributions vous remboursent la somme de 20€");
	System.out.println("8- Payez à l'hôpital 100€");
	System.out.println("9- Vous héritez : 100€");
	System.out.println("10- Aller en prison, ne passez pas par la case Départ");
	System.out.println("11- Payer votre Police d'Assurance : 50€");
	System.out.println("12- La vente de votre stock vous rapporte 50€");
	System.out.println("13- Avancer jusqu'à la case Départ");
	System.out.println("14- Recevez votre intérêt sur l'emprunt à 7% : 25€");
	System.out.println("15- Recevez votre revenu annuel : 100€");
	System.out.println("16- Vous avez gagne le deuxième prix de beauté: recevez 10€");

    }

    public void afficherChance()
    {
	System.out.println("1- Vous recevez une carte sortie de Prison");
	System.out.println("2- Reculez de trois cases.");
	System.out.println("3- Vous êtes imposés pour les réparations de voirie à raison de :");
	System.out.println("4- Amende pour excès de vitesse 15€");
	System.out.println("5- Faites des réparations ");
	System.out.println("6- Amende pour ivresse 20€");
	System.out.println("7- Avancer jusqu'à la case Départ");
	System.out.println("8- Aller en prison, ne passez pas par la case Départ");
	System.out.println("9- Rendez-vous à l'Avenue Henri-Martin");
	System.out.println("10- Rendez-vous à la gare de Lyon");
	System.out.println("11- Payer pour frais de scolarité 150€");
	System.out.println("12- Vous avez gagnez le prix de mots croisés. Recevez 100€");
	System.out.println("13- La Banque vous verse un dividende de 50€");
	System.out.println("14- Rendez-vous à la Rue de la Paix.");
	System.out.println("15- Votre immeuble et votre prêt rapportent. Vous devez toucher 150€");
	System.out.println("16- Accédez au Boulevard de la Villette.");
    }
}
