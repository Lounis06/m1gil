package tp3.drawingLoader;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import tp2.shapes.Drawable;
import tp2.shapes.ShapeFactory;

public class DrawingBuilderStd implements DrawingBuilder {
	private Point p1 = null, p2 = null;
	private Color c = null;
	private Double radius = null;
	private ShapeType shape = null;
	private ShapeFactory shapeFactory;
	private List<Drawable> drawables;

	private DrawingBuilderStd(ShapeFactory shapeFactory) {
		this.shapeFactory = shapeFactory;
	}
	
	@Override
	public void startShape(ShapeType s) {
		shape = s;
	}

	@Override
	public void addRadius(double radius) {
		if (this.radius != null) {
			throw new IllegalArgumentException("radius has already been read");
		}
		this.radius = radius;
	}

	@Override
	public void addPoint(Point p) {
		if (p1 != null) {
			if (p2 != null) {
				throw new IllegalArgumentException("all points have already been read");
			}
			p2 = p;
		} else {
			p1 = p;
		}
	}

	@Override
	public void setColor(Color c) {
		if (this.c != null) {
			throw new IllegalArgumentException("color has already been read");
		}
		this.c = c;
	}

	@Override
	public void endShape() {
		switch (shape) {
			case Line:
				drawables.add(shapeFactory.createLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), c));
				break;
			case Rectangle:
				drawables.add(shapeFactory.createRectangle(p1.getX(), p1.getY(), p2.getX(), p2.getY(), c));
				break;
			case Circle:
				drawables.add(shapeFactory.createCircle(p1.getX(), p1.getY(), radius, c));
				break;
			default:
				break;
		}
	}

	@Override
	public List<Drawable> getDrawing() {
		return drawables;
	}

}
