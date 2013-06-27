package business.Bean.model;

import java.util.List;

import persistence.dto.DTO;
import persistence.dto.LocaisPersonagens;
import persistence.exceptions.UpdateEntityException;
import business.DAO.model.LocaisPersonagensDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.model.LocalsCharactersNotFoundException;
import datatype.SimpleDate;

public class LocaisPersonagensBean {
	
	LocaisPersonagensDAO dao;

	public LocaisPersonagensBean() {
		dao = new LocaisPersonagensDAO();
	}
	
	/**
	 * Adiciona um Local novo onde o Personagem passou.
	 * 
	 * @param localName nome do Local.
	 * @param arrivalYear ano de chegada.
	 * @param leaveYear ano de saída.
	 * @throws UnreachableDataBaseException
	 * @throws LocalNotFoundException
	 */
	public synchronized void addLocalsCharacters(String localName, SimpleDate arrivalYear, SimpleDate leaveYear) throws UnreachableDataBaseException, LocalNotFoundException {
		//localName = localName.toLowerCase();
		try {
			List<DTO> check = dao.findLocalsCharacters(localName);
			for (DTO dto : check) {
				if (((LocaisPersonagens) dto).getAnoChegada().equals(arrivalYear) && ((LocaisPersonagens) dto).getAnoSaida().equals(leaveYear))
					throw new IllegalArgumentException("Dado já existe.");
			}
		} catch (LocalsCharactersNotFoundException e) {
			dao.addLocalsCharacters(localName, arrivalYear, leaveYear);
		}
	}	
	
	/**
	 * Remove um Local específico onde o Personagem passou.
	 * 
	 * @param localName nome do Local.
	 * @throws UnreachableDataBaseException
	 * @throws LocalsCharactersNotFoundException
	 */
	public synchronized void removeLocalsCharacters(String localName) throws UnreachableDataBaseException, LocalsCharactersNotFoundException {
		dao.removeLocalsCharacters(localName);		
	}
	

	/**
	 * Atualiza dados do Local onde o Personagem passou.
	 * 
	 * @param oldLocal nome do Local a ser modificado.
	 * @param newLocal nome novo do Local.
	 * @param arrivalYear ano de chegada.
	 * @param leaveYear ano de saída.
	 * @throws UnreachableDataBaseException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 * @throws LocalsCharactersNotFoundException
	 */
	public synchronized void updateLocalsCharacters(String oldLocal, String newLocal, SimpleDate arrivalYear, SimpleDate leaveYear) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException, LocalsCharactersNotFoundException {
		if(oldLocal == null || newLocal == null || arrivalYear == null || leaveYear == null ||
				oldLocal.equals("") || newLocal.equals("") || arrivalYear.equals("") || leaveYear.equals("")) 
			throw new IllegalArgumentException("Argumentos não podem ser nulos/vazios.");
		
		oldLocal = oldLocal.toLowerCase();
		newLocal = newLocal.toLowerCase();
		try{
			List<DTO> check = dao.findLocalsCharacters(newLocal);
			for (DTO dto : check) {
				if (((LocaisPersonagens) dto).getLocal().equals(newLocal))
					throw new IllegalArgumentException("Encontro já existe.");
			}
		} catch (LocalsCharactersNotFoundException e){
			dao.updateLocalsCharacters(oldLocal, newLocal, arrivalYear, leaveYear);
		}		
	}
	
	/**
	 * Retorna todos Locais por onde o Personagem já passou.
	 * 
	 * @return uma lista dos Locais por onde o Personagem já passou.
	 * @throws UnreachableDataBaseException
	 * @throws LocalsCharactersNotFoundException
	 */
	public List<DTO> listAllEncounters() throws UnreachableDataBaseException, LocalsCharactersNotFoundException {
		return dao.getAllLocalsCharacters();
	}

}
