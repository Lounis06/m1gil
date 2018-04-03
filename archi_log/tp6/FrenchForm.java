package tp6;

public class FrenchForm extends FormLanguage {

	private FormFormat format;
	
	public FrenchForm(FormFormat format) {
		this.format = format;
	}
	
	@Override
	public void addTitleHeader(String formTitle) {
		// TODO
	}

	@Override
	public void addSeparator() {
		// TODO
	}

	@Override
	public void addNameLine() {
		format.addField("Nom");
	}

	@Override
	public void addFirstNameLine() {
		format.addField("Prénom");
	}

	@Override
	public void addEmailLine() {
		format.addField("Courriel");
	}

	@Override
	public String send() {
		return format.send();
	}

}
