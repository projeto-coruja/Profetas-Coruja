package webview.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import business.Bean.user.AuthBean;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

import webview.util.AlertsUtility;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/doLogout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	// Pega a sessão do usuário.
		AuthBean auth = new AuthBean();
		try {
			auth.logOut(session);	// Chama o método de logout.
		} catch (UserNotFoundException e) {
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		}
		
		AlertsUtility.alertAndRedirectPage(response, "Logout feito com sucesso!", "public/index.jsp"); // Exibe alerta e redireciona o usuário para a home.
	}

}
