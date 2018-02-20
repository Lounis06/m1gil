package tp1.polynome;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolynomeStd {
	private List<Monome> coefficients;
	
	public PolynomeStd(double[] coefficients) {
		List<Monome> list = new ArrayList<Monome>();
		for(int i = 0; i < coefficients.length; ++i) {
			if (coefficients[i] != 0) {
				this.coefficients.add(new Monome(i, coefficients[i]));
			}
		}
		this.coefficients = list;
	}
	
	public PolynomeStd(List<Monome> coefficients) {
		this.coefficients = coefficients;
	}
	
	public int getDegree() {
		int max = 0;
		Iterator<Monome> it = coefficients.iterator();
		while(it.hasNext()) {
			Monome m = it.next();
			if(m.getDegree() > max) {
				max = m.getDegree();
			}
		}
		return max;
	}
	
	public double getCoefficient(int i) {
		if (i>=coefficients.size())
			throw new InvalidParameterException("Coefficient supérieur au degree du polynome.");
		for(Monome m: coefficients) {
			if(m.getDegree()==i) {
				return m.getCoefficient(i);
			}
		}
		return 0;
	}
	
	public double computeValue(double x) {
		// Ajoute la constante au résultat :
		double res = getCoefficient(0);
		
		// Démarre avec x puissance 1 :
		double d=x;
		
		// Ajoute le produit des puisances de x multipliée par le coef correspondant : 
		for(int i=1; i<coefficients.size(); ++i) {
			res+=(d*getCoefficient(i));
			
			// Puissance suivante
			d*=x;
		}
		
		// Renvoi le résultat :
		return res;
	}
}
