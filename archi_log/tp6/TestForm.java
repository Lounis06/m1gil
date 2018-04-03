package tp6;

public class TestForm {
	public static FormGenerator getFormGenerator() {
		FormFormat format = new HTMLForm();
		return new FrenchForm(format);
	}
	
	public static void testGenerator(FormGenerator fg) {
		fg.addTitleHeader("URouen Corp.");
		fg.addSeparator();
		fg.addNameLine();
		fg.addFirstNameLine();
		fg.addSeparator();
		fg.addEmailLine();

		String Result = fg.send();
		System.out.println(Result);
	}
	
	public static void main(String[] args) {
		testGenerator(getFormGenerator());
	}
}


