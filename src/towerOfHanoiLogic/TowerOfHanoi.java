package towerOfHanoiLogic;

import javafx.animation.FadeTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import shapes.Rectangle;
import shapes.Rod;

public class TowerOfHanoi {
	
	private static Rod leftRod;
	private static Rod midRod;
	private static Rod rightRod;
	private static Rectangle bottomRod;
	private static Rectangle[] recs;
	private static Canvas[] canvases;
	private static TilePane pane;
	private static int moves;
	
	private static void towerOfHanoi(int n, Rod from, Rod destination, Rod middle_man) 
    { 
        if (n == 1)  
        	movePiece(n,from,destination);
        else {
        towerOfHanoi(n-1, from, middle_man, destination);
        movePiece(n,from,destination);
        towerOfHanoi(n-1, middle_man, destination, from);
        }
    }
	
	private static void movePiece(int n,Rod from,Rod destination) 
	{	
		StackPane stk = new StackPane();
		stk.setPrefSize(850, 300);
		Canvas tempBefore = new Canvas(850,300);
		Canvas tempAfter = new Canvas(850,300);
		
		//before is used to show before moving the piece
		FadeTransition tBefore = new FadeTransition();
		tBefore.setFromValue(1.0);
		tBefore.setToValue(0);
    	tBefore.setNode(tempBefore);
    	tBefore.setDuration(Duration.seconds(1.5));
    	tBefore.play();
    	recs[n-1].setGraphicsContext(tempBefore.getGraphicsContext2D());
    	recs[n-1].drawRectangle(new int[] {0,0,0});
    	
    	//before is used to show before moving the piece
    	FadeTransition tAfter = new FadeTransition();
		tAfter.setFromValue(0.0);
		tAfter.setToValue(1.0);
    	tAfter.setNode(tempAfter);
    	tAfter.setDuration(Duration.seconds(1.5));
    	tAfter.play();
    	recs[n-1].setGraphicsContext(tempAfter.getGraphicsContext2D());
    	recs[n-1].setLayoutX((destination.getRodNumber()*200) - (n)*10 +5);
    	recs[n-1].setLayoutY(235-(destination.getNumberOfPieces())*15);
    	recs[n-1].drawRectangle(new int[] {0,0,0});
    	
    	tempAfter.setOnMousePressed(e ->{
        	tBefore.playFromStart();
        	tAfter.playFromStart();
    	});
		
		GraphicsContext g = canvases[moves].getGraphicsContext2D();
    	
    	leftRod.setGraphicsContext(g); 
		leftRod.drawRectangle(new int[] {0,0,0});
		
		midRod.setGraphicsContext(g);
		midRod.drawRectangle(new int[] {0,0,0});
		
		rightRod.setGraphicsContext(g);
		rightRod.drawRectangle(new int[] {0,0,0});
		
		bottomRod.setGraphicsContext(g);
		bottomRod.drawRectangle(new int[] {0,0,0});
    	
    	g.setFont(new Font("Arial",30));
    	g.fillText(""+(moves+1), 700, 50);
    	
    	    	
    	from.decrementNumberOfPieces();
    	destination.incrementNumberOfPieces();
    	
    	//draw rest of pieces except the one we moved
    	for(int i = 0 ; i < recs.length ;i++ ) 
    		if(i != n-1) {
				recs[i].setGraphicsContext(g);
				recs[i].drawRectangle(new int[] { 0, 0, 0 });
    		}	
    	
    	
    	stk.getChildren().addAll(canvases[moves],tempBefore,tempAfter);
    	pane.getChildren().add(stk);
    	moves++;
	}
	
	public static void towerOfHanoi(Rectangle[] rectangles,Canvas[] canvs,TilePane tile) 
	{
		moves = 0;
		recs = rectangles;
		canvases = canvs;
		pane = tile;
		int n = recs.length;
		
		Canvas baseCan = new Canvas();
		baseCan.setWidth(850);
		baseCan.setHeight(300);
		GraphicsContext g = baseCan.getGraphicsContext2D();
				
		leftRod = new Rod(g,10,150,1);
		leftRod.setLayoutX(200);
		leftRod.setLayoutY(100);
		leftRod.setFill(new int[] {220,115,40});
		leftRod.drawRectangle(new int[] {0,0,0});
		leftRod.setNumberOfPieces(recs.length);
		
		midRod = new Rod(g,10,150,2);
		midRod.setLayoutX(400);
		midRod.setLayoutY(100);
		midRod.setFill(new int[] {220,115,40});
		midRod.drawRectangle(new int[] {0,0,0});
		
		rightRod = new Rod(g,10,150,3);
		rightRod.setLayoutX(600);
		rightRod.setLayoutY(100);
		rightRod.setFill(new int[] {220,115,40});
		rightRod.drawRectangle(new int[] {0,0,0});
		
		bottomRod = new Rectangle(g,600,15);
		bottomRod.setLayoutX(100);
		bottomRod.setLayoutY(250);
		bottomRod.setFill(new int[] {220,115,40});
		bottomRod.drawRectangle(new int[] {0,0,0});
		
		
		int[][] colors = {
				{255,0,0},
				{0,255,0},
				{0,0,255},
				{255,255,0},
				{255,0,255},
				{0,255,255},
				{168,0,230}
				};
		
		//draw base shape
		//smallest rec at index 0
		for(int i = n-1 ; i >= 0 ; i--) 
		{
			recs[i] = new Rectangle(g,(i+1)*20,15);
			recs[i].setLayoutX((i == n-1) ? 200 - (n)*10 +5 : recs[i+1].getLayoutX()+10);
			recs[i].setLayoutY(265-(n-i+1)*15);
			recs[i].setFill(colors[i]);
			recs[i].drawRectangle(new int[] {0,0,0});	
		}
		
		pane.getChildren().add(baseCan);
		
		//==//
		towerOfHanoi(n,leftRod,rightRod,midRod);
		//==//
	}
}
