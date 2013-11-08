package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Role;

public interface RoleDAO {
	
	public List<Role> getRoles();
	
	public List<Role> getRolesByProfile(Integer idProfile);
	
	public Role getRoleById(Integer id);
	
	public Role getRoleByName(String name);
	
	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
}