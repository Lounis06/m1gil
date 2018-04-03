package tp3.drawingLoader;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import tp2.shapes.Drawable;

public interface DrawingBuilder {
	public static enum ShapeType {Line, Circle, Rectangle};
	
	public void startShape(ShapeType s);
	public void addRadius(double radius);
	public void addPoint(Point p);
	public void setColor(Color c);
	public void endShape();
	public List<Drawable> getDrawing();
}
