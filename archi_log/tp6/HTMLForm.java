package tp6;

public class HTMLForm implements FormFormat {
	private String head;
	private String body;
	
	@Override
	public void addTitle(String title) {
		head += "<title>"+title+"</title>";
	}
	
	@Override
	public void addSeparator() {
		body += "<hr>";
	}
	
	@Override
	public void addField(String name) {
		String formatted = name.toLowerCase();
		body += "<label for=\""+formatted+"\">"+name+"</label>"
				+"<input type=\"text\" id=\""+formatted+"\" name=\""+formatted+"\" />";
	}

	@Override
	public String send() {
		return
			"<html><head>"+this.head+"</head><body>"+this.body+"</body></html>";
	}

}
