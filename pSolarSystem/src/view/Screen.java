package view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.EnumPlanete;
import model.Planete;
import model.Variable;

public class Screen extends StackPane{
	private Circle crclSun;
	private List<Planete> listePlanete;
	private List<PlaneteCircle> listePlaneteC;
	private List<Infos> listeInfos;
	private List<PathTransition> listeAnimationOrbite;
	private List<Circle> listeLigneOrbitale;
	
	public Screen() {
		// TODO Auto-generated constructor stub
		listePlanete = new ArrayList<>();
		listePlaneteC = new ArrayList<>();
		listeInfos = new ArrayList<>();
		for(EnumPlanete p : EnumPlanete.values()) {
			//Création de la planète model + ajout dans liste
			Planete newPlanete = new Planete(p);
			listePlanete.add(newPlanete);
			//Création de la planète view 
			PlaneteCircle planeteC = new PlaneteCircle(newPlanete, PlaneteColor.getPlaneteColor(newPlanete.getPlanete()));
			listePlaneteC.add(planeteC);
			//Création des infos view de la planète
			Infos info = new Infos(newPlanete);
			listeInfos.add(info);
			getChildren().addAll(planeteC, info);
		}
		
		getChildren().addAll(getCrclSun());
		System.out.println(listePlanete);
		

		//Ligne orbitale
		listeLigneOrbitale = new ArrayList<>();
		createLigneOrbitale();
		showLigneOrbitale(false);
		
		//Animation d'orbite
		listeAnimationOrbite = new ArrayList<>();
		orbiteAnimation();

		//Background noir
		BackgroundFill bgFill = new BackgroundFill(Color.BLACK, null, null);
		Background bg = new Background(bgFill);
		setBackground(bg);
	}
	
	public Circle getCrclSun() {
		if(crclSun == null) {
			crclSun = new Circle(Variable.RAYON_SOLEIL, Color.YELLOW);
		}
		return crclSun;
	}

	public void createLigneOrbitale() {
		for(Planete p : listePlanete) {
			Circle orbite = new Circle(p.getDistance() + Variable.RAYON_SOLEIL);
			orbite.setStroke(Color.GREY);
			orbite.setFill(Color.TRANSPARENT);
			listeLigneOrbitale.add(orbite);
		}
		getChildren().addAll(listeLigneOrbitale);
	}
	
	public void actualizeLigneOrbitale() {
		getChildren().removeAll(listeLigneOrbitale);
		listeLigneOrbitale.clear();
		createLigneOrbitale();
	}
	
	public void showLigneOrbitale(boolean val) {
		for(Circle c : listeLigneOrbitale) {
			c.setVisible(val);
		}
	}
	
	public Planete getPlanete(String nomPlanete) {
		for(Planete p : listePlanete) {
			if(p.getPlanete().getNom().equals(nomPlanete))
				return p;
		}
		return null;
	}
	
	public PlaneteCircle getPlaneteC(Planete planete) {
		for(PlaneteCircle pC : listePlaneteC) {
			if(planete.equals(pC.getPlanete()))
				return pC;
		}
		return null;
	}
	
	public Infos getInfos(Planete planete) {
		for(Infos info : listeInfos) {
			if(info.getText().equals(planete.getPlanete().getNom()))
				return info;
		}
		return null;
	}
	
	public Circle createOrbitePath(Planete planete) {
		Circle orbitePath = new Circle(planete.getDistance() + Variable.RAYON_SOLEIL);
		orbitePath.setStroke(Color.GREY);
		orbitePath.setFill(Color.TRANSPARENT);
		return orbitePath;
		
	}
	
	public PathTransition createOrbite(Planete planete) {
		
		PathTransition orbite = new PathTransition();
		orbite.setNode(getPlaneteC(planete));
		orbite.setDuration(Duration.seconds(planete.getPlanete().getTempsOrbite()));
		orbite.setPath(createOrbitePath(planete));
		orbite.setCycleCount(PathTransition.INDEFINITE);
		orbite.setInterpolator(Interpolator.LINEAR);
		
		return orbite;
	}
	
	public PathTransition createOrbiteInfo(Planete planete) {
		
		PathTransition orbite = new PathTransition();
		orbite.setNode(getInfos(planete));
		orbite.setDuration(Duration.seconds(planete.getPlanete().getTempsOrbite()));
		orbite.setPath(createOrbitePath(planete));
		orbite.setCycleCount(PathTransition.INDEFINITE);
		orbite.setInterpolator(Interpolator.LINEAR);
		
		return orbite;
	}
	
	public void orbiteAnimation() {
		listeAnimationOrbite.clear();
		for(Planete animP : listePlanete) {
			listeAnimationOrbite.add(createOrbite(animP));
			listeAnimationOrbite.add(createOrbiteInfo(animP));
		}
		for(PathTransition pt : listeAnimationOrbite) {
			pt.play();
		}
	}
	
	
	public void actualizePlanete() {
		for(Planete p : listePlanete) {
			p.setRayon();
			p.setDistance();
			getPlaneteC(p).actualize();
		}
	}
	
	
	public void actualizeSizeSun() {
		getCrclSun().setRadius(Variable.RAYON_SOLEIL);
	}
	
	public void actualize() {
		actualizeSizeSun();
		actualizePlanete();
		orbiteAnimation();
		actualizeLigneOrbitale();
	}
	
	
}
