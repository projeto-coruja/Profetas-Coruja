package business.DAO.model;

import java.util.List;

import datatype.SimpleDate;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.GroupCharacterNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import persistence.EntityManager;
import persistence.exceptions.UpdateEntityException;
import persistence.model.IdentifiedEntity;
import persistence.model.GrupoMovimento;
import persistence.model.GrupoPersonagem;
import persistence.util.DataAccessLayerException;

public class GrupoPersonagemDAO {

	private EntityManager manager;

	public GrupoPersonagemDAO() {
		manager = new EntityManager();	
	}
	
	public GrupoPersonagem addGroupCharacter(SimpleDate entryYear, String groupMovementName) throws UnreachableDataBaseException, GroupMovementNotFoundException {
		GrupoMovimento newGrupoMovimento = (new GrupoMovimentoDAO()).findGroupMovementByName(groupMovementName);		
		GrupoPersonagem newGroupCharacter = new GrupoPersonagem(entryYear, newGrupoMovimento);
		
		try {
			manager.save(newGrupoMovimento);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");			
		}
		return newGroupCharacter;
	}
	
	public void removeGroupCharacter(String groupMovementName) throws UnreachableDataBaseException, GroupCharacterNotFoundException {
		List<IdentifiedEntity> check = null;
		GrupoMovimento select = null;
		try {
			check = findGroupCharacterByGroupName(groupMovementName);
			for(IdentifiedEntity dto : check) {
				if (((GrupoMovimento) dto).getNome().equals(groupMovementName))
					select = (GrupoMovimento) dto;
			}
			if(select == null)	throw new GroupCharacterNotFoundException("Grupo-Personagem não encontrado.");
			manager.delete(select);
		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public GrupoPersonagem updateGroupCharacter(String oldGroupMovementName, String newGroupMovementName, SimpleDate newEntryYear) throws UnreachableDataBaseException, IllegalArgumentException, UpdateEntityException, GroupCharacterNotFoundException {
		List<IdentifiedEntity> check = null;
		GrupoPersonagem selectGrupoPersonagem = null;
		GrupoMovimento selectGrupoMovimento = null;
		
		boolean unmodifiedGroupMovement = false;
		try {
			if(newGroupMovementName != null && !newGroupMovementName.isEmpty()) {
				try {	
					selectGrupoMovimento = (new GrupoMovimentoDAO()).findGroupMovementByName(newGroupMovementName);
				} catch(DataAccessLayerException e) {
					e.printStackTrace();
					throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
				} catch (GroupMovementNotFoundException e) {
					throw new IllegalArgumentException("Grupo/Movimento não existe.");
				}
			}
			else unmodifiedGroupMovement = true;
			
			check = findGroupCharacterByGroupName(oldGroupMovementName);
			for(IdentifiedEntity dto : check) {
				if(((GrupoPersonagem) dto).getGrupoMovimento().getNome().equals(oldGroupMovementName))
					selectGrupoPersonagem = (GrupoPersonagem) dto;
			}
			
			if(!unmodifiedGroupMovement) selectGrupoPersonagem.setGrupoMovimento(selectGrupoMovimento);
			selectGrupoPersonagem.setAnoIngresso(newEntryYear);
			manager.update(selectGrupoPersonagem);

		} catch(DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
		
		return selectGrupoPersonagem;
	}
	
	public List<IdentifiedEntity> findGroupCharacterByEntryYear(SimpleDate year) throws UnreachableDataBaseException, GroupCharacterNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoPersonagem WHERE anoIngresso = '" + year + "' ORDER BY anoIngresso, grupoMovimento.nome");
			if(resultSet == null) {
				throw new GroupCharacterNotFoundException ("Grupo-Personagem não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados.");
		}
	}
	
	public List<IdentifiedEntity> findGroupCharacterByGroupName(String groupName) throws UnreachableDataBaseException, GroupCharacterNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoPersonagem WHERE grupoMovimento.nome = '" + groupName + "' ORDER BY grupoMovimento.nome, anoIngresso");
			if(resultSet == null) {
				throw new GroupCharacterNotFoundException ("Grupo-Personagem não encontrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public List<IdentifiedEntity> getAllGroupsCharacters() throws  UnreachableDataBaseException, GroupCharacterNotFoundException {
		List<IdentifiedEntity> resultSet = null;
		try {
			resultSet = manager.find("FROM GrupoPersonagem ORDER BY grupoMovimento.nome, anoIngresso");
			if(resultSet == null) {
				throw new GroupCharacterNotFoundException ("Não existe nenhum Grupo-Personagem cadastrado.");
			}
			else return resultSet;
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}	
}
