package business.Bean.search;

import java.util.List;

import datatype.SimpleDate;
import business.DAO.search.PersonagemSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.DuplicatePersonagemException;
import business.exceptions.search.PersonagemNotFoundException;
import persistence.dto.DTO;
import persistence.dto.Personagem;



public class PersonagemSearchBean {
	PersonagemSearchDAO dao;

	public PersonagemSearchBean(){
		dao = new PersonagemSearchDAO();
	}

	
	public List<DTO> findPersonagemByNome(String nome) throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findPersonagemByNome(nome);
	}
	public List<DTO> findPersonagemByApelido(String apelido) throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findPersonagemByApelido(apelido);
	}
	public List<DTO> findPersonagemByLocalNascimento(long localNascimento) throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findPersonagemByLocalNascimento(localNascimento);
	}
	public List<DTO> findPersonagemByLocalMorte(long localMorte) throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findPersonagemByLocalMorte(localMorte);
	}
	public List<DTO> findPersonagemByDataNascimento(SimpleDate dataNascimento) throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findPersonagemByDataNascimento(dataNascimento);
	}
	public List<DTO> findPersonagemByDataMorte(SimpleDate dataMorte) throws UnreachableDataBaseException, PersonagemNotFoundException{
		return dao.findPersonagemByDataMorte(dataMorte);
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
	

	public Personagem findPersonagemByExactNome(String nome) throws UnreachableDataBaseException, PersonagemNotFoundException{
		List<DTO> resultSet;
		resultSet =  dao.findExactPersonagemByExactNome(nome);
		for(DTO dto : resultSet){
			if(((Personagem)dto).getNome().equals(nome));
					return (Personagem) dto;
		}
		throw new PersonagemNotFoundException("Entrada não encontrada.");
	}


}