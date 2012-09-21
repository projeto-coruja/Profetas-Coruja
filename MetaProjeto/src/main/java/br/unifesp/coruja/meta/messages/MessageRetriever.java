package br.unifesp.coruja.meta.messages;

public class MessageRetriever {
	
	private MessageRepository repository;
	private static MessageRetriever instance;
	
	private MessageRetriever() {
		repository = XMLMessagesParser.generateRepositoryFromXML();
	}
	
	public static MessageRetriever getInstance() {
		if(instance == null) instance = new MessageRetriever();
		return instance;
	}
	
	public String getMessage(String category, String context, String reference, int id){
		try {
			if(category.isEmpty() 
					|| context.isEmpty() 
					|| reference.isEmpty() 
					|| id <= 0) throw new IllegalArgumentException();
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		return repository.getMessage(category, context, reference, id);
	}
	
	public static void main(String[] args) {
		MessageRetriever test = MessageRetriever.getInstance();
		System.out.println(test.getMessage("aCategory", "somewhere", "alsoThere", 3));
	}
}
