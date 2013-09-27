package business.DAO.search;

import java.util.List;

import persistence.EntityManager;
import persistence.model.ReligiaoCrencas;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ReligionNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;

public class ReligiaoCrencasSearchDAO {
	
	private EntityManager manager;

	public ReligiaoCrencasSearchDAO(){
		manager= new EntityManager();
	}
	
	/**
	 * Pesquisa por um  grupo personagem
	 * @param nome - nome do grupo personagem.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public ReligiaoCrencas findExactReligiaoCrencas(String nome) throws ReligionNotFoundException, UnreachableDataBaseException{
		try {
			ReligiaoCrencas resultSet = manager.findByNaturalId(ReligiaoCrencas.class, nome);
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
	 * Pesquisa por uma parte do nome da Religiao 
	 * @param nome - nome da religiao.
	 * @throws UnreachableDataBaseException
	 * @throws PersonagemNotFoundException
	 */
	public List<ReligiaoCrencas> findReligiaoCrencaByNome(String nome) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<ReligiaoCrencas> resultSet = null;
		try {
			resultSet = manager.find("from religiaocrencas where nome like '%" + nome + "%' order by anoIngresso");
			if(resultSet.isEmpty()) {
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
	public List<ReligiaoCrencas> findReligiaoCrencaByDescricao(String descricao) throws  UnreachableDataBaseException, ReligionNotFoundException {
		List<ReligiaoCrencas> resultSet = null;
		try {
			resultSet = manager.find("from religiaocrencas where descricao like '%" + descricao + "% order by descricao");
			if(resultSet.isEmpty()) {
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
	public List<ReligiaoCrencas> findAllReligiaoCrenca() throws  UnreachableDataBaseException, ReligionNotFoundException  {
		List<ReligiaoCrencas> resultSet = null;
		try {
			resultSet = manager.find("from religiaocrencas order by nome");
			if(resultSet.isEmpty()) {
				throw new ReligionNotFoundException("Não existe nenhuma Religiao Crenca cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

}
