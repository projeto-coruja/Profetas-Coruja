package br.unifesp.maritaca.dataaccess.dao;

import java.util.List;

import br.unifesp.maritaca.dataaccess.domain.UserVO;

public interface UserDAO {

	public UserVO findUserByEmail(String email);
	public void saveUser(UserVO user);
    public List<UserVO> findAll();
}