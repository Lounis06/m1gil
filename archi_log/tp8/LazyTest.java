package tp8;

public class LazyTest {
	public static void main(String args[]) {
		Logger l1 = Logger.uncalledYet();
		System.out.println("1st reference : " + l1);
		// 1st reference : null
		Logger l2 = Logger.getInstance();
		System.out.println("2nd reference : " + l2);
		// 2nd reference : tp8.Logger@15db9742
		System.out.println("l1/l2 : same object ? " + (l1 == l2));
		// l1/l2 : same object ? false
		Logger l3 = Logger.getInstance();
		System.out.println("3rd reference : " + l3);
		// 3rd reference : tp8.Logger@15db9742
		System.out.println("l2/l3 : same object ? " + (l2 == l3));
		// l2/l3 : same object ? true
		
		/* The following line doesn't compile. It shows the only way
		to get a reference to Logger is to call static method
		Logger.getInstance()
		*/
		Logger l = new Logger();
	}
}
