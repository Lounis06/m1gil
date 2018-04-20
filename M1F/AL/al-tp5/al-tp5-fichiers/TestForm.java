
public class TestForm {
	public FormGenerator getFormGenerator() {
		// Obscure fonction retournant un generateur de formulaire.
		// Vous la definirez pour vos tests.
		// Ex: return new HTMLForm();
	}
	
	public testGenerator(FormGenerator fg) {
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


