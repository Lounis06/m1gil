package tp1.polynome;

public interface Polynome {
	public int getDegree();
	public double getCoefficient(int i);
	public double computeValue(double x);
	public Polynome sum(Polynome p);
	public Polynome prod(Polynome p);
}
