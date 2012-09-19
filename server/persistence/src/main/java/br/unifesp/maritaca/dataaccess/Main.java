package br.unifesp.maritaca.dataaccess;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.unifesp.maritaca.dataaccess.dao.UserDAO;
import br.unifesp.maritaca.dataaccess.domain.UserVO;

public class Main {
	
	/*public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
        System.out.println("bean: " + userDAO != null ? "no nulo":"nulo");
        UserVO user = new UserVO();

        user.setEmail("1email");
        userDAO.saveUser(user);
        user = new UserVO();

        user.setEmail("2email");
        userDAO.saveUser(user);
        
        List<UserVO> users = userDAO.findAll();
        for(UserVO u : users) {
            System.out.println("u: " + u.getEmail());
        }        
    }*/
}