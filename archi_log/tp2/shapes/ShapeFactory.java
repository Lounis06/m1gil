package tp2.shapes;

import java.awt.Color;

public interface ShapeFactory {
	public Line createLine(double x0, double y0, double x1, double y1, Color c);
	public Circle createCircle(double cx, double cy, double radius, Color c);
	public Rectangle createRectangle(double x0, double y0, double x1, double y1, Color c);
}
