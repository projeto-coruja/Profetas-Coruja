package alt;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLMessagesParser {
	
	private static final String filename = "modelo-alt.xml"; 
	
	public static MessageRepository generateRepositoryFromXML() {
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);
		MessageRepository repo = new MessageRepository();
		
		/* São 3 loops aninhados: o loop pai adiciona categorias, o segundo loop adiciona os
		 * "buckets" (agrupamentos por referencia) e o terceiro adiciona as mensagens em si
		 */
		try {
			//Primeiro pegando a lista de categorias
			Document message_list = builder.build(xmlFile);
			Element rootNode = message_list.getRootElement();
			List<Element> categoryList = rootNode.getChildren();
			
			//Agora iterando pela lista de categorias
			for(Element cat : categoryList){
				
				//Nova categoria adicionada no repositório
				String category = cat.getAttributeValue("name");
				repo.putCategory(category);
				
				//E agora pegando os buckets da categoria, e iterando neles
				List<Element> bucketList = cat.getChildren();
				for(Element bucket : bucketList) {
					
					//Repete tudo com as referẽncias
					String reference = bucket.getAttributeValue("reference");
					repo.putBucket(category, reference);
					List<Element> messageList = bucket.getChildren();
					for(Element msg : messageList) {
						
						//Finalmente, adicionando a mensagem com o atributo "id"
						int id = Integer.parseInt(msg.getAttributeValue("id"));
						String message = msg.getText();
						repo.putMessage(category, reference, id, message);
					}
				}
			}
			//Para prevenir alterações no repositório, chama a função de finalizar construção:
			repo.finishRepositoryBuilding();
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return repo;
	}

}
