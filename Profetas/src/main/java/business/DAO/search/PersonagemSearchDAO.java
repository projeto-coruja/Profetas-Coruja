package business.DAO.search;

import java.util.List;

import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.PersonagemNotFoundException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Personagem;
import persistence.util.DataAccessLayerException;

public class PersonagemSearchDAO {

		private PersistenceAccess manager;
		
		public PersonagemSearchDAO(){
			manager= new PersistenceAccess();
		}
		/**
		 * Pesquisa por um personagem
		 * @param nome - nome do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public Personagem findexactPersonagem(String nome) throws PersonagemNotFoundException, UnreachableDataBaseException{
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("FROM PersonagemMO"+		
						"with nome = '"+nome+"'"+
						"ORDER BY nome " +"");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else{
					
					return (Personagem) resultSet.get(0);
				}
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
			
		}
		/**
		 * Pesquisa por uma "parcela" do nome do personagem 
		 * @param nome - nome do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagem(String nome) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from PersonagemMO where nome like '%" + nome +"%' "
						+ "order by cod, titulo, anoInicio, anoFim");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Persongem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
}
