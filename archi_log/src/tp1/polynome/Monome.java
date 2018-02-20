package tp1.polynome;

import java.lang.Math;

public class Monome implements Polynome {
	private int degree;
	private double coefficient;
	
	public Monome(int degree, double coefficient) {
		this.degree = degree;
		this.coefficient = coefficient;
	}
	
	@Override
	public int getDegree() {
		return degree;
	}

	@Override
	public double getCoefficient(int i) {
		return i == degree ? coefficient : 0;
	}

	@Override
	public double computeValue(double x) {
		return coefficient * Math.pow(x, degree);
	}

	@Override
	public Polynome sum(Polynome p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynome prod(Polynome p) {
		// TODO Auto-generated method stub
		return null;
	}

}
