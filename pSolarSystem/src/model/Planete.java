package model;

public class Planete{
	private double rayon, distance;
	private EnumPlanete planete;
	
	public Planete(EnumPlanete planeteNom) {
		super();
		this.planete = planeteNom;
		setRayon();
		setDistance();
	}

	public double getRayon() {
		return rayon;
	}

	public void setRayon() {
		this.rayon = Variable.RAYON_SOLEIL/planete.getRayonProportion() * Variable.ECHELLE_TAILLE_PLANETE;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance() {
		this.distance = (planete.getDistance()/ Variable.KM_TO_PIXEL) / Variable.ECHELLE_DISTANCE_PLANETE;
	}

	public EnumPlanete getPlanete() {
		return planete;
	}

	@Override
	public String toString() {
		return planete.getNom() + "[rayon=" + rayon + ", distance=" + distance + ", planete=" + planete + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planete == null) ? 0 : planete.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Planete other = (Planete) obj;
		if (planete != other.planete)
			return false;
		return true;
	}
	
	
	
}
