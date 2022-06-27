package model;

public enum EnumPlanete {
	MERCURE(285.5, 57.9, "Mercure", 88),
	VENUS(115, 108.2, "Venus", 224),
	TERRE(109.2, 149.5,"Terre", 365),
	MARS(205.4, 227.9, "Mars", 687),
	JUPITER(9.9, 778.6, "Jupiter", 4307),
	SATURNE(11.9, 1430, "Saturne", 10767),
	URANUS(27.4, 2900, "Uranus", 30660),
	NEPTUNE(28.2, 4500, "Neptune", 60225);

	private double rayonProportion, distance, tempsOrbite;
	private String nom;
	
	EnumPlanete(double rayonProportion, double distance, String nom, double tempsOrbite) {
		// TODO Auto-generated constructor stub
		this.rayonProportion = rayonProportion;
		this.distance = distance;
		this.nom = nom;
		this.tempsOrbite = tempsOrbite;
	}

	//Proportion du rayon d'une planète par rapport au soleil (1/x)
	public double getRayonProportion() {
		return rayonProportion;
	}
	
	//Distance par rapport au Soleil en Km
	public double getDistance() {
		return distance * 1000000;
	}
	
	public String getNom() {
		return this.nom;
	}

	public double getTempsOrbite() {
		return tempsOrbite;
	}

	
}
