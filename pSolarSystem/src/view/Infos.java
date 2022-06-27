package view;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.Planete;

public class Infos extends Label{
	Planete planete;
	private static final double DECALAGE_TEXTE = 30;
	public Infos(Planete planete) {
		this.planete = planete;
//		String tmp = planete.getPlanete().getNom() + "\nRayon : " + planete.getRayon() + "\nDistance : " + planete.getDistance();
		String tmp = planete.getPlanete().getNom();
		setTextFill(Color.WHITE);
		setText(tmp);
		setTranslateX(planete.getDistance() + 55);
		setTranslateY(-planete.getRayon() - DECALAGE_TEXTE);
	}
	
}
