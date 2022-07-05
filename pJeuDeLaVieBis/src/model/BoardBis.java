package model;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardBis extends VBox{
	private Button lancer, reset;
	private Grille grid;
	private static boolean enCoursdExec = false;
	private static boolean firstStart = true;
	private Thread t = new Thread(){
		public synchronized void run() {
			while(true) {
				actualisation();
	    		  try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
		}
	};
	
	public BoardBis() {
		getChildren().add(getGrid());
		getChildren().addAll(getLancer(), getReset());
	}
	

	
	public void actualisation() {
		Grille cloneGrid = grid.cloneGrid();
		for(Node lignes : cloneGrid.getChildren()) {
			if(lignes instanceof HBox) {
				HBox ligne = (HBox) lignes;
				for(Node b : ligne.getChildren()) {
					if(b instanceof TestCase) {
						TestCase gridCase = (TestCase) b;
						TestCase realCase = grid.getCase(gridCase.getPosX(), gridCase.getPosY());
						int nbCase = cloneGrid.nbCaseAutour(gridCase);
						if(nbCase == 3) {
							realCase.setBlack();
						}
						else if(nbCase == 2) {
							if(!gridCase.isVivant())
								realCase.setWhite();
							else
								realCase.setBlack();
						}
						else if(nbCase < 2 || nbCase > 3){
							realCase.setWhite();
						}
						//realCase.setInfos(String.valueOf(nbCase));
					}
				}
			}
		}	
	}
	
	public Button getLancer() {
		if(lancer == null) {
			lancer = new Button("Lancer");
			lancer.setPrefWidth(Double.MAX_VALUE);
			
			lancer.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					synchronized (t) {
						if(enCoursdExec) {
							try {
								t.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							enCoursdExec = false;
							lancer.setText("Lancer");
						}
						else {
							if(firstStart) {
								t.start();
								firstStart = false;
							}
							else {
								t.notify();
							}
							lancer.setText("Pause");
							enCoursdExec = true;
						}
					}
				}
			});
		}
		return lancer;
	}

	public Button getReset() {
		if(reset == null) {
			reset = new Button("Reset");
			reset.setPrefWidth(Double.MAX_VALUE);
			reset.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					//getChildren().add(grid.cloneGrid());
					grid.resetGrid();
					if(enCoursdExec) {
						synchronized (t) {
							try {
								t.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
				}
			});
		}
		return reset;
	}
	
	public Grille getGrid() {
		if(grid == null) {
			grid = new Grille();
		}
		return grid;
	}
}
