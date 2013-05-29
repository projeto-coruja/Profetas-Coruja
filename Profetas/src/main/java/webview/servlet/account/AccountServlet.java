package webview.servlet.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.exceptions.UpdateEntityException;

import business.Bean.user.AdminBean;
import business.Bean.user.AuthBean;
import business.Bean.user.RegisterUserBean;
import business.exceptions.login.ProfileNotFoundException;
import business.exceptions.login.UnreachableDataBaseException;
import business.exceptions.login.UserNotFoundException;

/**
 * Servlet implementation class AccoutRemoveServlet
 */
@WebServlet("/doChangesToAccount")
public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AuthBean auth = new AuthBean();
		
		String action = request.getParameter("action");
		String email = request.getParameter("email");
		String newEmail = request.getParameter("newEmail");
		String newPassword = request.getParameter("newPassword");
		String newProfile = request.getParameter("newProfile");
		
		if(auth.isLoggedIn(session)){	// Verifica se o usuário está logado.
			// Verifica se a mudança vai ser na própria conta (todos tem permissão para essa ação).
			if(action.equals("changeSelfAccountInformation")){
				try {
					if(auth.validateToken(session)){	// Autenticação.
						RegisterUserBean bean = new RegisterUserBean();
						bean.changeUserInformation((String) session.getAttribute("userMail"), newEmail, newPassword);
					}
				} catch (UnreachableDataBaseException e) {
					e.printStackTrace();
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (UpdateEntityException e) {
					e.printStackTrace();
				}
			}
			// Verifica se a mudança vai ser em outra conta (Precisa ter permissão especial).
			else if(action.equals("changeUserAccountInformation")){
				try {
					if(auth.allowedOperation("userInfoUpdatePermission", session, true)){ // Autenticação.
						AdminBean admin = new AdminBean();
						admin.changeUserInformation(email, newEmail, newPassword, newProfile);	
					}
				} catch (UnreachableDataBaseException e) {
					e.printStackTrace();
				} catch (UserNotFoundException e) {
					e.printStackTrace();
				} catch (ProfileNotFoundException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (UpdateEntityException e) {
					e.printStackTrace();
				}
			}
		}
	}

}