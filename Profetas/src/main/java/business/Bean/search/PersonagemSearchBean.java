package business.Bean.search;

import java.util.List;

import business.DAO.search.PersonagemSearchDAO;

import persistence.dto.DTO;


public class PersonagemSearchBean {
	PersonagemSearchDAO dao;
	
	public PersonagemSearchBean(){
		dao = new PersonagemSearchDAO();
	}
	
	public List<DTO> findbyname(String name){
		
		return null;
		
		
	}
	

}
