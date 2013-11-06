package br.unifesp.profetas.persistence.domain;

import java.util.List;

import br.unifesp.profetas.persistence.model.Profile;

public interface ProfileDAO {

	public List<Profile> profileList();
	
	public Profile getProfileById(Integer id);
	
	public void saveProfile(Profile profile);
	
	public void updateLocal(Profile profile);
	
	public void deleteLocal(Profile profile);
}