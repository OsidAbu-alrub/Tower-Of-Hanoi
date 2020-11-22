package userInterface;

import common.Common;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import shapes.Rectangle;
import towerOfHanoiLogic.TowerOfHanoi;

public class MainWindow {
	
	public static void mainWindow(AnchorPane anchor) 
	{
		//textfield for taking input from user
		TextField inputField = new TextField();
		inputField.setPrefSize(280, 40);
		inputField.setFont(Font.font(20));
		inputField.setPromptText("Minimum: 2 Maximum: 7");
		
		//button to run program
		Button runBtn = Common.createBtn("RUN", Color.WHITE, 20, 120, 35);
		Common.fancyBtn(runBtn);
		
		//HBox for button and input field
		HBox hInput = new HBox(10);
		hInput.setAlignment(Pos.CENTER);
		hInput.getChildren().addAll(inputField,runBtn);
		anchor.getChildren().add(hInput);
		AnchorPane.setTopAnchor(hInput, 70.0);
		AnchorPane.setLeftAnchor(hInput, 130.0);
		
		//label for input field
		Label numberOfRingsLabel = new Label("Number of disks:");
		numberOfRingsLabel.setFont(Font.font(20));
		anchor.getChildren().add(numberOfRingsLabel);
		AnchorPane.setTopAnchor(numberOfRingsLabel, 40.0);
		AnchorPane.setLeftAnchor(numberOfRingsLabel, 130.0);
		
		//number of moves
		TextField numberOfMovesField = new TextField();
		numberOfMovesField.setFont(Font.font(30));
		numberOfMovesField.setAlignment(Pos.CENTER);
		numberOfMovesField.setEditable(false);
		numberOfMovesField.setPrefSize(120, 40);
		
		//label for number of moves field
		Label numberOfMovesLabel = new Label("Number of moves:");
		numberOfMovesLabel.setFont(Font.font(24));
		
		//Hbox for label and field
		HBox hMoves = new HBox(5);
		hMoves.setAlignment(Pos.CENTER);
		hMoves.getChildren().addAll(numberOfMovesLabel,numberOfMovesField);
		anchor.getChildren().addAll(hMoves);
		AnchorPane.setTopAnchor(hMoves,80.0);
		AnchorPane.setLeftAnchor(hMoves,1000.0);
		
		//root pane for canvases 
		ScrollPane root = new ScrollPane();
		root.setStyle("-fx-background: rgb(255,255,255);");
		root.setMinSize(1800, 800);
		root.setPrefSize(1800, 800);
		root.setHbarPolicy(ScrollBarPolicy.NEVER);
		root.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		root.setVmax(1);
		
		anchor.getChildren().add(root);
		AnchorPane.setLeftAnchor(root, 60.0);
		AnchorPane.setTopAnchor(root, 160.0);
		
		TilePane hScroll = new TilePane();
		hScroll.setOrientation(Orientation.HORIZONTAL);
		hScroll.setPrefColumns(2);		
		root.setContent(hScroll);
		
		//warning label if user enters something thats not valid
		Label warnLabel = new Label("Number between 2 and 7 only*");
		warnLabel.setTextFill(Color.RED);
		warnLabel.setFont(Font.font(18));
		anchor.getChildren().add(warnLabel);
		AnchorPane.setTopAnchor(warnLabel, 110.0);
		AnchorPane.setLeftAnchor(warnLabel, 130.0);
		warnLabel.setVisible(false);
		
		//functionality of run button
		runBtn.setOnAction(e ->{
			try {
				int n = Integer.parseInt(inputField.getText());
				if(n >= 2 && n <= 7) {
					warnLabel.setVisible(false);
					hScroll.getChildren().clear();
					numberOfMovesField.clear();		
					inputField.clear();
					
					numberOfMovesField.setText(""+((int) Math.pow(2, n)-1));
					Canvas[] canvases = new Canvas[(int) (Math.pow(2, n)-1)];
					Rectangle[] recs = new Rectangle[n];
					
					for (int i = 0; i < canvases.length; i++) 
					{
						canvases[i] = new Canvas();
						canvases[i].setWidth(850);
						canvases[i].setHeight(300);
					}
					TowerOfHanoi.towerOfHanoi(recs, canvases, hScroll);
				}
				else
				{
					warnLabel.setVisible(true);
				}
			}
			catch(Exception o) {}
		});
		
		
		
	}
}
