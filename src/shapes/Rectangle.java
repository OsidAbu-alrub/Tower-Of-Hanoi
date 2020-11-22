package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;


public class Rectangle{


	private int width;
	private int height;
	private int layoutX;
	private int layoutY;
	private int[] fill;
	private GraphicsContext graphicsContext;

	public Rectangle(GraphicsContext graphicsContext) {
		fill = new int[3];
		this.graphicsContext = graphicsContext;
	}

	public Rectangle(GraphicsContext graphicsContext, int width, int height) {
		this(graphicsContext);
		this.width = width;
		this.height = height;
	}
	
	public void drawRectangle(int[] color) {
		int[] x = { layoutX, layoutX + width, layoutX + width, layoutX };
		int[] y = { layoutY, layoutY, layoutY + height, layoutY + height };
		
		// left side vertical line
		Line.line(graphicsContext, layoutX, layoutY + height, layoutX, layoutY, color);

		for (int i = 0; i < x.length - 1; i++)
			Line.line(graphicsContext, x[i], y[i], x[i + 1], y[i + 1], color);
		Fill();
	}

	private void Fill() {
		Color fillColor = Color.rgb(fill[0],fill[1],fill[2]);
		PixelWriter pixelWriter = graphicsContext.getPixelWriter();
		
		for (int i = layoutX + 1; i < layoutX + width; i++)
			for (int j = layoutY + 1; j < layoutY + height; j++)
				pixelWriter.setColor(i, j, fillColor);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getLayoutX() {
		return layoutX;
	}

	public void setLayoutX(int layoutX) {
		this.layoutX = layoutX;
	}

	public int getLayoutY() {
		return layoutY;
	}

	public void setLayoutY(int layoutY) {
		this.layoutY = layoutY;
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	public int[] getFill() {
		return fill;
	}

	public void setFill(int[] fill) {
		this.fill = fill;
	}
	
	

}
