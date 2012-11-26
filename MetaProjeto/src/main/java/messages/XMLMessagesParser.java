package messages;

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
				
				//E agora pegando os contextos da categoria, e iterando neles
				List<Element> contextList = cat.getChildren();
				for(Element ctx : contextList) {
					
					//Repete tudo com os contextos
					String context = ctx.getAttributeValue("name");
					repo.putContext(category, context);
					List<Element> messageList = ctx.getChildren();
					for(Element msg : messageList) {
						
						String reference = msg.getAttributeValue("reference");
						if(!repo.referenceExists(category, context, reference))
							repo.putReference(category, context, reference);
						
						//Finalmente, adicionando a mensagem com o atributo "id"
						int id = Integer.parseInt(msg.getAttributeValue("id"));
						String message = msg.getText();
						repo.putMessage(category, context, reference, id, message);
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
