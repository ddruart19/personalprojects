package model;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Grille extends VBox{
	private static int tailleCote = 35;
	
	public Grille() {
		for(int i = 0; i<tailleCote; i++) {
			HBox ligne = new HBox();
			for(int j = 0; j<tailleCote; j++) {
				TestCase gridCase = new TestCase(j, i);
				//gridCase.setInfos(j + ":" + i);
				ligne.getChildren().add(gridCase);
			}
			getChildren().add(ligne);
		}
	}
	
	private Grille(boolean test) {
		
	}
	
	public int nbCaseAutour(TestCase caseMilieu) {
		int nbCase=0;
		TestCase gridCase = caseMilieu;
		int posX = gridCase.getPosX()-1;
		int posY = gridCase.getPosY()-1;
		int saveX = posX;
		for(int i=0; i<3; i++) {
			
			posX = saveX;
			for(int j=0; j<3; j++) {
				if(posX == gridCase.getPosX() && posY == gridCase.getPosY()) {
										
				}
				else {
					if(getCase(posX, posY) != null) {
						if(getCase(posX, posY).isVivant()) {
							nbCase++;
						}
							
					}
					
				}
				posX++;
			}
			posY++;
		}	
		return nbCase;
	}
	
	public void resetGrid() {
		for(Node lignes : getChildren()) {
			if(lignes instanceof HBox) {
				HBox ligne = (HBox) lignes;
				for(Node b : ligne.getChildren()) {
					if(b instanceof TestCase) {
						TestCase gridCase = (TestCase) b;
						gridCase.setWhite();
						//gridCase.setInfos("0");
					}
				}
			}
		}	
	}
	
	public TestCase getCase(int x, int y) {
		for(Node lignes : getChildren()) {
			if(lignes instanceof HBox) {
				HBox ligne = (HBox) lignes;
				for(Node b : ligne.getChildren()) {
					if(b instanceof TestCase) {
						TestCase gridCase = (TestCase) b;
						if(gridCase.getPosX() == x && gridCase.getPosY() == y) {
							return gridCase;
						}
					}
				}
			}
		}	
		return null;
	}
	
	public Grille cloneGrid() {
		Grille grid = new Grille(true);
		for(Node lignes : getChildren()) {
			if(lignes instanceof HBox) {
				HBox ligne = (HBox) lignes;
				HBox cloneLigne = new HBox();
				for(Node testCase : ligne.getChildren()) {
					if(testCase instanceof TestCase) {
						TestCase laCase = (TestCase) testCase;
						cloneLigne.getChildren().add(laCase.clone());
					}
				}
				grid.getChildren().add(cloneLigne);
			}
		}	
		return grid;
	}
}
