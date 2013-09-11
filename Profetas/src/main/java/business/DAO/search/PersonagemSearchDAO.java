package business.DAO.search;

import java.util.List;

import datatype.SimpleDate;


import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.model.ReligionNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;
import persistence.PersistenceAccess;
import persistence.dto.DTO;
import persistence.dto.FontesObras;
import persistence.dto.Personagem;
import persistence.util.DataAccessLayerException;

public class PersonagemSearchDAO {

		private PersistenceAccess manager;
		
		public PersonagemSearchDAO(){
			manager= new PersistenceAccess();
		}
		private String getQueryNormalization(String var){
			var.replaceAll("'", "''");
					
			return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
		}
		
		public List<DTO> findPersonagemMainAND(String nome, String apelido, String local_nascimento, double local_nascimento_latitude,
				double local_nascimento_longitude,SimpleDate data_nascimento, String local_morte, double local_morte_latitude, 
				double local_morte_longitude, SimpleDate data_morte,
				String biografia, String ocupacao, String formacao, 
				String referencia_bibliografica, //procurar por uma fonte obras
				String titulo, String comentario, String ref_circ_obra, String url, String copias_manuscritas, String traducoes, 
				SimpleDate dataImpressao, String editor, 
				String grupoMovimento, SimpleDate anoInicio_grupomovimento, SimpleDate anoFim_grupomovimento, String descricao_grupomovimento, String local_grupomovimento,
				double latitude_grupomovimento, double longitude_grupomovimento,
				String localimpressao, double latitude_localimpressao, double longitude_localimpressao,
				String classificacao,
				List<DTO>religiao, List<DTO>grupo, List<DTO> locais_visitados, List<DTO>encontro) throws LocalNotFoundException, UnreachableDataBaseException, FontesObrasNotFoundException, GroupMovementNotFoundException, ClassificationNotFoundException{
			
			List<DTO> resultSet = null;
			String query= "from PersoangemMO where apelido like" + getQueryNormalization("'%" + apelido +"%'")
					+ "AND biografia like" + getQueryNormalization("'%" + biografia +"%'")
					+ "AND datamorte like" + getQueryNormalization("'%" + data_morte+"%'")
					+ "AND datanascimento like" + getQueryNormalization("'%" + data_nascimento+"%'")
					+ "AND formacao like" + getQueryNormalization("'%" + formacao +"%'")
					+ "AND nome like" + getQueryNormalization("'%" + nome +"%'")
					+ "AND ocupacao like" + getQueryNormalization("'%" + ocupacao +"%'")
					+ "order by nome";
			resultSet = manager.findEntity(query);
			LocalSearchDAO local_nascimento_dao = new LocalSearchDAO();
			LocalSearchDAO local_morte_dao = new LocalSearchDAO();
			FontesObrasSearchDAO referencia_bibliografica_dao = new FontesObrasSearchDAO();
			List<DTO> result_localNascimento = local_nascimento_dao.findLocalByAll(local_nascimento, local_nascimento_latitude, local_nascimento_longitude);
			List<DTO> result_localMorte = local_morte_dao.findLocalByAll(local_morte, local_morte_latitude, local_morte_longitude);
			List<DTO> result_referencia_bibliografica = referencia_bibliografica_dao.findFontesObrasByAll(referencia_bibliografica, comentario,
					ref_circ_obra, url, copias_manuscritas, traducoes, dataImpressao, editor, grupoMovimento, anoInicio_grupomovimento,
					anoFim_grupomovimento, descricao_grupomovimento, local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento,
					localimpressao, latitude_localimpressao, longitude_localimpressao, classificacao);
			
			//cruzamento de personagem  e local nascimento
			for(DTO l : result_localNascimento){
				resultSet.addAll(manager.findEntity("FROM PersonagemMO WHERE localnascimento_id = "+l.getId()));
			}
			//cruzamento de personagem  e local morte
			for(DTO l : result_localMorte){
				resultSet.addAll(manager.findEntity("FROM PersonagemMO WHERE localmorte_id = "+l.getId()));
			}
			//cruzamento de personagem  e local morte
			for(DTO l : result_referencia_bibliografica){
				resultSet.addAll(manager.findEntity("FROM PersonagemMO WHERE referencia_bibliografica_id = "+l.getId()));
			}
			return resultSet;
			
			
			
		}
		/**
		 * Pesquisa por um personagem
		 * @param nome - nome do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findExactPersonagemByExactNome(String nome) throws PersonagemNotFoundException, UnreachableDataBaseException{
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("FROM PersonagemMO"+		
						" where nome = '"+nome+"' "+
						" ORDER BY nome ");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else{
					
					//return (Personagem) resultSet.get(0);
					return resultSet;
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
		public List<DTO> findPersonagemByNome(String nome) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where nome like '%" + nome +"%' "
						+ "order by cod, titulo, anoInicio, anoFim");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		
		/**
		 * Pesquisa por uma "parcela" do apelido do personagem 
		 * @param apelido - apelido do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByApelido(String apelido) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where apelido like '%" + apelido +"%' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pelo local de nascimento
		 * @param localnascimento_id - id do local de nascimento do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByLocalNascimento(long localnascimento_id ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where localnascimento_id  = '" + localnascimento_id +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pelo local da morte
		 * @param localmorte_id - id do local da morte do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByLocalMorte(long localmorte_id ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where localmorte_id  = '" + localmorte_id +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pela data de nascimento
		 * @param dataNascimento- data de nascimento do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByDataNascimento(SimpleDate dataNascimento ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where datanascimento = '" + dataNascimento +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pela data da morte
		 * @param dataMorte- data da morte do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<DTO> findPersonagemByDataMorte(SimpleDate dataMorte ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<DTO> resultSet = null;
			try {
				resultSet = manager.findEntity("from personagemMO where datanascimento = '" + dataMorte +"' "
						+ "order by apelido");
				
				if(resultSet == null) {
					throw new PersonagemNotFoundException ("Personagem não encontrado.");
				}
				else return resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		/**
		 * Pesquisa personagens pela religiao
		 * @param religiao-religiao do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 * @throws ReligionNotFoundException 
		 */
		public List<DTO> findPersonagemByReligiao(String religiao) throws  UnreachableDataBaseException, PersonagemNotFoundException, ReligionNotFoundException {
			List<DTO> resultSet = null;
			ReligiaoCrencasSearchDAO dao = new ReligiaoCrencasSearchDAO();
			try {
								
				List<DTO> resultados = dao.findReligiaoCrencaByNome(religiao);
				for(DTO p : resultados){
					resultSet = manager.findEntity("from personagemMO_religiaocrencasMO where religiao_id = '" + p +"' "
							+ "order by nome");
				}
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
		 * Pesquisa personagens pela obra do personagem
		 * @param  obra-titulo da obra do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 * @throws FontesObrasNotFoundException 
		 */
		public List<DTO> findPersonagemByObra(String obra) throws  UnreachableDataBaseException, PersonagemNotFoundException, FontesObrasNotFoundException{
			List<DTO> resultSet = null;
			FontesObrasSearchDAO dao = new FontesObrasSearchDAO();
			try {
								
				List<DTO> resultados = dao.findFontesObrasByTitulo(obra);
				for(DTO p : resultados){
					resultSet = manager.findEntity("from personagemMO_fontesobrasMO where obras_id = '" + p +"' "
							+ "order by nome");
				}
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
				resultSet = manager.findEntity("from personagemMO order by nome, apelido");
				if(resultSet == null) {
					throw new PersonagemNotFoundException("Não existe nenhum Personagem cadastrado.");
				}
				else return resultSet;
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
		}
		
		
			
}