package business.DAO.search;

import java.util.ArrayList;
import java.util.List;

import datatype.SimpleDate;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.model.CharacterNotFoundException;
import business.exceptions.model.ClassificationNotFoundException;
import business.exceptions.model.GroupMovementNotFoundException;
import business.exceptions.model.LocalNotFoundException;
import business.exceptions.model.ReligionNotFoundException;
import business.exceptions.search.PersonagemNotFoundException;
import business.exceptions.search.business.DAO.search.FontesObrasNotFoundException;
import persistence.EntityManager;
import persistence.model.EntityModel;
import persistence.util.DataAccessLayerException;

public class PersonagemSearchDAO {

		private EntityManager manager;
		
		public PersonagemSearchDAO(){
			manager= new EntityManager();
		}
		private String getQueryNormalization(String var){
			var.replaceAll("'", "''");
					
			return "LOWER(TRANSLATE("+var+",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
		}
		public List<EntityModel> findPersonagemGeneric(String string) throws  UnreachableDataBaseException, CharacterNotFoundException{
			 List <EntityModel> resultSet = null;
			 try {
				 String query= "from PersoangemMO where apelido like" + getQueryNormalization("'%" + string +"%'")
							+ "OR biografia like" + getQueryNormalization("'%" + string +"%'")
							+ "OR formacao like" + getQueryNormalization("'%" +string +"%'")
							+ "OR nome like" + getQueryNormalization("'%" + string +"%'")
							+ "OR ocupacao like" + getQueryNormalization("'%" + string +"%'")				
							+ "order by nome";
					
				resultSet = manager.find(query);
				
				if(resultSet==null){
					throw new CharacterNotFoundException("Personagem não encontrado.");
				}else return resultSet;
			}catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
			 
		 }
		
		public List<EntityModel> findPersonagemMainAND(String nome, String apelido, String local_nascimento, double local_nascimento_latitude,
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
				List<EntityModel>religiao, List<EntityModel>grupo, List<EntityModel> locais_visitados, List<EntityModel>encontro) throws LocalNotFoundException, UnreachableDataBaseException, FontesObrasNotFoundException, GroupMovementNotFoundException, ClassificationNotFoundException, CharacterNotFoundException{
			
			List<EntityModel> resultSet = null;
			List<EntityModel> resultTemp=null;

			
			PersonagemSearchDAO personagens = new PersonagemSearchDAO();
			
			List<EntityModel> resultPersonagem = personagens.findPersonagemByAll(nome, apelido, local_nascimento, local_nascimento_latitude, 
					local_nascimento_longitude, data_nascimento, local_morte, local_morte_latitude, local_morte_longitude, data_morte,
					biografia, ocupacao, formacao, referencia_bibliografica, titulo, comentario, ref_circ_obra, url, copias_manuscritas,
					traducoes, dataImpressao, editor, grupoMovimento, anoInicio_grupomovimento, anoFim_grupomovimento, descricao_grupomovimento,
					local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento, localimpressao, latitude_localimpressao, longitude_localimpressao, classificacao);
					
			resultSet= resultPersonagem;
			if(religiao!=null && resultSet!=null){
				for(EntityModel r : religiao){
					for(EntityModel l : resultSet){
						 resultTemp = manager.find("FROM personagemmo_religiaocrencasmo WHERE personagemmo_id =" + l.getId() + "AND religião_id="+ r.getId());
					}
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			if(grupo!=null && resultSet!=null){
				for(EntityModel g : grupo){
					for(EntityModel l : resultSet){
						 resultTemp = manager.find("FROM personagemmo_grupopersonagemmo WHERE personagemmo_id =" + l.getId() + "AND grupo_id="+ g.getId());
					}
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			if(locais_visitados!=null && resultSet!=null){
				for(EntityModel lv :locais_visitados){
					for(EntityModel l : resultSet){
						 resultTemp = manager.find("FROM personagemmo_locaispersonagensmo WHERE personagemmo_id =" + l.getId() + "AND locaisvisitados_id="+ lv.getId());
					}
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			if(encontro!=null && resultSet!=null){
				for(EntityModel e : encontro){
					for(EntityModel l : resultSet){
						 resultTemp = manager.find("FROM personagemmo_encontromo WHERE personagemmo_id =" + l.getId() + "AND encontro_id="+ e.getId());
					}
				}
			}
			resultSet = resultTemp;
			resultTemp= null;
			
			
			if(resultSet == null) {
				return new ArrayList<DTO>();
				//throw new CharacterNotFoundException("Personagem não encontrado.");
			}
			else return (List<EntityModel>)resultSet;
			
			
		}
		public List<EntityModel> findPersonagemByAll(String nome, String apelido, String local_nascimento, double local_nascimento_latitude,
				double local_nascimento_longitude,SimpleDate data_nascimento, String local_morte, double local_morte_latitude, 
				double local_morte_longitude, SimpleDate data_morte,
				String biografia, String ocupacao, String formacao, 
				String referencia_bibliografica, //procurar por uma fonte obras
				String titulo, String comentario, String ref_circ_obra, String url, String copias_manuscritas, String traducoes, 
				SimpleDate dataImpressao, String editor, 
				String grupoMovimento, SimpleDate anoInicio_grupomovimento, SimpleDate anoFim_grupomovimento, String descricao_grupomovimento, String local_grupomovimento,
				double latitude_grupomovimento, double longitude_grupomovimento,
				String localimpressao, double latitude_localimpressao, double longitude_localimpressao,
				String classificacao) throws UnreachableDataBaseException, LocalNotFoundException, FontesObrasNotFoundException, GroupMovementNotFoundException, ClassificationNotFoundException, CharacterNotFoundException{
			
					
			List<EntityModel> resultSet = null;
			

			try {
				
				//novas modificações comecam aqui
				///procura por um grupo movimento
				LocalSearchDAO local_nascimento_dao = new LocalSearchDAO();
				
				LocalSearchDAO local_morte_dao = new LocalSearchDAO();
				
				FontesObrasSearchDAO referencia_bibliografica_dao = new FontesObrasSearchDAO();
				
				List<EntityModel> result_localNascimento = local_nascimento_dao.findLocalByAll(local_nascimento, local_nascimento_latitude, local_nascimento_longitude);
				
				List<EntityModel> result_localMorte = local_morte_dao.findLocalByAll(local_morte, local_morte_latitude, local_morte_longitude);
				
				List<EntityModel> result_referencia_bibliografica = referencia_bibliografica_dao.findFontesObrasByAll(referencia_bibliografica, comentario,
						ref_circ_obra, url, copias_manuscritas, traducoes, dataImpressao, editor, grupoMovimento, anoInicio_grupomovimento,
						anoFim_grupomovimento, descricao_grupomovimento, local_grupomovimento, latitude_grupomovimento, longitude_grupomovimento,
						localimpressao, latitude_localimpressao, longitude_localimpressao, classificacao);
				//cruzamento de personagem e local nascimento
				String localnascimento_query = "( ";
				boolean first = true;
				for(EntityModel l : result_localNascimento){
					if(! first ){
						localnascimento_query+=" OR ";
					} 
					first = false;
					localnascimento_query+="localnascimento_id = "+l.getId()+" ";
					
				}
				localnascimento_query+=" ) ";
				//cruzamento de personagem e local morte
				String localmorte_query = "( ";
				first = true;
				for(EntityModel l :  result_localMorte){
					if(! first ){
						localmorte_query +=" OR ";
					} 
					first = false;
					localmorte_query +=" localmorte_id = "+l.getId()+" ";
					
				}
				localmorte_query +=" ) ";
				
				//cruzamento de personagem e fontes obras
				String refbibliografica_query = "( ";
				first = true;
				for(EntityModel l :result_referencia_bibliografica){
					if(! first ){
						refbibliografica_query+=" OR ";
					} 
					first = false;
					refbibliografica_query+="  referencia_bibliografica_id= "+l.getId()+" ";
					
				}
				refbibliografica_query+=" ) ";
				
				String query= "from PersonagemMO where apelido like " + (apelido==null || apelido.equals("")?"%":getQueryNormalization("'%" + apelido +"%'"))
						+ (biografia==null || biografia.equals("")?"": " AND biografia like " + getQueryNormalization("'%" + biografia +"%'"))
						+ (data_morte==null?"": " AND datamorte like " + getQueryNormalization("'%" + data_morte+"%'"))
						+ (data_nascimento==null?"": " AND datanascimento like " + getQueryNormalization("'%" + data_nascimento+"%'"))
						+ (formacao==null || formacao.equals("")?"":" AND formacao like " + getQueryNormalization("'%" + formacao +"%'"))
						+ (nome==null || nome.equals("")? "": " AND nome like " + getQueryNormalization("'%" + nome +"%'"))
						+ (ocupacao==null || ocupacao.equals("")? "": " AND ocupacao like " + getQueryNormalization("'%" + ocupacao +"%'"))
						+ (localnascimento_query == null || localnascimento_query.equals("(  ) ")?"":" AND " + localnascimento_query)
						+ (localmorte_query==null || localmorte_query.equals("(  ) ")?"":" AND " + localmorte_query)
						+ (refbibliografica_query == null|| refbibliografica_query.equals("(  ) ")?"":" AND " + refbibliografica_query)						
						+ "order by nome";
				resultSet = manager.find(query);
				
			
				
				if(resultSet == null) {
					//throw new CharacterNotFoundException("Personagem não encontrado.");
					return new ArrayList<DTO>();
					
				}
				else return (List<EntityModel>)resultSet;
			
			} catch (DataAccessLayerException e) {
				e.printStackTrace();
				throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
			}
			
			
			
		}
		/**
		 * Pesquisa por um personagem
		 * @param nome - nome do personagem.
		 * @throws UnreachableDataBaseException
		 * @throws PersonagemNotFoundException
		 */
		public List<EntityModel> findExactPersonagemByExactNome(String nome) throws PersonagemNotFoundException, UnreachableDataBaseException{
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("FROM Personagem"+		
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
		public List<EntityModel> findPersonagemByNome(String nome) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("from PersonagemMO where nome like '%" + nome +"%' "
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
		public List<EntityModel> findPersonagemByApelido(String apelido) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("from PersonagemMO where apelido like '%" + apelido +"%' "
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
		public List<EntityModel> findPersonagemByLocalNascimento(long localnascimento_id ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("from PersonagemMO where localnascimento_id  = '" + localnascimento_id +"' "
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
		public List<EntityModel> findPersonagemByLocalMorte(long localmorte_id ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("from personagemMO where localmorte_id  = '" + localmorte_id +"' "
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
		public List<EntityModel> findPersonagemByDataNascimento(SimpleDate dataNascimento ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("from personagemMO where datanascimento = '" + dataNascimento +"' "
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
		public List<EntityModel> findPersonagemByDataMorte(SimpleDate dataMorte ) throws  UnreachableDataBaseException, PersonagemNotFoundException {
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("from personagemMO where datanascimento = '" + dataMorte +"' "
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
		public List<EntityModel> findPersonagemByReligiao(String religiao) throws  UnreachableDataBaseException, PersonagemNotFoundException, ReligionNotFoundException {
			List<EntityModel> resultSet = null;
			ReligiaoCrencasSearchDAO dao = new ReligiaoCrencasSearchDAO();
			try {
								
				List<EntityModel> resultados = dao.findReligiaoCrencaByNome(religiao);
				for(EntityModel p : resultados){
					resultSet = manager.find("from personagemMO_religiaocrencasMO where religiao_id = '" + p +"' "
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
		public List<EntityModel> findPersonagemByObra(String obra) throws  UnreachableDataBaseException, PersonagemNotFoundException, FontesObrasNotFoundException{
			List<EntityModel> resultSet = null;
			FontesObrasSearchDAO dao = new FontesObrasSearchDAO();
			try {
								
				List<EntityModel> resultados = dao.findFontesObrasByTitulo(obra);
				for(EntityModel p : resultados){
					resultSet = manager.find("from personagemMO_fontesobrasMO where obras_id = '" + p +"' "
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
		public List<EntityModel> findAllPersonagem() throws  UnreachableDataBaseException, PersonagemNotFoundException  {
			List<EntityModel> resultSet = null;
			try {
				resultSet = manager.find("from PersonagemMO order by nome, apelido");
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