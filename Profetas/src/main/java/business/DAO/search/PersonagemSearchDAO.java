package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;


import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.DuplicatePersonagemException;
import business.exceptions.search.PersonagemNotFoundException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.Encontro;
import persistence.dto.FontesObras;
import persistence.dto.GrupoPersonagem;
import persistence.dto.LocaisPersonagens;
import persistence.dto.Local;
import persistence.dto.Personagem;
import persistence.dto.ReligiaoCrencas;
import persistence.util.DataAccessLayerException;

public class PersonagemSearchDAO {

		private PersistenceAccess manager;
		
		public PersonagemSearchDAO(){
			manager= new PersistenceAccess();
		}
		/**
		 * Pesquisa por um personagem
		 * @param nome - nome do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public Personagem findExactPersonagem(String nome) throws PersonagemNotFoundException, UnreachableDataBaseException{
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("FROM PersonagemMO"+		
						"with nome = '"+nome+"'"+
						"ORDER BY nome " +"");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else{
					
					return (Personagem) resultSet.get(0);
				}
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
			
		}
		/**
		 * Pesquisa por uma "parcela" do nome do personagem 
		 * @param nome - nome do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagem(String nome) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from PersonagemMO where nome like '%" + nome +"%' "
						+ "order by cod, titulo, anoInicio, anoFim");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Persongem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		
		/**
		 * Pesquisa todos os personagens 
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findAllPersonagem() throws  UnreachableDataBaseException, PersonagemNotFoundException  {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from PersonagemMO order by nome, apelido");
				if(resultSet == null) {
					throw new PersonagemNotFoundException("Não existe nenhum Personagem cadastrado.");
				}
				else return resultSet;
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		
		/**
		 * Adiciona um personagem 
		 * @param nome - nome do personagem.
		 * @param apelido - apelido do personagem.
		 * @param localNascimento - Local localNascimento personagem.
		 * @param dataNascimento - dataNascimento personagem.
		 * @param localMorte - localMorte personagem.
		 * @param dataMorte - dataMorte personagem.
		 * @param biografia - biografia personagem.
		 * @param ocupacao - ocupacao personagem.
		 * @param formacao -formacao personagem.
		 * @param referencia_bibliografica - referencia_bibliografica personagem.
		 * @param religião - religião personagem.
		 * @param grupo -  grupo personagem.
		 * @param locaisVisitados - locaisVisitados personagem.
		 * @param encontro -  encontro personagem.
		 * @param obras - obras personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 * @throws DuplicatePersonagemException
		 */
		public Personagem addPersonagem(String nome, String apelido,
				Local localNascimento, SimpleDate dataNascimento, Local localMorte,
				SimpleDate dataMorte, String biografia, String ocupacao,
				String formacao, FontesObras referencia_bibliografica,
				List<ReligiaoCrencas> religião, List<GrupoPersonagem> grupo,
				List<LocaisPersonagens> locaisVisitados, List<Encontro> encontro,
				List<FontesObras> obras) throws UnreachableDataBaseException, DuplicatePersonagemException {
			
			Personagem newId = new Personagem(nome, apelido, localNascimento, dataNascimento,localMorte,
					dataMorte,biografia,  ocupacao,formacao, referencia_bibliografica,
					religião, grupo, locaisVisitados, encontro, obras);
			try{
				findExactPersonagem(nome);
				throw new DuplicatePersonagemException("Personagem ja existe.");
			}catch(DataAccessLayerException e){
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");			
			} catch (PersonagemNotFoundException e) {
				manager.saveEntity(newId);
				return newId;
			}
			// TODO Auto-generated method stub
			
		}
}
