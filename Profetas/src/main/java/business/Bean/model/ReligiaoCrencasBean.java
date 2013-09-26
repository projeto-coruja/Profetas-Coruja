package business.Bean.model;

import java.util.List;

import persistence.model.EntityModel;
import persistence.model.ReligiaoCrencas;
import business.DAO.model.ReligiaoCrencasDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateReligionException;
import business.exceptions.model.ReligionNotFoundException;
import datatype.SimpleDate;

public class ReligiaoCrencasBean {
	
	private ReligiaoCrencasDAO dao;
	
	public ReligiaoCrencasBean(){
		dao = new ReligiaoCrencasDAO();
	}	
	
	/**
	 * Adiciona uma Religião/Crença nova.
	 * 
	 * @param name nome da nova Religião/Crença.
	 * @param yearBegin
	 * @param yearEnd
	 * @param description descrição sobre a Religião/Crença nova.
	 * @throws UnreachableDataBaseException
	 * @throws DuplicateReligionException
	 */
	public synchronized void addReligion(String name, SimpleDate yearBegin, SimpleDate yearEnd, String description) throws UnreachableDataBaseException, DuplicateReligionException {
		ReligiaoCrencas dto = null;
		try {
			dto = dao.findSingleReligionByName(name);
			if(dto != null)	throw new DuplicateReligionException("Religião/Crença já existe");
		} catch (ReligionNotFoundException e) {
			dao.addReligion(name, yearBegin, yearEnd, description);
			e.printStackTrace();
		}
	}

	/**
	 * Remove uma Religião/Crença específica.
	 * 
	 * @param name nome da Religião/Crença a ser removida.
	 * @throws UnreachableDataBaseException
	 * @throws ReligionNotFoundException
	 */
	public synchronized void removeReligionByName(String name) throws UnreachableDataBaseException, ReligionNotFoundException {
		dao.removeReligionByName(name);
	}

	/**
	 * Atualiza uma Religião/Crença específica.
	 * 
	 * @param name nome da Religião/Crença a ser atualizada.
	 * @param yearBegin
	 * @param yearEnd
	 * @param description a descrição da Religião/Crença a ser atualizada.
	 * @param newName nome novo da Religião/Crença.
	 * @param newYearBegin
	 * @param newYearEnd
	 * @param newDescription descrição nova da Religião/Crença.
	 * @throws UnreachableDataBaseException
	 * @throws ReligionNotFoundException
	 * @throws DuplicateReligionException
	 */
	public synchronized void updateReligion(String name, SimpleDate yearBegin, SimpleDate yearEnd, String description, String newName, SimpleDate newYearBegin, SimpleDate newYearEnd, String newDescription) throws UnreachableDataBaseException, ReligionNotFoundException, DuplicateReligionException {
		ReligiaoCrencas religion = dao.findSingleReligionByName(name);
		religion.setNome(newName);
		religion.setAnoInicio(newYearBegin);
		religion.setAnoFim(newYearEnd);
		religion.setDescricao(newDescription);
		dao.updateReligion(religion);
	}

	/**
	 * Retorna uma lista das Religiões/Crenças existentes.
	 * 
	 * @return uma lista ddas Religiões/Crenças existentes.
	 * @throws UnreachableDataBaseException
	 * @throws ReligionNotFoundException
	 */
	public List<EntityModel> listAllReligions() throws UnreachableDataBaseException, ReligionNotFoundException {
		return dao.getAllReligions();
	}
	
}
