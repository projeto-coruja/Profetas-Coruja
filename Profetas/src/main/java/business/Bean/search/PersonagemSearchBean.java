package business.Bean.search;

import java.util.List;

import datatype.SimpleDate;
import business.DAO.search.PersonagemSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.DuplicatePersonagemException;
import business.exceptions.search.PersonagemNotFoundException;
import persistence.dto.DTO;
import persistence.dto.Encontro;
import persistence.dto.FontesObras;
import persistence.dto.GrupoPersonagem;
import persistence.dto.LocaisPersonagens;
import persistence.dto.Local;
import persistence.dto.Personagem;
import persistence.dto.ReligiaoCrencas;


public class PersonagemSearchBean {
	PersonagemSearchDAO dao;

	public PersonagemSearchBean(){
		dao = new PersonagemSearchDAO();
	}

	public List<DTO> findbyname(String name){

		return null;


	}
	public List<DTO> findByCod(String nome) throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findPersonagem(nome);
	}

	public List<DTO> getAllEntries() throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findAllPersonagem();
	}

	/*public List<DTO> getAllEntriesWithContent() throws UnreachableDataBaseException, CodiceCaixaNotFoundException{
		DocumentoDAO documento = new DocumentoDAO();
		List<DTO> list = dao.findAllCodiceCaixa();
		List<DTO> contents = new ArrayList<DTO>();
		//		HashSet<Integer> rm = new HashSet<Integer>();
		for(int i = 0; i < list.size(); i++) {
			CodiceCaixa c = (CodiceCaixa) list.get(i);
			Long count = documento.countDocumentsByCriteria("codiceCaixa.cod = '" + c.getCod() + "'");
			if(count != 0)	contents.add(list.get(i));
			//			if(count == 0) rm.add(i);
		}
		//		for(Integer i : rm) list.remove(i.intValue());

		return contents;
	}

	 */
	public synchronized void add(String nome, String apelido,
			Local localNascimento, SimpleDate dataNascimento, Local localMorte,
			SimpleDate dataMorte, String biografia, String ocupacao,
			String formacao, FontesObras referencia_bibliografica,
			List<ReligiaoCrencas> religião, List<GrupoPersonagem> grupo,
			List<LocaisPersonagens> locaisVisitados, List<Encontro> encontro,
			List<FontesObras> obras) throws UnreachableDataBaseException, DuplicatePersonagemException{
		 if(dataMorte.getYear() < dataNascimento.getYear())	throw new IllegalArgumentException("dataMorte < dataNascimento");
		 if(nome == null)	throw new IllegalArgumentException("nome vazio");
		 nome = nome.toUpperCase();
		 /*dao.addPersonagem(nome, apelido,localNascimento, dataNascimento, localMorte, dataMorte, biografia, ocupacao,
					formacao, referencia_bibliografica,religião, grupo, locaisVisitados, encontro,
					obras);*/
		 
		 int id = 0;
		dao.novoaddPersonagem(id, apelido, biografia, dataMorte, dataNascimento, formacao, nome, ocupacao, localMorte, localNascimento, referencia_bibliografica);
	 }

	 public Personagem findExactPersonagem(String nome, String apelido) throws UnreachableDataBaseException,PersonagemNotFoundException{
		 List<DTO> resultSet;
		 resultSet = dao.findPersonagem(nome);
		 for(DTO dto : resultSet){
			 if(((Personagem)dto).getNome().equals(nome) && ((Personagem)dto).getApelido().equals(apelido))
				 return (Personagem) dto;
		 }
		 throw new PersonagemNotFoundException("Entrada não encontrada.");
	 }


}