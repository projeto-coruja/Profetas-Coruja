
public class Message {
	private String reference;
	private String text;

	public Message(int id, String reference, String text) {
		super();
		this.reference = reference;
		this.text = text;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
