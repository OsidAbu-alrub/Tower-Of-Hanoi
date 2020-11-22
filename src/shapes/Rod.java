package shapes;

import javafx.scene.canvas.GraphicsContext;

public class Rod extends Rectangle{
	
	private int numberOfPieces;
	private int rodNumber;
	/*
	 * 1 means left
	 * 2 means mid
	 * 3 means right
	 */
	
	public Rod(GraphicsContext graphicsContext, int width, int height,int rodNumber) {
		super(graphicsContext, width, height);
		this.rodNumber = rodNumber;
	}
	
	public void incrementNumberOfPieces() 
	{
		numberOfPieces++;
	}
	
	public void decrementNumberOfPieces() 
	{
		numberOfPieces--;
	}

	public int getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(int numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}

	public int getRodNumber() {
		return rodNumber;
	}

	public void setPlacement(int rodNumber) {
		this.rodNumber = rodNumber;
	}
	
	
	
	

}
