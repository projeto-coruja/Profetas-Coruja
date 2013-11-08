package br.unifesp.profetas.business.init;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifesp.profetas.persistence.domain.ProfileDAO;
import br.unifesp.profetas.persistence.domain.RoleDAO;
import br.unifesp.profetas.persistence.domain.UserAccountDAO;
import br.unifesp.profetas.persistence.model.Profile;
import br.unifesp.profetas.persistence.model.Role;
import br.unifesp.profetas.persistence.model.UserAccount;
import br.unifesp.profetas.util.ProfetasConstants;

@Service("profetasInit")
public class ProfetasInitImpl implements ProfetasInit {
	
	@Autowired private UserAccountDAO userAccountDAO;
	@Autowired private ProfileDAO profileDAO;
	@Autowired private RoleDAO roleDAO;

	public void createRolesAndProfiles() {
		createRoles();
		createProfiles();
		createAdminUser();
	}
	
	private void createRoles(){
		String[] roles = { ProfetasConstants.ROLE_NAME_ADMIN, ProfetasConstants.ROLE_NAME_SAVE, ProfetasConstants.ROLE_NAME_SEARCH };
		
		for(String r : roles){
			Role role = roleDAO.getRoleByName(r);
			if(role == null){
				role = new Role();
				role.setName(r);
				roleDAO.saveRole(role);

				if(role.getId() == null)
					throw new RuntimeException("Role "+ r +" can not be created.");
			}
		}		
	}
	
	private void createProfiles(){
		String[] profiles	= { ProfetasConstants.PROFILE_ADMIN, ProfetasConstants.PROFILE_NEWUSER };
		String[] adminRoles	= { ProfetasConstants.ROLE_NAME_ADMIN, ProfetasConstants.ROLE_NAME_SAVE, ProfetasConstants.ROLE_NAME_SEARCH };
		for(String p : profiles){
			Profile profile = profileDAO.getProfileByName(p);
			if(profile == null){
				profile = new Profile();				
				profile.setName(p);
				if(p.equals(ProfetasConstants.PROFILE_ADMIN)){
					List<Role> roles = new ArrayList<Role>(adminRoles.length);
					for(String r : adminRoles){
						Role role = roleDAO.getRoleByName(r);
						roles.add(role);
					}
					profile.setRoles(new HashSet<Role>(roles));
				}
				else if(p.equals(ProfetasConstants.PROFILE_NEWUSER)){
					List<Role> roles = new ArrayList<Role>(1);
					Role role = roleDAO.getRoleByName(ProfetasConstants.ROLE_NAME_SEARCH);
					roles.add(role);
					profile.setRoles(new HashSet<Role>(roles));
				}
				profileDAO.saveProfile(profile);
				
				if(profile.getId() == null)
					throw new RuntimeException("Profile "+ p +" can not be created.");
			}
		}
	}
	
	private void createAdminUser(){
		UserAccount adminUser = userAccountDAO.getUserByUsername(ProfetasConstants.ADMIN_USER);
		if(adminUser == null){
			adminUser = new UserAccount();
			adminUser.setName("Admin");
			adminUser.setEmail(ProfetasConstants.ADMIN_USER);
			adminUser.setPassword(ProfetasConstants.ADMIN_PASS);
			adminUser.setCreationDate(new Date());
			Profile adminProfile = profileDAO.getProfileByName(ProfetasConstants.PROFILE_ADMIN);
			if(adminProfile == null){
				throw new RuntimeException("Admin Profile does not exist.");
			}
			adminUser.setProfile(adminProfile);
			userAccountDAO.saveUserAccount(adminUser);
			
			if(adminUser.getId() == null)
				throw new RuntimeException("User admin can not be created.");
		}
	}
}