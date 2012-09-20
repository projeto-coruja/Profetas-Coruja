import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;


public class MessageMapTest {
	MessageMap map;
	@Before
	@Test
	public void testGetMessageMap() throws SAXException, IOException, ParserConfigurationException {
		map = MessageMap.getMessageMap();
		assertNotNull(map);
	}
	@Test
	public void testGetMessage() {
		assertEquals("Texto de Saída",map.getMessage("texto_de_entrada", 1));
		assertEquals("Chave já em uso.",map.getMessage("IllegalArgumentException", 1));
		assertEquals("Ponteironulo!",map.getMessage("NullPointerException", 1));
		assertEquals("HAHAHAHA!",map.getMessage("NullPointerException", 2));
		assertEquals("HOHOHOHOHO!",map.getMessage("NullPointerException", 3));
		assertEquals("fuuuu!!",map.getMessage("TestPointerException", 1));
	}
}
