package model;

public class Variable {
	//Échelle du soleil
	public static double ECHELLE_SOLEIL = 1;
	//Nombre de km pour 1 pixel
	public static double KM_TO_PIXEL = 50000;
	//Augmentation de la taille des planètes (échelle x/1)
	public static double ECHELLE_TAILLE_PLANETE = 3;
	//Diminution de la distance Soleil - planète (échelle 1/x)
	public static double ECHELLE_DISTANCE_PLANETE = 20;
	//Rayon du Soleil
	public static double RAYON_SOLEIL = ((696340 / KM_TO_PIXEL) / ECHELLE_SOLEIL);
	
	

	public static void setECHELLE_SOLEIL(double eCHELLE_SOLEIL) {
		ECHELLE_SOLEIL = eCHELLE_SOLEIL;
	}

	public static void setKM_TO_PIXEL(double kM_TO_PIXEL) {
		KM_TO_PIXEL = kM_TO_PIXEL;
	}
	
	public static void setECHELLE_TAILLE_PLANETE(double eCHELLE_TAILLE_PLANETE) {
		ECHELLE_TAILLE_PLANETE = eCHELLE_TAILLE_PLANETE;
	}
	
	public static void setECHELLE_DISTANCE_PLANETE(double eCHELLE_DISTANCE_PLANETE) {
		ECHELLE_DISTANCE_PLANETE = eCHELLE_DISTANCE_PLANETE;
	}

	public static void setRAYON_SOLEIL() {
		RAYON_SOLEIL = ((696340 / KM_TO_PIXEL) / ECHELLE_SOLEIL);
	}
	
	
}
