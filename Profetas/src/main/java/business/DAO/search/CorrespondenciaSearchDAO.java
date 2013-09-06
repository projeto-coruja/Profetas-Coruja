//Tratar correspondencia! Pesquisas nao funcionam!!!!!
package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;

import persistence.PersistenceAccess;


import persistence.dto.Correspondencia;
import persistence.dto.DTO;
import persistence.dto.FontesObras;
import persistence.util.DataAccessLayerException;
import business.exceptions.model.CorrespondenceNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

public class CorrespondenciaSearchDAO {
	


	private PersistenceAccess manager;

	public CorrespondenciaSearchDAO() {
		manager = new PersistenceAccess();
	}

	/**
	 * Pesquisa por uma correspondencia usando o remetente
	 * @param id - id do rementente
	 * @throws UnreachableDataBaseException
	 * @throws CorrespondenceNotFoundException
	 */

	public Correspondencia findCorrespondenciaByRemetente(long id) throws  UnreachableDataBaseException, CorrespondenceNotFoundException  {

		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM correspondenciaMO WHERE remetente_id = '"+ id +"'"
					+ " ORDER BY id");
			if(resultSet == null) {
				throw new CorrespondenceNotFoundException ("correspondencia não encontrada.");
			}
			else return (Correspondencia) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa por uma correspondencia usando o destinatario
	 * @param id - id do destinatario
	 * @throws UnreachableDataBaseException
	 * @throws CorrespondenceNotFoundException
	 */

	public Correspondencia findCorrespondenciaByDestinatario(long id) throws  UnreachableDataBaseException, CorrespondenceNotFoundException  {

		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM correspondenciaMO WHERE destinatario_id = '"+ id +"'"
					+ " ORDER BY id");
			if(resultSet == null) {
				throw new CorrespondenceNotFoundException ("Correspondencia não encontrada.");
			}
			else return (Correspondencia) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa por uma correspondencia usando o local
	 * @param id - id do local
	 * @throws UnreachableDataBaseException
	 * @throws CorrespondenceNotFoundException
	 */

	public Correspondencia findCorrespondenciaByLocal(long id) throws  UnreachableDataBaseException, CorrespondenceNotFoundException  {

		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM correspondenciaMO WHERE local_id = '"+ id +"'"
					+ " ORDER BY id");
			if(resultSet == null) {
				throw new CorrespondenceNotFoundException ("Correspondencia não encontrada.");
			}
			else return (Correspondencia) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	/**
	 * Pesquisa por uma correspondencia usando a data
	 * @param id - id do local
	 * @throws UnreachableDataBaseException
	 * @throws CorrespondenceNotFoundException
	 */

	public Correspondencia findCorrespondenciaByData(SimpleDate data) throws  UnreachableDataBaseException, CorrespondenceNotFoundException  {

		List<DTO> resultSet = null;
		try {
			resultSet = manager.findEntity("FROM correspondenciaMO WHERE data = '"+ data +"'"
					+ " ORDER BY id");
			if(resultSet == null) {
				throw new CorrespondenceNotFoundException ("Correspondencia não encontrada.");
			}
			else return (Correspondencia) resultSet.get(0);
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}

	public FontesObras findExactFontesObrasById(long id) throws FontesObrasNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}

