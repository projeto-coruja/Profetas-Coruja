package alt;

import java.util.HashMap;
public class MessageRepository {
	
	private HashMap<String, HashMap<String, HashMap<String, HashMap<Integer, String>>>> repository;
	private boolean done;

	public MessageRepository() {
		repository = new HashMap<String, HashMap<String, HashMap<String, HashMap<Integer, String>>>>();
	}
	
	public void putCategory(String category){
		if(!done) repository.put(category, new HashMap<String, HashMap<String, HashMap<Integer, String>>>());
	}
	
	public void putContext(String category, String context){
		if(!done) repository.get(category).put(context, new HashMap<String, HashMap<Integer, String>>());
	}
	
	public void putReference(String category, String context, String reference) {
		if(!done) repository.get(category).get(context).put(reference, new HashMap<Integer, String>());
	}
	
	public void putMessage(String category, String context, String reference, int id, String message) {
		if(!done) repository.get(category).get(context).get(reference).put(id, message);
	}
	
	public void finishRepositoryBuilding() {
		done = true;
	}
	
	public String getMessage(String category, String context, String reference, int id) {
		return repository.get(category).get(context).get(reference).get(id);
	}
	
	public boolean referenceExists(String category, String context, String reference) {
		return repository.get(category).get(context).containsKey(reference);
	}

}
