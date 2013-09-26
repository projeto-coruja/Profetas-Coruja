package business.Bean.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.DAO.Config.ConfigurationDAO;
import business.DAO.login.ProfileDAO;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.general.DuplicatedEntryException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;

/**
 * Classe de configuração de sistema
 *
 */
public class Config {
	
	private ConfigurationDAO dao;
	private static Config instance;
	private HashMap<String,String> map;
	private String[] defaultPermissions;
	private List<String> permissions;
	/**
	 * Entradas padrão do sistema e seus valores padrão.
	 */
	private String[][] entries = new String[][]{
			{"mailAccount",""},
			{"mailPassword",""},
			{"mailHost","smtp.gmail.com"},
			{"mailPort","465"},
			{"mailSocketPort","465"}};
	/**
	 * Construtor.
	 * @throws UnreachableDataBaseException
	 */
	private Config() throws UnreachableDataBaseException {
		// Carrega as entradas, gravadas no banco de dados, na memória
		map = new HashMap<String, String>();
		dao = new ConfigurationDAO();
		for(int i = 0; i < entries.length; i++)
		try {
			map.put(entries[i][0], dao.getEntry(entries[i][0]).getValue());
		} catch (ConfigNotFoundException e) {
			try {
				dao.addProperty(entries[i][0], entries[i][1]);
			} catch (DuplicatedEntryException e1) {
				try {
					dao.updateProperty(entries[i][0], entries[i][1]);
				} catch (ConfigNotFoundException e2) {
					e2.printStackTrace();
				}
			}
			map.put(entries[i][0], entries[i][1]);
		}
		// Carregando lista de permissões.
		permissions = new ArrayList<String>();

		permissions.add("searchPermission");
		permissions.add("userInfoUpdatePermission");
		permissions.add("userRemovalPermission");
		permissions.add("userApprovalPermission");
		permissions.add("addNewUserPermission");
		
		// Carregar permissões padrão.
		ProfileDAO profDao = new ProfileDAO();
		try {
			defaultPermissions = profDao.getDefaultProfile().getPermissions().clone();
		} catch (NoDefaultProfileException e) {
			defaultPermissions = new String[]{};
		}
	}
	
	/**
	 * Pega instância de Config. (Singleton)
	 * @return instância de config
	 * @throws UnreachableDataBaseException
	 */
	public static Config getInstance() throws UnreachableDataBaseException{
		if(instance == null)	instance = new Config();
		return instance;
	}
	
	/**
	 * Retorna o valor de uma determinada entrada.
	 * @param entry - String contendo o nome da entrada.
	 * @return Valor da entrada.
	 * @throws ConfigNotFoundException
	 */
	public String getConfig(String entry) throws ConfigNotFoundException {
		if(!map.containsKey(entry)) throw new ConfigNotFoundException("Entrada não encontrada.");
		return map.get(entry);
	}
	
	/**
	 * Modifica o valor de uma entrada existente.
	 * @param entry - Entrada a ser modificado.
	 * @param value - Novo valor da entrada.
	 * @throws UnreachableDataBaseException
	 */
	public void setConfig(String entry, String value) throws UnreachableDataBaseException{
		if(!map.containsKey(entry))	throw new IllegalArgumentException("Entrada não existe.");
		map.put(entry, value);
		try {
			dao.updateProperty(entry, value);
		} catch (ConfigNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna todas as configurações para o valor padrão.
	 * @throws UnreachableDataBaseException
	 */
	public void reset() throws UnreachableDataBaseException{
		for(String[] s : entries){
			map.put(s[0], s[1]);
			try {
				dao.updateProperty(s[0], s[1]);
			} catch (ConfigNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Retorna lista de permissões
	 * @return List<String> permissions
	 */
	public List<String> getPermissionList(){
		return permissions;
	}
	
	/**
	 * Verifica se uma permissão existe no sistema.
	 * @param permission - String permission
	 * @return <b>True</b> se a permissão existe, <b>False</b> caso contrário.
	 */
	public boolean permissionDoesExist(String permission){
		return permissions.contains(permission);
	}
	
	/**
	 * Pega todas as permissões do usuário padrão (usado para visitantes não registrado).
	 * @return String[] contendo todas as permissões.
	 */
	public String[] getDefaultPermissions(){
		return defaultPermissions;
	}
	
	/**
	 * Atualiza as permissões padrão.
	 * @param permissions - String[] contendo todas as permissões.
	 * @throws UnreachableDataBaseException
	 * @throws NoDefaultProfileException
	 */
	public void updateDefaultPermissions(String[] permissions) throws UnreachableDataBaseException, NoDefaultProfileException{
		ProfileDAO profDao = new ProfileDAO();
		
		defaultPermissions = permissions;
		try {
			profDao.updateProfile(profDao.getDefaultProfile().getProfile(), defaultPermissions, true);
		} catch (ProfileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
