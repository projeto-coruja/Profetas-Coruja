package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;


import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ReligionNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.FontesObras;
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
		public List<DTO> findExactPersonagemByExactNome(String nome) throws PersonagemNotFoundException, UnreachableDataBaseException{
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("FROM personagemMO"+		
						" where nome = "+nome+" "+
						" ORDER BY nome ");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else{
					
					//return (Personagem) resultSet.get(0);
					return resultSet;
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
		public List<DTO> findPersonagemByNome(String nome) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where nome like '%" + nome +"%' "
						+ "order by cod, titulo, anoInicio, anoFim");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		
		/**
		 * Pesquisa por uma "parcela" do apelido do personagem 
		 * @param apelido - apelido do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByApelido(String apelido) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where apelido like '%" + apelido +"%' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pelo local de nascimento
		 * @param localnascimento_id - id do local de nascimento do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByLocalNascimento(long localnascimento_id ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where localnascimento_id  = '" + localnascimento_id +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pelo local da morte
		 * @param localmorte_id - id do local da morte do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByLocalMorte(long localmorte_id ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where localmorte_id  = '" + localmorte_id +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pela data de nascimento
		 * @param dataNascimento- data de nascimento do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByDataNascimento(SimpleDate dataNascimento ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where datanascimento = '" + dataNascimento +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pela data da morte
		 * @param dataMorte- data da morte do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByDataMorte(SimpleDate dataMorte ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where datanascimento = '" + dataMorte +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pela religiao
		 * @param religiao-religiao do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 * @throws ReligionNotFoundException 
		 */
		public List<DTO> findPersonagemByReligiao(String religiao) throws  UnreachableDataBaseException, PersonagemNotFoundException, ReligionNotFoundException {
			List<DTO> resultSet = null;
			ReligiaoCrencasSearchDAO dao = new ReligiaoCrencasSearchDAO();
			try {
								
				List<DTO> resultados = dao.findReligiaoCrencaByNome(religiao);
				for(DTO p : resultados){
					resultSet = manager.findEntity("from personagemMO_religiaocrencasMO where religiao_id = '" + p +"' "
							+ "order by nome");
				}
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Persongem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pela obra do personagem
		 * @param  obra-titulo da obra do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 * @throws FontesObrasNotFoundException 
		 */
		public List<DTO> findPersonagemByObra(String obra) throws  UnreachableDataBaseException, PersonagemNotFoundException, FontesObrasNotFoundException{
			List<DTO> resultSet = null;
			FontesObrasSearchDAO dao = new FontesObrasSearchDAO();
			try {
								
				List<DTO> resultados = dao.findFontesObrasByTitulo(obra);
				for(DTO p : resultados){
					resultSet = manager.findEntity("from personagemMO_fontesobrasMO where obras_id = '" + p +"' "
							+ "order by nome");
				}
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Persongem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa todos os personagens 
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findAllPersonagem() throws  UnreachableDataBaseException, PersonagemNotFoundException  {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO order by nome, apelido");
				if(resultSet == null) {
					throw new PersonagemNotFoundException("Não existe nenhum Personagem cadastrado.");
				}
				else return resultSet;
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		
		
			
}