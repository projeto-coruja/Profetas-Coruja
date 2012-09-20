import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XML {
	
	public static final int defaultSet = 1;
	private static final String fileName = "modelo.xml";

	public static String getExceptionMessage(int set, String exception, int id) throws TagNotFoundException, SAXException, IOException, ParserConfigurationException {
		// Instâncias
		File xml = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
		doc.getDocumentElement().normalize();
		
		// Busca pelo nó <exception>
		Node n = doc.getDocumentElement();
		NodeList nList = n.getChildNodes();
		for(int i = 0; i < nList.getLength(); i++){
			n = nList.item(i);
			if(n.getNodeName().equals("exception")){
				// Busca pelo nó <set> correspondente ao parâmetro set passado.
				nList = n.getChildNodes();
				n = nList.item(1);
				if(((Element)n).hasAttribute("number") && ((Element)n).getAttribute("number").equals(String.valueOf(set))){
					// Busca pelo nó <text> desejado.
					nList = n.getChildNodes();
					for(int j = 0; j < nList.getLength(); j++){
						n = nList.item(j);
						if(n.getNodeType() == Node.ELEMENT_NODE) {
							// Verificação do id do nó <text>
//							if(n.hasAttributes() && ((Element)n).getAttribute("id").equals(String.valueOf(id)) && ((Element)n).getAttribute("exception").equals(exception))
							return n.getChildNodes().item(0).getNodeValue();
						}
					}
				}
			}
		}
		throw new TagNotFoundException();
	}

	public static HashMap<String, Message> generateHashMap(int set) throws SAXException, IOException, ParserConfigurationException{
		
		// Instâncias
		HashMap<String, Message> map = new HashMap<String, Message>();
		File xml = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
		doc.getDocumentElement().normalize();
		
		// Busca pelo nó <exception>
		NodeList rootChildrens = doc.getDocumentElement().getChildNodes();
		NodeList nList = rootChildrens;
		Node root = rootChildrens.item(0);
		Node n;
		while(root != null){
			n = root;
			if(n.getNodeName().equals("exception") || n.getNodeName().equals("web")){
				// Busca pelo nó <set> correspondente ao parâmetro set passado.
				nList = n.getChildNodes();
				n = nList.item(1);
				if(((Element)n).hasAttribute("number") && ((Element)n).getAttribute("number").equals(String.valueOf(set))){
					// Busca pelo nó <text> desejado.
					nList = n.getChildNodes();
					for(int j = 0; j < nList.getLength(); j++){
						n = nList.item(j);
						if(n.getNodeType() == Node.ELEMENT_NODE) {
							// Verificação do id do nó <text>
//							if(n.hasAttributes() && ((Element)n).getAttribute("id").equals(String.valueOf(id)) && ((Element)n).getAttribute("reference").equals(exception))
							int id = Integer.parseInt(((Element)n).getAttribute("id"));
							map.put(
									((Element)n).getAttribute("reference")+id, 
									new Message(id,
											((Element)n).getAttribute("reference"),
											n.getChildNodes().item(0).getNodeValue()));
						}
					}
				}
			}
			root = root.getNextSibling();
		}
		return map;
	}

}
