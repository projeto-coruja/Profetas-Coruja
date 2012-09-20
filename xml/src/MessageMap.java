import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class MessageMap {

	private HashMap<String,Message> map;
	private static MessageMap instance;
	
	private MessageMap() throws SAXException, IOException, ParserConfigurationException{
		map = XML.generateHashMap(XML.defaultSet);
	}
	
	public static MessageMap getMessageMap() throws SAXException, IOException, ParserConfigurationException{
		if(instance == null)	instance = new MessageMap();
		return instance;
	}
	
	public String getMessage(String reference, int id){
		return map.get(reference+id).getText();
	}
	
	public void putNewMessage(String reference, String text, int id) {
		if(map.containsKey(reference+id) == true)	throw new IllegalArgumentException(getMessage("IllegalArgumentException",1));
		else map.put(reference+id, new Message(id,reference,text));
	}
	
}
