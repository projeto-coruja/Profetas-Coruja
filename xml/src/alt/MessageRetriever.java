package alt;

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
	
	public String getMessage(String category, String reference, int id) {
		return repository.getMessage(category, reference, id);
	}
	
	public static void main(String[] args) {
		MessageRetriever test = MessageRetriever.getInstance();
		System.out.println(test.getMessage("aCategory", "anExceptionName", 3));
	}
}
