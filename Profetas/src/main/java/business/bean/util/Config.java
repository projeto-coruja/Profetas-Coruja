package business.bean.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import persistence.EntityManager;
import persistence.model.Configuration;
import persistence.model.Profile;
import persistence.model.exceptions.EntityPersistenceException;
import business.dao.config.ConfigurationDAO;
import business.dao.login.ProfileDAO;
import business.exceptions.general.ConfigNotFoundException;
import business.exceptions.login.NoDefaultProfileException;
import business.exceptions.login.UnreachableDataBaseException;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

/**
 * Classe de configuração de sistema
 * 
 */
public enum Config {

    INSTANCE;

    private EntityManager em;
    private ConfigurationDAO dao;
    private HashMap<String, String> map;
    private List<String> permissions;
    private String[] defaultPermissions;

    /**
     * Entradas padrão do sistema e seus valores padrão.
     */
    private Map<String, String> defaultConfigMap = ImmutableMap.<String, String> builder()
	    .put("mailAccount", "").put("mailPassword", "").put("mailHost", "smtp.gmail.com")
	    .put("mailPort", "465").put("mailSocketPort", "465").build();

    private Config() {
	// Inicializando membros
	em = new EntityManager();
	dao = new ConfigurationDAO(em);
	map = Maps.newHashMap(defaultConfigMap);
	permissions = new ArrayList<String>();

	// Adicionando permissões
	permissions.add("searchPermission");
	permissions.add("userInfoUpdatePermission");
	permissions.add("userRemovalPermission");
	permissions.add("userApprovalPermission");
	permissions.add("addNewUserPermission");

	try {
	    for (Entry<String, String> pair : map.entrySet()) {
		Configuration cfg;
		cfg = dao.getEntry(pair.getKey());
		if (cfg != null && cfg.getValue() != null)
		    pair.setValue(cfg.getValue());
	    }

	    // Carregar permissões padrão.
	    ProfileDAO profDao = new ProfileDAO(em);
	    try {
		defaultPermissions = profDao.getDefaultProfile().getPermissions().clone();
	    } catch (NoDefaultProfileException e) {
		defaultPermissions = new String[0];
	    }
	} catch (UnreachableDataBaseException e) {
	    // Se acontecer mesmo não tem como recuperar...
	    throw new RuntimeException(e);
	}
    }

    /**
     * Retorna o valor de uma determinada entrada.
     * 
     * @param entry
     *            - String contendo o nome da entrada.
     * @return Valor da entrada.
     * @throws ConfigNotFoundException
     */
    public String getConfig(String entry) throws ConfigNotFoundException {
	if (!map.containsKey(entry)) {
	    throw new ConfigNotFoundException("Entrada não encontrada.");
	}
	return map.get(entry);
    }

    /**
     * Modifica o valor de uma entrada existente.
     * 
     * @param entry
     *            - Entrada a ser modificado.
     * @param value
     *            - Novo valor da entrada.
     * @throws UnreachableDataBaseException
     * @throws EntityPersistenceException
     */
    public void setConfig(String entry, String value) throws UnreachableDataBaseException, EntityPersistenceException {
	Configuration cfg = dao.getEntry(entry);
	if (cfg == null) {
	    cfg = new Configuration(entry, value);
	} else {
	    cfg.setValue(value);
	}
	cfg.save(em);
	map.put(entry, value);
    }

    /**
     * Retorna todas as configurações para o valor padrão.
     * 
     * @throws UnreachableDataBaseException
     */
    public void reset() throws UnreachableDataBaseException {
	map = Maps.newHashMap(defaultConfigMap);
	for (Entry<String, String> pair : map.entrySet()) {
	    Configuration cfg;
	    cfg = dao.getEntry(pair.getKey());
	    if (cfg != null)
		cfg.setValue(pair.getValue());
	}
    }

    /**
     * Retorna lista de permissões
     * 
     * @return List<String> permissions
     */
    public List<String> getPermissionList() {
	return permissions;
    }

    /**
     * Verifica se uma permissão existe no sistema.
     * 
     * @param permission
     *            - String permission
     * @return <b>True</b> se a permissão existe, <b>False</b> caso contrário.
     */
    public boolean permissionDoesExist(String permission) {
	return permissions.contains(permission);
    }

    /**
     * Pega todas as permissões do usuário padrão (usado para visitantes não
     * registrado).
     * 
     * @return String[] contendo todas as permissões.
     */
    public String[] getDefaultPermissions() {
	return defaultPermissions;
    }

    /**
     * Atualiza as permissões padrão.
     * 
     * @param permissions
     *            - String[] contendo todas as permissões.
     * @throws UnreachableDataBaseException
     * @throws NoDefaultProfileException
     * @throws EntityPersistenceException
     */
    public void updateDefaultPermissions(String[] permissions) throws UnreachableDataBaseException, NoDefaultProfileException, EntityPersistenceException {
	Profile defaultProfile = new ProfileDAO(em).getDefaultProfile();
	defaultProfile.setPermissions(permissions);
	defaultProfile.save(em);
	defaultPermissions = permissions;
    }
}
