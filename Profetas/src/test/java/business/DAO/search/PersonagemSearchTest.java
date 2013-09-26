package business.DAO.search;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import datatype.SimpleDate;
import persistence.model.Encontro;
import persistence.model.EntityModel;
import persistence.model.FontesObras;
import persistence.model.GrupoPersonagem;
import persistence.model.LocaisPersonagens;
import persistence.model.Local;
import persistence.model.Personagem;
import persistence.model.ReligiaoCrencas;
import business.DAO.search.PersonagemSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.CharacterNotFoundException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.search.DuplicatePersonagemException;
import business.exceptions.search.PersonagemNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;


public class PersonagemSearchTest {
	//@Test
	public void personagemSearchTest() throws UnreachableDataBaseException, PersonagemNotFoundException, DuplicatePersonagemException{
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
		//dao.novoaddPersonagem("joao", "joazinho", grecia, nasci, roma, nasci, "ajsidjiasdji", "pensador", "nenhuma", referencia_bibliografica, religiao, grupo, locaisVisitados, encontro, obras);
		//Personagem p = (Personagem) dao.findPersonagem("joao");
		List<EntityModel> lalala = dao.findAllPersonagem();
		for(EntityModel p : lalala){
			System.out.println(((Personagem)p).getNome());
		}
		//assertEquals("joao", p.getNome());
	}
	@Test(expected =  LocalNotFoundException.class)
	public void findPersonagemMainANDTest() throws LocalNotFoundException, UnreachableDataBaseException, FontesObrasNotFoundException, GroupMovementNotFoundException, Exception, CharacterNotFoundException{
		PersonagemSearchDAO dao = new PersonagemSearchDAO();
		Local nascimento = new Local();
		nascimento.setNome("Atenas");
		SimpleDate nascimento1 = null;
		Local impressao = new Local();;
		//impressao.setNome("");
		List<EntityModel> religiao = null;
		List<EntityModel> grupo = null;
		List<EntityModel> locais_visitados = null;
		List<EntityModel> encontro = null;
		
		
		
		List<DTO> lalala = dao.findPersonagemMainAND("Aristocles", "Platão",nascimento.getNome(), nascimento.getLatitude(),nascimento.getLongitude(), nascimento1,
				 nascimento.getNome(), nascimento.getLatitude(), nascimento.getLongitude(),  nascimento1, "", "","",
				 "", "","", "","","","", nascimento1,
				 "","",nascimento1,nascimento1, "", impressao.getNome(),
				impressao.getLatitude(),impressao.getLongitude(), impressao.getNome(), impressao.getLatitude(),impressao.getLongitude(),
				"", religiao, grupo, locais_visitados, encontro);
		
		if(lalala.isEmpty()){
			System.out.println("Empty");
		}else{
			for(DTO p : lalala){
				System.out.println(((Personagem)p).getNome());
			}
		}
		
		
		
		
	}

}
