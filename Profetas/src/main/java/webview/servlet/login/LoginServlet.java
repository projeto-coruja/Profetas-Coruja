package webview.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.exceptions.UpdateEntityException;

import webview.util.AlertsUtility;
import business.Bean.user.AuthBean;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String indexPage = "public/index.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AuthBean authetication = new AuthBean();;
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		try {
			authetication.authenticate(user, password, session, AuthBean.HashedPwd);
			if(!(Boolean)session.getAttribute("userLoginSuccessfull")){
				AlertsUtility.alertAndRedirectPage(response, 
						"Problema na autenticação.", 
						indexPage);
			}
			AlertsUtility.redirectOnly(response, indexPage);
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			AlertsUtility.alertAndRedirectPage(response, 
					"Login incorreto! Verifique seu email e senha, e tente de novo.", 
					indexPage);
		} catch (IllegalArgumentException e) {
		e.printStackTrace();
		} catch (UpdateEntityException e) {
			e.printStackTrace();
		}
	}
}
