package tp1.polynome.calculator;

import java.util.ArrayList;
import java.util.List;

import tp1.polynome.Monome;
import tp1.polynome.Polynome;
import tp1.polynome.PolynomeStd;

public class PolynomeCalculator {
	public Polynome sum(Polynome p, Polynome q) {
		int d = Math.max(p.getDegree(), q.getDegree());
		List<Monome> monomes = new ArrayList<Monome>();
		for (int i = 0; i < d; ++i) {
			monomes.add(new Monome(i, p.getCoefficient(i)+q.getCoefficient(i)));
		}
		return (Polynome) new PolynomeStd(monomes);
	}
	public Polynome prod(Polynome p, Polynome q) {
		// Ne pas coder cette méthode !
		// Code similaire à sum ...		
		throw new UnsupportedOperationException("Operation not implemented yet...");
	}
}
