package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class Line {

	static void line(GraphicsContext g, int x1, int y1, int x2, int y2, int[] color) {
		
		// instead of starting from right to left, we just swap them
		if (x1 > x2) {
			int temp;
			
			temp = x1;
			x1 = x2;
			x2 = temp;

			temp = y1;
			y1 = y2;
			y2 = temp;
		}
		PixelWriter pixelWriter = g.getPixelWriter();
		
		// positive m
		if (y2 > y1) {
			int dx = x2 - x1;
			int dy = y2 - y1;

			if (dx > dy) { // 0 < m < 1

				int p = 2 * dy - dx;
				int y = y1;

				// calculate y, steps on x
				for (int x = x1; x <= x2; x++)
				{
					if (p > 0) // positive p 
					{
						y++;
						p = p + 2 * (dy - dx);
					} 
					else // negative p, y stays the same
						p = p + 2 * dy;
					pixelWriter.setColor(x, y, Color.rgb(color[0], color[1], color[2]));
				}

			}
			else // m >= 1 
			{
				int p = 2 * dx - dy;
				int x = x1;
				
				// steps on y, calcualte x
				for (int y = y1; y <= y2; y++) 
				{
					if (p > 0) 
					{ 
						x++;
						p = p + 2 * (dx - dy);
					}
					else 
						p = p + 2 * dx;
					pixelWriter.setColor(x, y, Color.rgb(color[0], color[1], color[2]));
				}

			}

		}

		// negative m
		else {

			int dx = x2 - x1;
			int dy = Math.abs(y2 - y1);

			if (dx > dy) // -1 < m < 0 
			{

				int p = 2 * dy - dx;
				int y = y1;

				// walk on x, calculate y
				for (int x = x1; x <= x2; x++) 
				{
					if (p > 0) // p positive
					{ 
						y--;
						p = p + 2 * (dy - dx);
					} else  // p negative
						p = p + 2 * dy;
					pixelWriter.setColor(x, y, Color.rgb(color[0], color[1], color[2]));
				}

			} 
			else // m <= -1 
			{
				int p = 2 * dx - dy;
				int x = x1;

				// walk on y, calculate x
				for (int y = y1; y >= y2; y--) 
				{
					if (p > 0) //p postive, so increment  x 
					{
						x++;
						p = p + 2 * (dx - dy);
					}
					else //p negative, x stays the same
						p = p + 2 * dx;
					
					pixelWriter.setColor(x, y, Color.rgb(color[0], color[1], color[2]));
				}
			}

		}
	}
}
