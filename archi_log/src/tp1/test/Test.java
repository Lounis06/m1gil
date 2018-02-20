package tp1.test;
import tp1.polynome.Polynome;
import tp1.polynome.PolynomeStd;
import tp1.polynome.calculator.PolynomeCalculator;

public class Test {
	public static void main(String[] args) {
		System.out.println("Coucou");
		
		double[] cx={0.0,1.0,0,0,1};
		Polynome p = (Polynome) new PolynomeStd(cx);
		
		System.out.println("p(2)="+p.computeValue(2.0));

		double[] cx2={1.0,0,0,1};
		Polynome p2 = (Polynome) new PolynomeStd(cx2);

		System.out.println("p2(2)="+p2.computeValue(2.0));

		PolynomeCalculator pC = new PolynomeCalculator();
		System.out.println("(p+p2)(2)="+pC.sum( p, p2).computeValue(2.0));
}
}
