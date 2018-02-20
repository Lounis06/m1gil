package tp2.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import tp2.noise.Noise;

public class HandLine implements Line {
	private double x0, y0, x1, y1;
	private Color c;

	public HandLine(double x0, double y0, double x1, double y1, Color c) {
		this.x0 = x0; this.y0 = y0;
		this.x1 = x1; this.y1 = y1;
		this.c = c;
	}
	
	public double getLength() {
		double dx=Math.abs(x1-x0);
		double dy=Math.abs(y1-y0);
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public void draw(Graphics2D screen) {
		screen.setColor(c);
		screen.draw(new Line2D.Double(
				Noise.getNoise()+x0, Noise.getNoise()+y0,
				Noise.getNoise()+x1, Noise.getNoise()+y1));
	}

}
