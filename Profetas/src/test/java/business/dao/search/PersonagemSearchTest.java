package business.dao.search;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import persistence.model.Local;
import persistence.model.Personagem;
import persistence.model.ReligiaoCrencas;
import persistence.model.exceptions.CharacterNotFoundException;
import persistence.model.exceptions.GroupMovementNotFoundException;
import persistence.model.exceptions.LocalNotFoundException;
import business.dao.model.LocalDAO;
import business.dao.model.PersonagemDAO;
import business.dao.model.ReligiaoCrencasDAO;
import business.dao.search.LocalSearchDAO;
import business.dao.search.PersonagemSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.DuplicatePersonagemException;
import business.exceptions.search.PersonagemNotFoundException;
import business.exceptions.search.business.dao.search.FontesObrasNotFoundException;

import com.google.common.collect.Lists;

public class PersonagemSearchTest {

    PersonagemSearchDAO charSearch = new PersonagemSearchDAO();
    LocalSearchDAO localSearch = new LocalSearchDAO();
    PersonagemDAO charCreator = new PersonagemDAO();
    LocalDAO localCreator = new LocalDAO();
    ReligiaoCrencasDAO religCreator = new ReligiaoCrencasDAO();

    @Before
    public void setUp() {
	try {
	    // Criar personagems, locais e religiões
	    Local grecia = localCreator.addLocal(new Local("Grécia", 1.0, 1.0));

	    List<ReligiaoCrencas> religioes = Lists.newArrayList(
		    religCreator.addReligion(new ReligiaoCrencas("ateu", null, null, null)),
		    religCreator.addReligion(new ReligiaoCrencas("ilusionista", null, null, null)));

	    Personagem joaozinho = new Personagem("joão", "joãozinho", grecia, null, grecia, null,
		    null, null, null, null, religioes, null, null, null, null);
	    charCreator.addCharacter(joaozinho);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void findPersonagemByNameTest() throws UnreachableDataBaseException, PersonagemNotFoundException, DuplicatePersonagemException {
	List<IdentifiedEntity> result = charSearch.findExactPersonagemByExactNome("joão");
	assertEquals(1, result.size());
	assertEquals("joão", ((Personagem) result.get(0)).getNome());
    }

    public void findPersonagemByLocalTest() throws LocalNotFoundException, UnreachableDataBaseException, FontesObrasNotFoundException, GroupMovementNotFoundException, Exception, CharacterNotFoundException {
	Local local = localSearch.findExactLocalByNome("Grécia");
	List<IdentifiedEntity> result = charSearch.findPersonagemByLocalNascimento(local.getId());
	assertEquals(1, result.size());
	assertEquals("joão", ((Personagem) result.get(0)).getNome());
    }
}
