package view;

import javafx.scene.paint.Color;
import model.EnumPlanete;
public enum PlaneteColor {
	MERCURE(EnumPlanete.MERCURE, Color.BROWN),
	VENUS(EnumPlanete.VENUS, Color.BEIGE),
	TERRE(EnumPlanete.TERRE, Color.BLUE),
	MARS(EnumPlanete.MARS, Color.CRIMSON),
	JUPITER(EnumPlanete.JUPITER, Color.BURLYWOOD),
	SATURNE(EnumPlanete.SATURNE, Color.PERU),
	URANUS(EnumPlanete.URANUS, Color.SKYBLUE),
	NEPTUNE(EnumPlanete.NEPTUNE, Color.TURQUOISE);

	private Color color;
	private EnumPlanete planete;
	PlaneteColor(EnumPlanete planete, Color color) {
		// TODO Auto-generated constructor stub
		this.color = color;
		this.planete = planete;
	}

	public Color getColor() {
		return color;
	}
	
	public EnumPlanete getPlanete() {
		return planete;
	}
	
	public static Color getPlaneteColor(EnumPlanete planete) {
		for(PlaneteColor pc : PlaneteColor.values()) {
			if(pc.getPlanete().equals(planete))
				return pc.getColor();
		}
		return null;
	}
	
}
