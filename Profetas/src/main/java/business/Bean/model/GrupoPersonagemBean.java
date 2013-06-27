package business.Bean.model;

import java.util.List;

import persistence.dto.DTO;
import persistence.dto.GrupoPersonagem;
import persistence.exceptions.UpdateEntityException;
import business.DAO.model.GrupoPersonagemDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.GroupCharacterNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import datatype.SimpleDate;

public class GrupoPersonagemBean {
	
	GrupoPersonagemDAO dao;

	public GrupoPersonagemBean() {
		dao = new GrupoPersonagemDAO();
	}
	
	/**
	 * Adiciona um Grupo/Movimento novo para Personagem.
	 * 
	 * @param entryYear ano de ingresso do Personagem neste Grupo/Movimento.
	 * @param groupMovementName nome do Grupo/Movimento.
	 * @throws UnreachableDataBaseException
	 * @throws GroupMovementNotFoundException
	 */
	public synchronized void addGroupCharacter(SimpleDate entryYear, String groupMovementName) throws UnreachableDataBaseException, GroupMovementNotFoundException {
		try {
			List<DTO> check = dao.findGroupCharacterByEntryYear(entryYear);
			for (DTO dto : check) {
				if (((GrupoPersonagem) dto).getGrupoMovimento().getNome().equals(groupMovementName))
					throw new IllegalArgumentException("Encontro já existe.");
			}			
		} catch (GroupCharacterNotFoundException e) {
			dao.addGroupCharacter(entryYear, groupMovementName);
		}
	}
	
	/**
	 * Remove um Grupo/Movimento (relacionado a Personagem).
	 * 
	 * @param name nome do Grupo/Movimento a ser removido.
	 * @throws UnreachableDataBaseException
	 * @throws GroupCharacterNotFoundException
	 */
	public synchronized void removeGroupMovementByName(String name) throws UnreachableDataBaseException, GroupCharacterNotFoundException {
		dao.removeGroupCharacter(name);
	}
	
	/**
	 * Atualiza dados de um Grupo/Movimento (relacionado a Personagem).
	 * 
	 * @param oldGroupMovementName nome do Grupo/Movimento a ser modificado.
	 * @param newGroupMovementName nome novo do Grupo/Movimento.
	 * @param newEntryYear ano de ingresso do Personagem neste Grupo/Movimento.
	 * @throws UnreachableDataBaseException
	 * @throws IllegalArgumentException
	 * @throws UpdateEntityException
	 * @throws GroupCharacterNotFoundException
	 */
	public synchronized void updateGroupCharacter(String oldGroupMovementName, String newGroupMovementName, SimpleDate newEntryYear) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException, GroupCharacterNotFoundException {
		if(oldGroupMovementName == null || newGroupMovementName == null || newEntryYear == null || oldGroupMovementName.equals("") || newGroupMovementName.equals("") || newEntryYear.equals("")) 
			throw new IllegalArgumentException("Argumentos não podem ser nulos/vazios.");
		
		try{
			List<DTO> check = dao.findGroupCharacterByGroupName(newGroupMovementName);
			for (DTO dto : check) {
				if (((GrupoPersonagem) dto).getGrupoMovimento().getNome().equals(newGroupMovementName))
					throw new IllegalArgumentException("Dado já existe.");
			}
		} catch (GroupCharacterNotFoundException e){
			dao.updateGroupCharacter(oldGroupMovementName, newGroupMovementName, newEntryYear);
		}	
	}
	
	/**
	 * Retorna uma lista de Grupos/Movimentos existentes (relacionados a Personagem).
	 * 
	 * @return uma lista de Grupos/Movimentos existentes (relacionados a Personagem).
	 * @throws UnreachableDataBaseException
	 * @throws GroupCharacterNotFoundException
	 */
	public List<DTO> listAllGroupsCharacters() throws UnreachableDataBaseException, GroupCharacterNotFoundException {
		return dao.getAllGroupsCharacters();
	}

}
