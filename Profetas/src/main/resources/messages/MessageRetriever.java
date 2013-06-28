package messages;

import static general.UtilityClass.isInit;

/**
 * A singleton facade for MessageRepository, which initializes the repository using {@link XMLMessagesParser} and
 * then offers a single function for retrieving the messages, which also does some input validation.
 * 
 * @author Daniel Gracia, Vitor Kawai
 * @since Milestone 1
 * @see XMLMessagesParser
 *
 */
public class MessageRetriever {
	
	private MessageRepository repository;
	private static MessageRetriever instance = new MessageRetriever();
	
	private MessageRetriever() {
		repository = XMLMessagesParser.generateRepositoryFromXML();
	}
	
	/**
	 * Get a instance of {@code MessageRetriever}. Should be thread-safe.
	 * @return a instance of the retriever
	 */
	public static MessageRetriever getInstance() {
		return instance;
	}
	/**
	 * Gets a message from the repository.
	 * 
 	 * @param category the name of an category
	 * @param context the name of an context
	 * @param reference the name of an reference
	 * @param id a number representing a message
	 * @return a {@code String} containing the message if it exists, {@code null} otherwise
	 * @throws IllegalArgumentException if the arguments are {@code null}, or if {@code id} is less than 1
	 */
	public String getMessage(String category, String context, String reference, int id) throws IllegalArgumentException{
		if(isInit(category)
				|| isInit(context) 
				|| isInit(reference) 
				|| id <= 0) throw new IllegalArgumentException("Invalid values.");
		return repository.getMessage(category, context, reference, id);
	}
	
	public static void main(String[] args) {
		MessageRetriever test = MessageRetriever.getInstance();
		System.out.println(test.getMessage("aCategory", "somewhere", "alsoThere", 3));
	}
}
