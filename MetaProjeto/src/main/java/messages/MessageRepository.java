package messages;

import java.util.HashMap;

/**
 * A class representing a message repository, as defined by our NIH XML schema. It's mostly a wrapper for a demoniac
 * {@code HashMap<String, HashMap<String, HashMap<String, HashMap<Integer, String>>>>}, where each map represents a level in the
 * XML tree.
 * 
 * @author Daniel Gracia, Vitor Kawai
 * @since Milestone 1
 * @see XMLMessagesParser
 */
public class MessageRepository {
	
	private HashMap<String, HashMap<String, HashMap<String, HashMap<Integer, String>>>> repository;
	private boolean done;

	public MessageRepository() {
		repository = new HashMap<String, HashMap<String, HashMap<String, HashMap<Integer, String>>>>();
	}
	
	/**
	 * Create a new category of messages in the repository.
	 * Does nothing if the repository is done.
	 * 
	 * @param category the name of the new category
	 */
	public void putCategory(String category){
		if(!done) repository.put(category, new HashMap<String, HashMap<String, HashMap<Integer, String>>>());
	}
	
	/**
	 * Create a new context of messages inside an existing category in the repository.
	 * Does nothing if the repository is done.
	 * 
	 * @param category the name of an existing category
	 * @param context the name of the new context
	 * @throws IllegalArgumentException when the category doesn't exist
	 */
	public void putContext(String category, String context) throws IllegalArgumentException{
		if(!done) try {
			repository.get(category).put(context, new HashMap<String, HashMap<Integer, String>>());
		} catch (NullPointerException npe) {
			throw new IllegalArgumentException("Invalid path");
		}
	}
	
	/**
	 * Create a new reference type for messages inside an existing context of an existing category in the repository.
	 * Does nothing if the repository is done.
	 * 
	 * @param category the name of an existing category
	 * @param context the name of an existing context
	 * @param reference the name of the new reference
	 * @throws IllegalArgumentException whenever the category or context doesn't exist
	 */
	public void putReference(String category, String context, String reference) throws IllegalArgumentException{
		if(!done) try {
			repository.get(category).get(context).put(reference, new HashMap<Integer, String>());
		} catch (NullPointerException npe) {
			throw new IllegalArgumentException("Invalid path");
		}
	}
	
	/**
	 * Create a new message in the repository. Does nothing if the repository is done.
	 * 
	 * @param category the name of an existing category
	 * @param context the name of an existing context
	 * @param reference the name of an existing reference
	 * @param id a number representing the message
	 * @param message a {@code String} containing the message
	 * @throws IllegalArgumentException whenever the category, context or reference doesn't exist
	 */
	public void putMessage(String category, String context, String reference, int id, String message) {
		if(!done) try {
			repository.get(category).get(context).get(reference).put(id, message);
		} catch (NullPointerException npe) {
			throw new IllegalArgumentException("Invalid path");
		}
	}
	
	/**
	 * Makes the repository "done", preventing any further change to the underlying {@code HashMap}.
	 */
	public void finishRepositoryBuilding() {
		done = true;
	}
	
	/**
	 * Gets a message from the repository.
	 * 
 	 * @param category the name of an existing category
	 * @param context the name of an existing context
	 * @param reference the name of an existing reference
	 * @param id a number representing a existing message
	 * @return a {@code String} containing the message
	 * 
	 */
	public String getMessage(String category, String context, String reference, int id) {
		try {
			return repository.get(category).get(context).get(reference).get(id);
		} catch (NullPointerException npe) {
			return null;
		}
	}
	
	/** Checks if a reference exists. I'm not sure why does this exist.
	 * 
 	 * @param category the name of an existing category
	 * @param context the name of an existing context
	 * @param reference the name of an reference
	 * @return {@code true} if the reference exists, {@code false} otherwise
	 */
	public boolean referenceExists(String category, String context, String reference) {
		try {
			return repository.get(category).get(context).containsKey(reference);
		} catch (NullPointerException npe) {
			return false;
		}
	}

}
