package tp2.shapes;

import java.awt.Color;

public class HandShape implements ShapeFactory {

	@Override
	public Line createLine(double x0, double y0, double x1, double y1, Color c) {
		return new HandLine(x0, y0, x1, y1, c);
	}

	@Override
	public Circle createCircle(double cx, double cy, double radius, Color c) {
		return new HandCircle(cx, cy, radius, c);
	}

	@Override
	public Rectangle createRectangle(double x0, double y0, double x1, double y1, Color c) {
		return new HandRectangle(x0, y0, x1, y1, c);
	}

}
