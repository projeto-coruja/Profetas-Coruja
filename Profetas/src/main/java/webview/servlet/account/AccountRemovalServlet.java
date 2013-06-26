package webview.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.dto.UserAccount;
import webview.util.AlertsUtility;
import business.Bean.user.AdminBean;
import business.Bean.user.AuthBean;
import business.DAO.login.UserDAO;
import business.exceptions.DisallowedOperationException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Servlet implementation class AccountRemovalServlet
 */
@WebServlet("/protected/admin/removeAccount")
public class AccountRemovalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountRemovalServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO loginDAO = new UserDAO();
		AuthBean auth = new AuthBean();
		UserAccount userToRemove = null;
		
		AdminBean adm = new AdminBean();
		
		String email = request.getParameter("email");
		
		try {
			if(auth.allowedOperation("userRemovalPermission", request.getSession(), true)){
				
				userToRemove = loginDAO.findUserByEmail(email);

				adm.removeUser(email);
				// TODO: Tratar os redirecionamentos!
				if(userToRemove.getProfile().equals("default"))
					response.sendRedirect("public/index.jsp"); 
				else
					response.sendRedirect("public/index.jsp");
			}
			else{
				// Alertar a falta de permissão.
			}
		} catch (UnreachableDataBaseException e) {
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (DisallowedOperationException e) {
			AlertsUtility.alertAndRedirectPage(response, "Operação inválido!", "public/index.jsp");
		}
	}
	
}