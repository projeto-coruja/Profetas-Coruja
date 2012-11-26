package messages;

import static general.UtilityClass.isInit;

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
		if(isInit(category)
				|| isInit(context) 
				|| isInit(reference) 
				|| id <= 0) throw new IllegalArgumentException();
		return repository.getMessage(category, context, reference, id);
	}
	
	public static void main(String[] args) {
		MessageRetriever test = MessageRetriever.getInstance();
		System.out.println(test.getMessage("aCategory", "somewhere", "alsoThere", 3));
	}
}
