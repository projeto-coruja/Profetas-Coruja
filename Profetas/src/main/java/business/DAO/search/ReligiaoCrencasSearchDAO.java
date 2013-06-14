package business.DAO.search;

import java.util.List;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.ReligiaoCrencas;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ReligionNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;

public class ReligiaoCrencasSearchDAO {
	private PersistenceAccess manager;

	public ReligiaoCrencasSearchDAO(){
		manager= new PersistenceAccess();
	}
	private String getQueryNormalization(String var){
		var.replace("'", "\'");

		return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}
	/**
	 * Pesquisa por um  grupo personagem
	 * @param nome - nome do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public ReligiaoCrencas findExactReligiaoCrencas(String nome) throws ReligionNotFoundException, UnreachableDataBaseException{
		nome = getQueryNormalization(nome);
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ReligiaoCrencasMO"+		
					" where nome = "+nome+" "+
					" ORDER BY nome ");

			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao não encontrada.");
			}
			else{

				return (ReligiaoCrencas) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}

	}

	/**
	 * Pesquisa por uma Religiao/Crenca exata por Id
	 * @param nome - nome da Religiao.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public ReligiaoCrencas findExactReligiaoCrencaById(int id) throws ReligionNotFoundException, UnreachableDataBaseException{

		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM ReligiaoCrencasMO"+		
					" where id = "+id+" "+
					" ORDER BY nome ");

			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao não encontrada.");
			}
			else{

				return (ReligiaoCrencas) resultSet.get(0);
			}
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}

	}
	/**
	 * Pesquisa por uma "parcela" do nome da Religiao 
	 * @param nome - nome da religiao.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findReligiaoCrencaByNome(String nome) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from GrupoPersonagemMO where nome like '%" + nome +"%' "
					+ "order by anoIngresso");

			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao não encontrada.");
			}
			else return resultSet;

		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa por uma "parcela" da descricao da Religiao/Crenca
	 * @param descricao - descricao do grupo movimento.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findReligiaoCrencaByDescricao(String descricao) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ReligiaoCrencasMO where descricao like '%" + descricao +"%' "
					+ "order by descricao");
			
			if(resultSet == null) {
				throw new ReligionNotFoundException ("Religiao/Crenca não encontrada por descricao.");
			}
			else return resultSet;
		
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	

	/**
	 * Pesquisa todas as Religioes/crencas
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<DTO> findAllReligiaoCrenca() throws  UnreachableDataBaseException, ReligionNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from ReligiaoCrencaMO order by nome");
			if(resultSet == null) {
				throw new ReligionNotFoundException("Não existe nenhuma Religiao Crenca cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}








}
