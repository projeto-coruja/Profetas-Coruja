package business.Bean.search;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import datatype.SimpleDate;

import persistence.dto.DTO;
import persistence.dto.FontesObras;
import persistence.dto.PalavraChave;
import persistence.dto.Personagem;
import business.DAO.search.ClassificacaoSearchDAO;
import business.DAO.search.FontesObrasSearchDAO;
import business.DAO.search.GrupoMovimentoSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;


public class FontesObrasSearchTest {

	//@Test
	public void findByclassificacaotest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException {
		FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra
		ClassificacaoSearchDAO dao2 = new ClassificacaoSearchDAO();
		
		DTO tipo  = dao2.findExactClassificacao("romance");
	
		
		List<DTO> lalala = dao.findFontesObrasByClassificacao(tipo.getId());
		for(DTO p : lalala){
			System.out.println(((FontesObras)p).getTitulo());
		}
		
	}
	//@Test
	public void findBygrupoMovimentotest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException, GroupMovementNotFoundException {
		FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra
		GrupoMovimentoSearchDAO dao2 = new GrupoMovimentoSearchDAO();
		
		DTO tipo  = (DTO) dao2.findExactGrupoMovimentoByNome("grupo");
		//List<DTO> grupo =  dao2.findAllGrupoMovimento();
		//for(DTO g : grupo){
		System.out.println(tipo.getId());
		//}
		
		
		List<DTO> lalala = dao.findFontesObrasByGrupoMovimento(tipo.getId());
		for(DTO p : lalala){
			System.out.println(((FontesObras)p).getTitulo());
		}
		
	}
	@Test
		public void findByTitulotest() throws UnreachableDataBaseException, FontesObrasNotFoundException, ClassificationNotFoundException, GroupMovementNotFoundException {
			FontesObrasSearchDAO dao = new FontesObrasSearchDAO();//pesquisa pela fonte/obra
			
			
		//	DTO tipo  = (DTO) dao.findExactFontesObras("Tragedia Grega");
			//DTO tipo  = (DTO) dao.findFontesObrasByTitulo("Tragedia");
			List<DTO> tipo =  dao.findFontesObrasByTitulo("Tragedia");
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
	//@Test
	public void findMain() throws FontesObrasNotFoundException, UnreachableDataBaseException, GroupMovementNotFoundException, LocalNotFoundException, ClassificationNotFoundException{
		SimpleDate dataimpressao = new SimpleDate((short) 0);
		//dataimpressao.format();
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
		
		List<DTO> fontes = dao.mainSearchAND("um belo", "bela","","www.teste.or.br", "", "",dataimpressao,"tio","grupo",dataimpressao,dataimpressao, "um grupo para teste", "local1", 15.522 , 5233.33 , "local1", 15.522 , 5233.33 , "romance",null,  null,null,null,null);
		if(fontes !=null){
			for(DTO p : fontes){
				System.out.println(((FontesObras)p).getTitulo());
			}
		}else{
			System.out.println("Lista vazia");
		}
	
	} 

}
