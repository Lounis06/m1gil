package tp2.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import tp2.noise.Noise;

public class HandCircle implements Circle {
	private double cx, cy, rad;
	private Color c;
	
	public HandCircle(double cx, double cy, double rad, Color c) {
		this.cx = cx; this.cy = cy; this.rad = rad;
		this.c = c;
	}

	public double getRadius() {
		return rad;
	}

	public void draw(Graphics2D screen) {
		screen.setColor(c);
		screen.draw(new Ellipse2D.Double(
				Noise.getNoise()+cx-rad, Noise.getNoise()+cy-rad,
				Noise.getNoise()+rad*2, Noise.getNoise()+rad*2));
	}

}
