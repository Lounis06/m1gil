package tp2.client;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tp2.shapes.Drawable;
import tp2.shapes.HandShape;
import tp2.shapes.ShapeFactory;
import tp2.viewer.GraphicViewer;

public class Test {
	public static List<Drawable> getDemo(ShapeFactory sF) {
		List<Drawable> ls=new ArrayList<Drawable>();
		ls.add(sF.createLine(0, 500, 800, 500, Color.GREEN));
		ls.add(sF.createLine(300, 0, 0, 300, Color.YELLOW));
		
		ls.add(sF.createLine(30, 300, 180, 200, Color.BLUE));
		ls.add(sF.createLine(330, 300, 180, 200, Color.BLUE));
		ls.add(sF.createRectangle(30, 300,330, 500, Color.RED));

		double sunX = 600;
		double sunY = 120;
		double sunRad = 60; 
		ls.add(sF.createCircle(sunX, sunY, sunRad, Color.BLACK));
		int sunRay = 20;
		for (int i=0; i<sunRay; ++i) {
			double tau=i*2*Math.PI/sunRay;
			ls.add(sF.createLine(sunX+(sunRad+5)*Math.cos(tau),
				sunY-(sunRad+5)*Math.sin(tau),  
				sunX+(1.5*sunRad+5)*Math.cos(tau),
				sunY-(1.5*sunRad+5)*Math.sin(tau),
				Color.BLACK));
		}

		double manX=600;
		double manY=450;
		ls.add(sF.createLine(manX, manY-70, manX-40, manY-110, Color.RED));
		ls.add(sF.createLine(manX, manY-70, manX+40, manY-110, Color.RED));
		ls.add(sF.createCircle(manX, manY-120, 20, Color.GRAY));
		ls.add(sF.createLine(manX, manY, manX, manY-100, Color.BLUE));
		ls.add(sF.createLine(manX, manY, manX-20, manY+50, Color.BLACK));
		ls.add(sF.createLine(manX, manY, manX+20, manY+50, Color.BLACK));
		

		return ls;
	}
	
	public static void main(String[] args)  {
		GraphicViewer gv = new GraphicViewer();
		List<Drawable> demo=getDemo(new HandShape());
		gv.draw(demo);
	}
}
