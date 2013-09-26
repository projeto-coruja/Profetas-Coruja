package business.Bean.model;

import java.util.List;

import persistence.exceptions.UpdateEntityException;
import persistence.model.Encontro;
import persistence.model.IdentifiedEntity;
import business.DAO.model.EncontroDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.EncounterNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import datatype.SimpleDate;

public class EncontroBean {
	
	EncontroDAO dao;

	public EncontroBean() {
		dao = new EncontroDAO();
	}
	
	/**
	 * Adiciona um Encontro novo.
	 * 
	 * @param date data do Encontro.
	 * @param localName nome do local do Encontro.
	 * @throws UnreachableDataBaseException
	 * @throws EncounterNotFoundException
	 * @throws LocalNotFoundException
	 */
	public synchronized void addEncounter(SimpleDate date, String localName) throws UnreachableDataBaseException, LocalNotFoundException {
		//localName = localName.toLowerCase();
		try {
			List<IdentifiedEntity> check = dao.findEncounterByDate(date);
			for (IdentifiedEntity dto : check) {
				if (((Encontro) dto).getLocal().getNome().equals(localName))
					throw new IllegalArgumentException("Encontro já existe.");
			}
		} catch (EncounterNotFoundException e) {
			dao.addEncounter(date, localName);
		}
	}

	/*public synchronized void removeEncounter(String localName) {
		PersonagemBean personagemBean = new PersonagemBean();
		List<DTO> results = null;

		//localName = localName.toLowerCase();

		try {
			results = personagemBean.findCharacterByEncounter(localName);
			for(DTO dto : results){
				Personagem personagem = (Personagem) dto;
				if(personagem.getEncontro() != null){
					personagem.setEncontro(null);
				}
				personagemBean.updateCharacter(personagem);
			}
		} catch (CharacterNotFoundException e) {
			
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UpdateEntityException e) {
			e.printStackTrace();
		} finally {
			dao.removeEncounter(localName);
		}
	}*/
	
	/**
	 * Remove um Local específico.
	 * 
	 * @param localName nome do local do Encontro a ser removido.
	 * @throws UnreachableDataBaseException
	 * @throws EncounterNotFoundException
	 */
	public synchronized void removeEncounter(String localName) throws UnreachableDataBaseException, EncounterNotFoundException {
		dao.removeEncounter(localName);		
	}
	
	/**
	 * Atualiza um Encontro específico
	 * 
	 * @param oldEncounterLocal nome do Local do Encontro a ser atualizado.
	 * @param newEncounterLocal nome novo do Local do Encontro.
	 * @param newDate data nova do Encotro.
	 * @throws UnreachableDataBaseException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 * @throws EncounterNotFoundException
	 */
	public synchronized void updateEncounter(String oldEncounterLocal, String newEncounterLocal, SimpleDate newDate) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException, EncounterNotFoundException {
		if(oldEncounterLocal == null || newEncounterLocal == null || newDate == null || oldEncounterLocal.equals("") || newEncounterLocal.equals("") || newDate.equals("")) 
			throw new IllegalArgumentException("Argumentos não podem ser nulos/vazios.");
		
		oldEncounterLocal = oldEncounterLocal.toLowerCase();
		newEncounterLocal = newEncounterLocal.toLowerCase();
		try{
			List<IdentifiedEntity> check = dao.findEncounterByLocalName(newEncounterLocal);
			for (IdentifiedEntity dto : check) {
				if (((Encontro) dto).getLocal().equals(newEncounterLocal))
					throw new IllegalArgumentException("Encontro já existe.");
			}
		} catch (EncounterNotFoundException e){
			dao.updateEncounter(oldEncounterLocal, newEncounterLocal, newDate);
		}		
	}
	
	/**
	 * Retorna uma lista dos Encontros existentes.
	 * 
	 * @return uma lista dos Encontros existentes.
	 * @throws UnreachableDataBaseException
	 * @throws EncounterNotFoundException
	 */
	public List<IdentifiedEntity> listAllEncounters() throws UnreachableDataBaseException, EncounterNotFoundException {
		return dao.getAllEncounters();
	}

}
