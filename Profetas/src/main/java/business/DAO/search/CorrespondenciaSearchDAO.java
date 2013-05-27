//Tratar correpondencia! Pesquisas nao funcionam!!!!!
package business.DAO.search;

import java.util.List;

import persistence.PersistenceAccess;


import persistence.dto.Correspondencia;
import persistence.dto.DTO;
import persistence.util.DataAccessLayerException;
import business.exceptions.model.CorrespondenceNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class CorrespondenciaSearchDAO {
	


	private PersistenceAccess manager;

	public CorrespondenciaSearchDAO() {
		manager = new PersistenceAccess();
	}



	public Correspondencia findExactClassificacao(String tipo) 
			throws  UnreachableDataBaseException, CorrespondenceNotFoundException  {

		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM CorrespondenciaMO WHERE tipo = '"+ tipo +"'"
					+ " ORDER BY id, tipo");
			if(resultSet == null) {
				throw new CorrespondenceNotFoundException ("Correspondencia não encontrada.");
			}
			else return (Correspondencia) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<DTO> findCodiceCaixaByTipo(String tipo) throws  UnreachableDataBaseException,CorrespondenceNotFoundException {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from CorrespondenciaMO where tipo like '%" + tipo +"%' "
					+ "order by id, tipo");

			if(resultSet == null) {
				throw new CorrespondenceNotFoundException ("Correspondencia não encontrada.");
			}
			else return resultSet;

		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<DTO> findCodiceCaixaById(int id) throws  UnreachableDataBaseException,CorrespondenceNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from CCorrespondenciaMO where id =" + id +"order by id, tipo");
			if(resultSet == null) {
				throw new CorrespondenceNotFoundException ("Id de Correspondencia não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public List<DTO> findAllClassificacao() throws  UnreachableDataBaseException, CorrespondenceNotFoundException  {
		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("from CorrespondenciaMO order by tipo");
			if(resultSet == null) {
				throw new CorrespondenceNotFoundException("Não existe nenhuma Correspondencia cadastrada.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

}

