package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Role;

public interface RoleDAO {
	
	public List<Role> roleList();
	
	public Role getRoleById(Integer id);
	
	public void saveRole(Role role);
	
	public void updateRole(Role role);
	
	public void deleteRole(Role role);
}