package business.DAO.search;

import java.util.List;

import persistence.EntityManager;
import persistence.model.EntityModel;
import persistence.util.DataAccessLayerException;
import business.exceptions.login.UnreachableDataBaseException;

class DAOUtility {

	private DAOUtility() {
		//Classe puramente estática
	}
	
	public static <T extends EntityModel> List<T> queryList(EntityManager manager, String table, String field, Object info) throws UnreachableDataBaseException {
		try {
			return manager.find("FROM " + table + " WHERE " + field + " = '" + info + "'" + " ORDER BY id");
		} catch (DataAccessLayerException e) {
			e.printStackTrace();
			throw new UnreachableDataBaseException("Erro ao acessar o banco de dados");
		}
	}
	
	public static String normalize(String var) {
		var.replace("'", "\'");
		return "LOWER(TRANSLATE(" + var + ",'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY'))";
	}
	
}
