package business.Bean.model;

import java.util.List;

import persistence.exceptions.UpdateEntityException;
import persistence.model.IdentifiedEntity;
import persistence.model.Local;
import business.DAO.model.LocalDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateLocalException;
import business.exceptions.model.LocalNotFoundException;

public class LocalBean {
	
	private LocalDAO dao;
	
	public LocalBean(){
		dao = new LocalDAO();
	}
	
	/**
	 * Adiciona um Local novo.
	 * 
	 * @param name - Nome do novo Local.
	 * @param latitude - Latitude do novo Local.
	 * @param longitude - Longitude do novo Local.
	 * @throws UnreachableDataBaseException
	 * @throws DuplicateLocalException
	 */
	public synchronized void addLocal(String name, double latitude, double longitude) throws UnreachableDataBaseException, DuplicateLocalException {
		try {
			dao.findExactLocal(latitude, longitude);
		} catch (LocalNotFoundException e) {
			dao.addLocal(name, latitude, longitude);
			e.printStackTrace();
		}
	}
	
	/**
	 * Remove um Local específico.
	 * 
	 * @param latitude - Latitude do Local a ser removido.
	 * @param longitude - Longitude do Local a ser removido.
	 * @throws UnreachableDataBaseException
	 */
	public synchronized void removeLocal(double latitude, double longitude) throws UnreachableDataBaseException {
		Local local;
		try {
			local = dao.findExactLocal(latitude, longitude);
			dao.removeLocal(local);
		} catch (LocalNotFoundException e){
			
		}
	}
	
	/**
	 * Atualiza um Local específico.
	 * 
	 * @param name - Nome do Local a ser atualizado.
	 * @param latitude - Latitude do Local a ser atualizado.
	 * @param longitude - Longitude do Local a ser atualizado.
	 * @param newName - Nome novo do Local.
	 * @param newLatitude - Latitude nova do Local.
	 * @param newLongitude - Longitude nova do Local.
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 */
	public synchronized void updateLocal(String name, double latitude, double longitude, String newName, double newLatitude, double newLongitude) 
			throws UnreachableDataBaseException, LocalNotFoundException, IllegalArgumentException, UpdateEntityException {
		
		Local local  = dao.findExactLocal(latitude, longitude);
		local.setNome(newName);
		local.setLatitude(newLatitude);
		local.setLongitude(newLongitude);
		dao.updateLocal(local);
	}
	
	/**
	 * Retorna uma lista dos Locais existentes.
	 * 
	 * @return uma lista dos Locais existentes.
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public List<IdentifiedEntity> listAllLocals() throws UnreachableDataBaseException, LocalNotFoundException {
		return dao.findAllLocal();
	}
	
}
