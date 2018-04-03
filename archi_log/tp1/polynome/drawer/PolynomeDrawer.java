package tp1.polynome.drawer;

import tp1.polynome.Polynome;
import wonderland.graphicPackageYoullNeverFind.*;

public class PolynomeDrawer {
	public void plot(Polynome p, double x1, double x2, double stepx, WonderPlotCanvas canvas) {
		double x=x1;
		canvas.moveTo(x, p.computeValue(x));
		x+=stepx;
		while (x<x2) {
			canvas.lineTo(x, p.computeValue(x));			
			x+=stepx;
		}
	}
}
