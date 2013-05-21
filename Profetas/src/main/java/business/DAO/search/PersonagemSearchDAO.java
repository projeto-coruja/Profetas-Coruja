package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;

import business.exceptions.documents.CodiceCaixaNotFoundException;
import business.exceptions.documents.DuplicateCodiceCaixaException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.search.DuplicatePersonagemException;
import business.exceptions.search.PersonagemNotFoundException;
import persistence.PersistenceAccess;
import persistence.dto.CodiceCaixa;
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
		public Personagem findexactPersonagem(String nome) throws PersonagemNotFoundException, UnreachableDataBaseException{
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
		public Personagem addPersonagem(String nome, String apelido,
				Local localNascimento, SimpleDate dataNascimento, Local localMorte,
				SimpleDate dataMorte, String biografia, String ocupacao,
				String formacao, FontesObras referencia_bibliografica,
				List<ReligiaoCrencas> religião, List<GrupoPersonagem> grupo,
				List<LocaisPersonagens> locaisVisitados, List<Encontro> encontro,
				List<FontesObras> obras) {
			Long id;
			Personagem newId = new Personagem(id, nome, apelido, localNascimento, dataNascimento,localMorte,
					dataMorte,biografia,  ocupacao,formacao, referencia_bibliografica,
					religião, grupo, locaisVisitados, encontro, obras);
			try{
				findexactPersonagem(nome);
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
