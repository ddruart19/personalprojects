package model;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class TestCase extends StackPane{
	private Rectangle r;
	private Label infos;
	private int posX, posY;
	
	
	public TestCase(int x, int y) {
		getChildren().addAll(getR(), getInfos());
		this.posX = x;
		this.posY = y;
		
//		setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				if(event.isDragDetect())
//					setBlack();
//				else
//					getR().setFill(Color.RED);
//				
//			}
//		});
		
		
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(isVivant())
					setWhite();
				else
					setBlack();
				
			}
		});
		
	}
	
	public Paint getCouleur() {
		return getR().getFill();
	}
	
	public boolean isVivant() {
		if(getR().getFill().equals(Color.BLACK))
			return true;
		return false;
	}
	
	public void setWhite() {
		getR().setFill(Color.WHITE);
		getInfos().setTextFill(Color.BLACK);
	}
	
	public void setBlack() {
		getR().setFill(Color.BLACK);
		getInfos().setTextFill(Color.WHITE);
	}
	
	public Rectangle getR() {
		if(r == null) {
			int tailleCote = 20;
			r = new Rectangle();
			r.setFill(Color.WHITE);
			r.setStroke(Color.BLACK);
			r.setHeight(tailleCote);
			r.setWidth(tailleCote);
		}
		return r;
	}
	
	public Label getInfos() {
		if(infos == null) {
			infos = new Label();
		}
		return infos;
	}
	
	public void setInfos(String txt) {
		getInfos().setText(txt);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	@Override
	public String toString() {
		
		return posX + ":" + posY;
	}
	
	public TestCase clone() {
		TestCase clone = new TestCase(getPosX(), getPosY());
		clone.setInfos(this.getInfos().getText());
		if(this.isVivant())
			clone.setBlack();
		else
			clone.setWhite();
		return clone;
	}
}
