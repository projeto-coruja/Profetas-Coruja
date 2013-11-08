
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.unifesp.profetas.business.account.UserDTO;
import br.unifesp.profetas.business.authentication.Login;
import br.unifesp.profetas.business.authentication.LoginImpl;

public class MainClass {

	public static void main(String[] args) {
		/*ApplicationContext appContext = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		
		Login login = (Login) appContext.getBean(LoginImpl.class);
		UserDTO u = login.getUserByUsername("sirghost@gmail.com");
		
		System.out.println("user: " + u.getFullName());*/
	}
}