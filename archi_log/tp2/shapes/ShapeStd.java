package tp2.shapes;

import java.awt.Color;

public class ShapeStd implements ShapeFactory {

	public Line createLine(double x0, double y0, double x1, double y1, Color c) {
		return new LineStd(x0, y0, x1, y1, c);
	}

	public Circle createCircle(double cx, double cy, double radius, Color c) {
		return new CircleStd(cx, cy, radius, c);
	}

	public Rectangle createRectangle(double x0, double y0, double x1, double y1, Color c) {
		return new RectangleStd(x0, y0, x1, y1, c);
	}

}
