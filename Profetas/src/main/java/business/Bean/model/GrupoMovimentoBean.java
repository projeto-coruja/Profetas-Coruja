package business.Bean.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import persistence.dto.DTO;
import persistence.dto.GrupoMovimento;
import persistence.dto.Local;
import persistence.exceptions.UpdateEntityException;
import business.DAO.model.GrupoMovimentoDAO;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.DuplicateGroupMovementException;
import business.exceptions.model.GroupMovementNotFoundException;
import datatype.SimpleDate;

public class GrupoMovimentoBean {
	
	GrupoMovimentoDAO dao;

	public GrupoMovimentoBean() {
		dao = new GrupoMovimentoDAO();
	}
	
	/**
	 * Adiciona um Grupo/Movimento novo.
	 * 
	 * @param name nome do novo Grupo/Movimento.
	 * @param yearBegin ano de início do Grupo/Movimento.
	 * @param yearEnd ano de fim do Grupo/Movimento.
	 * @param description descriçao do Grupo/Movimento.
	 * @param local lista de Locias onde teve o Grupo/Movimento.
	 * @throws UnreachableDataBaseException
	 * @throws DuplicateGroupMovementException
	 */
	public synchronized void addGroupMovement(String name, SimpleDate yearBegin, SimpleDate yearEnd, String description, List<Local> local) throws UnreachableDataBaseException, DuplicateGroupMovementException {
		try {
			dao.findGroupMovementByName(name);
		} catch (GroupMovementNotFoundException e) {
			dao.addGroupMovement(name, yearBegin, yearEnd, description, local);
		}
	}
	
	/**
	 * Remove um Grupo/Movimento.
	 * 
	 * @param name nome do Grupo/Movimento a ser removido.
	 * @throws UnreachableDataBaseException
	 */
	public synchronized void removeGroupMovementByName(String name) throws UnreachableDataBaseException {
		GrupoMovimento grupoMovimento;
		try {
			grupoMovimento = dao.findGroupMovementByName(name);
			dao.removeGroupMovement(grupoMovimento);					
		} catch (GroupMovementNotFoundException e){
			
		}
	}
	
	/**
	 * Atualiza dados de um Grupo/Movimento.
	 * 
	 * @param name nome do Grupo/Movimento a ser modificado.
	 * @param newName nome novo do Grupo/Movimento.
	 * @param newYearBegin ano de início do Grupo/Movimento.
	 * @param newYearEnd ano de fim do Grupo/Movimento.
	 * @param newDescription descrição do Grupo/Movimento.
	 * @param newLocal lista de Locias onde teve o Grupo/Movimento.
	 * @throws UnreachableDataBaseException
	 * @throws GroupMovementNotFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws UpdateEntityException
	 * @throws DuplicateGroupMovementException
	 */
	public synchronized void updateGroupMovement(String name, String newName, SimpleDate newYearBegin, SimpleDate newYearEnd, String newDescription, List<Local> newLocal) throws UnreachableDataBaseException, GroupMovementNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, UpdateEntityException, DuplicateGroupMovementException {
		GrupoMovimento grupoMovimento = dao.findGroupMovementByName(name);
		grupoMovimento.setNome(newName);
		grupoMovimento.setAnoInicio(newYearBegin);
		grupoMovimento.setAnoFim(newYearEnd);
		grupoMovimento.setDescricao(newDescription);
		grupoMovimento.setLocal(newLocal);
		dao.updateGroupMovement(grupoMovimento);	
	}
	
	/**
	 * Retorna uma lista de Grupos/Movimentos existentes.
	 * 
	 * @return uma lista de Grupos/Movimentos existentes.
	 * @throws UnreachableDataBaseException
	 * @throws GroupMovementNotFoundException
	 */
	public List<DTO> listAllGroupsMovement() throws UnreachableDataBaseException, GroupMovementNotFoundException {
		return dao.getAllGroupsMovement();
	}

}
