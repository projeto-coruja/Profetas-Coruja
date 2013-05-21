package business.Bean.search;

import java.util.ArrayList;
import java.util.List;
import business.DAO.search.PersonagemSearchDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.PersonagemNotFoundException;
import persistence.dto.DTO;
import persistence.dto.Personagem;


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
