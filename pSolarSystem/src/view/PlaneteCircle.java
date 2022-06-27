package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Planete;
import model.Variable;

public class PlaneteCircle extends Circle{
	private Color color;
	private Planete planete;
	
	public PlaneteCircle(Planete planete, Color color) {
		super(planete.getRayon(), color);
		this.color = color;
		this.planete = planete;
	}

	public Color getColor() {
		return color;
	}

	public Planete getPlanete() {
		return planete;
	}
	
	public void actualize() {
		setRadius(planete.getRayon());
	}
	
}
