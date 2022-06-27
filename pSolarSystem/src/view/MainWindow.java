package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Variable;

public class MainWindow extends AnchorPane{
	private Screen screen;
	private HBox menu;
	private Slider sldEchellePlanete, sldEchelleDistance;
	private Label lblEchellePlanete, lblEchelleDistance;
	private TextField txtKmToPx;
	private Button btnKmToPx;
	private CheckBox cbShowOrbite;
	
	public MainWindow() {
		// TODO Auto-generated constructor stub
		getChildren().addAll(getScreen(), getMenu());
		
		//Mise en page
		//screen
		setTopAnchor(getScreen(), 0.);
		setRightAnchor(getScreen(), 0.);
		setLeftAnchor(getScreen(), 0.);
		setBottomAnchor(getScreen(), getMenu().getPrefHeight());
		
		//Menu
		setRightAnchor(getMenu(), 0.);
		setLeftAnchor(getMenu(), 0.);
		setBottomAnchor(getMenu(), 0.);
	}

	public Screen getScreen() {
		if(screen == null) {
			screen = new Screen();
		}
		return screen;
	}

	public HBox getMenu() {
		if(menu == null) {
			menu = new HBox();
			
			menu.setPrefHeight(50);
			//Km to Pixel
			VBox menuKmToPx = new VBox();
			menuKmToPx.getChildren().addAll(new Label("Km to pixel"), getTxtKmToPx(), getBtnKmToPx());
			
			//Echelle taille planete
			VBox menuEchellePlanete = new VBox();
			menuEchellePlanete.getChildren().addAll(getLblEchellePlanete(), getSldEchellePlanete());
			
			//Echelle taille distance
			VBox menuEchelleDistance = new VBox();
			menuEchelleDistance.getChildren().addAll(getLblEchelleDistance(), getSldEchelleDistance());
			
			menu.getChildren().addAll(menuKmToPx, getCbShowOrbite(), menuEchellePlanete, menuEchelleDistance);
			//Background
			BackgroundFill bgFill = new BackgroundFill(Color.GREEN, null, null);
			Background bg = new Background(bgFill);
			menu.setBackground(bg);
		}
		return menu;
	}

	public Slider getSldEchellePlanete() {
		if(sldEchellePlanete == null) {
			sldEchellePlanete = new Slider(1, 50, Variable.ECHELLE_TAILLE_PLANETE);
			
			sldEchellePlanete.valueChangingProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					double newEchelle = Math.round(sldEchellePlanete.getValue());
					Variable.setECHELLE_TAILLE_PLANETE(newEchelle);
					getScreen().actualizePlanete();
					showOrbite();
					getLblEchellePlanete().setText("Échelle taille planète ("+ (int)newEchelle +"/1)");
				}
			});
			
			sldEchellePlanete.setMajorTickUnit(4);
			//sldKmtoPixel.setBlockIncrement(100000);
			sldEchellePlanete.setSnapToTicks(true);
			sldEchellePlanete.setMinorTickCount(0);
			sldEchellePlanete.setShowTickMarks(true);
			sldEchellePlanete.setShowTickLabels(true);
		}
		return sldEchellePlanete;
	}

	
	
	public Label getLblEchellePlanete() {
		if(lblEchellePlanete == null) {
			lblEchellePlanete = new Label("Échelle taille planète ("+ (int) getSldEchellePlanete().getValue() +"/1)");
		}
		return lblEchellePlanete;
	}

	public TextField getTxtKmToPx() {
		if(txtKmToPx == null) {
			txtKmToPx = new TextField();
			txtKmToPx.setText(String.valueOf(Variable.KM_TO_PIXEL));
		}
		return txtKmToPx;
	}

	public Button getBtnKmToPx() {
		if(btnKmToPx == null) {
			btnKmToPx = new Button("Valider");
			
			btnKmToPx.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Variable.setKM_TO_PIXEL(Double.parseDouble(getTxtKmToPx().getText()));
					Variable.setRAYON_SOLEIL();
					System.out.println(Variable.KM_TO_PIXEL);
					getScreen().actualize();
					showOrbite();
				}
			});
		}
		return btnKmToPx;
	}

	public CheckBox getCbShowOrbite() {
		if(cbShowOrbite == null) {
			cbShowOrbite = new CheckBox("Show orbite");
			cbShowOrbite.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					showOrbite();
					
				}
			});
			
		}
		return cbShowOrbite;
	}
	
	public void showOrbite() {
		if(cbShowOrbite.isSelected()) {
			getScreen().showLigneOrbitale(true);
		}
		else {
			getScreen().showLigneOrbitale(false);
		}
	}

	public Slider getSldEchelleDistance() {
		if(sldEchelleDistance == null) {
			sldEchelleDistance = new Slider(1, 250, Variable.ECHELLE_DISTANCE_PLANETE);
			
			sldEchelleDistance.valueChangingProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					// TODO Auto-generated method stub
					double newEchelle = Math.round(sldEchelleDistance.getValue());
					Variable.setECHELLE_DISTANCE_PLANETE(newEchelle);;
					getScreen().actualize();
					showOrbite();
					getLblEchelleDistance().setText("Échelle distance (1/"+ (int)newEchelle +")");
				}
			});
			
			sldEchelleDistance.setMajorTickUnit(10);
			//sldKmtoPixel.setBlockIncrement(100000);
			sldEchelleDistance.setSnapToTicks(true);
			sldEchelleDistance.setMinorTickCount(0);
			sldEchelleDistance.setShowTickMarks(true);
			sldEchelleDistance.setShowTickLabels(true);
			
		}
		return sldEchelleDistance;
	}

	public Label getLblEchelleDistance() {
		if(lblEchelleDistance == null) {
			lblEchelleDistance = new Label("Échelle distance (1/"+ (int) getSldEchelleDistance().getValue() +")");
		}
		return lblEchelleDistance;
	}
	
	
	
}
