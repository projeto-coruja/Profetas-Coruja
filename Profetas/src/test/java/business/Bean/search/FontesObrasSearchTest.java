package business.Bean.search;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import datatype.SimpleDate;

import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.FontesObras;
import persistence.dto.PalavraChave;
import persistence.dto.Personagem;
import business.DAO.search.ClassificacaoSearchDAO;
import business.DAO.search.FontesObrasSearchDAO;
import business.DAO.search.GrupoMovimentoSearchDAO;
import business.DAO.search.PersonagemSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;


public class FontesObrasSearchTest {

	//@Test
	public void findByclassificacaotest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException {
		FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra
		ClassificacaoSearchDAO dao2 = new ClassificacaoSearchDAO();
		
		DTO tipo  = dao2.findExactClassificacao("matematica");
	
		
		List<DTO> lalala = dao.findFontesObrasByClassificacao(tipo.getId());
		for(DTO p : lalala){
			System.out.println(((FontesObras)p).getTitulo());
		}
		
	}
	
	
	//@Test(expected = ClassificationNotFoundException.class)
	public void findByclassificacaoNotFoundtest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException {
		FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra
		ClassificacaoSearchDAO dao2 = new ClassificacaoSearchDAO();
		
		DTO tipo  = dao2.findExactClassificacao("m");
	
		
		List<DTO> lalala = dao.findFontesObrasByClassificacao(tipo.getId());
		for(DTO p : lalala){
			System.out.println(((FontesObras)p).getTitulo());
		}
		
	}
	//@Test
	public void findBygrupoMovimentotest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException, GroupMovementNotFoundException {
		FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra
		GrupoMovimentoSearchDAO dao2 = new GrupoMovimentoSearchDAO();
		
		DTO tipo  = (DTO) dao2.findExactGrupoMovimentoByNome("escola de pitagoras");
		//List<DTO> grupo =  dao2.findAllGrupoMovimento();
		//for(DTO g : grupo){
		System.out.println(tipo.getId());
		//}
		
		
		List<DTO> lalala = dao.findFontesObrasByGrupoMovimento(tipo.getId());
		for(DTO p : lalala){
			System.out.println(((FontesObras)p).getTitulo());
		}
		
	}
	//@Test
		public void findByTitulotest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException, GroupMovementNotFoundException {
			FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra
			
			
		//	DTO tipo  = (DTO) dao.findExactFontesObras("Tragedia Grega");
			//DTO tipo  = (DTO) dao.findFontesObrasByTitulo("Tragedia");
			List<DTO> tipo =  dao.findFontesObrasByTitulo("O triangulo de Pitagoras");
			for(DTO g : tipo){
				System.out.println(((DTO) g).getId());
				System.out.println(((FontesObras)g).getTitulo());
			}
			
			
			
		}
	//@Test
	public void findAlltest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException {
		FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra	
		List<DTO> lalala = dao.findAllFontesObras();
		for(DTO p : lalala){
			System.out.println(((FontesObras)p).getTitulo());
		}
		
	}
	@Test
		public void findtest() throws FontesObrasNotFoundException, UnreachableDataBaseException, PersonagemNotFoundException{
			PersistenceAccess manager = new PersistenceAccess();
			PersonagemSearchDAO daoPersonagem = new PersonagemSearchDAO();
			FontesObrasSearchDAO dao =new FontesObrasSearchDAO();
			System.out.println("antes");
			List<DTO> p= (List<DTO>) daoPersonagem.findExactPersonagemByExactNome("odisseu");
			System.out.println("depois");
			DTO o = dao.findExactFontesObrasById(1);
			for(DTO j:p){
				String query = ("FROM fontesobrasmo_personagemmo WHERE personagens_id =" + ((DTO) j).getId() + " AND fontesobrasmo_id="+ o.getId());
			
			
				List<DTO> resultSet = manager.findEntity(query);
				System.out.println(resultSet);
			}
		}
	
	//@Test(expected =  GroupMovementNotFoundException.class)
	public void findMain() throws FontesObrasNotFoundException, UnreachableDataBaseException, GroupMovementNotFoundException, LocalNotFoundException, ClassificationNotFoundException{
		SimpleDate dataimpressao = new SimpleDate((short) 2000,(short)02,(short)05);
		SimpleDate ano_inicioGrupo = new SimpleDate((short) 2000);
		SimpleDate ano_fimGrupo = new SimpleDate((short) 2012);
		FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra	
		List<Personagem> leitores = new ArrayList<Personagem>();
		List<Personagem> autores = new ArrayList<Personagem>();
		List<Personagem> personagens = new ArrayList<Personagem>();
		List<PalavraChave> palavras = new ArrayList<PalavraChave>();
		List<FontesObras> obrascitadas = new ArrayList<FontesObras>();
		obrascitadas.add(null);
		personagens.add(new Personagem("hades", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		palavras.add(new PalavraChave("obra"));
		autores.add(new Personagem("ulisses", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		leitores.add(new Personagem("hercules", null, null, null, null, null, null, null, null, null, null, null, null, null, null));
		
		List<DTO> fontes = dao.mainSearchAND("O triangulo de pitagoras", "comentario","nenhuma","http://pt.wikipedia.org/wiki/Escola_pitag%C3%B3rica", "nenhuma", "italiano, portugues",dataimpressao,"nenhum","escola de pitagoras",ano_inicioGrupo, ano_fimGrupo, "pai da matematica", "Grecia Central", 38.60, 22.71, "Grecia Central", 38.60, 22.71, "matematica",null,  null,null,null,null);
		if(fontes !=null){
			for(DTO p : fontes){
				System.out.println(((FontesObras)p).getTitulo());
			}
		}else{
			System.out.println("Lista vazia");
		}
	
	} 

}
