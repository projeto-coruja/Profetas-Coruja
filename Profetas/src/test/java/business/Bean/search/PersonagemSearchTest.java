package business.Bean.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import datatype.SimpleDate;

import persistence.dto.Encontro;
import persistence.dto.FontesObras;
import persistence.dto.GrupoPersonagem;
import persistence.dto.LocaisPersonagens;
import persistence.dto.Local;
import persistence.dto.ReligiaoCrencas;
import persistence.model.ReligiaoCrencasMO;

import business.DAO.search.PersonagemSearchDAO;


public class PersonagemSearchTest {
	
	@Before
	@Test
	public void personagemSearchTest(){
		PersonagemSearchDAO dao = new PersonagemSearchDAO();
		Local grecia =new Local("Grecia", 1.0, 1.0);
		Local roma= new Local("Roma", 1.0,1.0);
		FontesObras referencia_bibliografica = null;
		List<ReligiaoCrencas> religiao = new ArrayList<ReligiaoCrencas>();
		religiao.add(new ReligiaoCrencas("ateu", null, null, null));
		religiao.add(new ReligiaoCrencas("ilusionista", null, null, null));
		List<GrupoPersonagem> grupo = null;
		List<LocaisPersonagens> locaisVisitados = null;
		List<Encontro> encontro = null;
		List<FontesObras> obras = null;
		SimpleDate nasci = null;
		dao.addPersonagem("joao", "joazinho", grecia, nasci, roma, nasci, "ajsidjiasdji", "pensador", "nenhuma", referencia_bibliografica, religiao, grupo, locaisVisitados, encontro, obras);
		
		
		
	}

}
